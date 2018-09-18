package com.meitu.show.model;

import com.meitu.show.presenter.base.BaseModelInf;

import java.io.Serializable;
import java.util.List;

public class PoProlistModel implements BaseModelInf,Serializable {
    /**
     * code : 0
     * content : [{"height":1500,"id":248473,"sort":0,"url":"ifile/201809/15372373054523019.jpg","width":1000},{"height":1500,"id":248474,"sort":0,"url":"ifile/201809/153723730633095207.jpg","width":1000},{"height":1500,"id":248475,"sort":0,"url":"ifile/201809/15372373071867130.jpg","width":1000},{"height":1500,"id":248476,"sort":0,"url":"ifile/201809/15372373080485296.jpg","width":1000},{"height":1500,"id":248477,"sort":0,"url":"ifile/201809/153723730890359144.jpg","width":1000},{"height":1500,"id":248478,"sort":0,"url":"ifile/201809/153723730977073810.jpg","width":1000},{"height":1500,"id":248479,"sort":0,"url":"ifile/201809/153723731063585910.jpg","width":1000},{"height":1500,"id":248480,"sort":0,"url":"ifile/201809/153723731150027235.jpg","width":1000},{"height":1500,"id":248481,"sort":0,"url":"ifile/201809/153723731238036650.jpg","width":1000},{"height":1500,"id":248482,"sort":0,"url":"ifile/201809/153723731323543194.jpg","width":1000},{"height":1500,"id":248483,"sort":0,"url":"ifile/201809/15372373140995570.jpg","width":1000},{"height":1500,"id":248484,"sort":0,"url":"ifile/201809/153723731494922553.jpg","width":1000},{"height":1500,"id":248485,"sort":0,"url":"ifile/201809/153723731579594548.jpg","width":1000},{"height":1500,"id":248486,"sort":0,"url":"ifile/201809/153723731665161350.jpg","width":1000},{"height":1500,"id":248487,"sort":0,"url":"ifile/201809/153723731749789781.jpg","width":1000},{"height":1500,"id":248488,"sort":0,"url":"ifile/201809/153723731835733607.jpg","width":1000},{"height":1500,"id":248489,"sort":0,"url":"ifile/201809/153723731924370376.jpg","width":1000},{"height":1500,"id":248490,"sort":0,"url":"ifile/201809/153723732013295403.jpg","width":1000},{"height":1500,"id":248491,"sort":0,"url":"ifile/201809/153723732101874827.jpg","width":1000},{"height":1500,"id":248492,"sort":0,"url":"ifile/201809/153723732190738393.jpg","width":1000},{"height":1500,"id":248493,"sort":0,"url":"ifile/201809/153723732279677365.jpg","width":1000},{"height":1500,"id":248494,"sort":0,"url":"ifile/201809/153723732369329195.jpg","width":1000},{"height":1500,"id":248495,"sort":0,"url":"ifile/201809/153723732458231456.jpg","width":1000},{"height":1500,"id":248496,"sort":0,"url":"ifile/201809/153723732548413500.jpg","width":1000},{"height":1500,"id":248497,"sort":0,"url":"ifile/201809/15372373263849756.jpg","width":1000},{"height":1500,"id":248498,"sort":0,"url":"ifile/201809/153723732726183949.jpg","width":1000},{"height":1500,"id":248499,"sort":0,"url":"ifile/201809/153723732813784333.jpg","width":1000},{"height":1500,"id":248500,"sort":0,"url":"ifile/201809/153723732904267477.jpg","width":1000},{"height":1500,"id":248501,"sort":0,"url":"ifile/201809/153723732992893806.jpg","width":1000},{"height":1500,"id":248502,"sort":0,"url":"ifile/201809/153723733081889262.jpg","width":1000},{"height":1500,"id":248503,"sort":0,"url":"ifile/201809/153723733171739448.jpg","width":1000},{"height":1500,"id":248504,"sort":0,"url":"ifile/201809/153723733261370014.jpg","width":1000},{"height":1500,"id":248505,"sort":0,"url":"ifile/201809/153723733352519512.jpg","width":1000},{"height":1500,"id":248506,"sort":0,"url":"ifile/201809/153723733442871325.jpg","width":1000},{"height":1500,"id":248507,"sort":0,"url":"ifile/201809/153723733534151255.jpg","width":1000},{"height":1500,"id":248508,"sort":0,"url":"ifile/201809/153723733624785932.jpg","width":1000},{"height":1500,"id":248509,"sort":0,"url":"ifile/201809/153723733715861009.jpg","width":1000},{"height":1500,"id":248510,"sort":0,"url":"ifile/201809/153723733806946279.jpg","width":1000},{"height":1500,"id":248511,"sort":0,"url":"ifile/201809/153723733922424738.jpg","width":1000},{"height":1067,"id":248512,"sort":0,"url":"ifile/201809/153723735398686866.jpg","width":1600}]
     * msg : 不是会员或未登录
     */

    private String code;
    private String msg;
    private List<ContentBean> content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean implements Serializable {
        /**
         * height : 1500
         * id : 248473
         * sort : 0
         * url : ifile/201809/15372373054523019.jpg
         * width : 1000
         */

        private int height;
        private int id;
        private int sort;
        private String url;
        private int width;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
