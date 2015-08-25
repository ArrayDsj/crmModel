package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.bean.RecallRecordBean;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/25.
 */
public class RecallRecordDaoImp {
    //根据学生ID返回所有回访记录集合
    public ArrayList<RecallRecordBean> getAllReacllsByStuID(int stuID){
        Connection con = ConnectionSQL.createConnectionSQL();
        RecallRecordBean recall = null;
        ArrayList<RecallRecordBean> allRecalls = new ArrayList<RecallRecordBean>();
        String sql = "select * from recallrecord where student_id = " + stuID ;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                recall = new RecallRecordBean();
                recall.setId(rs.getInt(1));
                recall.setTime(rs.getDate(2));
                recall.setDescribe(rs.getString(3));
                recall.setRecallMan(rs.getString(4));
                recall.setTitle(rs.getString(5));
                //查出谁负责这个学生
                recall.setEmp((EmployeeBean) new EmployeeDaoImp().getObjectByID(rs.getInt(6)));
                allRecalls.add(recall);
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

        if(allRecalls.size() != 0){
            return allRecalls;
        }else
            return null;
    }


    //添加回访记录
    public boolean addObject(int stuID,Object obj){
        RecallRecordBean recall = (RecallRecordBean)obj;
        Connection con = ConnectionSQL.createConnectionSQL();
        int result = -1;
        String addSQL = "insert into recallrecord(\n" +
                "recallRecord_time,\n" +
                "recallRecord_describe,\n" +
                "recallRecord_recallMan,\n" +
                "recallRecord_title,\n" +
                "staff_id,\n" +
                "student_id)\n" +
                "values(\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?);";

        try {
            PreparedStatement ps = con.prepareStatement(addSQL);
            ps.setDate(1, new java.sql.Date(recall.getTime().getTime()));
            ps.setString(2, recall.getDescribe());
            ps.setString(3, recall.getRecallMan());
            ps.setString(4, recall.getTitle());
            ps.setInt(5, recall.getEmp().getID());
            ps.setInt(6,stuID);
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

}
