package com.example.videoview.db;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

public class YoutubeDb {
    @TypeConverters(StringListConverter.class)
    @ColumnInfo(name = "youtube_url")
    private List<String> url;

    public YoutubeDb(List<String> url) {
        this.url = url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<String> getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Youtube{" + "url = '" + url + '\'' + "}";
    }
}