package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.dao.CrmDao;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class StudentDaoImp implements CrmDao{
    @Override
    public EmployeeBean login(String loginName, String passWord) {
        return null;
    }

    @Override
    public ArrayList<Object> getAllObjects() {
        return null;
    }

    @Override
    public boolean deleteObject(int ObjectID) {
        return false;
    }

    @Override
    public Object getObjectByID(int ObjectID) {
        return null;
    }

    @Override
    public boolean alterObject(Object alterObj) {
        return false;
    }

    @Override
    public boolean addObject(Object object) {
        return false;
    }

    @Override
    public ArrayList<Object> getObjectByCon(String item, String value) {
        return null;
    }
}
