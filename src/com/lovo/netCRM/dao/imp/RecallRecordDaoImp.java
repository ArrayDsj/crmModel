package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.RecallRecordBean;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
