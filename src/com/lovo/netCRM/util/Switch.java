package com.lovo.netCRM.util;

import com.lovo.netCRM.bean.PositionBean;

/**
 * Created by CodeA on 2015/8/23.
 */
public class Switch {
    private int id;
    private String name;
    private String describe;
    private String checkRight;
    private String queryRight;
    private String saleRight;
    private String managerRight ;
    private String backRight;

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









    public static String change2(String str){
        switch(str){
            case "查询权限" :
                return "checkRight";
            case "考核权限" :
                return "queryRight";
            case "销售统计分析" :
                return "saleRight";
            case "权限管理" :
                return "managerRight";
            case "后台权限" :
                return "backRight";
        }
        return null;
    }

    public static void writeRight(PositionBean pos, String right){
        switch(right){
            case "checkRight":
                pos.setCheckRight(true);

                break;
            case "queryRight":
                pos.setCheckRight(true);

                break;
            case "saleRight":

                pos.setSaleRight(true);

                break;
            case "managerRight":

                pos.setManagerRight(true);

                break;
            case "backRight":

                pos.setBackRight(true);
        }
    }
}
