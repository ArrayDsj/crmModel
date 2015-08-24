package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.ActiveBean;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by CodeA on 2015/8/24.
 */
public class ActiveDaoImp {
    //添加活动记录
    public boolean addActive(ActiveBean active,int schoolId) {
        Connection con = ConnectionSQL.createConnectionSQL();
        String sql =
                "insert into active(active_name,active_address,\n" +
                "active_title,active_time,staff_id,school_id) \n" +
                "values(?,?,?,?,?,?);";
        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,active.getName());
            ps.setString(2,active.getAddress());
            ps.setString(3, active.getTitle());
            ps.setDate(4, new java.sql.Date(active.getTime().getTime()));
            ps.setInt(5, active.getEmp().getID());
            ps.setInt(6,schoolId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if(result == 1){
            return true;
        }
        return false;
    }

    //参看活动记录
}
