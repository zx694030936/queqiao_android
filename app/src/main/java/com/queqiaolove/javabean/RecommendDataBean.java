package com.queqiaolove.javabean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zchk on 2016/11/3.
 */
public class RecommendDataBean implements Serializable{


    /**
     * returnvalue : true
     * msg : 成功
     * pczb_list : [{"id":"66","zhibo_fm_pic":"http://182.92.213.51:1070//img/defaut_live.png","btitle":"wq dfsa","saytitle":"LOL话题?","if_open":"1","isend":"0","username":"","city":"","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_53","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_53.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_53.m3u8","groupid":""},{"id":"55","zhibo_fm_pic":"http://182.92.213.51:1070//img/defaut_live.png","btitle":"达到啊","saytitle":"LOL话题?","if_open":"1","isend":"0","username":"","city":"","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_11","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_11.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_11.m3u8","groupid":""},{"id":"72","zhibo_fm_pic":"http://182.92.213.51:1070//img/defaut_live.png","btitle":"我在pc做直播","saytitle":"LOL话题?","if_open":"1","isend":"1","username":"","city":"","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_5","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_5.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_5.m3u8","groupid":""},{"id":"61","zhibo_fm_pic":"http://182.92.213.51:1070//img/defaut_live.png","btitle":"苦逼的一天","saytitle":"LOL话题?","if_open":"1","isend":"1","username":"joker","city":"昌平区","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_7","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_7.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_7.m3u8","groupid":"261668101832049072"}]
     * appzb_list : [{"id":"54","zhibo_fm_pic":"http://182.92.213.51:1070//img/1.png","btitle":"诶我去额为","saytitle":"#好身材show出来#","if_open":"1","isend":"0","username":"zy","city":"朝阳区","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_2","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_2.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_2.m3u8","groupid":"261681169349740100"},{"id":"58","zhibo_fm_pic":"http://182.92.213.51:1070//album_img/1611/161102092534469253_s.png","btitle":"基督教世界","saytitle":"#好身材show出来#","if_open":"2","isend":"0","username":"小武","city":"","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_4","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_4.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_4.m3u8","groupid":""},{"id":"73","zhibo_fm_pic":"http://182.92.213.51:1070//img/defaut_live.png","btitle":"好尴尬","saytitle":"#新人求关注#","if_open":"1","isend":"1","username":"wd","city":"","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_29","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_29.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_29.m3u8","groupid":"262038184244806056"},{"id":"70","zhibo_fm_pic":"http://182.92.213.51:1070//img/defaut_live.png","btitle":"直播","saytitle":"#新人求关注#","if_open":"1","isend":"1","username":"张大哥","city":"","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_31","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_31.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_31.m3u8","groupid":"261871495989429828"},{"id":"71","zhibo_fm_pic":"http://182.92.213.51:1070//img/defaut_live.png","btitle":"北京故宫","saytitle":"#秀恩爱#","if_open":"1","isend":"1","username":"杨大妈","city":"","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_30","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_30.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_30.m3u8","groupid":"261866188450038184"},{"id":"67","zhibo_fm_pic":"http://182.92.213.51:1070//img/defaut_live.png","btitle":"测试","saytitle":"#新人求关注#","if_open":"1","isend":"1","username":"伟伟","city":"","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_18","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_18.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_18.m3u8","groupid":"261762821635703212"}]
     * hdzb_list : [{"id":"65","zhibo_fm_pic":"http://182.92.213.51:1070//album_img/1611/161102092534469253_s.png","btitle":"帅哥直播","saytitle":"#好身材show出来#","if_open":"1","isend":"0","username":"刘美女","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_15","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_15.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_15.m3u8","groupid":""},{"id":"63","zhibo_fm_pic":"http://182.92.213.51:1070//album_img/1611/161102092534469253_s.png","btitle":"觉得基督教世界","saytitle":"#好身材show出来#","if_open":"2","isend":"1","username":"","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_14","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_14.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_14.m3u8","groupid":""},{"id":"62","zhibo_fm_pic":"http://182.92.213.51:1070//img/1.png","btitle":"搜索一下","saytitle":"#我颜值高#","if_open":"1","isend":"1","username":"","watch_num":"0","play_rtmp":"rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_13","play_flv":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_13.flv","play_hls":"http://4971.liveplay.myqcloud.com/live/4971_queqiao_13.m3u8","groupid":""}]
     * hdsp_list : [{"id":"1","atitle":"关于开展\u201c我们的节日·爱在七夕\u201d","video_pic":"http://182.92.213.51:1070//activityimg/161102105945733747_s.jpg","watch_num":"67","video_url":"http://4971.liveplay.myqcloud.com/live/"}]
     * hdyg_list : [{"id":"1","atitle":"关于开展\u201c我们的节日·爱在七夕\u201d","trailer_pic":"http://182.92.213.51:1070//activityimg/161102105945733747_s.jpg","city":"东城区","watch_num":"67","participant_num":"10","like_num":"5","daydiff":"22"}]
     */

    private String returnvalue;
    private String msg;
    /**
     * id : 66
     * zhibo_fm_pic : http://182.92.213.51:1070//img/defaut_live.png
     * btitle : wq dfsa
     * saytitle : LOL话题?
     * if_open : 1
     * isend : 0
     * username :
     * city :
     * watch_num : 0
     * play_rtmp : rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_53
     * play_flv : http://4971.liveplay.myqcloud.com/live/4971_queqiao_53.flv
     * play_hls : http://4971.liveplay.myqcloud.com/live/4971_queqiao_53.m3u8
     * groupid :
     */

    private List<PczbListBean> pczb_list;
    /**
     * id : 54
     * zhibo_fm_pic : http://182.92.213.51:1070//img/1.png
     * btitle : 诶我去额为
     * saytitle : #好身材show出来#
     * if_open : 1
     * isend : 0
     * username : zy
     * city : 朝阳区
     * watch_num : 0
     * play_rtmp : rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_2
     * play_flv : http://4971.liveplay.myqcloud.com/live/4971_queqiao_2.flv
     * play_hls : http://4971.liveplay.myqcloud.com/live/4971_queqiao_2.m3u8
     * groupid : 261681169349740100
     */

    private List<AppzbListBean> appzb_list;
    /**
     * id : 65
     * zhibo_fm_pic : http://182.92.213.51:1070//album_img/1611/161102092534469253_s.png
     * btitle : 帅哥直播
     * saytitle : #好身材show出来#
     * if_open : 1
     * isend : 0
     * username : 刘美女
     * watch_num : 0
     * play_rtmp : rtmp://4971.liveplay.myqcloud.com/live/4971_queqiao_15
     * play_flv : http://4971.liveplay.myqcloud.com/live/4971_queqiao_15.flv
     * play_hls : http://4971.liveplay.myqcloud.com/live/4971_queqiao_15.m3u8
     * groupid :
     */

    private List<HdzbListBean> hdzb_list;
    /**
     * id : 1
     * atitle : 关于开展“我们的节日·爱在七夕”
     * video_pic : http://182.92.213.51:1070//activityimg/161102105945733747_s.jpg
     * watch_num : 67
     * video_url : http://4971.liveplay.myqcloud.com/live/
     */

    private List<HdspListBean> hdsp_list;
    /**
     * id : 1
     * atitle : 关于开展“我们的节日·爱在七夕”
     * trailer_pic : http://182.92.213.51:1070//activityimg/161102105945733747_s.jpg
     * city : 东城区
     * watch_num : 67
     * participant_num : 10
     * like_num : 5
     * daydiff : 22
     */

    private List<HdygListBean> hdyg_list;

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

    public List<PczbListBean> getPczb_list() {
        return pczb_list;
    }

    public void setPczb_list(List<PczbListBean> pczb_list) {
        this.pczb_list = pczb_list;
    }

    public List<AppzbListBean> getAppzb_list() {
        return appzb_list;
    }

    public void setAppzb_list(List<AppzbListBean> appzb_list) {
        this.appzb_list = appzb_list;
    }

    public List<HdzbListBean> getHdzb_list() {
        return hdzb_list;
    }

    public void setHdzb_list(List<HdzbListBean> hdzb_list) {
        this.hdzb_list = hdzb_list;
    }

    public List<HdspListBean> getHdsp_list() {
        return hdsp_list;
    }

    public void setHdsp_list(List<HdspListBean> hdsp_list) {
        this.hdsp_list = hdsp_list;
    }

    public List<HdygListBean> getHdyg_list() {
        return hdyg_list;
    }

    public void setHdyg_list(List<HdygListBean> hdyg_list) {
        this.hdyg_list = hdyg_list;
    }

    public static class PczbListBean implements Serializable{
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

    public static class AppzbListBean {
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

    public static class HdzbListBean {
        private String id;
        private String zhibo_fm_pic;
        private String btitle;
        private String saytitle;
        private String if_open;
        private String isend;
        private String username;
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

    public static class HdspListBean {
        private String id;
        private String atitle;
        private String video_pic;
        private String watch_num;
        private String video_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtitle() {
            return atitle;
        }

        public void setAtitle(String atitle) {
            this.atitle = atitle;
        }

        public String getVideo_pic() {
            return video_pic;
        }

        public void setVideo_pic(String video_pic) {
            this.video_pic = video_pic;
        }

        public String getWatch_num() {
            return watch_num;
        }

        public void setWatch_num(String watch_num) {
            this.watch_num = watch_num;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }
    }

    public static class HdygListBean {
        private String id;
        private String atitle;
        private String trailer_pic;
        private String city;
        private String watch_num;
        private String participant_num;
        private String like_num;
        private String daydiff;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtitle() {
            return atitle;
        }

        public void setAtitle(String atitle) {
            this.atitle = atitle;
        }

        public String getTrailer_pic() {
            return trailer_pic;
        }

        public void setTrailer_pic(String trailer_pic) {
            this.trailer_pic = trailer_pic;
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

        public String getParticipant_num() {
            return participant_num;
        }

        public void setParticipant_num(String participant_num) {
            this.participant_num = participant_num;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getDaydiff() {
            return daydiff;
        }

        public void setDaydiff(String daydiff) {
            this.daydiff = daydiff;
        }
    }
}
