package com.example.videoview;

import com.example.videoview.db.VideosDb;
import com.example.videoview.entity.VideoType;
import com.example.videoview.entity.videos.Videos;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class Consts {

    protected static final String YOUTUBE_WITH_TIME_URL = "https://www.youtube.com/watch?v=wIjB8jTDOZU&t=14s";
    protected static final String YOUTUBE_SHORT_URL = "https://youtu.be/C23fO5mjRNA";
    protected static final String YOUTUBE_EMBED_URL = "https://www.youtube.com/embed/trVk_1Fn3fA";
    protected static final String YOUTUBE_WATCH_URL = "https://www.youtube.com/watch?v=trVk_1Fn3fA";
    protected static final String YOUTUBE_DIRECT_URL
            = "https://r8---sn-cxauxaxjvh-hn9el.googlevideo.com/videoplayback?expire=1569875776&ei=3xKSXaeuNYPa7gTHl6aYBQ&ip=82.209.210.219&id=o-AJEEXs1c77k4_c8_YAh-FU07GDZFp5EJHV4RgOuPhhc3&itag=22&source=youtube&requiressl=yes&mm=31%2C26&mn=sn-cxauxaxjvh-hn9el%2Csn-h0jeener&ms=au%2Conr&mv=m&mvi=7&pl=18&initcwndbps=915000&mime=video%2Fmp4&ratebypass=yes&dur=545.738&lmt=1569410370856695&mt=1569854132&fvip=5&fexp=23842630&beids=9466588&c=WEB&txp=2316222&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cmime%2Cratebypass%2Cdur%2Clmt&sig=ALgxI2wwRgIhANVIfonTTNjMySUoIouYI_Kf65YPEpVYpfekq7tZ4ETjAiEAxyMC5IebGxD22fYBQtA9CvdLs-vXSha1eoB2lOd8h6c%3D&lsparams=mm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AHylml4wRQIhAMyhLFJlNr3HxwhdLSGK-iXCPE6cb0DVST6D2AZOv-gdAiBjn5a1MwQB8Dw6awzswnKs-ILkU2QmA9xeagDXpYxzyA%3D%3D";
    protected static final String VIMEO_URL
            = "https://gcs-vimeo.akamaized.net/exp=1569858930~acl=%2A%2F365951685.mp4%2A~hmac=b1bf2c12f09420a97bc244524ed6d1ca415120f3cc26d224127bf1fef2a07538/vimeo-prod-skyfire-std-us/01/494/5/127472766/365951685.mp4";
    protected static final String MP4_URL
            = "https://d2v9y0dukr6mq2.cloudfront.net/video/preview/GTYSdDW/panoramic-view-of-cola-or-soda-in-the-glass-french-fries-potato-and-hamburger-sandwich-on-tray-on-table_enuxnii0l__PM.mp4";
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
