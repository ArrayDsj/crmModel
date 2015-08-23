package com.lovo.netCRM.util;

import com.lovo.netCRM.bean.PositionBean;

/**
 * Created by CodeA on 2015/8/23.
 */
public class Switch {
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
