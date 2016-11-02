package com.queqiaolove.javabean.login;

/**
 * Created by LENOVO on 2016/10/25.
 */
public class GetSmsCodeBean {

    /**
     * returnvalue : false
     *              true发送成功/false发送失败/noteover今天短信次数已用完
     * msg : 发送验证码失败，参数错误
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
