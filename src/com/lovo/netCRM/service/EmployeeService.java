package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.EmployeeBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/21.
 */
public interface EmployeeService {
    //��¼���󷽷�
    public EmployeeBean login(String loginName, String passWord);

    //ȡ������Ա����Ϣ
    public ArrayList<EmployeeBean> getAllStaffs();

    //ɾ��Ա����Ϣ
    public boolean deleteStaff(int EmployeeID);
}
