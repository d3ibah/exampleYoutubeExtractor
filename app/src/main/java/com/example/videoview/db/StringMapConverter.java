package com.example.videoview.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import androidx.room.TypeConverter;

public class StringMapConverter {
    @TypeConverter
    public static Map<String, String> fromString(String value) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        return new Gson().fromJson(value, mapType);
    }

    @TypeConverter
    public static String fromStringMap(Map<String, String> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
