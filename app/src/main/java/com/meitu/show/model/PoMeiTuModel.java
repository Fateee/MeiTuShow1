package com.meitu.show.model;

import com.meitu.show.presenter.base.BaseModelInf;

import java.util.List;

public class PoMeiTuModel implements BaseModelInf {

    /**
     * code : 0
     * content : [{"comments":0,"count":40,"countToday":0,"cover":"ifile/201809/15372372751539124.jpg","createTime":"2018-09-18 10:27:24","hasLike":false,"height":900,"id":5059,"isNew":true,"likes":20,"name":"[YOUMI] 2018.09.03 VOL.205","nameEn":"心妍小公主 [40P]","restrict":1,"sortLast":0,"sortLikes":0,"sortNominal":0,"status":1,"type":2,"views":301,"width":600},{"comments":0,"count":44,"countToday":0,"cover":"ifile/201809/153723696861768976.jpg","createTime":"2018-09-18 10:27:23","hasLike":false,"height":900,"id":5057,"isNew":true,"likes":0,"name":"[YOUMI] 2018.08.24 VOL.203","nameEn":"刘钰儿 [44P]","restrict":1,"sortLast":0,"sortLikes":0,"sortNominal":0,"status":1,"type":2,"views":143,"width":600},{"comments":0,"count":49,"countToday":0,"cover":"ifile/201809/15372371091039659.jpg","createTime":"2018-09-18 10:27:23","hasLike":false,"height":900,"id":5058,"isNew":true,"likes":14,"name":"[YOUMI] 2018.08.29 VOL.204","nameEn":"奶瓶土肥圆 [49P]","restrict":1,"sortLast":0,"sortLikes":0,"sortNominal":0,"status":1,"type":2,"views":132,"width":600},{"comments":0,"count":44,"countToday":0,"cover":"ifile/201809/153723679998181751.jpg","createTime":"2018-09-18 10:27:22","hasLike":false,"height":900,"id":5056,"isNew":true,"likes":0,"name":"[YOUMI] 2018.08.20 VOL.202","nameEn":"SOLO-尹菲 [44P]","restrict":1,"sortLast":0,"sortLikes":0,"sortNominal":0,"status":1,"type":2,"views":101,"width":600},{"comments":0,"count":46,"countToday":0,"cover":"ifile/201809/153723665324560768.jpg","createTime":"2018-09-18 10:27:21","hasLike":false,"height":900,"id":5055,"isNew":true,"likes":2,"name":"[YOUMI] 2018.08.17 VOL.201","nameEn":"孙梦瑶V [46P]","restrict":1,"sortLast":0,"sortLikes":0,"sortNominal":0,"status":1,"type":2,"views":100,"width":600},{"comments":0,"count":55,"countToday":0,"cover":"ifile/201809/153723644204267162.jpg","createTime":"2018-09-18 10:27:15","hasLike":false,"height":900,"id":5054,"isNew":true,"likes":1,"name":"[PartyCat轰趴猫] 2018.09.02","nameEn":"NO.215 [55+1P]","restrict":1,"sortLast":0,"sortLikes":0,"sortNominal":0,"status":1,"type":2,"views":142,"width":600},{"comments":0,"count":58,"countToday":0,"cover":"ifile/201809/153723622264347649.jpg","createTime":"2018-09-18 10:27:14","hasLike":false,"height":900,"id":5053,"isNew":true,"likes":0,"name":"[PartyCat轰趴猫] 2018.09.01","nameEn":"NO.214 [58+1P]","restrict":1,"sortLast":0,"sortLikes":0,"sortNominal":0,"status":1,"type":2,"views":88,"width":600},{"comments":0,"count":98,"countToday":0,"cover":"ifile/201809/153723577208929808.jpg","createTime":"2018-09-18 10:27:13","hasLike":false,"height":900,"id":5052,"isNew":true,"likes":24,"name":"[PartyCat轰趴猫] 2018.08.31","nameEn":"NO.213 [98+1P]","restrict":1,"sortLast":0,"sortLikes":0,"sortNominal":0,"status":1,"type":2,"views":249,"width":600},{"comments":0,"count":42,"countToday":0,"cover":"ifile/201809/153723542594156622.jpg","createTime":"2018-09-18 10:27:12","hasLike":false,"height":900,"id":5050,"isNew":true,"likes":0,"name":"[PartyCat轰趴猫] 2018.08.29","nameEn":"NO.211 [42+1P]","restrict":1,"sortLast":0,"sortLikes":0,"sortNominal":0,"status":1,"type":2,"views":141,"width":600},{"comments":0,"count":42,"countToday":0,"cover":"ifile/201809/15372355797604778.jpg","createTime":"2018-09-18 10:27:12","hasLike":false,"height":900,"id":5051,"isNew":true,"likes":2,"name":"[PartyCat轰趴猫] 2018.08.30","nameEn":"NO.212 安琪拉 [42+1P]","restrict":1,"sortLast":0,"sortLikes":0,"sortNominal":0,"status":1,"type":2,"views":127,"width":600}]
     * count : 4952
     * index : 1
     * pages : 496
     * search : {}
     * size : 10
     */

    private String code;
    private int count;
    private int index;
    private int pages;
    private SearchBean search;
    private int size;
    private List<ContentBean> content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public SearchBean getSearch() {
        return search;
    }

    public void setSearch(SearchBean search) {
        this.search = search;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class SearchBean {
    }

    public static class ContentBean {
        /**
         * comments : 0
         * count : 40
         * countToday : 0
         * cover : ifile/201809/15372372751539124.jpg
         * createTime : 2018-09-18 10:27:24
         * hasLike : false
         * height : 900
         * id : 5059
         * isNew : true
         * likes : 20
         * name : [YOUMI] 2018.09.03 VOL.205
         * nameEn : 心妍小公主 [40P]
         * restrict : 1
         * sortLast : 0
         * sortLikes : 0
         * sortNominal : 0
         * status : 1
         * type : 2
         * views : 301
         * width : 600
         */

        private int comments;
        private int count;
        private int countToday;
        private String cover;
        private String createTime;
        private boolean hasLike;
        private int height;
        private int id;
        private boolean isNew;
        private int likes;
        private String name;
        private String nameEn;
        private int restrict;
        private int sortLast;
        private int sortLikes;
        private int sortNominal;
        private int status;
        private int type;
        private int views;
        private int width;

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCountToday() {
            return countToday;
        }

        public void setCountToday(int countToday) {
            this.countToday = countToday;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isHasLike() {
            return hasLike;
        }

        public void setHasLike(boolean hasLike) {
            this.hasLike = hasLike;
        }

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

        public boolean isIsNew() {
            return isNew;
        }

        public void setIsNew(boolean isNew) {
            this.isNew = isNew;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public int getRestrict() {
            return restrict;
        }

        public void setRestrict(int restrict) {
            this.restrict = restrict;
        }

        public int getSortLast() {
            return sortLast;
        }

        public void setSortLast(int sortLast) {
            this.sortLast = sortLast;
        }

        public int getSortLikes() {
            return sortLikes;
        }

        public void setSortLikes(int sortLikes) {
            this.sortLikes = sortLikes;
        }

        public int getSortNominal() {
            return sortNominal;
        }

        public void setSortNominal(int sortNominal) {
            this.sortNominal = sortNominal;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
