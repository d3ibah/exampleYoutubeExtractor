package com.example.videoview.db;

import com.example.videoview.entity.VideoType;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Keep
@Entity(tableName = "videos")
public class VideosDb {

    @PrimaryKey(autoGenerate = true)
    int uid;

    @TypeConverters(VideoTypeConverter.class)
    VideoType type;
    String url;

    public VideosDb(VideoType type, String url) {
        this.type = type;
        this.url = url;
    }
}
