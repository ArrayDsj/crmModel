package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.EmployeeBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/21.
 */
public interface EmployeeService {
    //登录抽象方法
    public EmployeeBean login(String loginName, String passWord);

    //取得所有员工信息
    public ArrayList<EmployeeBean> getAllStaffs();

    //删除员工信息
    public boolean deleteStaff(int EmployeeID);
}
