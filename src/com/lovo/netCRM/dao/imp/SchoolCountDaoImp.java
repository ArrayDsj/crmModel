package com.lovo.netCRM.dao.imp;


import com.lovo.netCRM.bean.SchoolCountBean;
import com.lovo.netCRM.util.ConnectionSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by CodeA on 2015/8/27.
 */
public class SchoolCountDaoImp {
        public ArrayList<Object> getCounts(int cityId){
            Connection con = ConnectionSQL.createConnectionSQL();
            ArrayList<Object> counts = new ArrayList<Object>();
            SchoolCountBean count = null;
            //根据城市ID查找所有的学校,然后在这个表里面根据学校分组,查找学生信息
            String SQL ="select s.school_name '学校名称', \n" +
                    "(select count(class_id) from school s1 join classes c1\n" +
                    "on s1.school_id = c1.school_id\n" +
                    "where s.school_name = s1.school_name \n" +
                    ") '班级数量',\n" +
                    "(select count(stu_vip) from student stu2 join school s2\n" +
                    "where s.school_name = s2.school_name and stu_vip = 1\n" +
                    ") '会员数量',\n" +
                    "(select count(stu_vip) from student stu3 join school s3\n" +
                    "where s.school_name = s3.school_name and stu_vip = 0\n" +
                    ") '非会员数量'\n" +
                    "FROM student stu\n" +
                    "left join classes c on c.class_id = stu.classes_id\n" +
                    "left join school s on s.school_id = stu.school_id\n" +
                    "where area_id = "+ cityId+
                    " and stu_status = 1\n group by s.school_name;";
            try {
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery(SQL);
                while(rs.next()){
                    count = new SchoolCountBean();
                    count.setSchoolName(rs.getString(1));
                    count.setClassesNum(rs.getInt(2));
                    count.setVipNum(rs.getInt(3));
                    count.setNotVipNum(rs.getInt(4));
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


    public ArrayList<Object> getCounts(int cityId,String startTime,String endTime){
        Connection con = ConnectionSQL.createConnectionSQL();
        ArrayList<Object> timeCounts = new ArrayList<Object>();
        SchoolCountBean count = null;
        //根据城市ID查找所有的学校,然后在这个表里面根据学校分组,查找学生信息
        String SQL ="select s.school_name '学校名称', \n" +
                "(select count(class_id) from school s1 join classes c1\n" +
                "on s1.school_id = c1.school_id\n" +
                "where s.school_name = s1.school_name \n" +
                ") '班级数量',\n" +
                "(select count(stu_vip) from student stu2 join school s2\n" +
                "where s.school_name = s2.school_name and stu_vip = 1\n" +
                ") '会员数量',\n" +
                "(select count(stu_vip) from student stu3 join school s3\n" +
                "where s.school_name = s3.school_name and stu_vip = 0\n" +
                ") '非会员数量'\n" +
                "FROM student stu\n" +
                "left join classes c on c.class_id = stu.classes_id\n" +
                "left join school s on s.school_id = stu.school_id\n" +
                "where area_id = "+ cityId+"\n"+
                "and stu_status = 1\n " +
                "and school_foundTime between '"+startTime+"'and'"+ endTime +"'\n" +
                "group by s.school_name;";
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                count = new SchoolCountBean();
                count.setSchoolName(rs.getString(1));
                count.setClassesNum(rs.getInt(2));
                count.setVipNum(rs.getInt(3));
                count.setNotVipNum(rs.getInt(4));
                timeCounts.add(count);
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

        if(timeCounts.size() != 0){
            return timeCounts;
        }else
            return null;
    }
}

