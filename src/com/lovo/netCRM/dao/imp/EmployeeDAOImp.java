package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.dao.EmployeeDAO;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by CodeA on 2015/8/21.
 */
public class EmployeeDAOImp implements EmployeeDAO{
    @Override
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
}
