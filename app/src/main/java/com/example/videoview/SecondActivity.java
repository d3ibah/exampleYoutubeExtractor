package com.example.videoview;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

import static com.example.videoview.Consts.VIDEO_TITLE;
import static com.example.videoview.Consts.VIDEO_URL;
import static com.example.videoview.Consts.YOUTUBE_URL;

public class SecondActivity extends AppCompatActivity {

    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mainLayout = findViewById(R.id.main_layout);
        Intent intent = getIntent();
        extractUrl(intent.getStringExtra(YOUTUBE_URL));
    }

    private void extractUrl(String pageUrl) {
        if (pageUrl != null && (pageUrl.contains("://youtu.be/") || pageUrl.contains("youtube.com/watch?v="))) {
            getYoutubeDownloadUrl(pageUrl);
        } else {
            Toast.makeText(this, "Not a valid Youtube link!", Toast.LENGTH_LONG)
                 .show();
            //finish();
        }
    }

    private void getYoutubeDownloadUrl(String directUrl) {
        new YouTubeExtractor(this) {

            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles == null) {
                    // Something went wrong we got no urls. Always check this.
                    //finish();
                    Toast.makeText(SecondActivity.this, "Something went wrong we got no urls. Always check this.",
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
                                                                       .getAudioBitrate() + " kbit/s"
                : ytfile.getFormat()
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
                //playFromUrl(ytfile.getUrl(), videoTitle, filename);

                Intent intent = new Intent();
                intent.putExtra(VIDEO_URL, ytfile.getUrl());
                intent.putExtra(VIDEO_TITLE, filename);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mainLayout.addView(btn);
    }
}
