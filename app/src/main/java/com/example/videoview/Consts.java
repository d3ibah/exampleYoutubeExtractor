package com.example.videoview;

import com.example.videoview.db.VideosDb;
import com.example.videoview.entity.VideoType;
import com.example.videoview.entity.videos.Videos;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class Consts {

    protected static final String YOUTUBE_WITH_TIME_URL = "https://www.youtube.com/watch?v=wIjB8jTDOZU&t=14s";
    protected static final String YOUTUBE_URL = "YOUTUBE_URL";
    protected static final String VIDEO_URL = "VIDEO_URL";
    protected static final String VIDEO_TITLE = "VIDEO_TITLE";

    public static List<VideosDb> convertVideosToVideosDb(@NonNull Videos videos) {
        List<VideosDb> videosDbList = new ArrayList<>();
        if (videos.getYoutube() != null && videos.getYoutube()
                                                 .getUrl() != null) {
            for (int i = 0; i < videos.getYoutube()
                                      .getUrl()
                                      .size(); i++) {
                VideosDb videosDb = new VideosDb(VideoType.YOUTUBE, videos.getYoutube()
                                                                          .getUrl()
                                                                          .get(i));
                videosDbList.add(videosDb);
            }
        }
        if (videos.getVimeo() != null && videos.getVimeo()
                                               .getUrl() != null) {
            for (int i = 0; i < videos.getVimeo()
                                      .getUrl()
                                      .size(); i++) {
                VideosDb videosDb = new VideosDb(VideoType.VIMEO, videos.getVimeo()
                                                                        .getUrl()
                                                                        .get(i));
                videosDbList.add(videosDb);
            }
        }
        if (videos.getYoutube() != null && videos.getYoutube()
                                                 .getUrl() != null) {
            for (int i = 0; i < videos.getDirect()
                                      .getUrl()
                                      .size(); i++) {
                VideosDb videosDb = new VideosDb(VideoType.DIRECT, videos.getDirect()
                                                                         .getUrl()
                                                                         .get(i));
                videosDbList.add(videosDb);
            }
        }
        return videosDbList;
    }
}
