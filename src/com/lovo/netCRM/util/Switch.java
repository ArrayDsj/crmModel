package com.lovo.netCRM.util;

import com.lovo.netCRM.bean.PositionBean;

/**
 * Created by CodeA on 2015/8/23.
 */
public class Switch {
    public static String change2(String str){
        switch(str){
            case "��ѯȨ��" :
                return "checkRight";
            case "����Ȩ��" :
                return "queryRight";
            case "����ͳ�Ʒ���" :
                return "saleRight";
            case "Ȩ�޹���" :
                return "managerRight";
            case "��̨Ȩ��" :
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
