package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.DepartBean;
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
    public boolean addDept(Object newDept) {
        CrmDao departs = new DepartDaoImp();
        return departs.addObject(0, newDept);
    }


    @Override
    public boolean alterDept(Object willUpdateDept) {
        CrmDao departs = new DepartDaoImp();
        return departs.alterObject(0, willUpdateDept);
    }

    @Override
    public DepartBean getDeptByID(int departID) {
        CrmDao depart = new DepartDaoImp();
        DepartBean thisDept = (DepartBean)depart.getObjectByID(departID);
        return thisDept;
    }

    public DepartBean getDeptByName(String name){
        CrmDao depart =  new DepartDaoImp();
        DepartBean dept = (DepartBean) depart.getObjectByName(name);
        return dept;
    }
}
