package com.example.videoview.db;

import com.example.videoview.entity.VideoType;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Keep
@Entity(tableName = "videos")
public class VideosDb {

    @PrimaryKey(autoGenerate = true)
    int uidDdDdd;

    @TypeConverters(VideoTypeConverter.class)
    VideoType type;
    String url;

    public VideosDb(VideoType type, String url) {
        this.type = type;
        this.url = url;
    }

    //@Nullable
    //@Embedded
    //private YoutubeDb youtubeDb;
    //
    //@Nullable
    //@Embedded
    //private VimeoDb vimeoDb;
    //
    //@Nullable
    //@Embedded
    //private DirectDb directDb;
    //
    //public int getUid() {
    //    return uid;
    //}
    //
    //public void setUid(int uid) {
    //    this.uid = uid;
    //}
    //
    //@Nullable
    //public YoutubeDb getYoutubeDb() {
    //    return youtubeDb;
    //}
    //
    //public void setYoutubeDb(@Nullable YoutubeDb youtubeDb) {
    //    this.youtubeDb = youtubeDb;
    //}
    //
    //@Nullable
    //public VimeoDb getVimeoDb() {
    //    return vimeoDb;
    //}
    //
    //public void setVimeoDb(@Nullable VimeoDb vimeoDb) {
    //    this.vimeoDb = vimeoDb;
    //}
    //
    //@Nullable
    //public DirectDb getDirectDb() {
    //    return directDb;
    //}
    //
    //public void setDirectDb(@Nullable DirectDb directDb) {
    //    this.directDb = directDb;
    //}

    //public VideosDb(@Nullable YoutubeDb youtubeDb, @Nullable VimeoDb vimeoDb, @Nullable DirectDb directDb) {
    //    this.youtubeDb = youtubeDb;
    //    this.vimeoDb = vimeoDb;
    //    this.directDb = directDb;
    //}
}
