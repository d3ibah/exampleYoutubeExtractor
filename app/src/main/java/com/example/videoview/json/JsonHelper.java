package com.example.videoview.json;

import android.content.Context;
import android.util.Log;

import com.example.videoview.entity.videos.Videos;
import com.example.videoview.entity.vimeo.VimeoConfigResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonHelper {
    private static final String FILE_NAME = "videos.json";

    public static Videos importFromJSON(Context context) {

        InputStreamReader streamReader = null;
        InputStream inputStream = null;
        byte[] buffer = null;
        try {
            inputStream = context.getAssets()
                                 .open(FILE_NAME);
            streamReader = new InputStreamReader(inputStream);
            //return new Gson().fromJson(streamReader, Videos.class);

            //Gson gson = new Gson();
            //Videos videos = gson.fromJson(streamReader, Videos.class);
            //return videos;


            //Gson gson = new GsonBuilder().create();
            //VimeoConfigResponse vimeoConfigResponse = gson.fromJson(response.body().string(),
            //                                                        VimeoConfigResponse.class);

            inputStream = context.getAssets().open(FILE_NAME);
            int size = inputStream.available();
            buffer = new byte[size];
            inputStream.read(buffer);
            //inputStream.close();
            Log.e("22222", "Json in string: " + new String(buffer, "UTF-8"));
            streamReader = new InputStreamReader(inputStream);
            //Gson gson = new Gson();
            Gson gson = new GsonBuilder().create();
            Videos videos = gson.fromJson(new String(buffer, "UTF-8"), Videos.class);
            return videos;
            //return new Gson().fromJson(streamReader, Videos.class);

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
