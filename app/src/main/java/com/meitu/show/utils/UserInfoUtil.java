package com.meitu.show.utils;

import android.content.Context;

import com.meitu.show.model.RegisterModel;

public class UserInfoUtil {

    public static final int NOT_LOGIN = 224;
    public static final int IS_VIP = 240;
    public static final int NOT_VIP = 91;

    //0 未登录 1 不是vip 2 是vip
    public static int getUserStatus(Context context) {
        SharePreUtil mSharePreUtil = SharePreUtil.getInstance(context);
        RegisterModel.DataBean userinfo = mSharePreUtil.getUserInfo();
        //点击先判断是否登录 登录了就判断是否是vip 否则就进入登录界面
        if (userinfo == null) {
            return NOT_LOGIN;
        } else {
            if (userinfo.isVip()) {
                return IS_VIP;
            } else {
                return NOT_VIP;
            }
        }
    }

    public static boolean isUserLogin(Context context) {
        return getUserStatus(context) != NOT_LOGIN;
    }

    public static boolean isUserVip(Context context) {
        return getUserStatus(context) == IS_VIP;
    }
}
