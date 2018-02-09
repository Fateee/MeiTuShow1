package com.meitu.show.model;

import com.meitu.show.presenter.base.BaseModelInf;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/4.
 */

public class ProlistModel implements BaseModelInf,Serializable {
//    {"status":"OK",
//            "data":{
//        "list":[
//                {"img":"http://p.tao1o.com/k/1178/T/XiuRen/917/917_016_f6c_5400_3600.jpg"},
//        {"img":"http://p.tao1o.com/k/1178/T/XiuRen/917/917_017_chb_3600_5400.jpg"},
//        {"img":"http://p.tao1o.com/k/1178/T/XiuRen/917/917_018_2g0_3600_5400.jpg"},
//        {"img":"http://p.tao1o.com/k/1178/T/XiuRen/917/917_019_6ot_3600_5400.jpg"},
//        {"img":"http://p.tao1o.com/k/1178/T/XiuRen/917/917_020_2hn_3600_5400.jpg"}],
//        "total":5},
//        "desc":"OK"}
    private String status;

    private ProlistContent data;

    private String desc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProlistContent getData() {
        return data;
    }

    public void setData(ProlistContent data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class ProlistContent implements Serializable {
        private List<ProlistModel.ProlistContent.DataDetail> list;

        private int total;

        public List<ProlistModel.ProlistContent.DataDetail> getList() {
            return list;
        }

        public void setList(List<ProlistModel.ProlistContent.DataDetail> list) {
            this.list = list;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public static class DataDetail implements Serializable{
            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
