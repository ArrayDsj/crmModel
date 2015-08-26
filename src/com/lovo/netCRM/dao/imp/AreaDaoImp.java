package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.AreaBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class AreaDaoImp implements CrmDao{



    @Override
    public ArrayList<Object> getAllObjects() {
        ArrayList<Object> areas = new ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();
        String getAllAreaSQL = "select * from area";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getAllAreaSQL);
            while(rs.next()){
                AreaBean area = new AreaBean();
                area.setId(rs.getInt(1));
                area.setName(rs.getString(2));
                //在学校表中根据城市ID找出所有学校结合
                area.setSchool(new SchoolDaoImp().getSchoolByAreaID(rs.getInt(1)));
                areas.add(area);
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
        if(areas.size() != 0){
           return areas;
        }else
            return null;
    }

    @Override
    //返回AreaBean对象
    public Object getObjectByID(int ObjectID) {
        Connection con = ConnectionSQL.createConnectionSQL();
        AreaBean area = new AreaBean();
        String getAreaSQL = "select * from area where area_id = " + ObjectID;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getAreaSQL);
            while(rs.next()) {
                area.setId(rs.getInt("area_id"));
                area.setName(rs.getString(2));
                area.setSchool(new SchoolDaoImp().getSchoolByAreaID(ObjectID));
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
        if(area != null){
            return area;
        }else
            return null;
    }

    //根据名字查找对象
    public Object getObjectByName(String name) {
        Connection con = ConnectionSQL.createConnectionSQL();
        //查找当前用户信息
        String getAreaSQL = "select * from area where area_name = '" + name + "'";
        AreaBean areaBean = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getAreaSQL);
            while(rs.next()){
                //组合员工信息
                areaBean = new AreaBean();
                areaBean.setId(rs.getInt(1));
                areaBean.setName(rs.getString(2));
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
        if(areaBean != null){
            return areaBean;
        }else {
            JOptionPane.showMessageDialog(null, "集合中没有员工");
            return null;
        }
    }














    @Override
    public boolean deleteObject(int ObjectID) {
        return false;
    }

    @Override
    public ArrayList<Object> getObjectByCon(String item, String value) {
        return null;
    }

    @Override
    public boolean alterObject(int objectID, Object object) {
        return false;
    }

    @Override
    public boolean addObject(int objectID, Object object) {
        return false;
    }


}
