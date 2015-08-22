package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;
import com.lovo.netCRM.util.String2Int;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/21.
 */
public class EmployeeDaoImp implements CrmDao {
    @Override
    public EmployeeBean login(String loginName, String passWord) {
        Connection con = ConnectionSQL.createConnectionSQL();
        //查找当前用户信息
        String loginSQL = "select * from staff where staff_loginName = ? and staff_passWord = ?";
        EmployeeBean loginEmp = null;
        try {
            PreparedStatement ps = con.prepareStatement(loginSQL);
            ps.setString(1, loginName);
            ps.setString(2, passWord);
            ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 loginEmp = new EmployeeBean();
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if(loginEmp != null){
            return loginEmp;
        }else
            return null;
    }

    @Override
    public ArrayList<Object> getAllObjects() {
        ArrayList<Object> empList = new ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();
        //查找当前用户信息
        String allEmp = "select * from staff s\n" +
                "join depart d \n" +
                "on s.staff_department_id = d.depart_id\n" +
                "join position p\n" +
                "on s.staff_position_id = p.position_id";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(allEmp);
            while(rs.next()){
                //组合员工信息
                EmployeeBean emp = new EmployeeBean();
                emp.setID(rs.getInt(1));
                emp.setName(rs.getString(4));
                emp.setSex(rs.getString(5));
                emp.setBirthday(rs.getDate(6));
                emp.setEdu(rs.getString(7));
                emp.setSpeciality(rs.getString(8));
                emp.setPhone(rs.getString(9));
                emp.setAddress(rs.getString(10));
                emp.setPolity(rs.getString(11));
                emp.setHireDay(rs.getDate(12));
                emp.setStatus(rs.getBoolean(13));
                emp.setDept(rs.getString(17));
                emp.setPosition(rs.getString(21));
                //添加到集合中
                //如果员工属于离职状态则不显示
                if(!emp.isStatus()){
                    empList.add(emp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(empList.size() != 0){
           return empList;
        }else {
            JOptionPane.showMessageDialog(null,"集合中没有员工");
            return null;
        }
    }

    @Override
    public boolean deleteObject(int ObjectID) {
        //根据员工ID删除员工
        //不是物理删除,只是把stauts设置为1
        Connection con = ConnectionSQL.createConnectionSQL();
        //修改用户状态信息
        String deletEmpSQL = "update staff set staff_status = 1  where staff_id = " + ObjectID;
        int result = 0;
        try {
            Statement st = con.createStatement();
            result = st.executeUpdate(deletEmpSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(result == 1){
            return true;
        }else
            return false;
    }

    @Override
    public Object getObjectByID(int ObjectID) {
        Connection con = ConnectionSQL.createConnectionSQL();
        //按ID查找指定用户
        String getObjectByIDSQL = "select * from staff where staff_id = " + ObjectID;
        EmployeeBean emp = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getObjectByIDSQL);
            while(rs.next()){
                //组合员工信息
                emp = new EmployeeBean();
                emp.setID(rs.getInt(1));
                emp.setName(rs.getString(4));
                emp.setSex(rs.getString(5));
                emp.setBirthday(rs.getDate(6));
                emp.setEdu(rs.getString(7));
                emp.setSpeciality(rs.getString(8));
                emp.setPhone(rs.getString(9));
                emp.setAddress(rs.getString(10));
                emp.setPolity(rs.getString(11));
                emp.setHireDay(rs.getDate(12));
                emp.setStatus(rs.getBoolean(13));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(emp != null){
            return emp;
        }else
            return null;
    }

    @Override
    public boolean alterObject(EmployeeBean alterEmp) {
        Connection con = ConnectionSQL.createConnectionSQL();
        int id = alterEmp.getID();
        //传进来的员工是已经修改过后的员工信息

        //根据员工Bean中的信息,找出部门和职位所代表的数字
        String dept = alterEmp.getDept();
        String pos = alterEmp.getPosition();
        int deptID = String2Int.string2Int(alterEmp, dept);
        System.out.println(deptID);
        int posID = String2Int.string2Int(alterEmp,pos);
        System.out.println(posID);
//        String alterSQL = "update staff set staff_phone = '" + alterEmp.getPhone() +
//                "' , staff_department_id = " + deptID +
//                "  , staff_position_id = " + posID +
//                "  where staff_id = " + id;
        String aa = "update staff set \n" +
                    "staff_phone = ?,\n" +
                    "staff_department_id = ?,\n" +
                    "staff_position_id = ?\n" +
                    "where staff_id = ?;";
        int result = -1;
        try {
            //Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement(aa);
            ps.setString(1, alterEmp.getPhone());
            ps.setInt(2,deptID);
            ps.setInt(3, posID);
            ps.setInt(4, id);
            result = ps.executeUpdate();
            //result = st.executeUpdate(alterSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if(result == 1){
            return true;
        }else
            return false;
    }
}
