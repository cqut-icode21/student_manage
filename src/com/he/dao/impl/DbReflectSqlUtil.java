package com.he.dao.impl;

import com.he.annotation.Column;
import com.he.annotation.Table;
import com.he.dao.DataSqlOperation;
import com.he.entity.Student;
import com.he.entity.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

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
        System.out.println("SQL:  " + findDataSql);
        return findDataSql.toString();
    }

    public String findAllPage(c clazz){
        StringBuilder sql=new StringBuilder();
        sql.append("select ");
        sql.append("count(");
        Field[] field=clazz.getClass().getDeclaredFields();
        Column column=field[0].getAnnotation(Column.class);
        sql.append(column.columnName());
        sql.append(") as result");

        sql.append(" from ");
        Table table = clazz.getClass().getAnnotation(Table.class);
        sql.append(table.tableName());
        System.out.println("FindPage: "+sql);
        return sql.toString();
    }

    @Override
    public String addStudent(c clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ");


        Table tableAnnotation=(Table) clazz.getClass().getAnnotation(Table.class);
        String tableName=tableAnnotation.tableName();
        sql.append(tableName+"(");
        Field[] fields = clazz.getClass().getDeclaredFields();
        for (Field field : fields) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            sql.append(columnName).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        sql.append(" value(");
//        添加参数
        for (int i=0;i<fields.length;i++){
            sql.append("?");
            sql.append(",");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        return sql.toString();
    }

    public String findAllId(c clazz){
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        Field[] fields = clazz.getClass().getDeclaredFields();
        Column columnAnnotation = fields[0].getAnnotation(Column.class);
        String columnName = columnAnnotation.columnName();
        sql.append(columnName);
        sql.append(" from ");
        Table tableAnnotation = (Table) clazz.getClass().getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName);
        return sql.toString();
    }

    @Override
    public String deleteStudent(c clazz) {
        StringBuilder sql=new StringBuilder();
        Field[] fields=clazz.getClass().getDeclaredFields();
        Column idAnnotation=null;
        sql.append("delete from ");

        Table table=(Table) clazz.getClass().getAnnotation(Table.class);
        String tableName=table.tableName();
        sql.append(tableName).append(" ");
        sql.append("where ");

            idAnnotation = fields[0].getAnnotation(Column.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.columnName();
                sql.append(idName).append(" = ?");
            }

        return sql.toString();
    }

    @Override
    public String findStudent(c clazz) {
        Column idAnnotation = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select ");


        Field[] fields = clazz.getClass().getDeclaredFields();
        sql.append("*");
        sql.append(" from ");


        Table tableAnnotation = (Table) clazz.getClass().getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append(" where ");

            idAnnotation = fields[0].getAnnotation(Column.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.columnName();
                sql.append(idName).append(" = ?");
            }

        return sql.toString();
    }

    @Override
    public String upDateStudent(c clazz) {
        StringBuilder sql=new StringBuilder();
        sql.append("update ");
        Field[] fields=clazz.getClass().getDeclaredFields();
        Column idAnnotation=null;

        Table table=(Table) clazz.getClass().getAnnotation(Table.class);
        String tableName= table.tableName();
        sql.append(tableName+" set ");

        int i=0;
        for (;i<fields.length;i++){
            Column column=fields[i].getAnnotation(Column.class);
            String columnName= column.columnName();

            sql.append(columnName+"=?");
            sql.append(",");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(" where ");

            idAnnotation = fields[0].getAnnotation(Column.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.columnName();
                sql.append(idName).append(" = ?");

        }
        return sql.toString();
    }
}
