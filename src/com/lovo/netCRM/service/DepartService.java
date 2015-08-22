package com.lovo.netCRM.service;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public interface DepartService {
    //取得所有部门信息
    public ArrayList<Object> getAllDepts();
    //添加部门信息
    public boolean addDept();
    //修改部门信息
    public boolean alterDept(int departID);
}
