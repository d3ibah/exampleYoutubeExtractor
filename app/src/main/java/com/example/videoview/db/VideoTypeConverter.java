package com.example.videoview.db;

import com.example.videoview.entity.VideoType;

import androidx.room.TypeConverter;

public class VideoTypeConverter {
    @TypeConverter
    public String fromTaskType(VideoType type) {
        if (type == null) {
            return "";
        }

        return type.name();
    }

    @TypeConverter
    public VideoType toTaskType(String type) {
        if (type == null || type.equals("")) {
            return null;
        }

        return VideoType.valueOf(type);
    }
}
