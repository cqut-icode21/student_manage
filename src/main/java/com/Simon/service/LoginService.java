package com.Simon.service;

import com.Simon.dao.impl.DatabaseReflect;
import com.Simon.entities.User;

public class LoginService {
    private final String Account;
    private final String password;

    public LoginService(String Account, String password) {
        this.Account = Account;
        this.password = password;
    }

    public boolean login() {
        DatabaseReflect databaseReflect = new DatabaseReflect();
        User user = (User) databaseReflect.findById(User.class, Account);
        return password.equals(user.getPassword());
    }
}
