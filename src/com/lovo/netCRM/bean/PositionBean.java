package com.lovo.netCRM.bean;

/**
 * Created by CodeA on 2015/8/23.
 */
public class PositionBean {
    private int positionID;
    private String name;
    private String describe;
    private boolean checkRight;//查询权限
    private boolean queryRight;//考核权限
    private boolean saleRight;//销售统计权限
    private boolean managerRight;//权限管理
    private boolean backRight;//后台管理

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

    public boolean isCheckRight() {
        return checkRight;
    }

    public void setCheckRight(boolean checkRight) {
        this.checkRight = checkRight;
    }

    public boolean isQueryRight() {
        return queryRight;
    }

    public void setQueryRight(boolean queryRight) {
        this.queryRight = queryRight;
    }

    public boolean isSaleRight() {
        return saleRight;
    }

    public void setSaleRight(boolean saleRight) {
        this.saleRight = saleRight;
    }

    public boolean isManagerRight() {
        return managerRight;
    }

    public void setManagerRight(boolean managerRight) {
        this.managerRight = managerRight;
    }

    public boolean isBackRight() {
        return backRight;
    }

    public void setBackRight(boolean backRight) {
        this.backRight = backRight;
    }

    public PositionBean(int positionID,
                        String name,
                        String describe,
                        boolean checkRight,
                        boolean queryRight,
                        boolean saleRight,
                        boolean managerRight,
                        boolean backRight) {
        this.positionID = positionID;
        this.name = name;
        this.describe = describe;
        this.checkRight = checkRight;
        this.queryRight = queryRight;
        this.saleRight = saleRight;
        this.managerRight = managerRight;
        this.backRight = backRight;
    }

    public PositionBean() {

    }
}
