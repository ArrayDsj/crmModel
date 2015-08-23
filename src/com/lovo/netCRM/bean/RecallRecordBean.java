package com.lovo.netCRM.bean;

import java.util.Date;

/**
 * Created by CodeA on 2015/8/24.
 */
public class RecallRecordBean {
    private int id;
    private Date time;
    private String describe;
    private String recallMan;
    private String title;
    private EmployeeBean emp;

    public RecallRecordBean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRecallMan() {
        return recallMan;
    }

    public void setRecallMan(String recallMan) {
        this.recallMan = recallMan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EmployeeBean getEmp() {
        return emp;
    }

    public void setEmp(EmployeeBean emp) {
        this.emp = emp;
    }
}
