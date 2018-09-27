package com.meitu.show.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * 项目名:    AppUpdate
 * 包名       com.azhon.appupdate.utils
 * 文件名:    ScreenUtil
 * 创建时间:  2018/1/30 on 16:13
 * 描述:     TODO 获取屏幕 宽 高
 *
 * @author 阿钟
 */


public final class ScreenUtil {

    /**
     * 获取屏幕宽度（像素）
     *
     * @param context 上下文
     * @return px
     */
    public static int getWith(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度（像素）
     *
     * @param context 上下文
     * @return px
     */
    public static int getHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
