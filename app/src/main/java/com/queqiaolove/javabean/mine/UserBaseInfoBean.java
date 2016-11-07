package com.queqiaolove.javabean.mine;

import java.util.List;

/**
 * Created by zchk on 2016/11/3.
 */
public class UserBaseInfoBean {

    /**
     * returnvalue : true
     * msg : 成功
     * ucode : 1211010
     * nickname : zy
     * upic : http://182.92.213.51:1070//user_img/161101150006439881_s.png
     * step : 1
     * integrity_degree : 70%
     * yqcode : pgfu31
     * pic_list : [{"id":"10","upic":"http://182.92.213.51:1070//img/1.png"},{"id":"3","upic":"http://182.92.213.51:1070//img/3.png"},{"id":"14","upic":""}]
     * video_list : []
     */

    private String returnvalue;
    private String msg;
    private String ucode;
    private String nickname;
    private String upic;
    private String step;
    private String integrity_degree;
    private String yqcode;
    /**
     * id : 10
     * upic : http://182.92.213.51:1070//img/1.png
     */

    private List<PicListBean> pic_list;
    private List<String> video_list;

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

    public String getUcode() {
        return ucode;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUpic() {
        return upic;
    }

    public void setUpic(String upic) {
        this.upic = upic;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getIntegrity_degree() {
        return integrity_degree;
    }

    public void setIntegrity_degree(String integrity_degree) {
        this.integrity_degree = integrity_degree;
    }

    public String getYqcode() {
        return yqcode;
    }

    public void setYqcode(String yqcode) {
        this.yqcode = yqcode;
    }

    public List<PicListBean> getPic_list() {
        return pic_list;
    }

    public void setPic_list(List<PicListBean> pic_list) {
        this.pic_list = pic_list;
    }

    public List<String> getVideo_list() {
        return video_list;
    }

    public void setVideo_list(List<String> video_list) {
        this.video_list = video_list;
    }

    public static class PicListBean {
        private String id;
        private String upic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpic() {
            return upic;
        }

        public void setUpic(String upic) {
            this.upic = upic;
        }
    }
}
