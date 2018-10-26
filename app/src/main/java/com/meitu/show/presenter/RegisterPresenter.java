package com.meitu.show.presenter;

import com.meitu.show.Constant.UrlConst;
import com.meitu.show.model.RegisterModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.request.RegisterRequest;
import com.meitu.show.viewinf.RegisterIV;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter extends BasePresenter<RegisterIV,RegisterModel> {

    private final RegisterRequest mRegisterRequest;

    public RegisterPresenter() {
        super();
        mRegisterRequest = initRetrofit(UrlConst.mAppInfoUrl,RegisterRequest.class);
    }

    @Override
    public RegisterModel getModel() {
        return null;
    }

    public void postRegisterUser(String phone, String phoneCode) {
        if (getView() != null) getView().showLoading();
        Map<String,String> param = new HashMap<>();
        param.put("phone", phone);
        param.put("phonecode", phoneCode);
        Call<RegisterModel> requestCallback = mRegisterRequest.postRegisterUser(param);
        requestCallback.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if (getView() != null) getView().dismissLoading();
                if (response.body() == null || response.body().getCode() != 0) return;
                RegisterIV signView = getView();
                RegisterModel.DataBean data = response.body().getData();
                if (signView != null ) {
                    signView.notifyRegisterResult(data);
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {

            }
        });
    }

    public void getRegisterUser(String phone) {
        if (getView() != null) getView().showLoading();
        Map<String,String> param = new HashMap<>();
        param.put("phone", phone);
        param.put("phonecode", "520520");
        Call<RegisterModel> requestCallback = mRegisterRequest.postRegisterUser(param);
        requestCallback.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                RegisterIV signView = getView();
                if (signView != null) signView.dismissLoading();
                if (response.body() == null || response.body().getCode() != 0) {
                    signView.notifyRegisterResult(null);
                    return;
                }
                RegisterModel.DataBean data = response.body().getData();
                if (signView != null && data != null) {
                    signView.notifyRegisterResult(data);
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                RegisterIV signView = getView();
                if (signView != null) {
                    signView.dismissLoading();
                    signView.notifyRegisterResult(null);
                }
            }
        });
    }
}
