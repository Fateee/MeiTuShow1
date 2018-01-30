package com.meitu.show.model;

import com.meitu.show.presenter.base.BaseModelInf;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public class HomeMeituModel implements BaseModelInf {

    private String status;

    private Content data;

    private String desc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Content getData() {
        return data;
    }

    public void setData(Content data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class Content {
        private List<DataDetail> list;

        public List<DataDetail> getList() {
            return list;
        }

        public void setList(List<DataDetail> list) {
            this.list = list;
        }

        public static class DataDetail {
            private String img;

            private String link;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
