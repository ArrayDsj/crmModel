package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.dao.imp.DepartDaoImp;
import com.lovo.netCRM.service.DepartService;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public class DepartServiceImp implements DepartService{
    @Override
    public ArrayList<Object> getAllDepts() {
        CrmDao departs = new DepartDaoImp();
        return departs.getAllObjects();
    }

    @Override
    public boolean addDept() {
        return false;
    }

    @Override
    public boolean alterDept(int departID) {
        return false;
    }
}
