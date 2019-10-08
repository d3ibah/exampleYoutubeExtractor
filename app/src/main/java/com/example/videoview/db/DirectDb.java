package com.example.videoview.db;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

public class DirectDb {
    @TypeConverters(StringListConverter.class)
    @ColumnInfo(name = "direct_url")
    private List<String> url;

    public DirectDb(List<String> url) {
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
        return "Direct{" + "url = '" + url + '\'' + "}";
    }
}