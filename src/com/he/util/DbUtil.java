package com.he.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 13253
 * @date 2021/7/29 14:55
 * @className DbUtil
 */
public class DbUtil {
    public  Connection getConnection() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/studentdemo?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT";
        String useName = "root";
        String passWord = "root";
        Connection connection = null;
        try {
            System.out.println("开始加载驱动");
            Class.forName(driverName);
            System.out.println("驱动加载成功\n开始连接");
            connection = DriverManager.getConnection(url, useName, passWord);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        dbUtil.getConnection();
        System.out.println("连接成功");

    }
}


