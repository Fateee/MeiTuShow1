package com.meitu.show;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.presenter.base.BaseViewInf;

/**
 * Created by Administrator on 2018/1/18.
 */

public abstract class BaseActivity <P extends BasePresenter, V extends BaseViewInf> extends AppCompatActivity {

    private P mP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mP = getPresenter();
        mP.attach((V)this);
    }

    protected abstract P getPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mP.deAttach();
    }
}
