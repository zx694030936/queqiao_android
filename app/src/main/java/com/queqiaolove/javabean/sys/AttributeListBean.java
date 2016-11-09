package com.queqiaolove.javabean.sys;

import java.util.List;

/**
 * Created by zchk on 2016/11/9.
 */
public class AttributeListBean {

    /**
     * returnvalue : true
     * msg : 成功
     * count : 2
     * list : [{"acode":"10101","aname":"汉族"},{"acode":"10102","aname":"苗族"}]
     */

    private String returnvalue;
    private String msg;
    private String count;
    /**
     * acode : 10101
     * aname : 汉族
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
        private String acode;
        private String aname;

        public String getAcode() {
            return acode;
        }

        public void setAcode(String acode) {
            this.acode = acode;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }
    }
}
