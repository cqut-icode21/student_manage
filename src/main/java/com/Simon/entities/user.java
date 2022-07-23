package com.Simon.entities;

import com.Simon.annotation.Column;
import com.Simon.annotation.Id;
import com.Simon.annotation.Table;

@Table(tableName = "user", label = "用户表")
public class user {
    @Id(idName = "user_name")
    @Column(columnName = "user_name", label = "用户名")
    private String userName;
    @Column(columnName = "password", label = "密码")
    private String password;

    public user(String userName,String password){
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
