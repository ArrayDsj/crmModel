package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.ActiveBean;
import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.bean.ConnectRecordBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.dao.imp.SchoolDaoImp;
import com.lovo.netCRM.service.SchoolService;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class SchoolServiceImp implements SchoolService{
    @Override
    public boolean addActive(ActiveBean active) {
        return false;
    }

    @Override
    public boolean addConnectRecord(ConnectRecordBean con) {
        return false;
    }

    @Override
    public boolean addSchool(int id,SchoolBean school) {
        CrmDao crm = new SchoolDaoImp();
        return crm.addObject(0,school);
    }

    @Override
    public boolean alterSchoolByID(int schID) {
        new SchoolDaoImp().alterSchoolByID(schID);
        return false;
    }

    @Override
    public ActiveBean getActiveBySchool(int schID) {
        return null;
    }

    @Override
    public ClassesBean getClassBySchool(int schID) {
        return null;
    }




    @Override
    public SchoolBean getSchoolByID(int schID) {
        CrmDao crm = new SchoolDaoImp();
        SchoolBean sch =(SchoolBean)crm.getObjectByID(schID);
        if(sch != null){
            return sch;
        }
        return null;
    }




    @Override
    public SchoolBean getSchoolByArea(int areaID) {
        return null;
    }

    @Override
    public ArrayList<SchoolBean> getAllSchools() {
        return null;
    }


}
