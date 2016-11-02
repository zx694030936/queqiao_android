package com.queqiaolove.javabean.mine;

/**
 * Created by LENOVO on 2016/10/25.
 */
public class ChangePwdBean {
     /**
     * returnvalue : false
     * msg : 失败，手机验证码错误
     */

    private String returnvalue;
    private String msg;

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
}
