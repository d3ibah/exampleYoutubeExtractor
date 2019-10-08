package com.example.videoview;

import android.app.Application;

import com.example.videoview.db.DataBase;
import com.facebook.stetho.Stetho;

import androidx.room.Room;

public class App extends Application {

    private static App instance;
    private DataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        instance = this;
        dataBase = Room.databaseBuilder(this, DataBase.class, "videosdb")
                       .allowMainThreadQueries()
                       .fallbackToDestructiveMigration()
                       .build();
    }

    public static App getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return dataBase;
    }
}
