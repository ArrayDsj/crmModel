package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.bean.PositionBean;
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
public class PositionDaoImp implements CrmDao {
    @Override
    public EmployeeBean login(String loginName, String passWord) {
        return null;
    }

    @Override
    public ArrayList<Object> getAllObjects() {
        ArrayList<Object> positions = new ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();

        String allPositionsName = "select * from position";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(allPositionsName);
            while (rs.next()) {
                PositionBean pos = new PositionBean();
                //pos.setPositionID(rs.getInt(1));
                pos.setName(rs.getString(2));
                pos.setDescribe(rs.getString(3));
                positions.add(pos);
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
        if(positions.size() != 0){
            return positions;
        }else
            return null;
    }

    @Override
    public boolean deleteObject(int ObjectID) {
        return false;
    }

    @Override
    public Object getObjectByID(int ObjectID) {
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

    @Override
    public ArrayList<Object> getObjectByCon(String item, String value) {
        return null;
    }
}
