package com.meitu.show;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.presenter.HomePresenter;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.presenter.base.BaseViewInf;
import com.meitu.show.viewinf.HomeViewInterface;

public class MainActivity extends BaseActivity<HomePresenter,MainActivity> implements HomeViewInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected HomePresenter getPresenter() {
        return null;
    }
}
