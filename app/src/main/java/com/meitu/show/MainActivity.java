package com.meitu.show;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.presenter.HomePresenter;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.presenter.base.BaseViewInf;
import com.meitu.show.request.GetHomeRequest;
import com.meitu.show.viewinf.HomeViewInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity<HomePresenter,MainActivity> implements HomeViewInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        request();
    }

    private static final String TAG = "MainActivity";
    private int pageNo = 2;
//    private void request() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.meitu99.cn/")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        GetHomeRequest requestModel = retrofit.create(GetHomeRequest.class);
//        Call<HomeMeituModel> requestCallback = requestModel.getHomeMeitu(String.valueOf(pageNo));
//        requestCallback.enqueue(new Callback<HomeMeituModel>() {
//            @Override
//            public void onResponse(Call<HomeMeituModel> call, Response<HomeMeituModel> response) {
//                Log.e(TAG,""+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<HomeMeituModel> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public void notifyHomeUiWithData(List<HomeMeituModel.Content.DataDetail> list) {

    }
}
