package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.ClassesBean;
import com.lovo.netCRM.bean.StudentBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class StudentDaoImp implements CrmDao{


    public boolean deleteObject(int ObjectID,StudentBean stu) {
        Connection con = ConnectionSQL.createConnectionSQL();
        int classID = stu.getClasses().getId();
        //�޸��û�״̬��Ϣ
        String deletSQL = "update student set stu_status = 0 ,stu_vip = 0  where stu_id = " + ObjectID;
        String deletNum = "update classes set class_stuNum = class_stuNum - 1" +
                " where class_id = " + classID + ";";
        int result = 0;
        try {
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            st.executeUpdate(deletSQL);
            result++;
            st.executeUpdate(deletNum);
            result++;
            con.commit();
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
        if(result == 2){
            return true;
        }else
            return false;
    }




    @Override
    public boolean alterObject(int schID,Object alterObj) {
        StudentBean stu = (StudentBean)alterObj;
        Connection con = ConnectionSQL.createConnectionSQL();
        String alterSQL =
                "update student set stu_phone = ? ," +
                        "stu_fatherPhone = ?," +
                        "stu_motherPhone = ?," +
                        "stu_describe = ?," +
                        "classes_id = ?, " +
                        "stu_vip = ? " +
                        "where stu_id = ?";

        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(alterSQL);
            ps.setString(1,stu.getPhone());
            ps.setString(2,stu.getFatherPhone());
            ps.setString(3, stu.getMotherPhone());
            ps.setString(4,stu.getDescribe());
            ps.setInt(5, stu.getClasses().getId());
            ps.setBoolean(6, stu.isVip());
            ps.setInt(7, schID);
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
    public boolean addObject(int schoolID,Object object) {
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

        String alterSQL = "update classes set class_stuNum = class_stuNum + 1" +
                " where class_id = ?";
        try {
            //�ر��Զ��ύ,��������
            con.setAutoCommit(false);
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
            ps.setString(10, newStudent.getFatherPhone());
            ps.setString(11,newStudent.getMother());
            ps.setString(12, newStudent.getMotherPhone());
            ps.setInt(13, newStudent.getClasses().getId());
            ps.setInt(14,schoolID);

            result = ps.executeUpdate();

            ps = con.prepareStatement(alterSQL);
            ps.setInt(1,newStudent.getClasses().getId());
            ps.executeUpdate();
            con.commit();
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
        ArrayList<Object> stuListByCon = new ArrayList<Object>();
        StudentBean stu = null;
        Connection con = ConnectionSQL.createConnectionSQL();

        String conSQL = null;
        //����item��ȷ��SQL���
        switch(item){
            case "ѧ������" :
                if(value == null || value.trim().equals("")){
                    value = "";
                }
                conSQL ="select stu_id,stu_name, \n" +
                        "stu_sex,stu_status, \n" +
                        "stu_vip,stu_phone,class_name\n" +
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "where stu_name like '%" + value + "%'" ;
                        //"or stu_sex like '% '";
                break;

            case "�༶" :
                conSQL ="select stu_id,stu_name, \n" +
                        "stu_sex,stu_status, \n" +
                        "stu_vip,stu_phone,class_name\n" +
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "where class_name =  like '%" + value + "%';";
                break;

            case "��Ա" :
                conSQL = "select stu_id,stu_name, \n" +
                        "stu_sex,stu_status, \n" +
                        "stu_vip,stu_phone,class_name\n" +
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "where  stu_vip = 1";
                break;

            case "�ǻ�Ա" :
                conSQL = "select stu_id,stu_name, \n" +
                        "stu_sex,stu_status, \n" +
                        "stu_vip,stu_phone,class_name\n" +
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "where  stu_vip = 0";
                break;
        }
        try {
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(conSQL);
            while(rs.next()){
                if (!rs.getBoolean(4)){
                    continue;
                }
                stu = new StudentBean();
                stu.setId(rs.getInt(1));
                stu.setName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setVip(rs.getBoolean(5));
                stu.setPhone(rs.getString(6));
                //ʹ�ð༶ID���Ұ༶,ֻ��һ��
                stu.setClasses(new ClassesDaoImp().getClassesByName(rs.getString(7)));
                stuListByCon.add(stu);
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

        if(stuListByCon.size() != 0){
            return stuListByCon;
        }else {
            JOptionPane.showMessageDialog(null, "�޲�ѯ���");
            return null;
        }
    }


    /**
     * ����ѧ��
     * @param schoolID ѧУid
     * @param pageNow ҳ��
     */
    public ArrayList<Object> getObjectByCon(int schoolID,int pageNow,String item, String value) {
        ArrayList<Object> stuListByCon = new ArrayList<Object>();
        StudentBean stu = null;
        int pageSize = 3;
        Connection con = ConnectionSQL.createConnectionSQL();
        String conSQL = null;
        String countsSQL = null;
        ResultSet rs = null;
        //����item��ȷ��SQL���
        switch(item){
            case "����"://����������ѯȫ������ϢȻ���ҳ
                conSQL ="SELECT stu_id,stu_name, \n" +
                        "stu_sex,stu_status, \n" +
                        "stu_vip,stu_phone,class_name\n" +
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "AND s.school_id = " + schoolID +"\n"+
                        "and s.stu_status = 1\n"+
                        "order by stu_id DESC \n"+
                        "limit " + (pageNow - 1) * pageSize + "," + pageSize + ";\n" ;

                countsSQL = "SELECT count(*)\n"+
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "and s.stu_status = 1\n"+
                        "AND s.school_id = " + schoolID +"\n";
                break;
            case "ѧ������" :
                conSQL ="SELECT  stu_id,stu_name, \n" +
                        "stu_sex,stu_status, \n" +
                        "stu_vip,stu_phone,class_name\n" +
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "where stu_name like '%" + value + "%'\n" +
                        "AND s.school_id = " + schoolID +"\n"+
                        "and s.stu_status = 1\n"+
                        "order by stu_id DESC \n"+
                        "limit " + (pageNow - 1) * pageSize + "," + pageSize + ";\n" ;

                countsSQL = "SELECT count(*)\n"+
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "AND s.school_id = " + schoolID +"\n"+
                        "and s.stu_status = 1\n"+
                        "where stu_name like '%" + value + "%'\n" ;
                break;

            case "�༶" :
                conSQL ="SELECT  stu_id,stu_name, \n" +
                        "stu_sex,stu_status, \n" +
                        "stu_vip,stu_phone,class_name\n" +
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "where class_name   like '%" + value + "%'\n" +
                        "and s.stu_status = 1\n"+
                        "AND s.school_id = " + schoolID +"\n"+
                        "order by stu_id DESC \n"+
                        "limit " + (pageNow - 1) * pageSize + "," + pageSize + ";\n";

                countsSQL = "SELECT count(*)\n"+
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "AND s.school_id = " + schoolID +"\n"+
                        "and s.stu_status = 1\n"+
                        "where class_name like '%" + value + "%'\n" ;

                break;

            case "��Ա" :
                conSQL = "SELECT  stu_id,stu_name, \n" +
                        "stu_sex,stu_status, \n" +
                        "stu_vip,stu_phone,class_name\n" +
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "and s.stu_status = 1\n"+
                        "AND s.school_id = " + schoolID +"\n"+
                        "where  stu_vip = 1 \n"+
                        "order by stu_id DESC \n"+
                        "limit " + (pageNow - 1) * pageSize + "," + pageSize + ";\n";

                countsSQL = "SELECT count(*)\n"+
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "AND s.school_id = " + schoolID +"\n"+
                        "and s.stu_status = 1\n"+
                        "where  stu_vip = 1 \n";

                break;

            case "�ǻ�Ա" :
                conSQL = "SELECT  stu_id,stu_name, \n" +
                        "stu_sex,stu_status, \n" +
                        "stu_vip,stu_phone,class_name\n" +
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "and s.stu_status = 1\n"+
                        "AND s.school_id = " + schoolID +"\n"+
                        "where  stu_vip = 0 \n"+
                        "order by stu_id DESC \n"+
                        "limit " + (pageNow - 1) * pageSize + "," + pageSize + ";\n";

                countsSQL = "SELECT count(*)\n"+
                        "from student s\n" +
                        "join classes c\n" +
                        "on s.classes_id = c.class_id\n"+
                        "AND s.school_id = " + schoolID +"\n"+
                        "and s.stu_status = 1\n"+
                        "where  stu_vip = 0 \n";

                break;
        }
        try {
            Statement st  = con.createStatement();

            //��һ�������
            rs = st.executeQuery(countsSQL);
            //���ָ��ָ����ǵ�һ��֮ǰ,����ʹ�÷�����ָ��ָ���һ��
            // rs.next()
            rs.first();
            int counts = -1;
            //�����������Ϊ�� ��ʼ�ڶ��β�ѯ
            if(rs.getInt(1) != 0){
                counts = rs.getInt(1);
                //ȡ�õڶ���sql���Ľ��
                rs = st.executeQuery(conSQL);
                while(rs != null && rs.next()){
                    if (!rs.getBoolean(4)){
                        continue;
                    }
                    stu = new StudentBean();
                    stu.setId(rs.getInt(1));
                    stu.setName(rs.getString(2));
                    stu.setSex(rs.getString(3));
                    stu.setVip(rs.getBoolean(5));
                    stu.setPhone(rs.getString(6));
                    //ʹ�ð༶ID���Ұ༶,ֻ��һ��
                    stu.setClasses(new ClassesDaoImp().getClassesByName(rs.getString(7)));
                    stuListByCon.add(stu);
                }
                if(stuListByCon != null){
                    StudentBean count = (StudentBean)stuListByCon.get(0);
                    count.setDescribe(counts + "");
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

        if(stuListByCon.size() != 0){
            return stuListByCon;
        }else {
           // JOptionPane.showMessageDialog(null, "�޲�ѯ���");
            return null;
        }
    }





    @Override
    public Object getObjectByName(String name) {
        return null;
    }


    //��������������ͬһ��ѧУ��ѧ��,�����������
    public ArrayList<StudentBean> getStudentsBySchoolID(int schID){
        Connection con = ConnectionSQL.createConnectionSQL();
        StudentBean stu = null;
        ArrayList<StudentBean> allStus = new ArrayList<StudentBean>();
        String sql = "select * from student where school_id = " + schID ;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                if(!rs.getBoolean(7)){
                    continue;
                }
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
                //ʹ��ѧ��id����ѧ���طü�¼,�����ж��
                stu.setRecallRecord(new RecallRecordDaoImp().getAllReacllsByStuID(stu.getId()));
                //ʹ�ð༶ID���Ұ༶,ֻ��һ��
                stu.setClasses((ClassesBean) new ClassesDaoImp().getObjectByID(rs.getInt(15)));
                allStus.add(stu);

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


    //���ݳ���ID��ҳ����
    public ArrayList<Object> getAllObjects(int schoolId,int pageNow,int pageSize) {
        Connection con = ConnectionSQL.createConnectionSQL();
        StudentBean stu = null;
        ArrayList<Object> allStus = new ArrayList<Object>();
        String sql = "select * from student " +
                    "\n where school_id = " + schoolId +
                    "limit " + (pageNow - 1) * pageSize + "," + pageSize +";";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                if(!rs.getBoolean(7)){
                    continue;
                }
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
                //ʹ��ѧ��id����ѧ���طü�¼,�����ж��
                stu.setRecallRecord(new RecallRecordDaoImp().getAllReacllsByStuID(stu.getId()));
                //ʹ�ð༶ID���Ұ༶,ֻ��һ��
                stu.setClasses((ClassesBean) new ClassesDaoImp().getObjectByID(rs.getInt(15)));
                allStus.add(stu);

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


    @Override
    public Object getObjectByID(int ObjectID) {
        //���������ö���
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
                //ʹ��ѧ��id����ѧ���طü�¼,�����ж��
                stu.setRecallRecord(new RecallRecordDaoImp().getAllReacllsByStuID(stu.getId()));
                //ʹ�ð༶ID���Ұ༶,ֻ��һ��
                stu.setClasses((ClassesBean) new ClassesDaoImp().getObjectByID(rs.getInt(15)));
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
    public ArrayList<Object> getAllObjects() {
        return null;
    }

    @Override
    public boolean deleteObject(int objectID) {
        return false;
    }

}