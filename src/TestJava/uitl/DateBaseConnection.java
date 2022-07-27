package TestJava.uitl;

import java.sql.*;

public class DateBaseConnection {
    //地址
    static String url;
    //用户名
    static String user;
    //密码
    static String password;
    //链接方法
    public static Connection getConnection() throws ClassNotFoundException {
        url = "jdbc:mysql://localhost:3306/issa2?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT";
        // 用户名
        user = "root";
        // 密码
        password = "123456";
        Connection connection=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection= DriverManager.getConnection(url, user, password);
            System.out.println("successful connection");
        } catch (SQLException e) {
            System.out.println("fail to connect");
            throw new RuntimeException(e);
        }
        return connection;
    }
    //关闭链接
    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
           if(resultSet!=null){
               resultSet.close();
           }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
    }
}
