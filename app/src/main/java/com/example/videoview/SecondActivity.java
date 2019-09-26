package com.example.videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class SecondActivity extends AppCompatActivity {

    private String GRID_YOUTUBE_ID = "8saOHjoDX94";
    private String BASE_URL = "https://www.youtube.com";
    private String youtubeLink = BASE_URL + "/watch?v=" + GRID_YOUTUBE_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //extractYoutubeUrl();
    }
    //private void extractYoutubeUrl() {
    //    new YouTubeExtractor(this) {
    //        @Override
    //        public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
    //            if (ytFiles != null) {
    //                playVideo(ytFiles.get(17).getUrl());
    //            }
    //        }
    //    }.extract(youtubeLink, true, true);
    //}
    //private void playVideo(String downloadUrl) {
    //    SimpleExoPlayerView simpleExoPlayer = findViewById(R.id.player);
    //    simpleExoPlayer.setPlayer(Exoplayer.getSharedInstance(MainActivity.this).getSimpleExoPlayerView().getPlayer());
    //    Exoplayer.getSharedInstance(MainActivity.this).playStream(downloadUrl);
    //}

}
