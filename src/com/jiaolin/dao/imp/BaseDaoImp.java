package com.jiaolin.dao.imp;

import com.jiaolin.dao.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jiaolin.util.DatabaseUtil.close;
import static com.jiaolin.util.DatabaseUtil.getConnection;

/**
 * sql语句方法实现
 *
 * @author BaoXiangjie
 */
public class BaseDaoImp implements BaseDao {

    /**
     * 数据库连接
     * 预加载sql语句
     * 结果集
     * 获取sql语句
     */
    SqlDaoImp sqlCreat = new SqlDaoImp();
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet = null;

    /**
     * 查询所有数据sql语句方法实现
     *
     * @param clazz 类对象
     * @return 结果列表
     */
    @SuppressWarnings("All")
    @Override
    public <T> List<T> findAll(Class<?> clazz) {
        connection = getConnection();
        try {
            String sql = sqlCreat.findAllSql(clazz);
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
//            ---------------------------------------------------------------
            List list = new ArrayList();
            ResultSetMetaData md = resultSet.getMetaData();
            int columnCount = md.getColumnCount();
            while (resultSet.next()) {
                Map rowData = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), resultSet.getObject(i));
                }
                list.add(rowData);
            }
            return list;
//            ---------------------------------------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, resultSet);
        }
        return null;
    }

    /**
     * 添加数据sql语句方法实现
     *
     * @param clazz 类对象
     * @return 是否添加成功
     */
    @Override
    public boolean add(Class<?> clazz, ArrayList<String> aStudent) {
        connection = getConnection();
        String sql = sqlCreat.addSql(clazz);
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < aStudent.size(); i++) {
                statement.setString(i + 1, aStudent.get(i));
            }
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(connection, statement, resultSet);
        }
        return false;
    }

    /**
     * 根据id删除数据
     *
     * @param clazz 类对象
     * @param id    需要删除的对象的id
     * @return 是否删除成功
     */
    @Override
    public boolean delete(Class<?> clazz, String[] id) {
        connection = getConnection();
        String sql = sqlCreat.deleteSql(clazz, id.length);
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < id.length; i++) {
                statement.setString(i + 1, id[i]);
            }
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(connection, statement, resultSet);
        }
        return false;
    }

    /**
     * 根据id查找数据
     *
     * @param clazz 类对象
     * @param id    要查询的对象的id
     * @return 查找的数据
     */
    @Override
    public <T> List<T> findById(Class<?> clazz, Object id) {
        return null;
    }

    /**
     * 根据id修改数据
     *
     * @param aStudent 所要修改的数据
     * @return 是否修改成功
     */
    @Override
    public boolean update(Class<?> clazz, ArrayList<String> aStudent) {
        connection = getConnection();
        String sql = sqlCreat.updateSql(clazz);
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < aStudent.size(); i++) {
                if (i == 7)
                    statement.setString(i + 1, aStudent.get(0));
                else
                    statement.setString(i + 1, aStudent.get(i + 1));
            }

            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(connection, statement, resultSet);
        }
        return false;
    }
}
//
//    SqlDaoImp sqlCreat = new SqlDaoImp();
//    Connection connection;
//    PreparedStatement statement;
//    ResultSet resultSet = null;
//
//    @Override
//    public int findAll() {
//        int allTr = 0;
//        connection = DatabaseUtil.getConnection();
//        String sql = sqlCreat.findAllSql();
//        try {
//            statement = connection.prepareStatement(sql);
//            resultSet = statement.executeQuery();
//            resultSet.next();
//            allTr = resultSet.getInt(1);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            DatabaseUtil.close(connection, statement, resultSet);
//        }
//        return allTr;
//    }
//
//    public ArrayList<Student> getData(int currentPage, int temp) {
//        ArrayList<Student> result = new ArrayList<>();
//        connection = DatabaseUtil.getConnection();
//        String sql = sqlCreat.getDataOfStudent(currentPage);
//        Class<Student> clazz = Student.class;
//        Field[] fields = clazz.getDeclaredFields();
//
//        int starIndex = 0;
//        int endIndex = 0;
//        int allPage = (temp / 10) + 1;
//        if (currentPage == allPage) {
//            endIndex = temp;
//            starIndex = (currentPage - 1) * 10;
//        } else {
//            endIndex = currentPage * 10;
//            starIndex = (currentPage - 1) * 10;
//        }
//
//        try {
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, starIndex);
//            statement.setInt(2, endIndex - starIndex);
//            resultSet = statement.executeQuery();
//            int j = 0;
//
//            while (resultSet.next()) {
//                result.add(clazz.newInstance());
//
//                for (int i = 0; i < fields.length; i++) {
//                    Field field = fields[i];
//                    // 保证私有属性也能进行操作
//                    field.setAccessible(true);
//                    // 设置指定对象属性的值
//                    field.set(result.get(j), resultSet.getObject(i + 1));
//                }
//                j++;
//            }
//        } catch (SQLException | IllegalAccessException | InstantiationException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            DatabaseUtil.close(connection, statement, resultSet);
//        }
//
//        return result;
//    }
//
//    public void add(Class<?> clazz, ArrayList<String> aStudent) {
//        connection = DatabaseUtil.getConnection();
//        String sql = sqlCreat.addSql(clazz);
//
//        try {
//            statement = connection.prepareStatement(sql);
//
//            for (int i = 0; i < aStudent.size(); i++) {
//                statement.setString(i + 1,aStudent.get(i));
//            }
//
//            int i = statement.executeUpdate();
//            if (i > 0) {
//                System.out.println("插入成功！");
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            DatabaseUtil.close(connection, statement, resultSet);
//        }
//    }
//
//    public void delete(Class<?> clazz, String[] id) {
//        connection = DatabaseUtil.getConnection();
//        String sql = sqlCreat.deleteSql(clazz, id.length);
//        try {
//            statement = connection.prepareStatement(sql);
//
//            for (int i = 0; i < id.length; i++) {
//                statement.setString(i + 1, id[i]);
//            }
//
//            int i = statement.executeUpdate();
//            if (i > 0) {
//                System.out.println("删除成功。");
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            DatabaseUtil.close(connection, statement, resultSet);
//        }
//    }
//
//    public void update(Class<?> clazz, ArrayList<String> aStudent) {
//        connection = DatabaseUtil.getConnection();
//        String sql = sqlCreat.updateSql(clazz);
//        try {
//            statement = connection.prepareStatement(sql);
//            for (int i = 0; i < aStudent.size(); i++) {
//                if (i == 7)
//                    statement.setString(i + 1, aStudent.get(0));
//                else
//                    statement.setString(i + 1, aStudent.get(i + 1));
//            }
//
//            int i = statement.executeUpdate();
//            if (i > 0) {
//                System.out.println("修改成功。");
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            DatabaseUtil.close(connection, statement, resultSet);
//        }
//    }
//
//    public ArrayList<Student> search(Class<?> clazz, String tr) {
//        connection = DatabaseUtil.getConnection();
//        String sql = sqlCreat.search(clazz, tr);
//        ArrayList<Student> result = new ArrayList<>();
//        try {
//            statement = connection.prepareStatement(sql);
//
//            Field[] fields = clazz.getDeclaredFields();
//
//            resultSet = statement.executeQuery();
//
//            int j = 0;
//            while (resultSet.next()) {
//                result.add((Student) clazz.newInstance());
//
//                for (int k = 0; k < fields.length; k++) {
//                    Field field = fields[k];
//                    // 保证私有属性也能进行操作
//                    field.setAccessible(true);
//                    // 设置指定对象属性的值
//                    field.set(result.get(j), resultSet.getObject(k + 1));
//                }
//                j++;
//            }
//        } catch (SQLException | IllegalAccessException | InstantiationException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            DatabaseUtil.close(connection, statement, resultSet);
//        }
//        return result;
//    }
//}
