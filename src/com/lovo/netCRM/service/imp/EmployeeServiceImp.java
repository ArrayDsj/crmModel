package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.dao.EmployeeDAO;
import com.lovo.netCRM.dao.imp.EmployeeDAOImp;
import com.lovo.netCRM.service.EmployeeService;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/21.
 */
public class EmployeeServiceImp implements EmployeeService{
    @Override
    public EmployeeBean login(String loginName, String passWord) {
        EmployeeDAO emp = new EmployeeDAOImp();
        return emp.login(loginName,passWord);
    }

    @Override
    public ArrayList<EmployeeBean> getAllStaffs() {
        EmployeeDAO emp = new EmployeeDAOImp();
        return emp.getAllStaffs();
    }

    @Override
    public boolean deleteStaff(int EmployeeID) {
        EmployeeDAO emp = new EmployeeDAOImp();
        return emp.deleteStaff(EmployeeID);
    }
}
