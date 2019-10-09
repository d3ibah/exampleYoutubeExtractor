package com.example.videoview.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface VideoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addVideo(VideosDb videosDb);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addVideo(List<VideosDb> videosDbList);

    @Update
    void updateVideos(VideosDb videosDb);

    @Delete
    void deleteAllVideo(VideosDb videosDb);

    @Query("SELECT * FROM videos")
    List<VideosDb> getVideo();
}
