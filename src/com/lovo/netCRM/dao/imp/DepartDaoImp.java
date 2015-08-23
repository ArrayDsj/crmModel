package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CodeA on 2015/8/22.
 */
public class DepartDaoImp implements CrmDao{
    @Override
    public EmployeeBean login(String loginName, String passWord) {
        return null;
    }

    @Override
    public ArrayList<Object> getAllObjects() {
        ArrayList<Object> departs = new ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();

        String allDepartsName = "select * from depart";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(allDepartsName);
            while (rs.next()) {
                DepartBean dept = new DepartBean();
                dept.setDepartID(rs.getInt(1));
                dept.setDepartName(rs.getString(2));
                dept.setBuildTime(rs.getDate(3));
                dept.setDescribe(rs.getString(4));
                departs.add(dept);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(departs.size() != 0){
            return departs;
        }else
            return null;
    }

    @Override
    public boolean deleteObject(int ObjectID) {
        return false;
    }

    @Override
    public Object getObjectByID(int ObjectID) {
        Connection con = ConnectionSQL.createConnectionSQL();
        //按ID查找指定用户
        String getObjectByIDSQL = "select * from depart \n" +
                "where depart_id =" + ObjectID;
        DepartBean dept = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getObjectByIDSQL);
            while(rs.next()){
                //组合员工信息
                dept = new DepartBean();
                dept.setDepartID(rs.getInt(1));
                dept.setDepartName(rs.getString(2));
                dept.setBuildTime(rs.getDate(3));
                dept.setDescribe(rs.getString(4));
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
        if(dept != null){
            return dept;
        }else
            return null;
    }

    @Override
    public boolean alterObject(Object alterObj) {
        DepartBean alterDept = (DepartBean)alterObj;
        int departID = alterDept.getDepartID();
        Connection con = ConnectionSQL.createConnectionSQL();
        String deptDescribe = alterDept.getDescribe();
        String alterSQL = "update depart set \n" +
                "depart_describe = ?\n"+
                "where depart_id = ?;";
        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(alterSQL);
            ps.setString(1, deptDescribe);
            ps.setInt(2,departID);
            result = ps.executeUpdate();
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
    public boolean addObject(Object object) {
        DepartBean newDept = (DepartBean)object;
        String name = newDept.getDepartName();
        String describe = newDept.getDescribe();
        Date buildTime = newDept.getBuildTime();

        Connection con = ConnectionSQL.createConnectionSQL();
        int result = -1;
        String addSQL = "insert into depart(\n" +
                "depart_name,\n" +
                "depart_settime,\n" +
                "depart_describe)\n" +
                "values(\n" +
                "?,\n" +
                "?,\n" +
                "?);";
        try {
            PreparedStatement ps = con.prepareStatement(addSQL);
            ps.setString(1, name);
            ps.setDate(2, new java.sql.Date(buildTime.getTime()));
            ps.setString(3, describe);
            result = ps.executeUpdate();
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
    public ArrayList<Object> getObjectByCon(String item, String value) {
        return null;
    }
}
