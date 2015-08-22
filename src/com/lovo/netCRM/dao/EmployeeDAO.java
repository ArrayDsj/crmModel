package com.lovo.netCRM.dao;

import com.lovo.netCRM.bean.EmployeeBean;

/**
 * Created by CodeA on 2015/8/21.
 */
public interface EmployeeDAO {
    //登录操作,返回一个用户信息,因为要使用到权限等信息
    public EmployeeBean login(String loginName, String passWord);
}
