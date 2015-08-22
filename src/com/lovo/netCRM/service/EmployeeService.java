package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.EmployeeBean;

/**
 * Created by CodeA on 2015/8/21.
 */
public interface EmployeeService {
    public EmployeeBean login(String loginName, String passWord);
}
