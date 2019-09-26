package com.example.videoview;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.URI;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private TextView textView;
    private Button btn;
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private PlayerView playerViewYoutube;
    private SimpleExoPlayer playerYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player_view);
        playerViewYoutube = findViewById(R.id.player_view_youtube);
        textView = findViewById(R.id.textFromVideo);
        //videoView = findViewById(R.id.app_video_bg);
        btn = findViewById(R.id.button_super);

        //videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        //    @Override
        //    public void onPrepared(MediaPlayer mp) {
        //        mp.setLooping(true);
        //    }
        //});

        //btn.isVisibleToUserForAutofill()

        //String link
        //        = "http://mirrors.standaloneinstaller.com/video-sample/small.mp4";
        //videoView.setVideoURI(Uri.parse(link));
        //
        //videoView.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        playerYoutube = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());

        playerView.setPlayer(player);
        playerViewYoutube.setPlayer(playerYoutube);

        Uri mp4VideoUri =  Uri.parse("https://r8---sn-cxauxaxjvh-hn9el.googlevideo.com/videoplayback?expire=1569535700&ei=dOKMXarTB8TDyQWe8aCoCQ&ip=82.209.210.219&id=o-AMqb-ypLNU9jXDpjMVv9n5HQrr2iqs3ZchA3CrtqcdDy&itag=18&source=youtube&requiressl=yes&mm=31%2C26&mn=sn-cxauxaxjvh-hn9el%2Csn-4g5ednll&ms=au%2Conr&mv=m&mvi=7&pl=18&initcwndbps=896250&mime=video%2Fmp4&gir=yes&clen=27864156&ratebypass=yes&dur=545.738&lmt=1569412739305580&mt=1569513959&fvip=5&fexp=23842630&c=WEB&txp=5531432&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cmime%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=ALgxI2wwRAIgLThYLeIJcURzgfnA59W8P76Vs4V8GCk2Gc4nXfnYX18CIF8It0OND66J8mRyZnn9wNySy8T7Uw-kJ0WNVN2EXZQw&lsparams=mm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AHylml4wRAIgd5NzQZTBRDR4FZ1RxMkQ2OpCJzvnlhDmaXfCPvj0pTkCIE4LkjvpgJZsVlIFWuozQIy2qZCmC-XRhQMcRW-Zol6q");
        Log.e("uri", "uri " + mp4VideoUri.toString());

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                this,
                Util.getUserAgent(this, getString(R.string.app_name)));

        //"https://www.youtube.com/watch?v=wIjB8jTDOZU&t=14s");
        // https://youtu.be/C23fO5mjRNA
        // https://www.youtube.com/embed/trVk_1Fn3fA
        //"https://www.youtube.com/embed/trVk_1Fn3fA"
        // https://www.youtube.com/watch?v=trVk_1Fn3fA
        // https://gcs-vimeo.akamaized.net/exp=1569520853~acl=%2A%2F365951685.mp4%2A~hmac=74dc1a971c58c6b43b278c53788a142d416bd30b5cd93a6565db2969113557a7/vimeo-prod-skyfire-std-us/01/494/5/127472766/365951685.mp4
        // https://r8---sn-cxauxaxjvh-hn9el.googlevideo.com/videoplayback?expire=1569535700&ei=dOKMXarTB8TDyQWe8aCoCQ&ip=82.209.210.219&id=o-AMqb-ypLNU9jXDpjMVv9n5HQrr2iqs3ZchA3CrtqcdDy&itag=18&source=youtube&requiressl=yes&mm=31%2C26&mn=sn-cxauxaxjvh-hn9el%2Csn-4g5ednll&ms=au%2Conr&mv=m&mvi=7&pl=18&initcwndbps=896250&mime=video%2Fmp4&gir=yes&clen=27864156&ratebypass=yes&dur=545.738&lmt=1569412739305580&mt=1569513959&fvip=5&fexp=23842630&c=WEB&txp=5531432&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cmime%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=ALgxI2wwRAIgLThYLeIJcURzgfnA59W8P76Vs4V8GCk2Gc4nXfnYX18CIF8It0OND66J8mRyZnn9wNySy8T7Uw-kJ0WNVN2EXZQw&lsparams=mm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AHylml4wRAIgd5NzQZTBRDR4FZ1RxMkQ2OpCJzvnlhDmaXfCPvj0pTkCIE4LkjvpgJZsVlIFWuozQIy2qZCmC-XRhQMcRW-Zol6q
        //https://d2v9y0dukr6mq2.cloudfront.net/video/preview/GTYSdDW/panoramic-view-of-cola-or-soda-in-the-glass-french-fries-potato-and-hamburger-sandwich-on-tray-on-table_enuxnii0l__PM.mp4

        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mp4VideoUri);
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);


        //String userAgent = Util.getUserAgent(getApplicationContext(), getString(R.string.app_name));
        //DashChunkSource.Factory dashChunkSourceFactory = new DefaultDashChunkSource.Factory(
        //        new DefaultHttpDataSourceFactory("ua", new DefaultBandwidthMeter()));
        //DataSource.Factory manifestDataSourceFactory = new DefaultHttpDataSourceFactory(userAgent);
        //return new DashMediaSource.Factory(dashChunkSourceFactory, manifestDataSourceFactory).createMediaSource(uri);
    }
}
