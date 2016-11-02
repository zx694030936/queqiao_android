package com.queqiaolove.javabean.push;

/**
 * Created by WD on 2016/10/22.
 */
public class GetPushUrlBean {


    /**
     * returnvalue : true
     * msg : 创建直播成功
     * push_url : rtmp://4501.livepush.myqcloud.com/live/4501_queqiao_2?bizid=4501&txSecret=bf35fec35e7a6083dc1c502fb305b1ca&txTime=580C38F9
     */

    private String returnvalue;
    private String msg;
    private String push_url;

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

    public String getPush_url() {
        return push_url;
    }

    public void setPush_url(String push_url) {
        this.push_url = push_url;
    }
}
