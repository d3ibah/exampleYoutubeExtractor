package com.example.videoview.db;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.room.TypeConverter;

class StringListConverter {
    private final Type type = new TypeToken<List<String>>() {
    }.getType();

    @TypeConverter
    public String fromStringList(List<String> stringList) {
        if (stringList == null) {
            return "";
        }

        return GsonHolder.getGson()
                         .toJson(stringList, type);
    }

    @TypeConverter
    public List<String> toStringList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }

        return GsonHolder.getGson()
                         .fromJson(data, type);
    }
}
