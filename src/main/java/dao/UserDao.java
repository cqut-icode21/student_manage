package dao;

import enties.User;

import java.sql.Connection;

public interface UserDao {
    public User getLoginUser(User user);
}
