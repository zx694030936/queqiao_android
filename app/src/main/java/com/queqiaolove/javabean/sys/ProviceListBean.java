package com.queqiaolove.javabean.sys;

import java.util.List;

/**
 * Created by WD on 2016/10/22.
 */
public class ProviceListBean {

    /**
     * returnvalue : true
     * msg : 成功
     * count : 34
     * list : [{"province_id":"12","province_name":"安徽省"},{"province_id":"33","province_name":"澳门特别行政区"},{"province_id":"1","province_name":"北京市"},{"province_id":"13","province_name":"福建省"},{"province_id":"28","province_name":"甘肃省"},{"province_id":"19","province_name":"广东省"},{"province_id":"20","province_name":"广西壮族自治区"},{"province_id":"24","province_name":"贵州省"},{"province_id":"21","province_name":"海南省"},{"province_id":"3","province_name":"河北省"},{"province_id":"16","province_name":"河南省"},{"province_id":"8","province_name":"黑龙江省"},{"province_id":"17","province_name":"湖北省"},{"province_id":"18","province_name":"湖南省"},{"province_id":"7","province_name":"吉林省"},{"province_id":"10","province_name":"江苏省"},{"province_id":"14","province_name":"江西省"},{"province_id":"6","province_name":"辽宁省"},{"province_id":"5","province_name":"内蒙古自治区"},{"province_id":"30","province_name":"宁夏回族自治区"},{"province_id":"29","province_name":"青海省"},{"province_id":"15","province_name":"山东省"},{"province_id":"4","province_name":"山西省"},{"province_id":"27","province_name":"陕西省"},{"province_id":"9","province_name":"上海市"},{"province_id":"23","province_name":"四川省"},{"province_id":"34","province_name":"台湾省"},{"province_id":"2","province_name":"天津市"},{"province_id":"26","province_name":"西藏自治区"},{"province_id":"32","province_name":"香港特别行政区"},{"province_id":"31","province_name":"新疆维吾尔自治区"},{"province_id":"25","province_name":"云南省"},{"province_id":"11","province_name":"浙江省"},{"province_id":"22","province_name":"重庆市"}]
     */

    private String returnvalue;
    private String msg;
    private String count;
    /**
     * province_id : 12
     * province_name : 安徽省
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
        private String province_id;
        private String province_name;

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }
    }
}
