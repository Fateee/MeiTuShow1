package com.meitu.show.request;



import com.meitu.show.model.RegisterModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface RegisterRequest {

    @POST("api/login")
    Call<RegisterModel> postRegisterUser(@QueryMap Map<String, String> params);

    @GET("api/login")
    Call<RegisterModel> getRegisterUser(@QueryMap Map<String, String> params);
}
