package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.ClassesBean;

/**
 * Created by CodeA on 2015/8/24.
 */
public interface ClassesService {
    public boolean addClasses(int objectID,ClassesBean cla);

    public boolean alterClassByID(int claID);
}
