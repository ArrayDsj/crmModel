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
        EmployeeDaoImp emp = new EmployeeDaoImp();
        return emp.login(loginName, passWord);
    }

    @Override
    public ArrayList<Object> getAllStaffs() {
        CrmDao emp = new EmployeeDaoImp();
        return emp.getAllObjects();
    }

    public ArrayList<Object> getAllStaffs(int pageNow,int pageSize,String item, String value) {
        EmployeeDaoImp emp = new EmployeeDaoImp();
        return emp.getObjectByCon(pageNow, pageSize,item,value);
    }

    @Override
    //更改状态值
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

    //添加员工信息
    public boolean addStaff(int empID,EmployeeBean newEmp) {
        CrmDao emp = new EmployeeDaoImp();
        return emp.addObject(empID, newEmp);
    }

    @Override
    public ArrayList<Object> getStaffByCon(String item, String value) {
        if(item.equals("部门")){
            int deptID = Integer.parseInt(value);
            ArrayList<Object> objs = new ArrayList<Object>();
            ArrayList<EmployeeBean> emps = new EmployeeDaoImp().getAllEmpByDeptID(deptID);
            for(EmployeeBean emp : emps){
                objs.add(emp);
            }
            return objs;
        }
        CrmDao emp = new EmployeeDaoImp();
        return emp.getObjectByCon(item, value);
    }

    @Override
    public EmployeeBean getEmpByName(String name) {
        CrmDao emp = new EmployeeDaoImp();
        EmployeeBean thisEmp = (EmployeeBean)emp.getObjectByName(name);
        return thisEmp;
    }

    @Override
    public boolean alterStaff(int objID,EmployeeBean alterEmp) {
        CrmDao emp = new EmployeeDaoImp();
        return emp.alterObject(objID,alterEmp);
    }

}
