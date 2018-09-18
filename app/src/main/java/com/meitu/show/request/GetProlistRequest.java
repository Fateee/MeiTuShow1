package com.meitu.show.request;

import com.meitu.show.model.PoProlistModel;
import com.meitu.show.model.ProlistModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2018/1/26.
 */

public interface GetProlistRequest {

    @GET("details")
    Call<ProlistModel> getProlistMeitu(@Query("pageNum") String pageNo,@Query("url") String url);

    @GET("details")
    Call<ProlistModel> getProlistMeitu(@QueryMap Map<String, String> params);


    @GET("spacial/imagesv2")
    Call<PoProlistModel> getPoProlistMeitu(@QueryMap Map<String, String> params);
}
