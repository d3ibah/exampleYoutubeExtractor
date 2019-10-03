package com.example.videoview.entity.vimeo;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("title")
    private String title;

    public String getTitle(){
        return title;
    }

    @Override
    public String toString(){
        return
                "{" +
                "title = '" + title + '\'' +
                "}";
    }
}
