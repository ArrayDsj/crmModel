package com.lovo.netCRM.util;

import com.lovo.netCRM.bean.EmployeeBean;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by CodeA on 2015/8/22.
 * 根据名字查ID
 */
public class String2Int {
    public static int string2Int(Object obj, String str){
        if(obj instanceof EmployeeBean){
            EmployeeBean emp = (EmployeeBean)obj;
            if(emp.getDept().equals(str)){
                int deptID = -1;
                //到数据库中查找
                String sql = "select depart_id from depart where depart_name = '" + str + "'";
                Connection con = ConnectionSQL.createConnectionSQL();
                try {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()){
                        deptID = rs.getInt(1);
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
                return deptID;
            }
            else if(emp.getPosition().equals(str)){
                int posID = -1;
                //到数据库中查找
                String sql = "select position_id from t_position where position_name = '" + str + "'";
                Connection con = ConnectionSQL.createConnectionSQL();
                try {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()){
                        posID = rs.getInt(1);
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
                return posID;
            }else
                JOptionPane.showMessageDialog(null,"参数错误");
        }
        return 0;
    }
}
