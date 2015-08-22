package com.lovo.netCRM.bean;

import java.util.Date;

/**
 * Created by CodeA on 2015/8/21.
 * 雇员实体
 */
public class EmployeeBean {
    //索引ID
    private int ID;
    //登录名
    private String loginName;
    //登录密码
    private String passWord;
    //雇员姓名
    private String name;
    //雇员性别
    private String sex;
    //出生日期
    private Date birthday;
    //文化程度
    private String edu;
    //专业
    private String speciality;
    //联系电话
    private String phone;
    //住址
    private String address;
    //政治面貌
    private String polity;
    //入职时间
    private Date hireDay;
    //状态 离职/在职
    private boolean status;
    //所在部门
    private String dept;
    //工作职位
    private String position;
    //头像
    private String headFile;

    public String getHeadFile() {
        return headFile;
    }

    public void setHeadFile(String headFile) {
        this.headFile = headFile;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolity() {
        return polity;
    }

    public void setPolity(String polity) {
        this.polity = polity;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String job) {
        this.position = job;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public EmployeeBean() {
    }

    public EmployeeBean(String name,
                        String sex,
                        Date birthday,
                        String edu,
                        String speciality,
                        String phone,
                        String address,
                        String polity,
                        String dept,
                        String position,
                        String headFile) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.edu = edu;
        this.speciality = speciality;
        this.phone = phone;
        this.address = address;
        this.polity = polity;
        this.dept = dept;
        this.position = position;
        this.headFile = headFile;
    }
}
