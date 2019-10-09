package com.example.videoview.entity.quality;

import java.util.TreeMap;

import androidx.room.PrimaryKey;

public class VideoWithQuality {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private int uidVirginLink;
    private String videoTitle;
    private TreeMap<String, String> qualityList;
}
