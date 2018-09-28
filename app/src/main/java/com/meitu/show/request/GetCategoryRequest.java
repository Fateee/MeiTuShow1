package com.meitu.show.request;

import com.meitu.show.model.CategoryListModel;
import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.model.PoMeiTuModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2018/1/26.
 */

public interface GetCategoryRequest {

    /**
     * https://www.poshow18.com/spacial/list?type=2&key=views&size=10&index=1 //精选
     * https://www.poshow18.com/spacial/list?type=1&key=views&size=10&index=1 //专辑
     * https://www.poshow18.com/spacial/spacials?id=12
     */
    @GET("spacial/spacials")
    Call<CategoryListModel> getCategoryList(@QueryMap Map<String, String> params);

    <T> T initRetrofit(String url, Class<T> service);

}
