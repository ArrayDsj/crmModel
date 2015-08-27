package com.lovo.netCRM.bean;

/**
 * Created by CodeA on 2015/8/27.
 */
public class SchoolCountBean {
    private String schoolName;
    private int classesNum;
    private int vipNum;
    private int notVipNum;

    public SchoolCountBean() {
    }

    public String getSchoolName() {

        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getClassesNum() {
        return classesNum;
    }

    public void setClassesNum(int classesNum) {
        this.classesNum = classesNum;
    }

    public int getVipNum() {
        return vipNum;
    }

    public void setVipNum(int vipNum) {
        this.vipNum = vipNum;
    }

    public int getNotVipNum() {
        return notVipNum;
    }

    public void setNotVipNum(int notVipNum) {
        this.notVipNum = notVipNum;
    }
}
