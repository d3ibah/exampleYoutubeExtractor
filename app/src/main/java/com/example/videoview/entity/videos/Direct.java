package com.example.videoview.entity.videos;

import java.util.List;

public class Direct {
    private List<String> url;

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