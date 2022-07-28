package com.Simon.dao.impl;

import com.Simon.annotation.Column;
import com.Simon.annotation.Id;
import com.Simon.annotation.Table;
import com.Simon.dao.SqlDao;

import java.lang.reflect.Field;

/**
 * @author 潘琴
 * @date:2021/8/2
 * @description: 动态拼接SQL语句
 */
public class SqlDaoImpl implements SqlDao {

    @Override
    public String findAllSql(Class<?> clazz, int former, int now) {
        // SQL语句
        StringBuilder sql = new StringBuilder();
        sql.append("select ");

        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        // 遍历属性，获取类中注解的字段、表名
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            // 将字段拼接到SQL语句
            sql.append(columnName).append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" from ");

        // 获取类中注解的表名
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName);

        sql.append(" order by createTime");
        if (former == 0 && now == 0) {

        } else {
            sql.append(" limit ").append(former).append(" , ").append(now);
        }
        System.out.println("findAllSql:" + sql);
        return sql.toString();
    }


    @Override
    public String findByIdSql(Class<?> clazz) {
        Id idAnnotation = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select ");

        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        // 遍历属性，获取类中注解的字段、表名
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            // 将字段拼接到SQL语句
            sql.append(columnName).append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" from ");
        // 获取类中注解的表名
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append(" where ");
        // 获取字段id
        for (Field field : fields) {
            idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.idName();
                sql.append(idName).append(" = ?");
                break;
            }
        }
        System.out.println("findByIdSql:" + sql);
        return sql.toString();
    }

    @Override
    public String deleteSql(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Id idAnnotation = fields[0].getAnnotation(Id.class);
        String id = idAnnotation.idName();
        StringBuilder sql = new StringBuilder();
        sql.append("delete from `");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append("` where ").append(id).append(" = ?");
        System.out.println("deleteByIdSql:" + sql);
        return sql.toString();
    }


    @Override
    public String addSql(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sql = new StringBuilder();
        sql.append("insert into `");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append("` values(");
        for (int i = 0; i < fields.length; i++) {
            sql.append("? ,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(",NOW()");
        sql.append(")");
        System.out.println("addSql:" + sql);
        return sql.toString();
    }

    @Override
    public String updateSql(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sql = new StringBuilder();
        sql.append("update `");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append("` set ");


        for (Field field : fields) {

            Column column = field.getAnnotation(Column.class);
            sql.append(column.columnName());
            sql.append(" = ? ,");
        }
        sql.deleteCharAt(sql.length() - 1);
        Id idAnnotation = fields[0].getAnnotation(Id.class);
        String id = idAnnotation.idName();
        sql.append(" where ").append(id).append(" = ?");
        System.out.println("updateSql:" + sql);
        return sql.toString();

    }

    @Override
    public String findSearchSql(String text, Class<?> clazz, int former, int now) {
        Id idAnnotation = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        // 遍历属性，获取类中注解的字段、表名
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            // 将字段拼接到SQL语句
            sql.append(columnName).append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" from ");

        // 获取类中注解的表名
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append(" where concat ( ");

        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            // 将字段拼接到SQL语句
            sql.append(columnName).append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" ) like '%").append(text).append("%'");
        sql.append(" order by 'creatTime'");
        if (former == 0 && now == 0) {

        } else {
            sql.append(" limit ").append(former).append(" , ").append(now);
        }
        System.out.println("findSearchSql:" + sql);
        return sql.toString();
    }


}
