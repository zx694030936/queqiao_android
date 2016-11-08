package com.queqiaolove.javabean.mine;

import java.util.List;

/**
 * Created by zchk on 2016/11/8.
 */
public class MyPhotoListBean {

    /**
     * returnvalue : true
     * msg : 成功
     * count : 4
     * list : [{"id":"37","upic":"http://182.92.213.51:1070//album_img/1611/161108180043935132_s.jpg","zhibo_fm":"0"},{"id":"36","upic":"http://182.92.213.51:1070//album_img/1611/161108180004966388_s.jpg","zhibo_fm":"0"},{"id":"35","upic":"http://182.92.213.51:1070//album_img/1611/161108175839825445_s.jpg","zhibo_fm":"0"},{"id":"34","upic":"http://182.92.213.51:1070//album_img/1611/161108175339011067_s.jpg","zhibo_fm":"0"}]
     */

    private String returnvalue;
    private String msg;
    private String count;
    /**
     * id : 37
     * upic : http://182.92.213.51:1070//album_img/1611/161108180043935132_s.jpg
     * zhibo_fm : 0
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
        private String id;
        private String upic;
        private String zhibo_fm;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpic() {
            return upic;
        }

        public void setUpic(String upic) {
            this.upic = upic;
        }

        public String getZhibo_fm() {
            return zhibo_fm;
        }

        public void setZhibo_fm(String zhibo_fm) {
            this.zhibo_fm = zhibo_fm;
        }
    }
}
