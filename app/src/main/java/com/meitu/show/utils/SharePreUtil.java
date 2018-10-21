package com.meitu.show.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 项目名:    AppUpdate
 * 包名       com.azhon.appupdate.utils
 * 文件名:    SharePreUtil
 * 创建时间:  2018/1/27 on 16:56
 * 描述:     TODO 文件存储
 *
 * @author 阿钟
 */


public final class SharePreUtil {

    /**
     * 配置文件，文件名
     */
    private static final String SHARE_NAME = "app_update";
    private final String USER_INFO = "user_info";
    /**
     * 存字符串
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   值
     */
    public static void putString(Context mContext, String key, String values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, values).commit();
    }


    /**
     * 取字符串
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   默认值
     * @return 取出的值
     */
    public static String getString(Context mContext, String key, String values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, values);
    }


    /**
     * 存布尔值
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   值
     */
    public static void putBoolean(Context mContext, String key, boolean values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, values).commit();
    }

    /**
     * 取布尔值
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   默认值
     * @return true/false
     */
    public static boolean getBoolean(Context mContext, String key, boolean values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, values);
    }

    /**
     * 存int值
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   值
     */
    public static void putInt(Context mContext, String key, int values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, values).commit();
    }

    /**
     * 取int值
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   默认值
     * @return
     */
    public static int getInt(Context mContext, String key, int values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, values);
    }

    /**
     * 删除一条字段
     *
     * @param mContext 上下文
     * @param key      键
     */
    public static void deleShare(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        //单个清理
        sp.edit().remove(key).commit();
    }

    /**
     * 删除全部数据
     *
     * @param mContext 上下文
     */
    public static void deleShareAll(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        //全部清理
        sp.edit().clear().commit();
    }

    /**
     * 查看SharedPreferences的内容
     */
    public static String lookSharePre(Context context) {
        try {
            FileInputStream stream = new FileInputStream(new File("/data/data/" +
                    context.getPackageName() + "/shared_prefs", SHARE_NAME + ".xml"));
            BufferedReader bff = new BufferedReader(new InputStreamReader(stream));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bff.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "未找到当前配置文件！";
    }

    private SharedPreferences sharedPreferences;
    /*
     * 保存手机里面的名字
     */private SharedPreferences.Editor editor;

    private SharePreUtil(Context context,String FILE_NAME) {
        sharedPreferences = context.getSharedPreferences(USER_INFO,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private SharePreUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_INFO,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private static SharePreUtil mSharePreUtil;

    public static SharePreUtil getInstance(Context context) {
        synchronized (mSharePreUtil) {
            if (mSharePreUtil == null) {
                mSharePreUtil = new SharePreUtil(context);
            }
        }
        return mSharePreUtil;
    }
    /**
     * 存储
     */
    public void put(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 获取保存的数据
     */
    public Object getSharedPreference(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }

    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     */
    public Boolean contain(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
}