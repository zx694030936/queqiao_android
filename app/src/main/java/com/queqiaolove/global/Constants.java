package com.queqiaolove.global;

/**
 * Created by WD on 2016/9/10.
 */
public interface Constants {
    //String rtmpUrl = "rtmp://4501.liveplay.myqcloud.com/live/4501_71d16d8c765c11e69776e435c87f075e";
    //String rtmpUrl = "http://2000.liveplay.myqcloud.com/live/2000_1f4652b179af11e69776e435c87f075e.flv";
    String pushUrl = "rtmp://4501.livepush.myqcloud.com/live/4501_ed87e94f8f5d11e69776e435c87f075e?bizid=4501";
    String playUrl = "rtmp://4501.liveplay.myqcloud.com/live/4501_ed87e94f8f5d11e69776e435c87f075e";
    /*腾讯云IM*/
    final int IMSDK_APPID = 1400017146;//im，appid
    final int IMSDK_ACCOUNT_TYPE = 8120;//im，accounttype
    /*权限*/
    int MY_PERMISSIONS_REQUEST_CALL_PHONE = 110;

    /*推流直播*/
    final String USERID = "userid";//用户id
    final String IF_OPEN = "if_open";//直播加密方式：1公开，2密码，3门票，4范围
    final int IF_OPEN_OPEN = 1;//直播加密方式：1公开
    final int IF_OPEN_PWD = 2;//直播加密方式：2密码
    final int IF_OPEN_TICKET = 3;//直播加密方式：3门票
    final int IF_OPEN_RANG = 4;//直播加密方式：4范围
    final String PAGENO = "pageno";//第几页
    final String PAGESIZE = "pagesize";//每页几条
    final String BTITLE = "btitle";//标题
    final String SAYTITLE = "saytitle";//话题
    final String TICKET_PRICE = "ticket_price";//门票
    final String BEGIN_AGE = "begin_age";//开始年龄
    final String END_AGE = "end_age";//结束年龄
    final String MONTH_INCOME = "month_income";//月收入
    final String MARITAL_STATUS = "marital_status";//婚姻状况：1未婚，2已婚
    final int MARITAL_STATUS_HASNOT = 1;//婚姻状况：1未婚
    final int MARITAL_STATUS_HAS = 2;//婚姻状况：2已婚

    final String LIVETYPE = "livetype";//直播类别:1手机，2pc
    final int LIVETYPE_PHONE = 1;//直播类别:1手机
    final int LIVETYPE_PC = 2;//直播类别:2pc
    final int LIVETYPE_MM = 7;//直播类别:7相亲活动
    /*登陆*/
    /*获取验证码的方式*/
    final int OBTAINCODE_REGISTER = 1;//注册时
    final int OBTAINCODE_FORGETPWD = 2;//忘记密码
    final int OBTAINCODE_CHANGEPHONE = 3;//修改手机
    final int OBTAINCODE_CHANGEPWD = 4;//修改秘密


    /*个人*/
    final int UPLOADIMAGE_USERICON = 1;//上传用户头像
    final int UPLOADIMAGE_PHOTO = 2;//上传用户照片
    /*个人资料sp存储*/
    final String SP_COMPANYBUSINESS = "companybusiness";//公司行业
    final String SP_COMPANYBUSINESS_CODE = "companybusiness_code";//公司行业
    final String SP_COMPANYNATURE = "companynature";//公司性质
    final String SP_COMPANYNATURE_CODE = "companynature_code";//公司性质
    final String SP_SCHOOL = "scholl";//学校
    final String SP_MAJOR = "major";//专业
    final String SP_JOB = "job";//职业

    final String SP_LABELLIST = "labellist";//标签list
    final String SP_LANGUAGECODELIST = "languagecodelist";//标签list
    final String SP_LANGUAGELIST = "languagelist";//标签list

    /*系统*/
    final int ATTRIBUTE_COMPANYBUSSINESS = 104;//属性码：公司行业
    final int ATTRIBUTE_COMPANYNATURE = 105;//属性码：公司性质
    final int ATTRIBUTE_LAGUAGE = 106;//属性码：语言
    final int ATTRIBUTE_NATURE = 101;//属性码：民族
    final int ATTRIBUTE_EDUCATION = 102;//属性码：学历
    final int ATTRIBUTE_MONTHLYINCOME = 103;//属性码：月收入


}
