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
    private int uid;

    @TypeConverters(VideoTypeConverter.class)
    private VideoType type;
    private String url;

    public VideosDb(VideoType type, String url) {
        this.type = type;
        this.url = url;
    }

    public VideoType getType() {
        return type;
    }

    public void setType(VideoType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
