package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.ActiveBean;
import com.lovo.netCRM.dao.imp.ActiveDaoImp;
import com.lovo.netCRM.service.ActiveService;

/**
 * Created by CodeA on 2015/8/24.
 */
public class ActiveServiceImp implements ActiveService {
    @Override
    public boolean addActive(ActiveBean active, int schoolID) {
        ActiveDaoImp add = new ActiveDaoImp();
        add.addActive(active, schoolID);
        //修改学校中的inTime
        new SchoolServiceImp().alterSchoolByID(schoolID);
        return false;
    }
}
