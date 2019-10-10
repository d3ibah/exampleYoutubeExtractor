package com.example.videoview.db;

import com.example.videoview.entity.quality.VideoWithQuality;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { VideosDb.class, VideoWithQuality.class }, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract VideoDAO getVideosDao();
}
