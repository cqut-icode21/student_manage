package dao;

import entities.User;
import utils.DatebaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    @Override
    public User LoginDao(User user) {
        String username =user.getUsername();
        String password=user.getPassword();

        Connection connection= DatebaseUtil.getConnection();
        PreparedStatement pre=null;
        ResultSet res=null;

        String sql="SELECT *FROM user WHERE username='"+username+"'AND password= '"+password+"'";
        try{
           pre= connection.prepareStatement(sql);
           res= pre.executeQuery();

           while (res.next()){
               User user1=new User();
               user1.setUsername(res.getString("username"));
               user1.setPassword(res.getString("password"));
               return user1;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
