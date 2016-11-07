package com.queqiaolove.javabean.find;

import java.util.List;

/**
 * Created by zchk on 2016/11/7.
 */
public class ILikeWhoListBean {



    /**
     * returnvalue : true
     * msg : true
     * count : 1
     * countall : 1
     * list : [{"userid":2,"pic":"","indbdate":"","step":"1"}]
     */

    private String returnvalue;
    private String msg;
    private String count;
    private String countall;
    /**
     * userid : 2
     * pic :
     * indbdate :
     * step : 1
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

    public String getCountall() {
        return countall;
    }

    public void setCountall(String countall) {
        this.countall = countall;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int userid;
        private String pic;
        private String indbdate;
        private String step;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getIndbdate() {
            return indbdate;
        }

        public void setIndbdate(String indbdate) {
            this.indbdate = indbdate;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }
    }
}
