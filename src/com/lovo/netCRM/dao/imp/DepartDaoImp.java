package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public class DepartDaoImp implements CrmDao{
    @Override
    public EmployeeBean login(String loginName, String passWord) {
        return null;
    }

    @Override
    public ArrayList<Object> getAllObjects() {
        ArrayList<Object> departsName = new ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();

        String allDepartsName = "select depart_name from depart";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(allDepartsName);
            while (rs.next()) {
                String dept = rs.getString(1);
                departsName.add(dept);
                System.out.println(dept);
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
        if(departsName.size() != 0){
            return departsName;
        }else
            return null;
    }

    @Override
    public boolean deleteObject(int ObjectID) {
        return false;
    }
}
