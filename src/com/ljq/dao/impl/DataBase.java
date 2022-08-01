package ljq.dao.impl;

import ljq.entity.Students;
import ljq.utils.DataBaseUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase implements BaseDao {
    Connection connection = null;

    PreparedStatement statement = null;

    ResultSet resultSet = null;

    SqlDaoImp sqlDao = new SqlDaoImp();

    public int allTr() {
        int allTr = 0;
        connection = DataBaseUtil.getConnection();
        String sql = sqlDao.getAllTr();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.next();
            allTr = resultSet.getInt(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DataBaseUtil.close(connection, statement, resultSet);
        }
        return allTr;
    }

    public ArrayList<Students> getData(int currentPage, int temp) {
        ArrayList<Students> result = new ArrayList<>();
        connection = DataBaseUtil.getConnection();
        String sql = sqlDao.getDataOfStudent(currentPage);
        Class<Students> clazz = Students.class;
        Field[] fields = clazz.getDeclaredFields();

        int starIndex = 0;
        int endIndex = 0;
        int allPage = (temp / 10) + 1;
        if (currentPage == allPage) {
            endIndex = temp;
            starIndex = (currentPage - 1) * 10;
        } else {
            endIndex = currentPage * 10;
            starIndex = (currentPage - 1) * 10;
        }

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, starIndex);
            statement.setInt(2, endIndex - starIndex);
            resultSet = statement.executeQuery();
            int j = 0;

            while (resultSet.next()) {
                result.add(clazz.newInstance());

                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(result.get(j), resultSet.getObject(i + 1));
                }
                j++;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException throwable) {
            throwable.printStackTrace();
        } finally {
            DataBaseUtil.close(connection, statement, resultSet);
        }

        return result;
    }

    @Override
    public Object findById(Class<?> clazz, Object id) {
        Object object = null;
        Field[] fields = clazz.getDeclaredFields();

        // 1、获取数据库连接
        connection = DataBaseUtil.getConnection();
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
        } catch (RuntimeException | SQLException | IllegalAccessException | InstantiationException throwable) {
            throwable.printStackTrace();
        } finally {
            // 7、释放资源
            DataBaseUtil.close(connection, statement, resultSet);
        }
        return object;
    }

//    @Override
//    public boolean delete(Class<?> clazz, Object id) {
//        int result = -1;
//        // 1、获取数据库连接
//        connection = DataBaseUtil.getConnection();
//        try {
//            // 2、获取到SQL语句
//            String sql = sqlDao.deleteSql(clazz);
//            // 3、预编译SQL语句
//            statement = connection.prepareStatement(sql);
//            // 4、填充参数（可选）
//            statement.setObject(1, id);
//            // 5、执行SQL语句
////            System.out.println("delete:" + statement);
//            System.out.println("删除成功！");
//            result = statement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // 7、释放资源
//            DataBaseUtil.close(connection, statement, resultSet);
//        }
//        return result > 0;
//    }

    //    @Override
//    public boolean add(Object object) {
//        int result = -1;
//        // 1、获取数据库连接
//        connection = DataBaseUtil.getConnection();
//        Field[] fields = object.getClass().getDeclaredFields();
//        try {
//            // 2、获取到SQL语句
//            String sql = sqlDao.addSql(object.getClass());
//            // 3、预编译SQL语句
//            statement = connection.prepareStatement(sql);
//            // 4、填充参数（可选）
//            for (int i = 0; i < fields.length; i++) {
//                Field field = fields[i];
//                // 保证私有属性也能进行操作
//                field.setAccessible(true);
//                // 设置指定对象属性的值
//                Object o = field.get(object);
//                statement.setObject(i + 1, o);
//            }
//            // 5、执行SQL语句
////            System.out.println("add:" + statement);
//            System.out.println("添加成功！");
//            result = statement.executeUpdate();
//        } catch (SQLException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // 7、释放资源
//            DataBaseUtil.close(connection, statement, resultSet);
//        }
//        return result > 0;
//    }
    @Override
    public void add(Class<?> clazz, ArrayList<String> student) {
        connection = DataBaseUtil.getConnection();
        try {
            String sql = sqlDao.addSql(clazz);
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < student.size(); i++) {
                statement.setString(i + 1, student.get(i));
            }
            int i = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 7、释放资源
            DataBaseUtil.close(connection, statement, resultSet);
        }
    }

    //        @Override
//    public boolean update(Object object, Object id) {
//        int result = -1;
//        Field[] fields = object.getClass().getDeclaredFields();
//
//        // 1、获取数据库连接
//        connection = DataBaseUtil.getConnection();
//        try {
//            // 2、获取到SQL语句
//            String sql = sqlDao.updateSql(object.getClass());
//            // 3、预编译SQL语句
//            statement = connection.prepareStatement(sql);
//            // 4、填充参数（可选）
//            for (int i = 0; i < fields.length; i++) {
//                Field field = fields[i];
//                // 保证私有属性也能进行操作
//                field.setAccessible(true);
//                Object o = field.get(object);
//                // 设置指定对象属性的值
//                if (field.getAnnotation(Id.class) != null) {
//                    statement.setObject(8, o);
//                    continue;
//                } else
//                    statement.setObject(i, o);
//            }
////            statement.setObject(fields.length,id);
//            // 5、执行SQL语句
////            System.out.println("update:" + statement);
//            System.out.println("修改成功！");
//            result = statement.executeUpdate();
//        } catch (SQLException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // 7、释放资源
//            DataBaseUtil.close(connection, statement, resultSet);
//        }
//        return result > 0;
//    }
    @Override
    public void update(Class<?> clazz, ArrayList<String> student) {
        connection = DataBaseUtil.getConnection();
        try {
            String sql = sqlDao.updateSql(clazz);
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < student.size(); i++) {
                if (i == 7) {
                    statement.setString(i + 1, student.get(0));
                } else {
                    statement.setString(i + 1, student.get(i + 1));
                }
            }
            int i = statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 7、释放资源
            DataBaseUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public void delete(Class<?> clazz, String[] id) {
        connection = DataBaseUtil.getConnection();
        try {
            String sql = sqlDao.deleteSql(clazz, id.length);
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < id.length; i++) {
                statement.setString(i + 1, id[i]);
                System.out.println("id" + id[i] + i);
            }
            int i = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 7、释放资源
            DataBaseUtil.close(connection, statement, resultSet);
        }
    }

    public ArrayList<Students> search(Class<?> clazz, String str) {
        connection = DataBaseUtil.getConnection();
        ArrayList<Students> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        try {
            String sql = sqlDao.searchSql(clazz, str);
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int j = 0;
            while (resultSet.next()) {
                list.add((Students) clazz.newInstance());

                for (int k = 0; k < fields.length; k++) {
                    Field field = fields[k];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(list.get(j), resultSet.getObject(k + 1));
                }
                j++;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            // 7、释放资源
            DataBaseUtil.close(connection, statement, resultSet);
        }
        return list;
    }
}
