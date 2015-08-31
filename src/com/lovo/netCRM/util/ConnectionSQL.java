package com.lovo.netCRM.util;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by CodeA on 2015/8/21.
 * �������ݿ�Ĺ�����
 */
public final class ConnectionSQL {
    private static final String ACTIONPATH = "config.properties";
    private static String db_username = null;
    private static String db_password = null;
    private static String db_url = null;
    private static String driver = null;
    private static Connection connection = null;
    private static ConnectionSQL instance = null;

    private ConnectionSQL(){
    }

    public static ConnectionSQL getInstance(){
        if(instance == null){
            synchronized (ConnectionSQL.class) {
                if(instance == null){
                    instance = new ConnectionSQL();
                }
            }
        }
        return instance;
    }

    static{
        Properties pro = new Properties();
        try {
            pro.load(new FileInputStream(ACTIONPATH));
            db_username = pro.getProperty("db_username");
            db_password = pro.getProperty("db_password");
            db_url = pro.getProperty("db_url");
            driver = pro.getProperty("driver");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "���������ļ�ʧ��");
            e.printStackTrace();
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "���ݿ�����ʧ��,��ȷ��������ȷ");
            e.printStackTrace();
        }
    }

    //�������
    public static Connection createConnectionSQL(){
        try {
            connection = DriverManager.getConnection(db_url, db_username, db_password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "���ݿ�����ʧ��,��ȷ��������ȷ,mysql������������");
        }
        return connection;
    }
}

