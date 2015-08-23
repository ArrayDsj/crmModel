package com.lovo.netCRM.bean;

/**
 * Created by CodeA on 2015/8/23.
 */
public class PositionBean {
    private int positionID;
    private String name;
    private String describe;

    public PositionBean(int positionID, String name, String describe) {
        this.positionID = positionID;
        this.name = name;
        this.describe = describe;
    }

    public PositionBean() {

    }

    public int getPositionID() {

        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
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
}
