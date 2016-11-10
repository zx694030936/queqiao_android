package com.queqiaolove.javabean.sys;

import java.util.List;

/**
 * Created by zchk on 2016/11/10.
 */
public class CityListBean {

    /**
     * returnvalue : true
     * msg : true
     * count : 14
     * list : [{"city_id":"208","city_name":"鄂州市","pid":"17","pname":"湖北省"},{"city_id":"215","city_name":"恩施土家族苗族自治州","pid":"17","pname":"湖北省"},{"city_id":"212","city_name":"黄冈市","pid":"17","pname":"湖北省"},{"city_id":"204","city_name":"黄石市","pid":"17","pname":"湖北省"},{"city_id":"209","city_name":"荆门市","pid":"17","pname":"湖北省"},{"city_id":"211","city_name":"荆州市","pid":"17","pname":"湖北省"},{"city_id":"216","city_name":"神农架","pid":"17","pname":"湖北省"},{"city_id":"205","city_name":"十堰市","pid":"17","pname":"湖北省"},{"city_id":"214","city_name":"随州市","pid":"17","pname":"湖北省"},{"city_id":"203","city_name":"武汉市","pid":"17","pname":"湖北省"},{"city_id":"213","city_name":"咸宁市","pid":"17","pname":"湖北省"},{"city_id":"207","city_name":"襄樊市","pid":"17","pname":"湖北省"},{"city_id":"210","city_name":"孝感市","pid":"17","pname":"湖北省"},{"city_id":"206","city_name":"宜昌市","pid":"17","pname":"湖北省"}]
     */

    private String returnvalue;
    private String msg;
    private String count;
    /**
     * city_id : 208
     * city_name : 鄂州市
     * pid : 17
     * pname : 湖北省
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
        private String city_id;
        private String city_name;
        private String pid;
        private String pname;

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }
    }
}
