package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.*;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/24.
 */
public class SchoolDaoImp implements CrmDao{
    @Override
    public EmployeeBean login(String loginName, String passWord) {
        return null;
    }

    @Override
    public ArrayList<Object> getAllObjects() {
        return null;
    }

    @Override
    public boolean deleteObject(int ObjectID) {
        return false;
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




    @Override
    public Object getObjectByID(int ObjectID) {
        Connection con = ConnectionSQL.createConnectionSQL();
        String getSchoolSQL = "select * from school where school_id = " + ObjectID;
        SchoolBean sch = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getSchoolSQL);
            while(rs.next()){
                sch = new SchoolBean();
                sch.setId(rs.getInt(1));
                sch.setName(rs.getString(2));
                sch.setMaster(rs.getString(3));
                sch.setPhone(rs.getString(4));
                sch.setStuNum(rs.getInt(5));
                sch.setTeaNum(rs.getInt(6));
                sch.setAddress(rs.getString(7));
                sch.setIPAddress(rs.getString(8));
                sch.setFlow(rs.getString(9));
                sch.setDescribe(rs.getString(10));
                sch.setStatus(rs.getBoolean(11));
                sch.setFoundTime(rs.getDate(12));
                sch.setProposeTime(rs.getDate(13));
                sch.setPermitTime(rs.getDate(14));
                //需要active的按id查找到的activeBean
                sch.setActive(getActiveBySchoolID(rs.getInt(15)));
                //根据xuexiaoid查找当前学校,一个学校对应唯一一个地区,一个地区对应多个学校
                sch.setArea((AreaBean) new AreaDaoImp().getObjectByID(rs.getInt(16)));
                sch.setConnectRecord(getConnectRecordBySchoolID(rs.getInt(17)));
                sch.setEmp((EmployeeBean) new EmployeeDaoImp().getObjectByID(rs.getInt(18)));
                sch.setInTime(rs.getDate(19));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(sch != null){
            return sch;
        }else
            return null;
    }


    /**
     *
     * @param schID
     * @return
     * 一个学校有多个活动记录
     */
    public ArrayList<ActiveBean> getActiveBySchoolID(int schID){
        Connection con = ConnectionSQL.createConnectionSQL();

        ArrayList<ActiveBean> actives = new ArrayList<ActiveBean>();
        String sql = "select * from active where active_id = " + schID ;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                ActiveBean active = new ActiveBean();
                active.setId(rs.getInt(1));
                active.setName(rs.getString(2));
                active.setAddress(rs.getString(3));
                active.setTitle(rs.getString(4));
                active.setTime(rs.getDate(5));
                active.setEmp((EmployeeBean) new EmployeeDaoImp().getObjectByID(rs.getInt(6)));
                actives.add(active);
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
        if(actives.size() != 0){
            return actives;
        }else
            return null;
    }


    /**
     *
     *
     * @return
     * 一个学校对应多个沟通记录
     */
    public ArrayList<ConnectRecordBean> getConnectRecordBySchoolID(int schID){
        Connection con = ConnectionSQL.createConnectionSQL();

        ArrayList<ConnectRecordBean> connectRecords = new ArrayList<ConnectRecordBean>();
        String sql = "select * from connectrecord where connectRecord_id = " + schID ;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                ConnectRecordBean connectRecord = new ConnectRecordBean();
                connectRecord.setId(rs.getInt(1));
                connectRecord.setMan(rs.getString(2));
                connectRecord.setTime(rs.getDate(3));
                connectRecord.setTime(rs.getDate(4));
                connectRecord.setEmp((EmployeeBean) new EmployeeDaoImp().getObjectByID(rs.getInt(5)));
                connectRecords.add(connectRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally{
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(connectRecords.size() != 0){
            return connectRecords;
        }else
            return null;
    }

    //查找所有满足在同一个地区的学校,返回这个集合
    public ArrayList<SchoolBean> getSchoolByAreaID(int areaID){
        Connection con = ConnectionSQL.createConnectionSQL();
        SchoolBean sch = null;
        ArrayList<SchoolBean> allSchoolInthisArea = new ArrayList<SchoolBean>();
        String sql = "select * from school where area_id = " + areaID ;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                sch = new SchoolBean();
                sch.setId(rs.getInt(1));
                sch.setName(rs.getString(2));
                sch.setMaster(rs.getString(3));
                sch.setPhone(rs.getString(4));
                sch.setStuNum(rs.getInt(5));
                sch.setTeaNum(rs.getInt(6));
                sch.setAddress(rs.getString(7));
                sch.setIPAddress(rs.getString(8));
                sch.setFlow(rs.getString(9));
                sch.setDescribe(rs.getString(10));
                sch.setStatus(rs.getBoolean(11));
                sch.setFoundTime(rs.getDate(12));
                sch.setProposeTime(rs.getDate(13));
                sch.setPermitTime(rs.getDate(14));
                //需要active的按id查找到的activeBean
                sch.setActive(getActiveBySchoolID(rs.getInt(15)));

                //根据地区id查找当前学校,一个学校对应唯一一个地区,一个地区对应多个学校
                //sch.setArea((AreaBean) new AreaDaoImp().getObjectByID(rs.getInt(16)));

                sch.setConnectRecord(getConnectRecordBySchoolID(rs.getInt(17)));
                sch.setEmp((EmployeeBean) new EmployeeDaoImp().getObjectByID(rs.getInt(18)));
                sch.setInTime(rs.getDate(19));
                allSchoolInthisArea.add(sch);
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

        if(allSchoolInthisArea.size() != 0){
            return allSchoolInthisArea;
        }else
            return null;
    }
}
