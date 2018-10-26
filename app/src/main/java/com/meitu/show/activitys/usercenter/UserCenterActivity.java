package com.meitu.show.activitys.usercenter;

import android.content.Context;
import android.content.Intent;

import com.meitu.show.BaseActivity;
import com.meitu.show.R;
import com.meitu.show.presenter.base.BasePresenter;

public class UserCenterActivity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,UserCenterActivity.class));
    }

    @Override
    protected void initBundle() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_usercenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDatas() {

    }
}
