package com.lovo.netCRM.service.imp;

import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.service.ConnectionService;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/25.
 */
public class ConnectionServiceImp implements ConnectionService {
    //����ѧУ��ID�ҳ�Ա����ID,��ͨ��Ա����ID�ҳ����ŵ�ID,��ͨ�����ŵ�ID�ҳ�����Ա��
    public ArrayList<Object> getAllEmpBySchoolID(int School) {
        //����ѧУID�ҵ�����id
        EmployeeBean emp = new SchoolServiceImp().getSchoolByID(School).getEmp();
        if(emp == null){
            return null;
        }
        //�õ����Ա�������ϵĲ�������(String)
        String deptName = emp.getDept();
        //���ݲ��������ڲ��ű��в���������ŵ�ID
        DepartBean dept = new DepartServiceImp().getDeptByName(deptName);
        if(dept == null){
            return null;
        }
        //ArrayList<EmployeeBean> allEmps = new EmployeeDaoImp().getAllEmpByDeptID(dept.getDepartID());

        ArrayList<Object> allEmps = new EmployeeServiceImp().getStaffByCon("����", dept.getDepartID() + "");
        return allEmps;
    }
}
