package com.meitu.show.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.meitu.show.Constant;
import com.meitu.show.model.PoProlistModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.request.GetProlistRequest;
import com.meitu.show.viewinf.ProListViewInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/2/4.
 */

public class PoProListPresenter extends BasePresenter<ProListViewInterface, PoProlistModel> implements Callback<PoProlistModel> {

    private final GetProlistRequest mRequestModel;

//    private int pageNo = 1;

    public PoProListPresenter() {
        OkHttpClient client = null;
        if (Constant.debug) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    //打印retrofit日志
                    Log.e("RetrofitLog","retrofitBack = "+message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(5000, TimeUnit.SECONDS)
                    .readTimeout(5000, TimeUnit.SECONDS)
                    .writeTimeout(5000, TimeUnit.SECONDS)
                    .build();
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Constant.mHomePoUrl).addConverterFactory(GsonConverterFactory.create());
        if (Constant.debug) {
            builder.client(client);
        }
        Retrofit mRetrofit = builder.build();
        mRequestModel = mRetrofit.create(GetProlistRequest.class);
    }

    @Override
    public PoProlistModel getModel() {
        return null;
    }

//    public void getProlistMeiTuList(boolean refresh,String url) {
//        if (TextUtils.isEmpty(url)) return;
//        if (refresh) pageNo = 1;
//        Map<String,String> param = new HashMap<>();
//        param.put("pageNum",String.valueOf(pageNo));
//        param.put("url",url);
//        Call<PoProlistModel> requestCallback = mRequestModel.getProlistMeitu(param);
//        requestCallback.enqueue(this);
//    }

    private int mImgId;

    public void getProlistMeiTuList(boolean refresh, int imgId) {
//        if (TextUtils.isEmpty(url)) return;
        if (imgId == 0) return;
        mImgId = imgId;
        if (refresh) {
            if (getView() != null) getView().showLoading();
//            pageNo = 1;
        }
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(mImgId));
        Call<PoProlistModel> requestCallback = mRequestModel.getPoProlistMeitu(param);
        requestCallback.enqueue(this);
    }


    @Override
    public void onResponse(Call<PoProlistModel> call, Response<PoProlistModel> response) {
        String status = response.body() != null ? response.body().getCode() : "";
        if (!"0".equals(status)) return;
//        pageNo++;
        ProListViewInterface homeView = getView();
        List<PoProlistModel.ContentBean> data = response.body().getContent();
        if (homeView != null) {
            homeView.dismissLoading();
            homeView.notifyListUiWithAllData(data);
        }
    }


    @Override
    public void onFailure(Call<PoProlistModel> call, Throwable t) {

    }
}
