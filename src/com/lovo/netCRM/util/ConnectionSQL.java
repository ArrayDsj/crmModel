package com.lovo.netCRM.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by CodeA on 2015/8/21.
 * �������ݿ�Ĺ�����
 */
public class ConnectionSQL {
    public static Connection createConnectionSQL() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/crm_system", "root", "dusj5282010");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("���ݿ�����ʧ��,��ȷ��������ȷ,mysql������������");
            e.printStackTrace();
        }
        return connection;
    }
}

