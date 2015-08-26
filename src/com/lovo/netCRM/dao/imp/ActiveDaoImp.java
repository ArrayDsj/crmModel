package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.ActiveBean;
import com.lovo.netCRM.bean.EmployeeBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.*;
import java.util.ArrayList;


/**
 * Created by CodeA on 2015/8/24.
 */
public class ActiveDaoImp implements CrmDao{

    //添加活动记录
    public boolean addObject(int objectId,Object object) {
        if(!(object instanceof ActiveBean)){
            return false;
        }
        ActiveBean active = (ActiveBean)object;
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
            ps.setInt(6, objectId);
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

    @Override
    public ArrayList<Object> getAllObjects() {
        return null;
    }

    @Override
    public boolean deleteObject(int objectID) {
        return false;
    }

    @Override
    public Object getObjectByID(int objectID) {
        return null;
    }

    @Override
    public boolean alterObject(int objectID, Object object) {
        return false;
    }



    @Override
    //根据学校ID取得所有活动记录
    public ArrayList<Object> getObjectByCon(String item, String value) {
        int schID = Integer.parseInt(value);
        Connection con = ConnectionSQL.createConnectionSQL();
        ArrayList<Object> allActives = new ArrayList<Object>();
        String getAllSQL = "select * from active where school_id = " + schID;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getAllSQL);
            while(rs.next()){
                ActiveBean active = new ActiveBean();
                active.setId(rs.getInt(1));
                active.setName(rs.getString(2));
                active.setAddress(rs.getString(3));
                active.setTitle(rs.getString(4));
                active.setTime(rs.getDate(5));
                active.setEmp((EmployeeBean) new EmployeeDaoImp().getObjectByID(rs.getInt(6)));
                allActives.add(active);
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
        if(allActives.size() != 0){
            return allActives;
        }else
            return null;
    }
}
