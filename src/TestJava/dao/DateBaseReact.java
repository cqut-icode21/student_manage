package TestJava.dao;

import TestJava.uitl.DateBaseConnection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DateBaseReact {
    Connection connection=null;
    /**
     * 预编译
     */
    PreparedStatement statement = null;
    /**
     * 结果集
     */
    ResultSet resultSet = null;
    SqlWords sql=new SqlWords();
    //查找并返回对象
    public Object findById(Class<?> clazz,Object id) throws Exception {
        sql.createTable(clazz);
        Object object=null;
        //获取属性
        Field[] fields=clazz.getDeclaredFields();
        try {
            connection= DateBaseConnection.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sql2= sql.createTable(clazz);
        statement=connection.prepareStatement(sql2);
        statement.execute();
        String sqls= sql.findSqlById(clazz);
        statement=connection.prepareStatement(sqls);
        statement.setObject(1,id);
        System.out.println("findById:"+statement);
        resultSet=statement.executeQuery();
        object=clazz.getDeclaredConstructor().newInstance();
        while(resultSet.next()){
            for(int i=0;i<fields.length;i++){
                Field field=fields[i];
                field.setAccessible(true);
                field.set(object,resultSet.getObject(i+1));
            }
        }
        DateBaseConnection.closeConnection(connection, statement, resultSet);
        return object;
    }
    /**
     * 删除
     *
     * @param clazz 类
     * @param id    需要删除的对象的id
     * @return 是否成功
     */

    public boolean delete(Class<?> clazz, Object id) throws SQLException {
        sql.createTable(clazz);
        try {
            connection=DateBaseConnection.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sql2= sql.createTable(clazz);
        statement=connection.prepareStatement(sql2);
        statement.execute();
        String sqls= sql.deleteSql(clazz);
        try {
            statement=connection.prepareStatement( sqls);
            statement.setObject(1,id);
            statement.executeUpdate();
            System.out.println("successful delete");
            return true;
        } catch (SQLException e) {
            System.out.println("delete wrong");
            return false;
        }finally {
                DateBaseConnection.closeConnection(connection, statement, resultSet);
        }
    }

    /**
     * 新增
     *
     * @param object 新增的对象
     * @return 是否成功
     */

    public boolean add(Object object) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Field[] fields=object.getClass().getDeclaredFields();
        try {
            connection=DateBaseConnection.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sql2= sql.createTable(object.getClass());
        statement=connection.prepareStatement(sql2);
        statement.execute();
        String sqls= sql.addSql(object.getClass());
        try {
            statement = connection.prepareStatement(sqls);
            for (int i=0;i<fields.length;i++){
                fields[i].setAccessible(true);
                Object value=fields[i].get(object);
                statement.setObject(i+1,value);
            }
            statement.executeUpdate();
            System.out.println("successful add");
            return true;
        }catch (SQLException e) {
            System.out.println("wrong add");
            return false;
        }finally {
            DateBaseConnection.closeConnection(connection, statement, resultSet);
        }
    }

    /**
     * 修改
     *
     * @param object 对象
     * @param id     需要修改的对象的id
     * @return 是否成功
     */

    public boolean update(Object object, Object id) throws NoSuchMethodException, SQLException {
        Field[] fields=object.getClass().getDeclaredFields();
         sql.createTable(object.getClass());
        try {
            connection=DateBaseConnection.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sql2= sql.createTable(object.getClass());
        statement=connection.prepareStatement(sql2);
        statement.execute();
        String sqls= sql.updateSql(object.getClass());
        try {
            statement = connection.prepareStatement(sqls);
            for (int i=0;i<fields.length;i++){
                fields[i].setAccessible(true);
                Object value=fields[i].get(object);
                statement.setObject(i+1,value);
            }
            statement.setObject(fields.length+1,id);
            statement.executeUpdate();
            System.out.println("successful update");
            return true;
        }catch (SQLException | IllegalAccessException e) {
            System.out.println("wrong update");
            return false;
        }finally {
            DateBaseConnection.closeConnection(connection, statement, resultSet);
        }
    }
    public List<Object> findAll(Class<?> clazz) throws Exception {
        sql.createTable(clazz);
        List<Object> list = new ArrayList<>();
        //获取属性
        Object object = null;
        Field[] fields = clazz.getDeclaredFields();
        try {
            connection = DateBaseConnection.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sql2 = sql.createTable(clazz);
        statement = connection.prepareStatement(sql2);
        statement.execute();
        String sqls = sql.findAllSql(clazz);
        statement = connection.prepareStatement(sqls);
        System.out.println("findById:" + statement);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            object=clazz.getDeclaredConstructor().newInstance();
            for(int i=0;i<fields.length;i++){
                Field field=fields[i];
                field.setAccessible(true);
                field.set(object,resultSet.getObject(i+1));
            }
            list.add(object);
        }
        DateBaseConnection.closeConnection(connection, statement, resultSet);
        return list;
    }
}
