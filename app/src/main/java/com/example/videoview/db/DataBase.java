package com.example.videoview.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = VideosDb.class, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract VideoDAO getVideosDao();
}
