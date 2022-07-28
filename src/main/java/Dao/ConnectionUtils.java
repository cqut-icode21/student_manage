package Dao;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class ConnectionUtils {

    private static Connection connection = null;

    public static Connection setConnection() throws SQLException {
        if(connection!=null&&!connection.isClosed()){
            return connection;
        }

        String url = "jdbc:mysql:///person?useSSL=false";
        String name = "root";
        String password = "13241324a";

        System.out.println("开始连接驱动");
        try{
            DriverManager.registerDriver(new Driver());
            System.out.println("驱动加载成功");
            connection = DriverManager.getConnection(url,name,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  connection;
    }

    public static void closeAll(Connection connection, PreparedStatement pre, ResultSet rel) throws SQLException {
        if(rel!=null){
            rel.close();
        }
        if(pre!=null){
            pre.close();
        }
        if(connection!=null){
            connection.close();
        }
    }
}
