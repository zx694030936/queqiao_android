package com.queqiaolove.javabean.login;

/**
 * Created by LENOVO on 2016/10/25.
 */
public class RegistBean {
    /**
     * returnvalue : false
     * msg : 注册失败，手机验证码错误
     * userid : 0
     * upic : http://182.92.213.51:1070//img/man.png
     */

    private String returnvalue;
    private String msg;
    private String userid;
    private String upic;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUpic() {
        return upic;
    }

    public void setUpic(String upic) {
        this.upic = upic;
    }
}
