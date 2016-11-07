package com.queqiaolove.javabean.mine;

import java.util.List;

/**
 * Created by zchk on 2016/11/4.
 */
public class UserInfoLabelListbean {

    /**
     * returnvalue : true
     * msg : 成功
     * count : 4
     * list : [{"id":"3","lame":"长腿欧巴"},{"id":"4","lame":"男神呀"},{"id":"1","lame":"帅气"},{"id":"2","lame":"阳光"}]
     */

    private String returnvalue;
    private String msg;
    private String count;
    /**
     * id : 3
     * lame : 长腿欧巴
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
        private String lame;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLame() {
            return lame;
        }

        public void setLame(String lame) {
            this.lame = lame;
        }
    }
}
