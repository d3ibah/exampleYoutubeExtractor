package com.example.videoview.db;

import java.util.List;

public class YoutubeDb {
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