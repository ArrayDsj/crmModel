package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.ActiveBean;
import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.bean.ConnectRecordBean;
import com.lovo.netCRM.bean.SchoolBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public interface SchoolService {
    public boolean addActive(ActiveBean active);

    public boolean addConnectRecord(ConnectRecordBean con);

    public boolean addSchool(SchoolBean school);

    public boolean alterSchoolByID(int schID);

    public ActiveBean getActiveBySchool(int schID);

    public ClassesBean getClassBySchool(int schID);

    public SchoolBean getSchoolByID(int schID);

    public SchoolBean getSchoolByArea(int areaID);

    public ArrayList<SchoolBean> getAllSchools();
}
