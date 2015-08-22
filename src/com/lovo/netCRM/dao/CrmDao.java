package com.lovo.netCRM.dao;

import com.lovo.netCRM.bean.EmployeeBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/21.
 */
public interface CrmDao {
    //登录操作,返回一个用户信息,因为要使用到权限等信息
    public EmployeeBean login(String loginName, String passWord);

    //初始化数据操作,当登录后显示所有员工信息
    public ArrayList<Object> getAllObjects();

    //删除员工信息
    public boolean deleteObject(int ObjectID);
}
