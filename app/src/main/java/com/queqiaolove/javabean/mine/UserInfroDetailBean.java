package com.queqiaolove.javabean.mine;

import java.util.List;

/**
 * Created by zchk on 2016/11/3.
 * 个人-个人资料
 */
public class UserInfroDetailBean {

    /**
     * returnvalue : true
     * msg : 成功
     * ucode : 1211010
     * like_num : 0
     * gz_num : 1
     * fs_num : 0
     * username : 张勇
     * nickname : zy
     * sex : 1
     * sex_str : 男
     * upic : http://182.92.213.51:1070//user_img/161101150006439881_s.png
     * age : 34
     * areaid : 1
     * area_str : 北京市
     * cityid : 384
     * city_str : 朝阳区
     * nation : 10101
     * nation_str : 汉族
     * education : 10202
     * education_str : 本科
     * mobile : 13366357516
     * declaration : 给你我的爱
     * ucontent :
     * qq : 240099000
     * weixin : zy@163.com
     * address :
     * step : 1
     * yqcode : pgfu31
     * qqbi : 0
     * myheight : 160
     * myweight : 55
     * month_income : 10303
     * month_income_str : 10000以上
     * marital_status : 1
     * marital_status_str : 未婚
     * child_status : 有一儿一女
     * buy_house : 无房
     * buy_car : 无车
     * school :
     * major :
     * job :
     * company_industry_str : 证券/金融
     * company_industry : 10401
     * company_nature_str : 私企
     * company_nature : 10501
     * language_list : []
     * label_list : [{"acode":"1","aname":"帅气"},{"acode":"2","aname":"阳光"},{"acode":"3","aname":"长腿欧巴"},{"acode":"4","aname":"男神呀"}]
     * pic_list : [{"id":"10","upic":"http://182.92.213.51:1070//img/1.png"},{"id":"3","upic":"http://182.92.213.51:1070//img/3.png"},{"id":"14","upic":""}]
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
    private List<String> language_list;
    /**
     * acode : 1
     * aname : 帅气
     */

    private List<LabelListBean> label_list;
    /**
     * id : 10
     * upic : http://182.92.213.51:1070//img/1.png
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

    public List<String> getLanguage_list() {
        return language_list;
    }

    public void setLanguage_list(List<String> language_list) {
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

    public static class LabelListBean {
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
