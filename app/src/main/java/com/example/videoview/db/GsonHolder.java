package com.example.videoview.db;

import com.google.gson.Gson;

import androidx.annotation.NonNull;

public final class GsonHolder {

    private GsonHolder() {
    }

    @NonNull
    public static Gson getGson() {
        return Holder.GSON;
    }

    static final class Holder {
        private static final Gson GSON = new Gson();
    }
}
