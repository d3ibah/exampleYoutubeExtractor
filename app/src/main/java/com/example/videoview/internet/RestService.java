package com.example.videoview.internet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    public static final String BASE_URL = "https://player.vimeo.com/video/";
    private static RestService instance;
    private VimeoConfApi vimeoConfApi;

    public RestService() {
        init();
    }

    public static RestService getInstance(){
        if(instance == null){
            instance = new RestService();
        }
        return instance;
    }

    public VimeoConfApi getVimeoConfApi() {
        return vimeoConfApi;
    }

    private void init(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(
                //        GsonConverterFactory.create(GsonHolder.getGson()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        vimeoConfApi = retrofit.create(VimeoConfApi.class);
    }
}
