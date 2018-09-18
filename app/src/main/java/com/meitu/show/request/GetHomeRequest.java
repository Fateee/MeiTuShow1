package com.meitu.show.request;

import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.model.PoMeiTuModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2018/1/26.
 */

public interface GetHomeRequest {

    @GET("list")
    Call<HomeMeituModel> getHomeMeitu(@Query("pageNum") String pageNo);

    @GET("list?pageNum=1")
    Call<HomeMeituModel> getHomeMeitu1();

    @GET("spacial/list")
    Call<PoMeiTuModel> getPoMeitu(@QueryMap Map<String, String> params);
}
