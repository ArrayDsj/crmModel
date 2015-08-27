package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.dao.imp.AddressCountDaoImp;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/27.
 */
public class AddressCountServiceImp {
    //地区统计
    public ArrayList<Object> getCounts(){
        return new AddressCountDaoImp().getCounts();
    }

}
