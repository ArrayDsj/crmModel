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
    public boolean alterStaff(int empID,EmployeeBean alterEmp);

    //�����Ա��
    public boolean addStaff(int id,EmployeeBean newEmp);

    //����������
    public ArrayList<Object> getStaffByCon(String item,String value);

    //�������ֵõ�����
    public EmployeeBean getEmpByName(String name);

}
