package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.dao.imp.EmployeeDaoImp;
import com.lovo.netCRM.service.EmployeeService;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/21.
 */
public class EmployeeServiceImp implements EmployeeService{
    @Override
    public EmployeeBean login(String loginName, String passWord) {
        CrmDao emp = new EmployeeDaoImp();
        return emp.login(loginName,passWord);
    }

    @Override
    public ArrayList<Object> getAllStaffs() {
        CrmDao emp = new EmployeeDaoImp();
        return emp.getAllObjects();
    }

    @Override
    public boolean deleteStaff(int EmployeeID) {
        CrmDao emp = new EmployeeDaoImp();
        return emp.deleteObject(EmployeeID);
    }

    @Override
    public EmployeeBean getStaffByID(int EmployeeID) {
        CrmDao emp = new EmployeeDaoImp();
        EmployeeBean thisEmp = (EmployeeBean)emp.getObjectByID(EmployeeID);
        return thisEmp;
    }

    @Override
    public boolean alterStaff(EmployeeBean alterEmp) {
        CrmDao emp = new EmployeeDaoImp();
        return emp.alterObject(alterEmp);
    }

    @Override
    public boolean addStaff(EmployeeBean newEmp) {
        CrmDao emp = new EmployeeDaoImp();
        return emp.addObject(newEmp);
    }
}
