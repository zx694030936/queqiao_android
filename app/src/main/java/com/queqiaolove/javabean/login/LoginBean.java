package com.queqiaolove.javabean.login;

/**
 * Created by LENOVO on 2016/10/25.
 */
public class LoginBean {


    /**
     * returnvalue : false
     * userid :
     * username :
     * upic :
     * msg : 登录失败，登录帐号或密码错误
     */

    private String returnvalue;
    private String userid;
    private String username;
    private String upic;
    private String msg;

    public String getReturnvalue() {
        return returnvalue;
    }

    public void setReturnvalue(String returnvalue) {
        this.returnvalue = returnvalue;
    }

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
