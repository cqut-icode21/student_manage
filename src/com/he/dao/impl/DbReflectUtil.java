package com.he.dao.impl;

import com.he.annotation.Column;
import com.he.dao.DataOperation;
import com.he.dao.DataSqlOperation;
import com.he.entity.Student;
import com.he.util.DbUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 13253
 * @date 2021/7/29 19:16
 * @className DbReflectUtil
 */
public class DbReflectUtil<c> implements DataOperation<c> {

    private final DataSqlOperation<c> SQL_OPERATION = new DbReflectSqlUtil<>();

    @Override
    public List<c> findData(c clazz, int currentPage, int pageIndex) {
        String findDataSQL = SQL_OPERATION.findDataSql(clazz, currentPage, pageIndex);
        DbUtil dbUtil = new DbUtil();
        Connection connection = dbUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<c> entities = new ArrayList<c>();
        Field[] fields=clazz.getClass().getDeclaredFields();
        try {
            statement = connection.prepareStatement(findDataSQL);
            resultSet = statement.executeQuery(findDataSQL);
            while (resultSet.next()) {
                Object obj = clazz.getClass().newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    field.set(obj, String.valueOf(resultSet.getObject(i + 1)));
                }
                entities.add((c) obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("findData出错");
            release(connection, statement, resultSet);
        }
        release(connection, statement, resultSet);
        return entities;
    }

    public int findAllPage(c clazz){
        String sql=SQL_OPERATION.findAllPage(clazz);

        DbUtil dbUtil = new DbUtil();
        Connection connection = dbUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        int allObject=-1;
        try {
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery(sql);
            while(resultSet.next()) {
                allObject = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(connection,statement,resultSet);
        }
        System.out.println("All Object: "+allObject);
        int allPage;
        if (allObject==0)
            allPage=1;
        else {
            if (allObject%10==0)
                allPage=allObject/10;
            else
                allPage=(allObject/10)+1;
        }
        System.out.println("ALl page:"+allPage);
         return allPage;
    }


    public boolean addStudent(c clazz) {
        Field[] fields=clazz.getClass().getDeclaredFields();
        DbUtil dbUtil = new DbUtil();
        Connection connection=dbUtil.getConnection();
        PreparedStatement statement=null;

        try {
            String sql=SQL_OPERATION.addStudent(clazz);
            statement=connection.prepareStatement(sql);
            for (int i=0;i<fields.length;i++){
                fields[i].setAccessible(true);
                Object info=fields[i].get(clazz);
                statement.setObject((i+1),info);
            }
            int i=statement.executeUpdate();
            return i>0;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
           release(connection,statement,null);
        }
        return false;
    }

    public void release(Connection con, Statement sta, ResultSet res) {

        try {
            if (res != null) {
                res.close();
            }


            if (sta != null) {
                sta.close();
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<c> findAllId(c clazz){
        Field[] fields= clazz.getClass().getDeclaredFields();
        Object object;
        List<c> list=new ArrayList<>();
        DbUtil dbUtil=new DbUtil();
        Connection connection=dbUtil.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;

        try {
            String sql=SQL_OPERATION.findAllId(clazz);
            System.out.println("find all id:"+sql);
            statement= connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while (resultSet.next()) {
                object=clazz.getClass().newInstance();
                for (int i = 0; i < 1; i++) {
                    Field field = fields[0];
                    field.setAccessible(true);
                    field.set(object, resultSet.getObject( 1));
                }
                list.add((c) object);
            }
            return list;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            release(connection, statement, resultSet);
        }
        return null;
    }

    public boolean deleteStudent(c clazz, Object id) {
        DbUtil dbUtil=new DbUtil();
        Connection connection=dbUtil.getConnection();
        PreparedStatement statement=null;

        try {
            String sql=SQL_OPERATION.deleteStudent(clazz);
            System.out.println(sql);
            statement=connection.prepareStatement(sql);
            statement.setObject(1,id);

            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(connection, statement, null);
        }
        return false;
    }

    public Object findById(c clazz, Object id) {
        Field[] fields= clazz.getClass().getDeclaredFields();
        Object object = null;
        DbUtil dbUtil=new DbUtil();
        Connection connection=dbUtil.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;


        try {
            String sql = SQL_OPERATION.findStudent(clazz);
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();

            object = clazz.getClass().newInstance();
            while (resultSet.next()) {
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    field.set(object, String.valueOf(resultSet.getObject(i + 1)));
                }
            }
        } catch (RuntimeException | SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {
            release(connection, statement, resultSet);
        }
        System.out.println("Stu:"+object);
        return object;
    }

    public boolean update(Object object, Object id) {
        Field[] fields= object.getClass().getDeclaredFields();
        DbUtil dbUtil=new DbUtil();
        Connection connection=dbUtil.getConnection();
        PreparedStatement statement=null;

        try {
            String sql = SQL_OPERATION.upDateStudent((c) object);
            statement = connection.prepareStatement(sql);
            for (int i=0;i<fields.length;i++){
                fields[i].setAccessible(true);
                Object o=fields[i].get(object);
                statement.setObject((i+1),o);
            }
            statement.setObject((fields.length+1),id);

            return statement.executeUpdate()>0;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            release(connection, statement, null);
        }
        return false;
    }
}

