package com.lovo.netCRM.bean;

import java.util.Date;

/**
 * Created by CodeA on 2015/8/24.
 */
public class StudentBean {
    private int id;
    private String name;
    private String sex;
    private String address;
    private Date birthday;
    private String describe;
    private boolean status;
    private boolean vip;
    private String phone;
    private String father;
    private String fatherPhone;
    private String mother;
    private String motherPhone;
    private RecallRecordBean recallRecord;
    private ClassesBean classes;

    public StudentBean() {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getMotherPhone() {
        return motherPhone;
    }

    public void setMotherPhone(String motherPhone) {
        this.motherPhone = motherPhone;
    }

    public RecallRecordBean getRecallRecord() {
        return recallRecord;
    }

    public void setRecallRecord(RecallRecordBean recallRecord) {
        this.recallRecord = recallRecord;
    }

    public ClassesBean getClasses() {
        return classes;
    }

    public void setClasses(ClassesBean classes) {
        this.classes = classes;
    }
}
