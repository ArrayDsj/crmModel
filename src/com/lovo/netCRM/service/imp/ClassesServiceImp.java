package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.dao.imp.ClassesDaoImp;
import com.lovo.netCRM.service.ClassesService;

/**
 * Created by CodeA on 2015/8/24.
 */
public class ClassesServiceImp implements ClassesService{
    @Override
    public boolean addClasses(int id,ClassesBean cla) {
        //添加新班级信息
        return new ClassesDaoImp().addObject(id,cla);
    }

    @Override
    public boolean alterClassByID(int claID) {
        return false;
    }
}
