package com.jiaolin.pojo;

import com.jiaolin.annotation.Column;
import com.jiaolin.annotation.Table;

@Table(tableName = "user")
public class User {
    @Column
    private String userName = "123456";
    @Column
    private String password = "123456";

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userNAme='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userNAme) {
        this.userName = userNAme;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
