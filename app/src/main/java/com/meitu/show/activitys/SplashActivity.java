package com.meitu.show.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.meitu.show.BaseActivity1;
import com.meitu.show.R;
import com.meitu.show.activitys.home.activity.HomeActivity;
import com.meitu.show.model.RegisterModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.utils.SharePreUtil;

public class SplashActivity extends BaseActivity1 {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
    }

    @Override
    protected int getContentView() {
        return R.layout.lg_fragment_splash;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDatas() {
        SharePreUtil mSharePreUtil = SharePreUtil.getInstance(getApplicationContext());
        RegisterModel.DataBean userinfo = mSharePreUtil.getUserInfo();
        //点击先判断是否登录 登录了就判断是否是vip 否则就进入登录界面
        if (userinfo == null) {
            RegisterActivity.startActivity(this);
        } else {
            HomeActivity.startActivity(this);
        }
        finish();
    }
}
