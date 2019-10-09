package com.example.videoview.entity;

public enum VideoType {

    YOUTUBE("YOUTUBE"), VIMEO("VIMEO"), DIRECT("DIRECT");

    private final String type;

    public String getType() {
        return type;
    }

    VideoType(String type) {
        this.type = type;
    }
}
