package com.lovo.netCRM.service;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public interface DepartService {
    //取得所有部门信息
    public ArrayList<Object> getAllDepts();
    //添加部门信息
    public boolean addDept(Object newDept);
    //修改部门信息
    public boolean alterDept(Object willUpdateDept);

    //按ID查找部门信息
    public Object getDeptByID(int departID);
}
