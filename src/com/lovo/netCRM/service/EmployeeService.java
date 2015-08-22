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
    public ArrayList<Object> getAllStaffs();

    //ɾ��Ա����Ϣ
    public boolean deleteStaff(int EmployeeID);

    //��ID�����û���Ϣ
    public EmployeeBean getStaffByID(int EmployeeID);

    //�޸�Ա������
    public boolean alterStaff(EmployeeBean alterEmp);

    //�����Ա��
    public boolean addStaff(EmployeeBean newEmp);
}
