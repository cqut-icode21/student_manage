package dao.impl;

import dao.UserDao;
import enties.User;

import java.sql.Connection;

public class UserDaoImpl implements UserDao {
    @Override
    public User getLoginUser(User user) {
        String sql = "select * form users where userName =? and password =? ";
        User users = new User();

        if (user.getUserName().equals("xie") && user.getPassword().equals("123456"))
            return users;

        return null;
    }
}
