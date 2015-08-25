package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.dao.imp.EmployeeDaoImp;
import com.lovo.netCRM.dao.imp.SchoolDaoImp;
import com.lovo.netCRM.util.String2Int;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/25.
 */
public class ConnectionServiceImp {
    //根据学校的ID找出员工的ID,再通过员工的ID找出部门的ID,在通过部门的ID找出所有员工
    public ArrayList<EmployeeBean> getAllEmpBySchoolID(int School) {
        //根据学校ID找到部门id
        EmployeeBean emp = ((SchoolBean)new SchoolDaoImp().getObjectByID(School)).getEmp();
        String deptName = emp.getDept();
        int deptID = String2Int.string2Int(emp, deptName);
        ArrayList<EmployeeBean> allEmps = new EmployeeDaoImp().getAllEmpByDeptID(deptID);
        return allEmps;
    }

}
