package com.he.entity;

import com.he.annotation.Column;
import com.he.annotation.Table;

/**
 * @author 13253
 * @date 2021/7/29 18:37
 * @className User
 */

@Table(tableName = "user")
public class User {
    @Column(columnName = "username")
    private String username;


    @Column(columnName = "password")
    private String password;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
