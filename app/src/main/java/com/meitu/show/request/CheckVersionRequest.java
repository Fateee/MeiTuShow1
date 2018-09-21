package com.meitu.show.request;


import com.meitu.show.model.version.AppVerInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CheckVersionRequest {
    @GET("api/checkversion")
    Call<AppVerInfo> checkNewestVersion();
}
