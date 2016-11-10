package com.queqiaolove.javabean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zchk on 2016/11/3.
 * 个人-个人资料
 */
public class UserInfroDetailBean implements Serializable{

    /**
     * returnvalue : true
     * msg : 成功
     * ucode : 1211026
     * like_num : 0
     * gz_num : 0
     * fs_num : 0
     * username :
     * nickname : wd
     * sex : 0
     * sex_str : 保密
     * upic : http://182.92.213.51:1070//user_img/161109102525040487_s.jpg
     * age : 无
     * areaid : 0
     * area_str :
     * cityid : 0
     * city_str :
     * nation : 0
     * nation_str :
     * education : 0
     * education_str :
     * mobile : 13388006724
     * declaration : fdsfdsf
     * ucontent : 可考虑进来了
     * qq : 3343443
     * weixin : sssss
     * address :
     * step : 1
     * yqcode : h4yymg
     * qqbi : 0
     * myheight : 无
     * myweight : 无
     * month_income : 0
     * month_income_str :
     * marital_status : 0
     * marital_status_str :
     * child_status :
     * buy_house :
     * buy_car :
     * school : 北大
     * major : 计算机
     * job : 程序员
     * company_industry_str :
     * company_industry : 0
     * company_nature_str :
     * company_nature : 0
     * language_list : [{"acode":"10601","aname":"中文"}]
     * label_list : [{"acode":"3","aname":"长腿欧巴"}]
     * pic_list : [{"id":"81","upic":"http://182.92.213.51:1070//album_img/1611/161108211917204736_s.jpg"},{"id":"55","upic":"http://182.92.213.51:1070//album_img/1611/161108193717565540_s.jpg"},{"id":"49","upic":"http://182.92.213.51:1070//album_img/1611/161108192250246054_s.jpg"},{"id":"40","upic":"http://182.92.213.51:1070//album_img/1611/161108182958640376_s.jpg"},{"id":"39","upic":"http://182.92.213.51:1070//album_img/1611/161108182812515853_s.jpg"},{"id":"38","upic":"http://182.92.213.51:1070//album_img/1611/161108182802562051_s.jpg"},{"id":"37","upic":"http://182.92.213.51:1070//album_img/1611/161108180043935132_s.jpg"},{"id":"36","upic":"http://182.92.213.51:1070//album_img/1611/161108180004966388_s.jpg"},{"id":"35","upic":"http://182.92.213.51:1070//album_img/1611/161108175839825445_s.jpg"},{"id":"34","upic":"http://182.92.213.51:1070//album_img/1611/161108175339011067_s.jpg"}]
     */

    private String returnvalue;
    private String msg;
    private String ucode;
    private int like_num;
    private int gz_num;
    private int fs_num;
    private String username;
    private String nickname;
    private String sex;
    private String sex_str;
    private String upic;
    private String age;
    private String areaid;
    private String area_str;
    private String cityid;
    private String city_str;
    private String nation;
    private String nation_str;
    private String education;
    private String education_str;
    private String mobile;
    private String declaration;
    private String ucontent;
    private String qq;
    private String weixin;
    private String address;
    private String step;
    private String yqcode;
    private String qqbi;
    private String myheight;
    private String myweight;
    private String month_income;
    private String month_income_str;
    private String marital_status;
    private String marital_status_str;
    private String child_status;
    private String buy_house;
    private String buy_car;
    private String school;
    private String major;
    private String job;
    private String company_industry_str;
    private String company_industry;
    private String company_nature_str;
    private String company_nature;
    /**
     * acode : 10601
     * aname : 中文
     */

    private List<LanguageListBean> language_list;
    /**
     * acode : 3
     * aname : 长腿欧巴
     */

    private List<LabelListBean> label_list;
    /**
     * id : 81
     * upic : http://182.92.213.51:1070//album_img/1611/161108211917204736_s.jpg
     */

    private List<PicListBean> pic_list;

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

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getGz_num() {
        return gz_num;
    }

    public void setGz_num(int gz_num) {
        this.gz_num = gz_num;
    }

    public int getFs_num() {
        return fs_num;
    }

    public void setFs_num(int fs_num) {
        this.fs_num = fs_num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex_str() {
        return sex_str;
    }

    public void setSex_str(String sex_str) {
        this.sex_str = sex_str;
    }

    public String getUpic() {
        return upic;
    }

    public void setUpic(String upic) {
        this.upic = upic;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getArea_str() {
        return area_str;
    }

    public void setArea_str(String area_str) {
        this.area_str = area_str;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity_str() {
        return city_str;
    }

    public void setCity_str(String city_str) {
        this.city_str = city_str;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNation_str() {
        return nation_str;
    }

    public void setNation_str(String nation_str) {
        this.nation_str = nation_str;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducation_str() {
        return education_str;
    }

    public void setEducation_str(String education_str) {
        this.education_str = education_str;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getUcontent() {
        return ucontent;
    }

    public void setUcontent(String ucontent) {
        this.ucontent = ucontent;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getYqcode() {
        return yqcode;
    }

    public void setYqcode(String yqcode) {
        this.yqcode = yqcode;
    }

    public String getQqbi() {
        return qqbi;
    }

    public void setQqbi(String qqbi) {
        this.qqbi = qqbi;
    }

    public String getMyheight() {
        return myheight;
    }

    public void setMyheight(String myheight) {
        this.myheight = myheight;
    }

    public String getMyweight() {
        return myweight;
    }

    public void setMyweight(String myweight) {
        this.myweight = myweight;
    }

    public String getMonth_income() {
        return month_income;
    }

    public void setMonth_income(String month_income) {
        this.month_income = month_income;
    }

    public String getMonth_income_str() {
        return month_income_str;
    }

    public void setMonth_income_str(String month_income_str) {
        this.month_income_str = month_income_str;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getMarital_status_str() {
        return marital_status_str;
    }

    public void setMarital_status_str(String marital_status_str) {
        this.marital_status_str = marital_status_str;
    }

    public String getChild_status() {
        return child_status;
    }

    public void setChild_status(String child_status) {
        this.child_status = child_status;
    }

    public String getBuy_house() {
        return buy_house;
    }

    public void setBuy_house(String buy_house) {
        this.buy_house = buy_house;
    }

    public String getBuy_car() {
        return buy_car;
    }

    public void setBuy_car(String buy_car) {
        this.buy_car = buy_car;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany_industry_str() {
        return company_industry_str;
    }

    public void setCompany_industry_str(String company_industry_str) {
        this.company_industry_str = company_industry_str;
    }

    public String getCompany_industry() {
        return company_industry;
    }

    public void setCompany_industry(String company_industry) {
        this.company_industry = company_industry;
    }

    public String getCompany_nature_str() {
        return company_nature_str;
    }

    public void setCompany_nature_str(String company_nature_str) {
        this.company_nature_str = company_nature_str;
    }

    public String getCompany_nature() {
        return company_nature;
    }

    public void setCompany_nature(String company_nature) {
        this.company_nature = company_nature;
    }

    public List<LanguageListBean> getLanguage_list() {
        return language_list;
    }

    public void setLanguage_list(List<LanguageListBean> language_list) {
        this.language_list = language_list;
    }

    public List<LabelListBean> getLabel_list() {
        return label_list;
    }

    public void setLabel_list(List<LabelListBean> label_list) {
        this.label_list = label_list;
    }

    public List<PicListBean> getPic_list() {
        return pic_list;
    }

    public void setPic_list(List<PicListBean> pic_list) {
        this.pic_list = pic_list;
    }

    public static class LanguageListBean implements Serializable{
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

    public static class LabelListBean implements Serializable{
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

    public static class PicListBean implements Serializable{
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
