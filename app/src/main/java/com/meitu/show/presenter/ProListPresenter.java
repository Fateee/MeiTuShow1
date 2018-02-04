package com.meitu.show.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.meitu.show.Constant;
import com.meitu.show.model.ProlistModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.request.GetHomeRequest;
import com.meitu.show.request.GetProlistRequest;
import com.meitu.show.viewinf.ProListViewInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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

public class ProListPresenter extends BasePresenter<ProListViewInterface,ProlistModel> implements Callback<ProlistModel> {

    private final GetProlistRequest mRequestModel;

    private int pageNo = 1;

    public ProListPresenter() {

//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                //打印retrofit日志
//                Log.e("RetrofitLog","retrofitBack = "+message);
//            }
//        });
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .connectTimeout(5000, TimeUnit.SECONDS)
//                .readTimeout(5000, TimeUnit.SECONDS)
//                .writeTimeout(5000, TimeUnit.SECONDS)
//                .build();

        Retrofit mRetrofit = new Retrofit.Builder().baseUrl(Constant.mHomeUrl)/*.client(client)*/
                .addConverterFactory(GsonConverterFactory.create()).build();
        mRequestModel = mRetrofit.create(GetProlistRequest.class);
    }

    @Override
    public ProlistModel getModel() {
        return null;
    }

    public void getProlistMeiTuList(boolean refresh,String url) {
        if (TextUtils.isEmpty(url)) return;
        if (refresh) pageNo = 1;
        Map<String,String> param = new HashMap<>();
        param.put("pageNum",String.valueOf(pageNo));
        param.put("url",url);
        Call<ProlistModel> requestCallback = mRequestModel.getProlistMeitu(param);
        requestCallback.enqueue(this);
    }

    @Override
    public void onResponse(Call<ProlistModel> call, Response<ProlistModel> response) {
        String status = response.body() != null ? response.body().getStatus() : "";
        if (!"OK".equals(status)) return;
        pageNo++;
        ProListViewInterface homeView = getView();
        ProlistModel.ProlistContent data = response.body().getData();
        if (homeView != null ) {
            homeView.notifyListUiWithData(data.getList(),pageNo > 1 ? false:true);
        }
    }

    @Override
    public void onFailure(Call<ProlistModel> call, Throwable t) {

    }
}
