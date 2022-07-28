package com.he.dao.impl;

import com.he.dao.LoginDao;
import com.he.dao.DataOperation;
import com.he.entity.Student;

import java.util.List;

public class OperationImpl<c> implements LoginDao, DataOperation<c> {
    private final DbReflectUtil<c> db = new DbReflectUtil<>();

    @Override
    public boolean isSuccess(String username, String password) {
        List<c> ids=db.findAllId((c) (new Student()));
        List<Student> allId= (List<Student>) ids;
        System.out.println(ids);
        for (Student student : allId) {
            if (username.equals(student.getId()))
                return true;
        }
        //我写了一个静态的,你们要写从数据库验证哈
        return username.equals("18380512003") && password.equals("");
    }

    @Override
    public List<c> findData(c clazz, int currentPage, int pageIndex) {
        return db.findData(clazz, currentPage, pageIndex);
    }

}
