package com.meitu.show.model;

import com.meitu.show.presenter.base.BaseModelInf;

public class RegisterModel implements BaseModelInf {
    /**
     * code : 200
     * data : {"creatdate":"2018-10-18 14:59:55","overdate":"2018-10-18 15:05:55","phone":"18501516535","vip":true}
     * msg : Login success.
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
         * creatdate : 2018-10-18 14:59:55
         * overdate : 2018-10-18 15:05:55
         * phone : 18501516535
         * vip : true
         */

        private String creatdate;
        private String overdate;
        private String phone;
        private boolean vip;

        public DataBean(String creatdate, String overdate, String phone, boolean vip) {
            this.creatdate = creatdate;
            this.overdate = overdate;
            this.phone = phone;
            this.vip = vip;
        }

        public String getCreatdate() {
            return creatdate;
        }

        public void setCreatdate(String creatdate) {
            this.creatdate = creatdate;
        }

        public String getOverdate() {
            return overdate;
        }

        public void setOverdate(String overdate) {
            this.overdate = overdate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }
    }
}
