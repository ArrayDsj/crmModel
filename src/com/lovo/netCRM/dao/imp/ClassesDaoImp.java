package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.bean.SchoolBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class ClassesDaoImp implements CrmDao{
    @Override
    public ArrayList<Object> getObjectByCon(String item, String value) {
        return null;
    }

    @Override
    public Object getObjectByName(String name) {
        return null;
    }

    @Override
    public boolean deleteObject(int ObjectID) {
        return false;
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
    public Object getObjectByID(int ObjectID) {
        Connection con = ConnectionSQL.createConnectionSQL();
        String getSchoolSQL = "select * from classes where class_id = " + ObjectID;
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
                cla.setSchool((SchoolBean) new SchoolDaoImp().getObjectByID(rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(cla != null){
            return cla;
        }else
            return null;
    }


    public boolean alterObject(int ObjectID,Object object) {
        ClassesBean alterclasses = (ClassesBean)object;
        Connection con = ConnectionSQL.createConnectionSQL();
        String alterSQL = "update classes set class_teaName = ? ,class_stuNum = ?" +
                        " where class_id = ?";
        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(alterSQL);
            ps.setString(1, alterclasses.getTeaName());
            ps.setInt(2,alterclasses.getStuNum());
            ps.setInt(3,ObjectID);
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

    //添加学生时,对应的班级人数加1
    //@Override
//    public boolean alterObject(Object object){
//        ClassesBean alterclasses = (ClassesBean)object;
//
//        Connection con = ConnectionSQL.createConnectionSQL();
//        String alterSQL = "update classes set class_stuNum = ? " +
//                "where class_id = ?";
//        int result = -1;
//        try {
//            PreparedStatement ps = con.prepareStatement(alterSQL);
//            ps.setInt(1, alterclasses.getStuNum());
//            ps.setInt(2,alterclasses.getId());
//            result = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally{
//            if(con != null){
//                try {
//                    con.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        if(result == 1){
//            return true;
//        }else
//            return false;
//    }


    @Override
    public boolean addObject(int ObjectID,Object object){
        ClassesBean newClasses = (ClassesBean)object;
        Connection con = ConnectionSQL.createConnectionSQL();
        int result = -1;
        String addSQL = "insert into classes(\n" +
                "class_name,\n" +
                "class_buildTime,\n" +
                "class_stuNum,\n" +
                "class_teaName,\n" +
                "school_id)\n" +
                "values(\n" +
                "?,\n" +
                "?,\n" +
                "?," +
                "?," +
                "?);";
        try {
            PreparedStatement ps = con.prepareStatement(addSQL);
            ps.setString(1, newClasses.getName());
            ps.setDate(2, new java.sql.Date(newClasses.getBuildTime().getTime()));
            //初始化班级人数为0;
            ps.setInt(3,0);
            ps.setString(4, newClasses.getTeaName());
            ps.setInt(5, ObjectID);
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


    public ClassesBean getClassesByName(String  name){
        Connection con = ConnectionSQL.createConnectionSQL();
        String getclassSQL = "select * from classes where class_name = '" + name + "'";
        ClassesBean cla = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getclassSQL);
            while(rs.next()){
                cla = new ClassesBean();
                cla.setId(rs.getInt(1));
                cla.setName(rs.getString(2));
                cla.setBuildTime(rs.getDate(3));
                cla.setStuNum(rs.getInt(4));
                cla.setTeaName(rs.getString(5));
                cla.setSchool((SchoolBean) new SchoolDaoImp().getObjectByID(rs.getInt(6)));
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
        if(cla != null){
            return cla;
        }else
            return null;
    }




}
