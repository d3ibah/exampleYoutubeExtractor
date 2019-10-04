package com.example.videoview.json;

import android.content.Context;

import com.example.videoview.entity.videos.Videos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JsonHelper {
    private static final String FILE_NAME = "videos.json";

    public static Videos importFromJSON(Context context) {
        InputStreamReader streamReader = null;
        InputStream inputStream = null;
        byte[] buffer;
        try {
            inputStream = context.getAssets()
                                 .open(FILE_NAME);
            int size = inputStream.available();
            buffer = new byte[size];
            inputStream.read(buffer);
            streamReader = new InputStreamReader(inputStream);
            Gson gson = new GsonBuilder().setPrettyPrinting()
                                         .create();

            return gson.fromJson(new String(buffer, StandardCharsets.UTF_8), Videos.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
