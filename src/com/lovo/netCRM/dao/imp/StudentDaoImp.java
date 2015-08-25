package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.bean.StudentBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class StudentDaoImp implements CrmDao{
    @Override
    public EmployeeBean login(String loginName, String passWord) {
        return null;
    }

    @Override
    public ArrayList<Object> getAllObjects() {
        return null;
    }

    @Override
    public boolean deleteObject(int ObjectID) {
        return false;
    }

    @Override
    public Object getObjectByID(int ObjectID) {
        //根据主键得对象
        Connection con = ConnectionSQL.createConnectionSQL();
        String SQL = "select * from student";
        StudentBean stu = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                stu = new StudentBean();
                stu.setId(rs.getInt(1));
                stu.setName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setAddress(rs.getString(4));
                stu.setBirthday(rs.getDate(5));
                stu.setDescribe(rs.getString(6));
                stu.setStatus(rs.getBoolean(7));
                stu.setVip(rs.getBoolean(8));
                stu.setPhone(rs.getString(9));
                stu.setFather(rs.getString(10));
                stu.setFatherPhone(rs.getString(11));
                stu.setMother(rs.getString(12));
                stu.setMotherPhone(rs.getString(13));
                //使用学生id查找学生回访记录,可以有多个
                stu.setRecallRecord(new RecallRecordDaoImp().getAllReacllsByStuID(stu.getId()));
                //使用班级ID查找班级,只有一个
                stu.setClasses((ClassesBean) new ClassesDaoImp().getObjectByStudentID(rs.getInt(15)));
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
        if(stu != null){
            return stu;
        }else
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


    public boolean addObject(Object object,int schoolID) {
        StudentBean newStudent = (StudentBean)object;
        Connection con = ConnectionSQL.createConnectionSQL();
        int result = -1;
        String addSQL = "insert into student(\n" +
                "stu_name,\n" +
                "stu_sex,\n" +
                "stu_address,\n" +
                "stu_birthday,\n" +
                "stu_describe,\n" +
                "stu_status,\n" +
                "stu_vip,\n" +
                "stu_phone,\n" +
                "stu_father,\n" +
                "stu_fatherPhone,\n" +
                "stu_mother,\n" +
                "stu_motherPhone,\n" +
                "classes_id,\n" +
                "school_id)\n" +
                "values(\n" +
                "?,?,?,?,?,\n" +
                "?,?,?,?,?,\n" +
                "?,?,?,?);";
        try {
            PreparedStatement ps = con.prepareStatement(addSQL);
            ps.setString(1, newStudent.getName());
            ps.setString(2, newStudent.getSex());
            ps.setString(3, newStudent.getAddress());
            ps.setDate(4, new java.sql.Date(newStudent.getBirthday().getTime()));
            ps.setString(5, newStudent.getDescribe());
            ps.setBoolean(6, newStudent.isStatus());
            ps.setBoolean(7, newStudent.isVip());
            ps.setString(8, newStudent.getPhone());
            ps.setString(9, newStudent.getFather());
            ps.setString(10,newStudent.getFatherPhone());
            ps.setString(11,newStudent.getMother());
            ps.setString(12,newStudent.getMotherPhone());
            ps.setInt(13, newStudent.getClasses().getId());
            ps.setInt(14,schoolID);

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


    //查找所有满足在同一个学校的学生,返回这个集合
    public ArrayList<StudentBean> getStudentBySchlloID(int schID){
        Connection con = ConnectionSQL.createConnectionSQL();
        StudentBean stu = null;
        ArrayList<StudentBean> allStus = new ArrayList<StudentBean>();
        String sql = "select * from student where school_id = " + schID ;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                stu = new StudentBean();
                stu.setId(rs.getInt(1));
                stu.setName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setAddress(rs.getString(4));
                stu.setBirthday(rs.getDate(5));
                stu.setDescribe(rs.getString(6));
                stu.setStatus(rs.getBoolean(7));
                stu.setVip(rs.getBoolean(8));
                stu.setPhone(rs.getString(9));
                stu.setFather(rs.getString(10));
                stu.setFatherPhone(rs.getString(11));
                stu.setMother(rs.getString(12));
                stu.setMotherPhone(rs.getString(13));
                //使用学生id查找学生回访记录,可以有多个
                stu.setRecallRecord(new RecallRecordDaoImp().getAllReacllsByStuID(stu.getId()));
                //使用班级ID查找班级,只有一个
                stu.setClasses((ClassesBean) new ClassesDaoImp().getObjectByStudentID(rs.getInt(15)));
                if(stu.isStatus()){
                    allStus.add(stu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if(allStus.size() != 0){
            return allStus;
        }else
            return null;
    }



}
