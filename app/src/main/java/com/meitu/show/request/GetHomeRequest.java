package com.meitu.show.request;

import com.meitu.show.model.HomeMeituModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/1/26.
 */

public interface GetHomeRequest {

    @GET("list")
    Call<HomeMeituModel> getHomeMeitu(@Query("pageNum") String pageNo);

    @GET("list?pageNum=1")
    Call<HomeMeituModel> getHomeMeitu1();
}
