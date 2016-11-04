package com.queqiaolove.javabean.find;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zchk on 2016/11/4.
 */
public class MakemakingActivityListBean implements Serializable{


    /**
     * returnvalue : true
     * msg : 成功
     * count : 3
     * list : [{"id":"3","apic":"http://182.92.213.51:1070//activityimg/161102124940366302_s.jpg","atitle":"恒大华府携手内蒙古新闻综合频道《有事您说话》","end_date":"2016/11/29 11:58:09","daydiff":"24","province":"内蒙古自治区","city":"赤峰市","address":"","watch_num":"6","participant_num":"1","like_num":"1","if_like":"0"},{"id":"1","apic":"http://182.92.213.51:1070//activityimg/161102105945733747_s.jpg","atitle":"关于开展\u201c我们的节日·爱在七夕\u201d","end_date":"2016/11/30 20:42:39","daydiff":"26","province":"北京市","city":"东城区","address":"","watch_num":"31","participant_num":"7","like_num":"5","if_like":"0"},{"id":"2","apic":"http://182.92.213.51:1070//activityimg/161102115314418082_s.jpg","atitle":"\u201c面朝大海，缘来相爱\u201d大型相亲交友活动你来了吗？","end_date":"2016/11/15 20:46:45","daydiff":"11","province":"甘肃省","city":"定西市","address":"","watch_num":"29","participant_num":"2","like_num":"4","if_like":"0"}]
     */

    private String returnvalue;
    private String msg;
    private String count;
    /**
     * id : 3
     * apic : http://182.92.213.51:1070//activityimg/161102124940366302_s.jpg
     * atitle : 恒大华府携手内蒙古新闻综合频道《有事您说话》
     * end_date : 2016/11/29 11:58:09
     * daydiff : 24
     * province : 内蒙古自治区
     * city : 赤峰市
     * address :
     * watch_num : 6
     * participant_num : 1
     * like_num : 1
     * if_like : 0
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
        private String apic;
        private String atitle;
        private String end_date;
        private String daydiff;
        private String province;
        private String city;
        private String address;
        private String watch_num;
        private String participant_num;
        private String like_num;
        private String if_like;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApic() {
            return apic;
        }

        public void setApic(String apic) {
            this.apic = apic;
        }

        public String getAtitle() {
            return atitle;
        }

        public void setAtitle(String atitle) {
            this.atitle = atitle;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getDaydiff() {
            return daydiff;
        }

        public void setDaydiff(String daydiff) {
            this.daydiff = daydiff;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getIf_like() {
            return if_like;
        }

        public void setIf_like(String if_like) {
            this.if_like = if_like;
        }
    }
}
