package com.lovo.netCRM.bean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/26.
 */
public class AddressCountBean {
    private int id;
    private ArrayList<AreaBean> cityName;
    //录入的学校总数
    private int schoolNum;
    //接洽中的学校数
    private int receivesSchoolNum;
    //待审的学校数 申请立项的
    private int proposeSchoolNum;
    //审核未通过的
    private int passSchoolNum;
    //允许的学校,推广状态
    private int permitSchoolNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<AreaBean> getCityName() {
        return cityName;
    }

    public void setCityName(ArrayList<AreaBean> cityName) {
        this.cityName = cityName;
    }

    public int getSchoolNum() {
        return schoolNum;
    }

    public void setSchoolNum(int schoolNum) {
        this.schoolNum = schoolNum;
    }

    public int getReceivesSchoolNum() {
        return receivesSchoolNum;
    }

    public void setReceivesSchoolNum(int receivesSchoolNum) {
        this.receivesSchoolNum = receivesSchoolNum;
    }

    public int getProposeSchoolNum() {
        return proposeSchoolNum;
    }

    public void setProposeSchoolNum(int proposeSchoolNum) {
        this.proposeSchoolNum = proposeSchoolNum;
    }

    public int getPassSchoolNum() {
        return passSchoolNum;
    }

    public void setPassSchoolNum(int passSchoolNum) {
        this.passSchoolNum = passSchoolNum;
    }

    public int getPermitSchoolNum() {
        return permitSchoolNum;
    }

    public void setPermitSchoolNum(int permitSchoolNum) {
        this.permitSchoolNum = permitSchoolNum;
    }
}
