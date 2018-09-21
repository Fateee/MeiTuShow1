package com.meitu.show.model.version;

import com.meitu.show.presenter.base.BaseModelInf;

public class AppVerInfo implements BaseModelInf {
    /**
     * code : 0
     * data : {"appcode":100,"appinfo":"第一版","appname":"魅图秀","appurl":"http://118.24.14.150/newestapp/meipic/poshow.apk","appversion":"1.0.0","lastForce":0}
     * msg : success
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * appcode : 100
         * appinfo : 第一版
         * appname : 魅图秀
         * appurl : http://118.24.14.150/newestapp/meipic/poshow.apk
         * appversion : 1.0.0
         * lastForce : 0
         */

        private int appcode;
        private String appinfo;
        private String appname;
        private String appurl;
        private String appversion;
        private int lastForce;

        public int getAppcode() {
            return appcode;
        }

        public void setAppcode(int appcode) {
            this.appcode = appcode;
        }

        public String getAppinfo() {
            return appinfo;
        }

        public void setAppinfo(String appinfo) {
            this.appinfo = appinfo;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getAppurl() {
            return appurl;
        }

        public void setAppurl(String appurl) {
            this.appurl = appurl;
        }

        public String getAppversion() {
            return appversion;
        }

        public void setAppversion(String appversion) {
            this.appversion = appversion;
        }

        public int getLastForce() {
            return lastForce;
        }

        public void setLastForce(int lastForce) {
            this.lastForce = lastForce;
        }
    }
}
