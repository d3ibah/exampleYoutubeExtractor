package com.example.videoview.entity.quality;

import com.example.videoview.db.StringMapConverter;

import java.util.Map;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

//@Entity(foreignKeys = @ForeignKey(entity = VideosDb.class, parentColumns = "uid", childColumns = "uidVirginLink", onDelete = CASCADE))
@Entity(tableName = "quality")
public class VideoWithQuality {

    @PrimaryKey
    private int uid;
    private String videoTitle;

    @TypeConverters(StringMapConverter.class)
    private Map<String, String> qualityList;

    public VideoWithQuality(int uid, String videoTitle, Map<String, String> qualityList) {
        this.uid = uid;
        this.videoTitle = videoTitle;
        this.qualityList = qualityList;
    }

    //public VideoWithQuality(int uid, String videoTitle) {
    //    this.uid = uid;
    //    this.videoTitle = videoTitle;
    //}

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public Map<String, String> getQualityList() {
        return qualityList;
    }

    public void setQualityList(Map<String, String> qualityList) {
        this.qualityList = qualityList;
    }
}
