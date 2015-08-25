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
    //����ѧУ��ID�ҳ�Ա����ID,��ͨ��Ա����ID�ҳ����ŵ�ID,��ͨ�����ŵ�ID�ҳ�����Ա��
    public ArrayList<EmployeeBean> getAllEmpBySchoolID(int School) {
        //����ѧУID�ҵ�����id
        EmployeeBean emp = ((SchoolBean)new SchoolDaoImp().getObjectByID(School)).getEmp();
        String deptName = emp.getDept();
        int deptID = String2Int.string2Int(emp, deptName);
        ArrayList<EmployeeBean> allEmps = new EmployeeDaoImp().getAllEmpByDeptID(deptID);
        return allEmps;
    }

}
