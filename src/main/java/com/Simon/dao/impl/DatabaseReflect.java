package com.Simon.dao.impl;

import com.Simon.dao.BaseDao;
import com.Simon.utils.DatabaseUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseReflect implements BaseDao {

    Connection connection = null;

    PreparedStatement statement = null;

    ResultSet resultSet = null;

    SqlDaoImpl sqlDao = new SqlDaoImpl();


    @Override
    public <T> List<T> search(Class clazz, String text, int former, int now) {
        List results = new ArrayList();
        Object object = null;

        Field[] fields = clazz.getDeclaredFields();

        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.findSearchSql(text, clazz, former, now);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 5、执行SQL语句
            System.out.println("findAll:" + statement);
            resultSet = statement.executeQuery();
            // 6、获取执行结果

            // 7、 遍历结果集
            while (resultSet.next()) {
                object = clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {

                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(object, resultSet.getObject(i + 1));
                }
                results.add(object);
            }

        } catch (RuntimeException | SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {
            // 8、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return results;

    }

    @Override
    public <T> List<T> findAll(Class<T> clazz, int former, int now) {
        List results = new ArrayList();
        Object object = null;

        Field[] fields = clazz.getDeclaredFields();

        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.findAllSql(clazz, former, now);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）


            // 5、执行SQL语句
            System.out.println("findAll:" + statement);
            resultSet = statement.executeQuery();
            // 6、获取执行结果

            // 7、 遍历结果集
            while (resultSet.next()) {
                object = clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {

                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(object, resultSet.getObject(i + 1));
                }
                results.add(object);
            }

        } catch (RuntimeException | SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {
            // 8、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return results;

    }

    @Override
    public Object findById(Class<?> clazz, Object id) {
        Object object = null;
        Field[] fields = clazz.getDeclaredFields();


        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.findByIdSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);

            // 4、填充参数（可选）
            statement.setObject(1, id);

            // 5、执行SQL语句
            System.out.println("findById:" + statement);
            resultSet = statement.executeQuery();
            // 6、获取执行结果
            object = clazz.newInstance();
            // 遍历结果集
            while (resultSet.next()) {
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(object, resultSet.getObject(i + 1));
                }
            }
            return object;
        } catch (RuntimeException | SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return object;
    }

    @Override
    public boolean delete(Class<?> clazz, Object id) {
        int result = 0;
        try {
            // 1、获取数据库连接
            connection = DatabaseUtil.getConnection();
            // 2、获取到SQL语句
            String sql = sqlDao.deleteSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            statement.setObject(1, id);

            // 5、执行SQL语句
            System.out.println("DeleteById:" + statement);
            result = statement.executeUpdate();
        } catch (RuntimeException | SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return result > 0;
    }

    @Override
    public boolean add(Object object) {
        int result = 0;
        try {
            // 1、获取数据库连接
            connection = DatabaseUtil.getConnection();
            // 2、获取到SQL语句
            String sql = sqlDao.addSql(object.getClass());
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(i + 1, fields[i].get(object));
            }
            // 5、执行SQL语句
            System.out.println("Add:" + statement);
            result = statement.executeUpdate();
        } catch (RuntimeException | SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            // 6、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return result > 0;
    }


    @Override
    public boolean update(Object object, Object id) {
        int result = 0;
        try {
            // 1、获取数据库连接
            connection = DatabaseUtil.getConnection();
            String sql = sqlDao.findAllSql(object.getClass(), 0, 0);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            // 2、获取到SQL语句
            sql = sqlDao.updateSql(object.getClass());
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            Field[] fields = object.getClass().getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                statement.setObject(j + 1, fields[j].get(object));
            }
            statement.setObject(fields.length + 1, id);

            // 5、执行SQL语句
            System.out.println("update:" + statement);
            result = statement.executeUpdate();
        } catch (RuntimeException | SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            // 6、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return result > 0;
    }


}
