package com.lovo.netCRM.service;

import com.lovo.netCRM.bean.EmployeeBean;

import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/21.
 */
public interface EmployeeService {
    //登录抽象方法
    public EmployeeBean login(String loginName, String passWord);

    //取得所有员工信息
    public ArrayList<Object> getAllStaffs();

    //删除员工信息
    public boolean deleteStaff(int EmployeeID);

    //按ID查找用户信息
    public EmployeeBean getStaffByID(int EmployeeID);

    //修改员工数据
    public boolean alterStaff(int empID,EmployeeBean alterEmp);

    //添加新员工
    public boolean addStaff(int id,EmployeeBean newEmp);

    //按条件查找
    public ArrayList<Object> getStaffByCon(String item,String value);

    //根据名字得到对象
    public EmployeeBean getEmpByName(String name);

}
