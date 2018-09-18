package com.meitu.show.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.meitu.show.Constant;
import com.meitu.show.model.PoMeiTuModel;
import com.meitu.show.model.PoMeiTuModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.request.GetHomeRequest;
import com.meitu.show.viewinf.HomeViewInterface;

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
 * Created by Administrator on 2018/1/18.
 */

public class HomePresenter extends BasePresenter<HomeViewInterface,PoMeiTuModel> implements Callback<PoMeiTuModel> {

    private Retrofit mRetrofit;
    private final GetHomeRequest mRequestModel;
    private int pageNo = 1;
    private int pageNum = 10;
    public HomePresenter() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder().baseUrl(Constant.mHomePoUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();

//        mRetrofit = new Retrofit.Builder().baseUrl(Constant.mHomePoUrl)
//                .addConverterFactory(GsonConverterFactory.create()).build();
        mRequestModel = mRetrofit.create(GetHomeRequest.class);
    }

    @Override
    public PoMeiTuModel getModel() {
        return new PoMeiTuModel();
    }

    public void getHomeMeiTuList(boolean refresh) {
        if (refresh) {
            if (getView() != null) getView().showLoading();
            pageNo = 1;
        }
        Map<String,String> param = new HashMap<>();
        param.put("type","2");
        param.put("key","createTime");
        param.put("size",String.valueOf(pageNum));
        param.put("index",String.valueOf(pageNo));
        Call<PoMeiTuModel> requestCallback = mRequestModel.getPoMeitu(param);
        requestCallback.enqueue(this);
    }

    @Override
    public void onResponse(Call<PoMeiTuModel> call, Response<PoMeiTuModel> response) {
        String status = response.body() != null ? response.body().getCode() : "";
        if (!"0".equals(status)) return;
        pageNo++;
        HomeViewInterface homeView = getView();
        List<PoMeiTuModel.ContentBean> dataList = response.body().getContent();
        if (homeView != null ) {
            homeView.dismissLoading();
            homeView.notifyHomeUiWithData(dataList,pageNo > 1 ? false:true);
        }
    }

    @Override
    public void onFailure(Call<PoMeiTuModel> call, Throwable t) {

    }
}
