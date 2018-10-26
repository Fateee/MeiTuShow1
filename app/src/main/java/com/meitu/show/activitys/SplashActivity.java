package com.meitu.show.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.meitu.show.BaseActivity;
import com.meitu.show.R;
import com.meitu.show.activitys.home.activity.HomeActivity;
import com.meitu.show.model.RegisterModel;
import com.meitu.show.presenter.RegisterPresenter;
import com.meitu.show.utils.SharePreUtil;
import com.meitu.show.viewinf.RegisterIV;

import butterknife.BindView;

public class SplashActivity extends BaseActivity<RegisterPresenter> implements RegisterIV {

    @BindView(R.id.iv_register_bg)
    ImageView mRegisterBg;

    private RegisterPresenter mRegisterPresenter;

    private long DELAY_SECONDS = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initDatas();
    }

    @Override
    protected void initBundle() {
    }

    @Override
    protected int getContentView() {
        return R.layout.lg_fragment_splash;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected RegisterPresenter getPresenter() {
        mRegisterPresenter = new RegisterPresenter();
        return mRegisterPresenter;
    }

    @Override
    protected void initDatas() {
        SharePreUtil mSharePreUtil = SharePreUtil.getInstance(getApplicationContext());
        RegisterModel.DataBean userinfo = mSharePreUtil.getUserInfo();
//        //点击先判断是否登录 登录了就判断是否是vip 否则就进入登录界面
//        if (userinfo == null) {
//            RegisterActivity.startActivity(this);
//        } else {
//            HomeActivity.startActivity(this);
//        }
        if (userinfo != null) {
            mRegisterPresenter.getRegisterUser(userinfo.getPhone());
        } else {
            goToMain(2);
        }
    }

    private void goToMain(long delayseconds) {
        if (mRegisterBg != null) {
            mRegisterBg.postDelayed(new Runnable() {
                @Override
                public void run() {
                    HomeActivity.startActivity(SplashActivity.this);
                    finish();
                }
            }, delayseconds*DELAY_SECONDS);
        }

    }

    @Override
    public void notifyRegisterResult(RegisterModel.DataBean data) {
        if (data != null) {
            SharePreUtil mSharePreUtil = SharePreUtil.getInstance(getApplicationContext());
            mSharePreUtil.saveUserInfo(data);
        }
        goToMain(1);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {
    }
}
