package com.he.dao.impl;

import com.he.annotation.Column;
import com.he.annotation.Table;
import com.he.dao.DataSqlOperation;
import com.he.entity.Student;
import com.he.entity.User;

import java.lang.reflect.Field;
import java.sql.Connection;

/**
 * @author 13253
 * @date 2021/7/29 19:19
 * @className DbReflectSqlUtil
 */
public class DbReflectSqlUtil<c> implements DataSqlOperation<c> {

    @Override
    public String findDataSql(c clazz, int currentPage, int pageIndex) {
        StringBuilder findDataSql = new StringBuilder();
        findDataSql.append("select ");
        for (Field field : clazz.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);
            findDataSql.append(column.columnName()).append(",");
        }

        findDataSql.deleteCharAt(findDataSql.length() - 1);
        findDataSql.append(" from ");
        Table table = clazz.getClass().getAnnotation(Table.class);

        findDataSql.append(table.tableName());
        findDataSql.append(" limit ");
        findDataSql.append((currentPage - 1) * pageIndex).append(",");
        findDataSql.append(pageIndex);
        System.out.println("SQL:  " + findDataSql.toString());
        return findDataSql.toString();
    }
}
