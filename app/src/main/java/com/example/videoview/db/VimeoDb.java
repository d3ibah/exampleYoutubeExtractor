package com.example.videoview.db;

import java.util.List;

public class VimeoDb {
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