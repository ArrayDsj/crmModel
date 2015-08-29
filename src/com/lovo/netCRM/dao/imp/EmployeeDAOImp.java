package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.DepartBean;
import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.bean.PositionBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;
import com.lovo.netCRM.util.String2Int;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CodeA on 2015/8/21.
 */
public class EmployeeDaoImp implements CrmDao {
    //员工的登录操作
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
                 loginEmp.setID(rs.getInt(1));
                 loginEmp.setName(rs.getString(4));
                 //loginEmp.setSex(rs.getString(5));
                 //loginEmp.setBirthday(rs.getDate(6));
                 //loginEmp.setEdu(rs.getString(7));
                 //loginEmp.setSpeciality(rs.getString(8));
                 //loginEmp.setPhone(rs.getString(9));
                 //loginEmp.setAddress(rs.getString(10));
                 //loginEmp.setPolity(rs.getString(11));
                 //loginEmp.setHireDay(rs.getDate(12));
                 //loginEmp.setStatus(rs.getBoolean(13));
                 loginEmp.setDept(rs.getString(14));
                 loginEmp.setPosition(rs.getString(15));
                 //loginEmp.setHeadFile(rs.getString(16));
                 DepartBean dept = new DepartBean();
                 dept = (DepartBean)new DepartDaoImp().getObjectByID(Integer.parseInt(rs.getString(14)));
                 loginEmp.setDepart(dept);
                 PositionBean pos = new PositionBean();
                 pos = (PositionBean)new PositionDaoImp().getObjectByID(Integer.parseInt(rs.getString(15)));
                 loginEmp.setPos(pos);
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
    //查询全部员工
    public ArrayList<Object> getAllObjects() {
        ArrayList<Object> empList = new ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();
        //查找当前用户信息
        String allEmp = "select * from staff s\n" +
                "join depart d \n" +
                "on s.staff_department_id = d.depart_id\n" +
                "join t_position p\n" +
                "on s.staff_position_id = p.position_id\n";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(allEmp);
            while(rs.next()){
                //先判断这个员工的状态
                //如果员工属于离职状态则不显示
                if(!rs.getBoolean(13)){
                    continue;
                }
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
                emp.setHeadFile(rs.getString(16));
                emp.setDept(rs.getString(18));
                emp.setPosition(rs.getString(22));
                empList.add(emp);
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
    //按条件查询用户信息
    public ArrayList<Object> getObjectByCon(String item, String value) {
        ArrayList<Object> result = new  ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();
        EmployeeBean emocounts = new  EmployeeBean();
        String conSQL = null;
        //记录满足条件的记录的条数;
        int counts = 0;
        //根据item来确认SQL语句
        switch(item){
            case "所有员工" :
                conSQL ="select * from staff s\n" +
                        "join depart d \n" +
                        "on s.staff_department_id = d.depart_id\n" +
                        "join t_position p\n" +
                        "on s.staff_position_id = p.position_id\n" +
                        "order by staff_id\n" ;
                break;

            case "员工姓名" :
                conSQL ="select * from staff s\n" +
                        "join depart d \n" +
                        "on s.staff_department_id = d.depart_id\n" +
                        "join t_position p\n" +
                        "on s.staff_position_id = p.position_id\n" +
                        "where s.staff_name like '%" + value + "% '\n" +
                        "order by staff_id\n" ;
                break;

            case "所属部门" :
                conSQL =  "select * from staff s\n" +
                        "join depart d \n" +
                        "on s.staff_department_id = d.depart_id\n" +
                        "join t_position p\n" +
                        "on s.staff_position_id = p.position_id\n" +
                        " where d.depart_name like '%" + value + "%'\n" +
                        "order by staff_id\n" ;
                break;

            case "文化程度" :
                conSQL =  "select * from staff s\n" +
                        "join depart d \n" +
                        "on s.staff_department_id = d.depart_id\n" +
                        "join t_position p\n" +
                        "on s.staff_position_id = p.position_id\n" +
                        "where s.staff_edu like '%" + value + "%'\n" +
                        "order by staff_id\n" ;

                break;

            case "工作职位" :
                conSQL =  "select * from staff s\n" +
                        "join depart d \n" +
                        "on s.staff_department_id = d.depart_id\n" +
                        "join t_position p\n" +
                        "on s.staff_position_id = p.position_id\n" +
                        " where p.position_name like '%" + value + "%'\n" +
                        "order by staff_id\n" ;
                break;
        }
        try {
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(conSQL);

            while(rs.next()){
                if(!rs.getBoolean(13)){
                    continue;
                }
                counts++;
            }
            emocounts.setID(counts);
            result.add(emocounts);
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
        return result;
    }

    public ArrayList<Object> getObjectByCon(int pageNow,int pageSize,String item, String value) {
        ArrayList<Object> empListByCon = new ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();
        String conSQL = null;
        //根据item来确认SQL语句
        switch(item){
            case "所有员工" :
                conSQL ="select * from staff s\n" +
                        "join depart d \n" +
                        "on s.staff_department_id = d.depart_id\n" +
                        "join t_position p\n" +
                        "on s.staff_position_id = p.position_id\n" +
                        "order by staff_id\n" +
                        "limit " + (pageNow - 1) * pageSize + "," + pageSize + ";";
                break;

            case "员工姓名" :
                conSQL ="select * from staff s\n" +
                        "join depart d \n" +
                        "on s.staff_department_id = d.depart_id\n" +
                        "join t_position p\n" +
                        "on s.staff_position_id = p.position_id\n" +
                        "where s.staff_name like '%" + value + "% '\n" +
                        "order by staff_id\n" +
                        "limit " + (pageNow - 1) * pageSize + "," + pageSize +";";
                break;

            case "所属部门" :
                conSQL =  "select * from staff s\n" +
                        "join depart d \n" +
                        "on s.staff_department_id = d.depart_id\n" +
                        "join t_position p\n" +
                        "on s.staff_position_id = p.position_id\n" +
                        " where d.depart_name like '%" + value + "%'\n" +
                        "order by staff_id\n" +
                        "limit " + (pageNow - 1) * pageSize + "," + pageSize + ";";
                break;

            case "文化程度" :
                conSQL =  "select * from staff s\n" +
                        "join depart d \n" +
                        "on s.staff_department_id = d.depart_id\n" +
                        "join t_position p\n" +
                        "on s.staff_position_id = p.position_id\n" +
                        "where s.staff_edu like '%" + value + "%'\n" +
                        "order by staff_id\n" +
                        "limit " + (pageNow - 1) * pageSize + "," + pageSize + ";";

                break;

            case "工作职位" :
                conSQL =  "select * from staff s\n" +
                        "join depart d \n" +
                        "on s.staff_department_id = d.depart_id\n" +
                        "join t_position p\n" +
                        "on s.staff_position_id = p.position_id\n" +
                        " where p.position_name like '%" + value + "%'\n" +
                        "order by staff_id\n" +
                        "limit " + (pageNow - 1) * pageSize + "," + pageSize + ";";
                break;
        }
        try {
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(conSQL);
            while(rs.next()){
                if(!rs.getBoolean(13)){
                    continue;
                }
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
                emp.setDept(rs.getString(18));
                emp.setPosition(rs.getString(22));
                empListByCon.add(emp);
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

        if(empListByCon.size() != 0){
            return empListByCon;
        }else {
            return null;
        }
    }


    @Override
    public Object getObjectByName(String name) {
        Connection con = ConnectionSQL.createConnectionSQL();
        //查找当前用户信息
        String allEmp = "select * from staff s\n" +
                "join depart d \n" +
                "on s.staff_department_id = d.depart_id\n" +
                "join t_position p\n" +
                "on s.staff_position_id = p.position_id\n" +
                "and staff_name = '" + name + "'";
        EmployeeBean emp = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(allEmp);
            while(rs.next()){
                //组合员工信息
                if(!rs.getBoolean(13)){
                    continue;
                }
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
                emp.setHeadFile(rs.getString(16));
                emp.setDept(rs.getString(18));
                emp.setPosition(rs.getString(22));
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
        }else {
            //JOptionPane.showMessageDialog(null,"集合中没有员工");
            return null;
        }
    }

    @Override
    public Object getObjectByID(int ObjectID) {
        Connection con = ConnectionSQL.createConnectionSQL();
        //按ID查找指定用户
        String getObjectByIDSQL = "select * from staff s\n" +
                "join depart d \n" +
                "on s.staff_department_id = d.depart_id\n" +
                "join t_position p\n" +
                "on s.staff_position_id = p.position_id\n" +
                "where staff_id =" + ObjectID;
        EmployeeBean emp = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getObjectByIDSQL);
            while(rs.next()){
                if(!rs.getBoolean(13)){
                    continue;
                }
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
                emp.setHeadFile(rs.getString(16));
                emp.setDept(rs.getString(18));
                emp.setPosition(rs.getString(22));
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
    //修改员工信息
    public boolean alterObject(int objID,Object alterObj) {
        EmployeeBean alterEmp = (EmployeeBean)alterObj;
        Connection con = ConnectionSQL.createConnectionSQL();
        int id = alterEmp.getID();
        //传进来的员工是已经修改过后的员工信息
        //根据员工Bean中的信息,找出部门和职位所代表的数字
        String dept = alterEmp.getDept();
        String pos = alterEmp.getPosition();
        String polity = alterEmp.getPolity();
        int deptID = String2Int.string2Int(alterEmp, dept);
        //System.out.println(deptID);
        int posID = String2Int.string2Int(alterEmp, pos);
        //System.out.println(posID);
        String alterSQL = "update staff set \n" +
                "staff_polity = ?,\n"+
                "staff_phone = ?,\n" +
                "staff_department_id = ?,\n" +
                "staff_position_id = ?\n" +
                "where staff_id = ?;";
        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(alterSQL);
            ps.setString(1,polity);
            ps.setString(2, alterEmp.getPhone());
            ps.setInt(3,deptID);
            ps.setInt(4, posID);
            ps.setInt(5, id);
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


    //新增员工
    public boolean addObject(Object object) {
        EmployeeBean newEmp = (EmployeeBean)object;
        //根据新员工的信息向数据库中添加员工
        Connection con = ConnectionSQL.createConnectionSQL();
        String staff_name = newEmp.getName();
        String staff_loginName = newEmp.getLoginName();
        String staff_passWord = newEmp.getPassWord();
        String staff_sex = newEmp.getSex();
        Date staff_birthday = newEmp.getBirthday();
        String staff_speciality = newEmp.getSpeciality();
        String staff_edu = newEmp.getEdu();
        String staff_phone = newEmp.getPhone();
        Date staff_hireday = newEmp.getHireDay();
        String staff_polity = newEmp.getPolity();
        String staff_address = newEmp.getAddress();
        boolean staff_status = newEmp.isStatus();
        String staff_headFile = newEmp.getHeadFile();
        int deptID = String2Int.string2Int(newEmp, newEmp.getDept());
        int posID = String2Int.string2Int(newEmp,newEmp.getPosition());

        int result = -1;
        String addSQL = "insert into staff(\n" +
                "staff_name,\n" +//姓名
                "staff_sex,\n" + //性别
                "staff_birthday,\n" + //生日
                "staff_edu,\n" + //学历
                "staff_speciality,\n" + //专业
                "staff_phone,\n" +  //电话
                "staff_address,\n" + //地址
                "staff_polity,\n" + //政治面貌
                "staff_hireday,\n" + //入职时间
                "staff_status,\n" + //状态
                "staff_department_id,\n" + //部门
                "staff_position_id,staff_headFile," +
                "staff_loginName,\n"+
                "staff_passWord\n"+
                ") values(\n" + //职位
                "?,?,\n" +
                "?,?,?,\n" +
                "?,?,?,\n" +
                "?,?,?,?,?,\n" +
                "?,?);";
        try {
            PreparedStatement ps = con.prepareStatement(addSQL);
            ps.setString(1, staff_name);
            ps.setString(2, staff_sex);
            ps.setDate(3, new java.sql.Date(staff_birthday.getTime()));
            ps.setString(4, staff_edu);
            ps.setString(5, staff_speciality);
            ps.setString(6,staff_phone);
            ps.setString(7,staff_address);
            ps.setString(8, staff_polity);
            ps.setDate(9, new java.sql.Date(staff_hireday.getTime()));
            ps.setBoolean(10, staff_status);//状态
            ps.setInt(11, deptID);
            ps.setInt(12, posID);
            ps.setString(13, staff_headFile);
            ps.setString(14, staff_loginName);
            ps.setString(15, staff_passWord);
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
    public boolean addObject(int objectID, Object object) {
        EmployeeBean newEmp = (EmployeeBean)object;
        //根据新员工的信息向数据库中添加员工
        Connection con = ConnectionSQL.createConnectionSQL();
        String staff_name = newEmp.getName();
        String staff_loginName = newEmp.getLoginName();
        String staff_passWord = newEmp.getPassWord();
        String staff_sex = newEmp.getSex();
        Date staff_birthday = newEmp.getBirthday();
        String staff_speciality = newEmp.getSpeciality();
        String staff_edu = newEmp.getEdu();
        String staff_phone = newEmp.getPhone();
        Date staff_hireday = newEmp.getHireDay();
        String staff_polity = newEmp.getPolity();
        String staff_address = newEmp.getAddress();
        boolean staff_status = newEmp.isStatus();
        String staff_headFile = newEmp.getHeadFile();
        int deptID = String2Int.string2Int(newEmp, newEmp.getDept());
        int posID = String2Int.string2Int(newEmp,newEmp.getPosition());

        int result = -1;
        String addSQL = "insert into staff(\n" +
                "staff_name,\n" +//姓名
                "staff_sex,\n" + //性别
                "staff_birthday,\n" + //生日
                "staff_edu,\n" + //学历
                "staff_speciality,\n" + //专业
                "staff_phone,\n" +  //电话
                "staff_address,\n" + //地址
                "staff_polity,\n" + //政治面貌
                "staff_hireday,\n" + //入职时间
                "staff_status,\n" + //状态
                "staff_department_id,\n" + //部门
                "staff_position_id,staff_headFile," +
                "staff_loginName,\n"+
                "staff_passWord\n"+
                ") values(\n" + //职位
                "?,?,\n" +
                "?,?,?,\n" +
                "?,?,?,\n" +
                "?,?,?,?,?,\n" +
                "?,?);";
        try {
            PreparedStatement ps = con.prepareStatement(addSQL);
            ps.setString(1, staff_name);
            ps.setString(2, staff_sex);
            ps.setDate(3, new java.sql.Date(staff_birthday.getTime()));
            ps.setString(4, staff_edu);
            ps.setString(5, staff_speciality);
            ps.setString(6,staff_phone);
            ps.setString(7,staff_address);
            ps.setString(8, staff_polity);
            ps.setDate(9, new java.sql.Date(staff_hireday.getTime()));
            ps.setBoolean(10, staff_status);//状态
            ps.setInt(11, deptID);
            ps.setInt(12, posID);
            ps.setString(13, staff_headFile);
            ps.setString(14, staff_loginName);
            ps.setString(15, staff_passWord);
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
    public boolean deleteObject(int ObjectID) {
        //根据员工ID删除员工
        //不是物理删除,只是把stauts设置为1
        Connection con = ConnectionSQL.createConnectionSQL();
        //修改用户状态信息
        String deletEmpSQL = "update staff set staff_status = 0," +
                            "staff_loginName = NULL," +
                            "staff_passWord = NULL where staff_id = " + ObjectID;
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


    /**
     * 通过部门ID查找所有的员工
     */

    public ArrayList<EmployeeBean> getAllEmpByDeptID(int deptID){
        Connection con = ConnectionSQL.createConnectionSQL();
        ArrayList<EmployeeBean> emps = new ArrayList<EmployeeBean>();
        String sql = "select staff_id ,staff_name ,staff_status from staff where staff_department_id= " + deptID;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                if(!rs.getBoolean(3)){
                    continue;
                }
                EmployeeBean emp = new EmployeeBean();
                emp.setID(rs.getInt(1));
                emp.setName(rs.getString(2));
                emps.add(emp);
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

        if(emps.size() != 0){
            return emps;
        }else
            return null;
    }

}
