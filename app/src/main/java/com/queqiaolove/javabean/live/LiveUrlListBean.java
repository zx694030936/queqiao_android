package com.queqiaolove.javabean.live;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LENOVO on 2016/10/24.
 */
public class LiveUrlListBean {


    /**
     * returnvalue : true
     * msg : 成功
     * count : 5
     * list : [{"id":"61","zhibo_fm_pic":"http://182.92.213.51:1070//img/5.png","btitle":"喜欢美食的进","saytitle":"#萌妹子#","if_open":"1","isend":"1","username":"","city":"","watch_num":"0","play_rtmp":"","play_flv":"","play_hls":"","groupid":""},{"id":"60","zhibo_fm_pic":"http://182.92.213.51:1070//img/4.png","btitle":"哥哥求带","saytitle":"#不坑不送#","if_open":"1","isend":"1","username":"","city":"","watch_num":"0","play_rtmp":"","play_flv":"","play_hls":"","groupid":""},{"id":"58","zhibo_fm_pic":"http://182.92.213.51:1070//img/2.png","btitle":"帅哥来了","saytitle":"#帅哥#","if_open":"1","isend":"1","username":"小武","city":"昌平区","watch_num":"0","play_rtmp":"","play_flv":"","play_hls":"","groupid":""},{"id":"59","zhibo_fm_pic":"http://182.92.213.51:1070//img/3.png","btitle":"歌神","saytitle":"#清新可爱#","if_open":"1","isend":"1","username":"","city":"","watch_num":"0","play_rtmp":"","play_flv":"","play_hls":"","groupid":""},{"id":"57","zhibo_fm_pic":"http://182.92.213.51:1070//img/1.png","btitle":"快来看看","saytitle":"#女神#","if_open":"1","isend":"1","username":"zy1","city":"朝阳区","watch_num":"0","play_rtmp":"","play_flv":"","play_hls":"","groupid":""}]
     */

    private String returnvalue;
    private String msg;
    private String count;
    /**
     * id : 61
     * zhibo_fm_pic : http://182.92.213.51:1070//img/5.png
     * btitle : 喜欢美食的进
     * saytitle : #萌妹子#
     * if_open : 1
     * isend : 1
     * username :
     * city :
     * watch_num : 0
     * play_rtmp :
     * play_flv :
     * play_hls :
     * groupid :
     */

    private List<ListBean> list;

    public String getReturnvalue() {
        return returnvalue;
    }

    public void setReturnvalue(String returnvalue) {
        this.returnvalue = returnvalue;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        private String id;
        private String zhibo_fm_pic;
        private String btitle;
        private String saytitle;
        private String if_open;
        private String isend;
        private String username;
        private String city;
        private String watch_num;
        private String play_rtmp;
        private String play_flv;
        private String play_hls;
        private String groupid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getZhibo_fm_pic() {
            return zhibo_fm_pic;
        }

        public void setZhibo_fm_pic(String zhibo_fm_pic) {
            this.zhibo_fm_pic = zhibo_fm_pic;
        }

        public String getBtitle() {
            return btitle;
        }

        public void setBtitle(String btitle) {
            this.btitle = btitle;
        }

        public String getSaytitle() {
            return saytitle;
        }

        public void setSaytitle(String saytitle) {
            this.saytitle = saytitle;
        }

        public String getIf_open() {
            return if_open;
        }

        public void setIf_open(String if_open) {
            this.if_open = if_open;
        }

        public String getIsend() {
            return isend;
        }

        public void setIsend(String isend) {
            this.isend = isend;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getWatch_num() {
            return watch_num;
        }

        public void setWatch_num(String watch_num) {
            this.watch_num = watch_num;
        }

        public String getPlay_rtmp() {
            return play_rtmp;
        }

        public void setPlay_rtmp(String play_rtmp) {
            this.play_rtmp = play_rtmp;
        }

        public String getPlay_flv() {
            return play_flv;
        }

        public void setPlay_flv(String play_flv) {
            this.play_flv = play_flv;
        }

        public String getPlay_hls() {
            return play_hls;
        }

        public void setPlay_hls(String play_hls) {
            this.play_hls = play_hls;
        }

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }
    }
}
