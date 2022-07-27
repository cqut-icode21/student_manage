package com.he.dao.impl;

import com.he.dao.LoginDao;
import com.he.dao.DataOperation;

import java.util.List;

/**
 * @author 13253
 * @date 2021/7/29 18:51
 * @className OperationImpl
 */
public class OperationImpl<c> implements LoginDao, DataOperation<c> {
    private final DbReflectUtil<c> db = new DbReflectUtil<>();

    @Override
    public boolean isSuccess(String username, String password) {

        //我写了一个静态的,你们要写从数据库验证哈
        return username.equals("123456") && password.equals("123456");
    }

    /**
     * 得到数据
     *
     * @param clazz
     * @param currentPage
     * @param pageIndex
     * @return 返回需要的数据
     */
    @Override
    public List<c> findData(c clazz, int currentPage, int pageIndex) {
        return db.findData(clazz, currentPage, pageIndex);
    }
}
