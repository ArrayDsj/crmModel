package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.PositionBean;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/22.
 */
public class PositionDaoImp implements CrmDao {


    @Override
    public boolean deleteObject(int ObjectID) {
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
    public Object getObjectByID(int ObjectID) {
        Connection con = ConnectionSQL.createConnectionSQL();
        //按ID查找指定职位信息
        String getObjectByIDSQL = "select * from t_position\n" +
                "where position_id = " + ObjectID;
        PositionBean pos = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getObjectByIDSQL);
            while(rs.next()){
                //组合员工信息
                pos = new PositionBean();
                pos.setPositionID(rs.getInt(1));
                pos.setName(rs.getString(2));
                pos.setDescribe(rs.getString(3));
                pos.setCheckRight(rs.getBoolean(4));
                pos.setQueryRight(rs.getBoolean(5));
                pos.setSaleRight(rs.getBoolean(6));
                pos.setManagerRight(rs.getBoolean(7));
                pos.setBackRight(rs.getBoolean(8));
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
        if(pos != null){
            return pos;
        }else
            return null;
    }




    @Override
    public ArrayList<Object> getAllObjects() {
        ArrayList<Object> positions = new ArrayList<Object>();
        Connection con = ConnectionSQL.createConnectionSQL();

        String allPositionsName = "select * from t_position";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(allPositionsName);
            while (rs.next()) {
                PositionBean pos = new PositionBean();
                pos.setPositionID(rs.getInt(1));
                pos.setName(rs.getString(2));
                pos.setDescribe(rs.getString(3));
                pos.setCheckRight(rs.getBoolean(4));
                pos.setQueryRight(rs.getBoolean(5));
                pos.setSaleRight(rs.getBoolean(6));
                pos.setManagerRight(rs.getBoolean(7));
                pos.setBackRight(rs.getBoolean(8));
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
    public boolean alterObject(int objectID,Object alterObj) {
        PositionBean alterPos = (PositionBean)alterObj;
        int departID = alterPos.getPositionID();
        Connection con = ConnectionSQL.createConnectionSQL();
        //修改权限
        boolean check = alterPos.isCheckRight();
        boolean query = alterPos.isQueryRight();
        boolean sale = alterPos.isSaleRight();
        boolean manager = alterPos.isManagerRight();
        boolean back = alterPos.isManagerRight();
        String alterSQL = "update t_position set \n" +
                "checkRight = ? ,\n"+
                "queryRight = ? ,\n"+
                "saleRight = ? ,\n"+
                "managerRight = ? ,\n"+
                "backRight = ? \n"+
                "where position_id = ?;";
        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(alterSQL);
            ps.setBoolean(1,check);
            ps.setBoolean(2,query);
            ps.setBoolean(3,sale);
            ps.setBoolean(4,manager);
            ps.setBoolean(5,back);
            ps.setInt(6, objectID);
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

    @Override
    public boolean addObject(int objectID,Object object) {
        PositionBean newPos = (PositionBean)object;
        String name = newPos.getName();
        String describe = newPos.getDescribe();
        boolean check = newPos.isCheckRight();
        boolean query = newPos.isQueryRight();
        boolean sale = newPos.isSaleRight();
        boolean manager = newPos.isManagerRight();
        boolean back = newPos.isBackRight();
        Connection con = ConnectionSQL.createConnectionSQL();
        int result = -1;
        String addSQL = "insert into t_position(\n" +
                "position_name,\n" +
                "position_describe,\n" +
                "checkRight,\n" +
                "queryRight,\n" +
                "saleRight,\n" +
                "managerRight,\n" +
                "backRight)\n" +
                "values(\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?\n" +
                ");";
        try {
            PreparedStatement ps = con.prepareStatement(addSQL);
            ps.setString(1,name);
            ps.setString(2,describe);
            ps.setBoolean(3, check);
            ps.setBoolean(4, query);
            ps.setBoolean(5, sale);
            ps.setBoolean(6, manager);
            ps.setBoolean(7,back);
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
