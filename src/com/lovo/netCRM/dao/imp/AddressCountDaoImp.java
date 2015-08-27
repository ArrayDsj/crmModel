package com.lovo.netCRM.dao.imp;

import com.lovo.netCRM.bean.AddressCountBean;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/27.
 */
public class AddressCountDaoImp {
    public ArrayList<Object> getCounts(){
        Connection con = ConnectionSQL.createConnectionSQL();
        ArrayList<Object> counts = new ArrayList<Object>();
        AddressCountBean count = null;
        String SQL = "select a.area_name ,count(school_foundTime) '¼��ѧУ',\n" +
                "(select count(school_status) from school s1 join area a1 \n" +
                "on s1.area_id = a1.area_id\n" +
                "where a.area_name = a1.area_name and s1.school_status = '��Ǣ��') '��Ǣ��',\n" +
                "\n" +
                "(select count(school_status) from school s2 join area a2\n" +
                "on s2.area_id = a2.area_id\n" +
                "where a.area_name = a2.area_name and s2.school_status = '����') '����',\n" +
                "\n" +
                "(select count(school_status) from school s3 join area a3\n" +
                "on s3.area_id = a3.area_id\n" +
                "where a.area_name = a3.area_name and s3.school_status = '���δͨ��') '���δͨ��',\n" +
                "\n" +
                "(select count(school_status) from school s4 join area a4\n" +
                "on s4.area_id = a4.area_id\n" +
                "where a.area_name = a4.area_name and s4.school_status = '�ƹ㿪չ') '�ƹ㿪չ'\n" +
                "\n" +
                "from school s right join area a\n" +
                "on s.area_id = a.area_id\n" +
                "group by area_name\n" +
                "order by a.area_id";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                count = new AddressCountBean();
                count.setCityName(rs.getString(1));
                count.setSchoolNum(rs.getInt(2));
                count.setReceivesSchoolNum(rs.getInt(3));
                count.setProposeSchoolNum(rs.getInt(4));
                count.setPassSchoolNum(rs.getInt(5));
                count.setPermitSchoolNum(rs.getInt(6));
                counts.add(count);
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

        if(counts.size() != 0){
            return counts;
        }else
            return null;
    }
}
