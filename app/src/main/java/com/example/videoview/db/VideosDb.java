package com.example.videoview.db;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Keep
@Entity(tableName = "videos")
public class VideosDb {

    @PrimaryKey(autoGenerate = true)
    int uid;

    String type;
    String url;

    public VideosDb(String type, String url) {
        this.type = type;
        this.url = url;
    }

    //@Nullable
    //@Embedded
    //private YoutubeDb youtubeDb;
    //
    //@Nullable
    //@Embedded
    //private VimeoDb vimeoDb;
    //
    //@Nullable
    //@Embedded
    //private DirectDb directDb;
    //
    //public VideosDb(@Nullable YoutubeDb youtubeDb, @Nullable VimeoDb vimeoDb, @Nullable DirectDb directDb) {
    //    this.youtubeDb = youtubeDb;
    //    this.vimeoDb = vimeoDb;
    //    this.directDb = directDb;
    //}
}
