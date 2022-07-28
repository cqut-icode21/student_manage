package utils;

import java.sql.*;

/**
 * @author 潘琴
 * @date:2021/8/1
 * @description: JDBC工具类
 */
public class DatabaseUtil {
    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public static Connection getConnection() {
        // 驱动信息
        String driverName = "com.mysql.cj.jdbc.Driver";
        // 数据库连接参数
        String url = "jdbc:mysql://localhost:3306/sql_demo?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT";
        // 用户名
        String userName = "root";
        // 密码
        String password = "12345";

        Connection collection = null;
        System.out.println("开始加载驱动......");
        // 注册驱动
        try {
            Class.forName(driverName);
            System.out.println("驱动加载成功！\n开始连接！");
            // 建立连接
            collection = DriverManager.getConnection(url, userName, password);
            if (collection == null){
               collection = getConnection();
            }
            System.out.println("-------------------连 接 成 功-------------------");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return collection;
    }

    /**
     * 释放资源
     *
     * @param connection 连接
     * @param statement  预编译
     * @param resultSet  结果集
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (statement != null) {
                statement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
