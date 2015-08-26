package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.ActiveBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.dao.imp.ActiveDaoImp;
import com.lovo.netCRM.service.ActiveService;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class ActiveServiceImp implements ActiveService {
    @Override
    public boolean addActive(int schoolID,ActiveBean active) {
        CrmDao crm = new ActiveDaoImp();
        crm.addObject(schoolID, active);
        //修改学校中的inTime
        new SchoolServiceImp().alterSchoolByID(schoolID);
        return false;
    }

    @Override
    public ArrayList<ActiveBean> getAllActivesByCon(int schoolId) {
        CrmDao crm = new ActiveDaoImp();
        ArrayList<Object> objs = crm.getObjectByCon("", schoolId + "");
        if(objs == null){
            return null;
        }
        //将Object对象转换成Active对象
        ArrayList<ActiveBean> allActivesBySchoolID = new ArrayList<ActiveBean>();
        for(Object obj : objs){
            if(obj instanceof ActiveBean ){
                ActiveBean active = (ActiveBean)obj;
                allActivesBySchoolID.add(active);
            }
        }
        return allActivesBySchoolID;
    }
}
