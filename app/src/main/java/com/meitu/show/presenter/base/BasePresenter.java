package com.meitu.show.presenter.base;


import android.util.Log;

import com.meitu.show.Constant.UrlConst;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/1/18.
 */

public abstract class BasePresenter<V extends BaseViewInf, M extends BaseModelInf> {

    private OkHttpClient client;
    public WeakReference<V> weakReference;

    public M model;

    public BasePresenter() {
        if (UrlConst.debug) {
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
    }

    public void attach(V v) {
        weakReference = new WeakReference<V>(v);
        model = getModel();
    }

    public abstract M getModel();

    public <T> T initRetrofit(String url, Class<T> service) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        if (UrlConst.debug && client!= null) {
            builder.client(client);
        }
        Retrofit mRetrofit = builder.build();
        return mRetrofit.create(service);
    }

    public void deAttach() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    public boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    public V getView() {
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
}
