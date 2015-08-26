package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.service.ConnectionService;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/25.
 */
public class ConnectionServiceImp implements ConnectionService {
    //根据学校的ID找出员工的ID,再通过员工的ID找出部门的ID,在通过部门的ID找出所有员工
    public ArrayList<Object> getAllEmpBySchoolID(int School) {
        //根据学校ID找到部门id
        EmployeeBean emp = new SchoolServiceImp().getSchoolByID(School).getEmp();
        if(emp == null){
            return null;
        }
        //得到这个员工对象上的部门属性(String)
        String deptName = emp.getDept();
        //根据部门名称在部门表中查找这个部门的ID
        DepartBean dept = new DepartServiceImp().getDeptByName(deptName);
        if(dept == null){
            return null;
        }
        //ArrayList<EmployeeBean> allEmps = new EmployeeDaoImp().getAllEmpByDeptID(dept.getDepartID());

        ArrayList<Object> allEmps = new EmployeeServiceImp().getStaffByCon("部门", dept.getDepartID() + "");
        return allEmps;
    }
}
