package com.example.videoview;

import android.os.Bundle;
import android.util.Log;

import com.example.videoview.db.DataBase;
import com.example.videoview.db.VideosDb;
import com.example.videoview.entity.VideoType;
import com.example.videoview.entity.quality.VideoWithQuality;
import com.example.videoview.entity.vimeo.ProgressiveItem;
import com.example.videoview.entity.vimeo.VimeoConfigResponse;
import com.example.videoview.internet.RestService;
import com.example.videoview.internet.VimeoConfApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoListAdapter adapter;
    private DataBase dataBase;

    private VimeoConfApi vimeoConfApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        dataBase = App.getInstance()
                      .getDatabase();

        vimeoConfApi = RestService.getInstance()
                                  .getVimeoConfApi();

        recyclerView = findViewById(R.id.recycler_view);

        adapter = initAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private VideoListAdapter initAdapter() {
        extractDirectLinks(getVirginLinks());
        return new VideoListAdapter(dataBase.getVideosDao()
                                            .getVideoWithQuality());
    }

    private List<VideosDb> getVirginLinks() {
        return dataBase.getVideosDao()
                       .getVideo();
    }

    private void extractDirectLinks(List<VideosDb> virginLinks) {
        List<VideoWithQuality> videoWithQualityList = new ArrayList<>();
        for (VideosDb videosDb : virginLinks) {
            if (videosDb.getType() == VideoType.DIRECT) {
                Map<String, String> qualityList = new HashMap<>();
                qualityList.put("without", videosDb.getUrl());
                videoWithQualityList.add(new VideoWithQuality(videosDb.getUid(), "", qualityList));
            }

            if (videosDb.getType() == VideoType.VIMEO) {
                getVimeoDirectUrls(getVideoId(videosDb.getUrl()), videosDb.getUid());
            }

            if (videosDb.getType() == VideoType.YOUTUBE) {

            }
        }
        dataBase.getVideosDao()
                .addVideoWithQuality(videoWithQualityList);
    }

    private String getVideoId(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    private void getVimeoDirectUrls(String videoId, final int idVirginLink) {
        Call<ResponseBody> call = vimeoConfApi.getDirectUrls(videoId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("222", "ResposeBody: " + response.body()
                                                       .toString());
                handleTokenResponse(response, idVirginLink);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("222", "ResposeBody Error: " + t.toString());
            }
        });
    }

    private void handleTokenResponse(Response<ResponseBody> response, int idVirginLink) {
        try {
            if (response.body() != null) {
                Gson gson = new GsonBuilder().create();
                VimeoConfigResponse vimeoConfigResponse = gson.fromJson(response.body()
                                                                                .string(), VimeoConfigResponse.class);
                Log.e("ResponseData body",
                      "Get token response from JSON successfully: " + vimeoConfigResponse.toString());

                //startVimeoVideo(vimeoConfigResponse);
                saveVimeoVideo(vimeoConfigResponse, idVirginLink);
            } else {
                Log.e("ResponseData NULL", "Token response body is null");
            }
        } catch (IOException e) {
            Log.e("Exception", "Can't get token from response " + e.getLocalizedMessage());
        }
    }

    private void saveVimeoVideo(VimeoConfigResponse vimeoConfigResponse, int idVirginLink) {
        Map<String, String> qualityList = new HashMap<>();
        for (ProgressiveItem progressiveItem : vimeoConfigResponse.getRequest()
                                                                  .getFiles()
                                                                  .getProgressive()) {
            qualityList.put(progressiveItem.getQuality(), progressiveItem.getUrl());
        }
        dataBase.getVideosDao()
                .addVideoWithQuality(new VideoWithQuality(idVirginLink, vimeoConfigResponse.getVideo()
                                                                                           .getTitle(), qualityList));
    }

    //private void startVimeoVideo(VimeoConfigResponse vimeoConfigResponse) {
    //    for (ProgressiveItem progressiveItem : vimeoConfigResponse.getRequest().getFiles().getProgressive()){
    //        if (progressiveItem.getQuality().equals("720p")){
    //            playFromUrl(progressiveItem.getUrl(), "Vimeo video");
    //            //tvTitle.setText(vimeoConfigResponse.getVideo().getTitle());
    //        }
    //    }
    //}
    //
    //private void playFromUrl(String youtubeDlUrl, String downloadTitle) {
    //    //tvTitle.setText(downloadTitle);
    //    //Uri mp4VideoUri = Uri.parse(youtubeDlUrl);
    //    //String userAgent = Util.getUserAgent(this, this.getString(R.string.app_name));
    //    //MediaSource videoSource = new ProgressiveMediaSource.Factory(new DefaultDataSourceFactory(this, userAgent))
    //    //        .createMediaSource(mp4VideoUri);
    //    //player.prepare(videoSource);
    //    //player.setPlayWhenReady(true);
    //}
}
