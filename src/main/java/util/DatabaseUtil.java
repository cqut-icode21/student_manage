package util;

import java.sql.*;

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
        String url = "jdbc:mysql://localhost:3306/servlet?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT";
        // 用户名
        String userName = "root";
        // 密码
        String password = "zx673494350";
        Connection collection = null;
        System.out.println("开始加载驱动......");
        // 注册驱动
        try {
            Class.forName(driverName);
//            DriverManager.registerDriver(new Driver());
//            System.setProperty("jdbc.drivers",driverName);
            System.out.println("驱动加载成功！\n开始连接！");
            // 建立连接
            collection = DriverManager.getConnection(url, userName, password);
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
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
