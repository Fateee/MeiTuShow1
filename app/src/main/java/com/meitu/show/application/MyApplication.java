package com.meitu.show.application;

import android.app.Application;
import android.util.Log;

import com.mob.MobSDK;
import com.payelves.sdk.EPay;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "d6c985d1eaab4382277fc23f0c6fc9b1");
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e(TAG, deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e(TAG, "onFailure : " + s);
            }
        });
        MobSDK.init(this, "281534c95cfa5", "222140dbc8142f3c5138d7dc54bbe582");
        EPay.getInstance(getApplicationContext()).init("w8MI3Kl5T", "22d83e9f590b42ee906a6d97e67a4355",
                "7017884946464769", "xiaomi");
    }
}
