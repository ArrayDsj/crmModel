package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.*;
import com.lovo.netCRM.dao.CrmDao;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CodeA on 2015/8/24.
 */
public class SchoolDaoImp implements CrmDao{

    @Override
    public ArrayList<Object> getAllObjects() {
        return null;
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
    public Object getObjectByName(String name) {
        return null;
    }


    @Override
    public boolean addObject(int ID,Object object) {
        Connection con = ConnectionSQL.createConnectionSQL();
        SchoolBean newsch = (SchoolBean)object;
        String SQL = "insert into school(\n" +
                "school_name,school_master,school_phone,school_stuNum,\n" +
                "school_teaNum,school_address,school_IPaddress,school_flow,\n" +
                "school_describe,school_status,\n" +
                "school_foundTime, \n" +
                "area_id,\n" +
                "staff_id) \n" +
                "VALUES(\n" +
                "?, ?, ?, ?, \n" +
                "?, ?, ?, ?, \n" +
                "?, ?,\n" +
                "?,\n" +
                "?,\n" +
                "?\n" +
                ");";
        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, newsch.getName());
            ps.setString(2, newsch.getMaster());
            ps.setString(3, newsch.getPhone());
            ps.setInt(4, newsch.getStuNum());
            ps.setInt(5, newsch.getTeaNum());
            ps.setString(6, newsch.getAddress());
            ps.setString(7, newsch.getIPAddress());
            ps.setString(8, newsch.getFlow());
            ps.setString(9, newsch.getDescribe());
            ps.setString(10, newsch.getStatus());
            ps.setDate(11, new java.sql.Date(newsch.getFoundTime().getTime()));
            ps.setInt(12,newsch.getArea().getId());
            ps.setInt(13,newsch.getEmp().getID());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result == 1){
            return true;
        }else
            return false;
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
                sch.setStatus(rs.getString(11));
                sch.setFoundTime(rs.getDate(12));
                sch.setProposeTime(rs.getDate(13));
                sch.setPermitTime(rs.getDate(14));
                //��Ҫactive�İ�id���ҵ���activeBean
                sch.setActive(getActiveBySchoolID(rs.getInt(15)));
                //����xuexiaoid���ҵ�ǰѧУ,һ��ѧУ��ӦΨһһ������,һ��������Ӧ���ѧУ
                sch.setArea((AreaBean) new AreaDaoImp().getObjectByID(rs.getInt(16)));
                //sch.setConnectRecord(getConnectRecordBySchoolID(rs.getInt(17)));
                sch.setEmp((EmployeeBean) new EmployeeDaoImp().getObjectByID(rs.getInt(18)));
                sch.setInTime(rs.getDate(19));
                sch.setCheckNotic(rs.getString(20));
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
     * һ��ѧУ�ж�����¼
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
     * һ��ѧУ��Ӧ�����ͨ��¼
     */
    public ArrayList<ConnectRecordBean> getConnectRecordBySchoolID(int schID){
        Connection con = ConnectionSQL.createConnectionSQL();

        ArrayList<ConnectRecordBean> connectRecords = new ArrayList<ConnectRecordBean>();
        String sql = "select * from connectrecord where school_id = " + schID ;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                ConnectRecordBean connectRecord = new ConnectRecordBean();
                connectRecord.setId(rs.getInt(1));
                connectRecord.setMan(rs.getString(2));
                connectRecord.setTime(rs.getDate(3));
                connectRecord.setPos(rs.getString(4));
                connectRecord.setDescribe(rs.getString(5));
                connectRecord.setEmp((EmployeeBean) new EmployeeDaoImp().getObjectByID(rs.getInt(6)));
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

    //��������������ͬһ��������ѧУ,�����������
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
                sch.setStatus(rs.getString(11));
                sch.setFoundTime(rs.getDate(12));
                sch.setProposeTime(rs.getDate(13));
                sch.setPermitTime(rs.getDate(14));
                //��Ҫactive�İ�id���ҵ���activeBean
                sch.setActive(getActiveBySchoolID(rs.getInt(15)));

                //���ݵ���id���ҵ�ǰѧУ,һ��ѧУ��ӦΨһһ������,һ��������Ӧ���ѧУ
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





    //����ѧУid�޸���Ϣ
    public boolean alterSchoolByID(int schID){
        java.sql.Date inTime = new java.sql.Date(new Date().getTime());
        Connection con = ConnectionSQL.createConnectionSQL();
        String alterSQL = "update school set school_inTime = ? where school_id = ?";
        int result = -1;

        try {
            PreparedStatement ps = con.prepareStatement(alterSQL);
            ps.setDate(1,inTime);
            ps.setInt(2,schID);
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
    public boolean alterObject(int objectID,Object object){
        SchoolBean sch = (SchoolBean)object;
        Connection con = ConnectionSQL.createConnectionSQL();
        String alterSQL =
                "update school set school_master = ? ," +
                "school_stuNum = ?," +
                "school_teaNum = ?," +
                "school_IPaddress = ?," +
                "school_flow = ? " +
                "where school_id = ?";

        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(alterSQL);
            ps.setString(1,sch.getMaster());
            ps.setInt(2,sch.getStuNum());
            ps.setInt(3, sch.getTeaNum());
            ps.setString(4, sch.getIPAddress());
            ps.setString(5, sch.getFlow());
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

    //�������޸�ѧУ��״̬
    public boolean alterSchoolByStatus(int schoolId,String status) {
        Connection con = ConnectionSQL.createConnectionSQL();
        String alterSQL =
                "update school set school_status = ? ,school_proposeTime = ?" +
                        "where school_id = ?";

        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(alterSQL);
            ps.setString(1,status);
            ps.setDate(2, new java.sql.Date(new Date().getTime()));
            ps.setInt(3,schoolId);
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

    public boolean alterSchoolByDescripOn(int schoolId,String descrip) {
        Connection con = ConnectionSQL.createConnectionSQL();
        String alterSQL =
                "update school set school_describe = ? ,school_permitTime = ? ,school_status = ? " +
                        " where  school_id = ?";

        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(alterSQL);
            ps.setString(1,descrip);
            ps.setDate(2, new java.sql.Date(new Date().getTime()));
            ps.setString(3, "�ƹ㿪չ");
            ps.setInt(4,schoolId);
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

    public boolean alterSchoolByDescripOff(int schoolId,String descrip) {
        Connection con = ConnectionSQL.createConnectionSQL();
        String alterSQL =
                "update school set school_describe = ? ,school_status = ? " +
                        " where  school_id = ?";

        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(alterSQL);
            ps.setString(1,descrip);

            ps.setString(2, "���δͨ��");
            ps.setInt(3,schoolId);
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
