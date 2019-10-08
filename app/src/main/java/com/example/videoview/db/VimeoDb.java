package com.example.videoview.db;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

public class VimeoDb {
    @TypeConverters(StringListConverter.class)
    @ColumnInfo(name = "vimeo_url")
    private List<String> url;

    public VimeoDb(List<String> url) {
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
        return "Vimeo{" + "url = '" + url + '\'' + "}";
    }
}