package com.example.videoview;

import android.util.SparseArray;

import com.example.videoview.entity.quality.VideoWithQuality;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import timber.log.Timber;

public class YoutubeDirectLinkExtractor {

    private int virginUid;
    private String pageUrl;

    public YoutubeDirectLinkExtractor(int virginUid, String pageUrl) {
        this.virginUid = virginUid;
        this.pageUrl = pageUrl;
        extractUrl(virginUid, pageUrl);
    }

    private VideoWithQuality videoWithQuality;

    public @NonNull
    VideoWithQuality getVideoWithQuality() {
        return videoWithQuality;
    }

    private void extractUrl(final int virginUid, String pageUrl) {
        if (pageUrl != null && (pageUrl.contains("://youtu.be/") || pageUrl.contains("youtube.com/watch?v="))) {
            getYoutubeDownloadUrl(virginUid, pageUrl);
        } else {
            Timber.e("Not a valid Youtube link!");
        }
    }

    private void getYoutubeDownloadUrl(final int virginUid, String directUrl) {
        new YouTubeExtractor(App.getInstance()
                                .getApplicationContext()) {

            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles == null) {
                    Timber.e("Something went wrong we got no urls. Always check this.");
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
                        saveYoutubeVideoWithQuality(virginUid, vMeta.getTitle(), ytFile);
                    }
                }
            }
        }.extract(directUrl, true, false);
    }

    private void saveYoutubeVideoWithQuality(final int virginUid, final String videoTitle, final YtFile ytfile) {
        String qualityYoutube = (ytfile.getFormat()
                                       .getHeight() == -1) ? "Audio " + ytfile.getFormat()
                                                                              .getAudioBitrate() + " kbit/s"
                : ytfile.getFormat()
                        .getHeight() + "p";
        qualityYoutube += (ytfile.getFormat()
                                 .isDashContainer()) ? " dash" : "";

        Map<String, String> qualityList = new HashMap<>();
        qualityList.put(qualityYoutube, ytfile.getUrl());
        if (videoWithQuality != null) {
            //TODO - change int id to String
            if (videoWithQuality.getUid() == 0) {
                videoWithQuality.setUid(virginUid);
            }
            if (videoWithQuality.getVideoTitle() == null) {
                videoWithQuality.setVideoTitle(videoTitle);
            }
            if (videoWithQuality.getQualityList() == null || videoWithQuality.getQualityList()
                                                                             .isEmpty()) {
                videoWithQuality.setQualityList(qualityList);
            } else {
                Map<String, String> tempQualityList = videoWithQuality.getQualityList();
                tempQualityList.putAll(qualityList);
                videoWithQuality.setQualityList(tempQualityList);
            }
        } else {
            videoWithQuality = new VideoWithQuality(virginUid, videoTitle, qualityList);
        }
        //String filename;
        //if (videoTitle != null && videoTitle.length() > 55) {
        //    filename = videoTitle.substring(0, 55) + "." + ytfile.getFormat()
        //                                                         .getExt();
        //} else {
        //    if (videoTitle == null) {
        //        filename = "Some video";
        //    } else {
        //        filename = videoTitle + "." + ytfile.getFormat()
        //                                            .getExt();
        //    }
        //}
        //filename = filename.replaceAll("[\\\\><\"|*?%:#/]", "");

    }
}
