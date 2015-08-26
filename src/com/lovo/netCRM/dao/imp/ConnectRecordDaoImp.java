package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.ConnectRecordBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/25.
 */
public class ConnectRecordDaoImp implements CrmDao {

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
        return null;
    }

    @Override
    public boolean alterObject(int objectID, Object object) {
        return false;
    }


    public boolean alterObject(Object alterObj) {
        return false;
    }


    public boolean addObject(Object object) {
        return false;
    }

    @Override
    public ArrayList<Object> getObjectByCon(String item, String value) {
        return null;
    }

    @Override
    public Object getObjectByName(String name) {
        return null;
    }

    @Override
    public boolean addObject(int schID,Object obj){
        ConnectRecordBean connectRecordBean = (ConnectRecordBean)obj;
        Connection con = ConnectionSQL.createConnectionSQL();

        int result = -1;
        String addSQL = "insert into connectrecord(\n" +
                "connectRecord_man,\n" +
                "connectRecord_time,\n" +
                "connectRecord_position,\n" +
                "connectRecord_describe,\n" +
                "staff_id,\n" +
                "school_id)\n" +
                "values(\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?);";

        try {
            PreparedStatement ps = con.prepareStatement(addSQL);
            ps.setString(1, connectRecordBean.getMan());
            ps.setDate(2,new Date(connectRecordBean.getTime().getTime()));
            ps.setString(3, connectRecordBean.getPos());
            ps.setString(4, connectRecordBean.getDescribe());
            ps.setInt(5, connectRecordBean.getEmp().getID());
            ps.setInt(6,schID);
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
