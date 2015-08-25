package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.*;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class ClassesDaoImp implements CrmDao{
    @Override
    public EmployeeBean login(String loginName, String passWord) {
        return null;
    }

    @Override
    public ArrayList<Object> getAllObjects() {
        ArrayList<Object> classes = new ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();
        String allClasses = "select * from classes";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(allClasses);
            while (rs.next()) {
                ClassesBean cla = new ClassesBean();
                cla.setId(rs.getInt(1));
                cla.setName(rs.getString(2));
                cla.setBuildTime(rs.getDate(3));
                cla.setStuNum(rs.getInt(4));
                cla.setTeaName(rs.getString(5));
                cla.setSchool((SchoolBean) new SchoolDaoImp().getObjectByID(rs.getInt(6)));
                classes.add(cla);
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
        if(classes.size() != 0){
            return classes;
        }else
            return null;
    }

    @Override
    public boolean deleteObject(int ObjectID) {
        return false;
    }

    @Override
    public Object getObjectByID(int ObjectID) {
        return null;
    }

    @Override
    public boolean alterObject(Object alterObj) {
        return false;
    }

    @Override
    public boolean addObject(Object object) {
        return false;
    }

    @Override
    public ArrayList<Object> getObjectByCon(String item, String value) {
        return null;
    }



    public Object getObjectByStudentID(int claID) {
        Connection con = ConnectionSQL.createConnectionSQL();
        String getSchoolSQL = "select * from classes where class_id = " + claID;
        ClassesBean cla = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getSchoolSQL);
            while(rs.next()){
                cla = new ClassesBean();
                cla.setId(rs.getInt(1));
                cla.setName(rs.getString(2));
                cla.setBuildTime(rs.getDate(3));
                cla.setStuNum(rs.getInt(4));
                cla.setTeaName(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(cla != null){
            return cla;
        }else
            return null;
    }


    public ArrayList<Object> getObjectByschID(int schID) {
        ArrayList<Object> clas = new ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();
        String getSchoolSQL = "select * from classes where school_id = " + schID;
        ClassesBean cla = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getSchoolSQL);
            while(rs.next()){
                cla = new ClassesBean();
                cla.setId(rs.getInt(1));
                cla.setName(rs.getString(2));
                cla.setBuildTime(rs.getDate(3));
                cla.setStuNum(rs.getInt(4));
                cla.setTeaName(rs.getString(5));
                clas.add(cla);
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
        if(clas.size() != 0){
            return clas;
        }else
            return null;
    }


}
