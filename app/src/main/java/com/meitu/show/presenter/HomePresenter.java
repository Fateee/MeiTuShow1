package com.meitu.show.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.request.GetHomeRequest;
import com.meitu.show.viewinf.HomeViewInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2018/1/18.
 */

public class HomePresenter extends BasePresenter<HomeViewInterface,HomeMeituModel> implements Callback<HomeMeituModel> {

    private String mHomeUrl = "http://www.meitu99.cn/";
    private Retrofit mRetrofit;
    private final GetHomeRequest mRequestModel;
    private int pageNo = 1;
    public HomePresenter() {
        mRetrofit = new Retrofit.Builder().baseUrl(mHomeUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        mRequestModel = mRetrofit.create(GetHomeRequest.class);
    }

    @Override
    public HomeMeituModel getModel() {
        return new HomeMeituModel();
    }

    public void getHomeMeiTuList(boolean refresh) {
        if (refresh) pageNo = 0;
        Call<HomeMeituModel> requestCallback = mRequestModel.getHomeMeitu(String.valueOf(pageNo));
        requestCallback.enqueue(this);
    }

    public void requestHomeUrl(String url) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
                super.run();
            }
        }.start();
    }

    @Override
    public void onResponse(Call<HomeMeituModel> call, Response<HomeMeituModel> response) {
        String status = response.body() != null ? response.body().getStatus() : "";
        if (!"OK".equals(status)) return;
        pageNo++;
        HomeViewInterface homeView = getView();
        HomeMeituModel.Content data = response.body().getData();
        if (homeView != null ) {
            homeView.notifyHomeUiWithData(data.getList());
        }
    }

    @Override
    public void onFailure(Call<HomeMeituModel> call, Throwable t) {

    }
}
