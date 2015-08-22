package com.lovo.netCRM.bean;

import java.util.Date;

/**
 * Created by CodeA on 2015/8/22.
 */
public class DepartBean {
    private int departID;
    private String departName;
    private String describe;
    private Date buildTime;

    public DepartBean(String departName, String describe, Date buildTime, int departID) {
        this.departName = departName;
        this.describe = describe;
        this.buildTime = buildTime;
        this.departID = departID;
    }

    public DepartBean() {

    }

    public int getDepartID() {

        return departID;
    }

    public void setDepartID(int departID) {
        this.departID = departID;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }
}
