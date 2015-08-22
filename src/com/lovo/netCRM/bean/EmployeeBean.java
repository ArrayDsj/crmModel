package com.lovo.netCRM.bean;

import java.util.Date;

/**
 * Created by CodeA on 2015/8/21.
 * ��Աʵ��
 */
public class EmployeeBean {
    //����ID
    private int ID;
    //��¼��
    private String loginName;
    //��¼����
    private String passWord;
    //��Ա����
    private String name;
    //��Ա�Ա�
    private String sex;
    //��������
    private Date birthday;
    //�Ļ��̶�
    private String edu;
    //רҵ
    private String speciality;
    //��ϵ�绰
    private String phone;
    //סַ
    private String address;
    //������ò
    private String polity;
    //��ְʱ��
    private Date hireDay;
    //״̬ ��ְ/��ְ
    private boolean status;
    //���ڲ���
    private String dept;
    //����ְλ
    private String position;
    //ͷ��
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
