package com.example.videoview;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class MainActivity extends AppCompatActivity {

    private static final String YOUTUBE_WITH_TIME_URL
            = "https://www.youtube.com/watch?v=wIjB8jTDOZU&t=14s";
    private static final String YOUTUBE_SHORT_URL = "https://youtu.be/C23fO5mjRNA";
    private static final String YOUTUBE_EMBED_URL = "https://www.youtube.com/embed/trVk_1Fn3fA";
    private static final String YOUTUBE_WATCH_URL = "https://www.youtube.com/watch?v=trVk_1Fn3fA";
    private static final String YOUTUBE_DIRECT_URL
            = "https://r8---sn-cxauxaxjvh-hn9el.googlevideo.com/videoplayback?expire=1569875776&ei=3xKSXaeuNYPa7gTHl6aYBQ&ip=82.209.210.219&id=o-AJEEXs1c77k4_c8_YAh-FU07GDZFp5EJHV4RgOuPhhc3&itag=22&source=youtube&requiressl=yes&mm=31%2C26&mn=sn-cxauxaxjvh-hn9el%2Csn-h0jeener&ms=au%2Conr&mv=m&mvi=7&pl=18&initcwndbps=915000&mime=video%2Fmp4&ratebypass=yes&dur=545.738&lmt=1569410370856695&mt=1569854132&fvip=5&fexp=23842630&beids=9466588&c=WEB&txp=2316222&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cmime%2Cratebypass%2Cdur%2Clmt&sig=ALgxI2wwRgIhANVIfonTTNjMySUoIouYI_Kf65YPEpVYpfekq7tZ4ETjAiEAxyMC5IebGxD22fYBQtA9CvdLs-vXSha1eoB2lOd8h6c%3D&lsparams=mm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AHylml4wRQIhAMyhLFJlNr3HxwhdLSGK-iXCPE6cb0DVST6D2AZOv-gdAiBjn5a1MwQB8Dw6awzswnKs-ILkU2QmA9xeagDXpYxzyA%3D%3D";
    private static final String VIMEO_URL
            = "https://gcs-vimeo.akamaized.net/exp=1569858930~acl=%2A%2F365951685.mp4%2A~hmac=b1bf2c12f09420a97bc244524ed6d1ca415120f3cc26d224127bf1fef2a07538/vimeo-prod-skyfire-std-us/01/494/5/127472766/365951685.mp4";
    private static final String MP4_URL
            = "https://d2v9y0dukr6mq2.cloudfront.net/video/preview/GTYSdDW/panoramic-view-of-cola-or-soda-in-the-glass-french-fries-potato-and-hamburger-sandwich-on-tray-on-table_enuxnii0l__PM.mp4";

    private PlayerView playerView;
    private SimpleExoPlayer player;

    private EditText etUrlLine;
    private Button btnPlayVideo;
    private Button btnYoutubeWithTimeUrl;
    private Button btnYoutubeShortUrl;
    private Button btnYoutubeEmbedUrl;
    private Button btnYoutubeWatchUrl;
    private Button btnVimeoUrl;
    private Button btnMp4Url;
    private ProgressBar mainProgressBar;
    private LinearLayout mainLayout;
    private Group groupMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player_view);
        etUrlLine = findViewById(R.id.et_url_line);
        btnPlayVideo = findViewById(R.id.btn_play_video);
        btnYoutubeWithTimeUrl = findViewById(R.id.btn_youtube_with_time);
        btnYoutubeShortUrl = findViewById(R.id.btn_youtube_short);
        btnYoutubeEmbedUrl = findViewById(R.id.btn_youtube_embed);
        btnYoutubeWatchUrl = findViewById(R.id.btn_youtube_watch);
        btnVimeoUrl = findViewById(R.id.btn_vimeo);
        btnMp4Url = findViewById(R.id.btn_mp4);
        mainProgressBar = findViewById(R.id.prgrBar);
        mainLayout = findViewById(R.id.choose_board);
        groupMain = findViewById(R.id.group_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setClickListeners();

        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        playerView.setPlayer(player);

        //Uri mp4VideoUri = Uri.parse(VIMEO_URL);
        //Log.e("uri", "uri " + mp4VideoUri.toString());
        //
        //DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
        //                                                                    Util.getUserAgent(this,
        //                                                                                      getString(
        //                                                                                              R.string.app_name)));
        //
        //MediaSource videoSource = new ProgressiveMediaSource.Factory(
        //        dataSourceFactory).createMediaSource(mp4VideoUri);
        //// Prepare the player with the source.
        //player.prepare(videoSource);
        //player.setPlayWhenReady(true);
    }

    private void setClickListeners() {
        setButtons();
        btnPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainProgressBar.setVisibility(View.VISIBLE);
                extractUrl(etUrlLine.getText()
                                    .toString());
            }
        });
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
        //btnVimeoUrl.setOnClickListener(new );
        //btnMp4Url.setOnClickListener(new);
    }

    private void addVideoUrl(String videoUrl) {
        etUrlLine.setText("");
        etUrlLine.setText(videoUrl);
    }

    private void extractUrl(String pageUrl) {
        if (pageUrl != null && (pageUrl.contains("://youtu.be/") || pageUrl.contains(
                "youtube.com/watch?v="))) {
            getYoutubeDownloadUrl(pageUrl);
        } else {
            Toast.makeText(this, "Not a valid Youtube link!", Toast.LENGTH_LONG)
                 .show();
            mainProgressBar.setVisibility(View.GONE);
        }
    }

    private void getYoutubeDownloadUrl(String directUrl) {
        new YouTubeExtractor(this) {

            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                mainProgressBar.setVisibility(View.GONE);

                if (ytFiles == null) {
                    // Something went wrong we got no urls. Always check this.
                    //finish();
                    Toast.makeText(MainActivity.this,
                                   "Something went wrong we got no urls. Always check this.",
                                   Toast.LENGTH_LONG)
                         .show();
                    return;
                }
                // Iterate over itags
                for (int i = 0, itag; i < ytFiles.size(); i++) {
                    itag = ytFiles.keyAt(i);
                    // ytFile represents one file with its url and meta data
                    YtFile ytFile = ytFiles.get(itag);

                    // Just add videos in a decent format => height -1 = audio
                    if (ytFile.getFormat()
                              .getHeight() == -1 || ytFile.getFormat()
                                                          .getHeight() >= 360) {
                        groupMain.setVisibility(View.GONE);
                        addButtonToMainLayout(vMeta.getTitle(), ytFile);
                    }
                }
                mainLayout.setVisibility(View.VISIBLE);
            }
        }.extract(directUrl, true, false);
    }

    private void addButtonToMainLayout(final String videoTitle, final YtFile ytfile) {
        // Display some buttons and let the user choose the format
        String btnText = (ytfile.getFormat()
                                .getHeight() == -1) ? "Audio " + ytfile.getFormat()
                                                                       .getAudioBitrate()
                                                      + " kbit/s" : ytfile.getFormat()
                                                                          .getHeight() + "p";
        btnText += (ytfile.getFormat()
                          .isDashContainer()) ? " dash" : "";
        Button btn = new Button(this);
        btn.setText(btnText);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String filename;
                if (videoTitle != null && videoTitle.length() > 55) {
                    filename = videoTitle.substring(0, 55) + "." + ytfile.getFormat()
                                                                         .getExt();
                } else {
                    if (videoTitle == null) {
                        filename = "Some video";
                    } else {
                        filename = videoTitle + "." + ytfile.getFormat()
                                                            .getExt();
                    }
                }
                filename = filename.replaceAll("[\\\\><\"|*?%:#/]", "");
                downloadFromUrl(ytfile.getUrl(), videoTitle, filename);
                groupMain.setVisibility(View.VISIBLE);
                mainLayout.setVisibility(View.GONE);
                //finish();
            }
        });
        mainLayout.addView(btn);
    }

    private void downloadFromUrl(String youtubeDlUrl, String downloadTitle, String fileName) {
        //Uri uri = Uri.parse(youtubeDlUrl);
        //DownloadManager.Request request = new DownloadManager.Request(uri);
        //request.setTitle(downloadTitle);
        //
        //request.allowScanningByMediaScanner();
        //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        //
        //DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        //manager.enqueue(request);

        Uri mp4VideoUri = Uri.parse(youtubeDlUrl);
        Log.e("uri", "uri " + mp4VideoUri.toString());

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                                                                            Util.getUserAgent(this,
                                                                                              getString(
                                                                                                      R.string.app_name)));

        MediaSource videoSource = new ProgressiveMediaSource.Factory(
                dataSourceFactory).createMediaSource(mp4VideoUri);
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
    }
}
