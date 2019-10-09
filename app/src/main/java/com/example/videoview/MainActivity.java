package com.example.videoview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videoview.db.DataBase;
import com.example.videoview.entity.vimeo.ProgressiveItem;
import com.example.videoview.entity.vimeo.VimeoConfigResponse;
import com.example.videoview.internet.RestService;
import com.example.videoview.internet.VimeoConfApi;
import com.example.videoview.json.JsonHelper;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.videoview.Consts.VIDEO_TITLE;
import static com.example.videoview.Consts.VIDEO_URL;
import static com.example.videoview.Consts.YOUTUBE_EMBED_URL;
import static com.example.videoview.Consts.YOUTUBE_SHORT_URL;
import static com.example.videoview.Consts.YOUTUBE_URL;
import static com.example.videoview.Consts.YOUTUBE_WATCH_URL;
import static com.example.videoview.Consts.YOUTUBE_WITH_TIME_URL;

public class MainActivity extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer player;

    private String urlFromExtractor;
    private String videoTitle;

    private EditText etUrlLine;
    private TextView tvTitle;
    private Button btnPlayVideo;
    private Button btnVimeoConfRequest;
    private Button btnYoutubeWithTimeUrl;
    private Button btnYoutubeShortUrl;
    private Button btnYoutubeEmbedUrl;
    private Button btnYoutubeWatchUrl;
    private Button btnVimeoUrl;
    private Button btnSaveLinksToDB;
    private ProgressBar mainProgressBar;

    private VimeoConfApi vimeoConfApi;

    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player_view);
        etUrlLine = findViewById(R.id.et_url_line);
        tvTitle = findViewById(R.id.tv_video_title);
        btnPlayVideo = findViewById(R.id.btn_choose_video_quality);
        btnVimeoConfRequest = findViewById(R.id.btn_retry);
        btnYoutubeWithTimeUrl = findViewById(R.id.btn_youtube_with_time);
        btnYoutubeShortUrl = findViewById(R.id.btn_youtube_short);
        btnYoutubeEmbedUrl = findViewById(R.id.btn_youtube_embed);
        btnYoutubeWatchUrl = findViewById(R.id.btn_youtube_watch);
        btnVimeoUrl = findViewById(R.id.btn_vimeo);
        btnSaveLinksToDB = findViewById(R.id.btn_save_to_db);
        mainProgressBar = findViewById(R.id.prgrBar);

        dataBase = App.getInstance()
                      .getDatabase();
        vimeoConfApi = RestService.getInstance().getVimeoConfApi();
    }

    @Override
    protected void onStart() {
        super.onStart();

        setClickListeners();

        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        playerView.setPlayer(player);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {
            return;
        }

        urlFromExtractor = data.getStringExtra(VIDEO_URL);
        videoTitle = data.getStringExtra(VIDEO_TITLE);
        setVisible(mainProgressBar, false);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
         if (urlFromExtractor != null && !urlFromExtractor.isEmpty() && videoTitle != null){
             playFromUrl(urlFromExtractor, videoTitle);
         } else {
             Toast.makeText(this, "Please try choosing a video again or checking video url.", Toast.LENGTH_LONG).show();
             setVisible(mainProgressBar, false);
         }
    }

    private void setClickListeners() {
        setButtons();
        btnPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisible(mainProgressBar, true);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(YOUTUBE_URL, etUrlLine.getText()
                                                      .toString());
                startActivityForResult(intent, 1);
            }
        });
    }

    private void setVisible(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private void setButtons() {
        btnYoutubeWithTimeUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVideoUrl(YOUTUBE_WITH_TIME_URL);
            }
        });
        btnYoutubeShortUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVideoUrl(YOUTUBE_SHORT_URL);
            }
        });
        btnYoutubeEmbedUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVideoUrl(YOUTUBE_EMBED_URL);
            }
        });
        btnYoutubeWatchUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVideoUrl(YOUTUBE_WATCH_URL);
            }
        });
        btnVimeoUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
        btnSaveLinksToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase.getVideosDao()
                        .addVideo(Consts.convertVideosToVideosDb(JsonHelper.importFromJSON(MainActivity.this)));
            }
        });
        btnVimeoConfRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = vimeoConfApi.getDirectUrls(127472766);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.e("1111111", "ResposeBody: " + response.body().toString());
                        handleTokenResponse(response);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("1111111", "ResposeBody Error: " + t.toString());
                    }
                });
            }
        });
    }

    private void addVideoUrl(String videoUrl) {
        etUrlLine.setText("");
        etUrlLine.setText(videoUrl);
    }

    private void playFromUrl(String youtubeDlUrl, String downloadTitle) {
        tvTitle.setText(downloadTitle);
        Uri mp4VideoUri = Uri.parse(youtubeDlUrl);
        String userAgent = Util.getUserAgent(this, this.getString(R.string.app_name));
        MediaSource videoSource = new ProgressiveMediaSource.Factory(new DefaultDataSourceFactory(this, userAgent))
                .createMediaSource(mp4VideoUri);
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
    }

    private void handleTokenResponse(Response<ResponseBody> response) {
        try {
            if (response.body() != null) {
                Gson gson = new GsonBuilder().create();
                VimeoConfigResponse vimeoConfigResponse = gson.fromJson(response.body().string(),
                                                               VimeoConfigResponse.class);
                Log.e("ResponseData body",
                      "Get token response from JSON successfully: " + vimeoConfigResponse.toString());

                startVimeoVideo(vimeoConfigResponse);
            } else {
                Log.e("ResponseData NULL","Token response body is null");
            }
        } catch (IOException e) {
            Log.e("Exception","Can't get token from response " + e.getLocalizedMessage());
        }
    }

    private void startVimeoVideo(VimeoConfigResponse vimeoConfigResponse) {
        for (ProgressiveItem progressiveItem : vimeoConfigResponse.getRequest().getFiles().getProgressive()){
            if (progressiveItem.getQuality().equals("720p")){
                playFromUrl(progressiveItem.getUrl(), "Vimeo video");
                tvTitle.setText(vimeoConfigResponse.getVideo().getTitle());
            }
        }
    }
}
