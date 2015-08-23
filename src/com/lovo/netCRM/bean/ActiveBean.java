package com.lovo.netCRM.bean;

import java.util.Date;

/**
 * Created by CodeA on 2015/8/23.
 * »î¶¯
 */
public class ActiveBean {
    private int id;
    private String name;
    private Date time;
    private String title;
    private String address;
    private EmployeeBean emp;

    public ActiveBean() {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmployeeBean getEmp() {
        return emp;
    }

    public void setEmp(EmployeeBean emp) {
        this.emp = emp;
    }
}
