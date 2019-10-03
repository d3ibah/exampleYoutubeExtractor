package com.example.videoview.internet;

import androidx.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VimeoConfApi {

    //    @GET("/api/aircrafts/{id}")
    //    Single<Aircraft> getAircraft(@Path("id") @NonNull int aircraftId);
    @GET("{id}/config")
    Call<ResponseBody> getDirectUrls(@Path("id") @NonNull int videoId);
}
