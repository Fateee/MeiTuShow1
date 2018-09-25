package com.meitu.show;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.presenter.base.BaseViewInf;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment<P extends BasePresenter, V extends BaseViewInf> extends Fragment {

    private P mP;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mP = getPresenter();
        if (mP == null) return;
        mP.attach((V) this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initData();
    }

    protected abstract P getPresenter();

    protected abstract int getContentView();

    protected abstract void initViews(View view);

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mP.deAttach();
    }

}
