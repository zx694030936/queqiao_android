package com.queqiaolove.javabean.main;

import java.util.List;

/**
 * Created by zchk on 2016/11/11.
 */
public class PhotoListBean {

    /**
     * returnvalue : true
     * msg : 成功
     * count : 3
     * list : [{"userid":"23","username":"1","upic":"http://182.92.213.51:1070//album_img/1611/161108132652136075_s.jpg","step":"1","integrity_degree":"50%","city":"昌平区","myheight":"178","age":"20","distance":"0米"},{"userid":"7","username":"joker","upic":"http://182.92.213.51:1070//album_img/161108210701735116_s.jpg","step":"1","integrity_degree":"50%","city":"昌平区","myheight":"178","age":"24","distance":"0米"},{"userid":"2","username":"zy","upic":"http://182.92.213.51:1070//album_img/1611/161108232733096485_s.jpg","step":"1","integrity_degree":"80%","city":"朝阳区","myheight":"160","age":"34","distance":"12233.0公里"}]
     */

    private String returnvalue;
    private String msg;
    private String count;
    /**
     * userid : 23
     * username : 1
     * upic : http://182.92.213.51:1070//album_img/1611/161108132652136075_s.jpg
     * step : 1
     * integrity_degree : 50%
     * city : 昌平区
     * myheight : 178
     * age : 20
     * distance : 0米
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

    public static class ListBean {
        private String userid;
        private String username;
        private String upic;
        private String step;
        private String integrity_degree;
        private String city;
        private String myheight;
        private String age;
        private String distance;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUpic() {
            return upic;
        }

        public void setUpic(String upic) {
            this.upic = upic;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }

        public String getIntegrity_degree() {
            return integrity_degree;
        }

        public void setIntegrity_degree(String integrity_degree) {
            this.integrity_degree = integrity_degree;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getMyheight() {
            return myheight;
        }

        public void setMyheight(String myheight) {
            this.myheight = myheight;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }
}
