package com.meitu.show;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meitu.show.downloader.DownloadManager;
import com.meitu.show.model.eventbus.EventConst;
import com.meitu.show.model.eventbus.MessageEvent;
import com.meitu.show.model.version.AppVerInfo;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.presenter.base.BaseViewInf;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/18.
 */

public abstract class BaseActivity <P extends BasePresenter, V extends BaseViewInf> extends AppCompatActivity {

    private P mP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mP = getPresenter();
        if (mP == null) return;
        mP.attach((V)this);
    }

    protected abstract int getContentView();

    protected abstract P getPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mP == null) return;
        mP.deAttach();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEventbus(MessageEvent messageEvent) {
        switch (messageEvent.getMsgCode()) {
            case EventConst.CHECK_APP_INFO:
                AppVerInfo appinfo = (AppVerInfo) messageEvent.getObj();
                int versionCode = BuildConfig.VERSION_CODE;
                AppVerInfo.DataBean verinfo = appinfo.getData();
                if (verinfo.getAppcode() > versionCode) {//有新版
                    DownloadManager manager = DownloadManager.getInstance(this);
                    manager.setApkName(verinfo.getAppname()+".apk").setApkUrl(verinfo.getAppurl()).setSmallIcon(R.mipmap.ic_launcher)
                            .setApkDescription(verinfo.getAppinfo()).setApkVersionCode(verinfo.getAppcode()).setApkVersionName(verinfo.getAppversion())
                            .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate").download(BaseActivity.this);
                }
                break;
        }
    }
}
