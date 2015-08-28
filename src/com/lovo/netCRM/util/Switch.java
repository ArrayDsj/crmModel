package com.lovo.netCRM.util;

import com.lovo.netCRM.bean.ClassesBean;

/**
 * Created by CodeA on 2015/8/23.
 */
public class Switch {
    //权限管理列表
    private int id;
    private String name;
    private String describe;
    private String checkRight;
    private String queryRight;
    private String saleRight;
    private String managerRight ;
    private String backRight;

    //学生列表信息
    private int stuId;
    private String stuName;
    private String sex;
    private String vip;
    private String phone;
    private ClassesBean classes;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClassesBean getClasses() {
        return classes;
    }

    public void setClasses(ClassesBean classes) {
        this.classes = classes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCheckRight() {
        return checkRight;
    }

    public void setCheckRight(String checkRight) {
        this.checkRight = checkRight;
    }

    public String getQueryRight() {
        return queryRight;
    }

    public void setQueryRight(String queryRight) {
        this.queryRight = queryRight;
    }

    public String getSaleRight() {
        return saleRight;
    }

    public void setSaleRight(String saleRight) {
        this.saleRight = saleRight;
    }

    public String getManagerRight() {
        return managerRight;
    }

    public void setManagerRight(String managerRight) {
        this.managerRight = managerRight;
    }

    public String getBackRight() {
        return backRight;
    }

    public void setBackRight(String backRight) {
        this.backRight = backRight;
    }


    public static String boolean2String(boolean bo){
        if(bo){
            return "是";
        }else
            return "否";
    }

}
