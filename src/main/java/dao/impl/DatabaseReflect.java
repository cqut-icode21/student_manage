package dao.impl;



import annotation.Column;
import annotation.Id;
import dao.BaseDao;
import utils.DatabaseUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 潘琴
 * @date:2021/8/1
 * @description: 实现SQL语句 及 增删改查方法
 */
public class DatabaseReflect implements BaseDao {
    /**
     * 数据库连接
     */
    Connection connection = null;
    /**
     * 预编译
     */
    PreparedStatement statement = null;
    /**
     * 结果集
     */
    ResultSet resultSet = null;
    /**
     * SQL语句实现类
     */
    SqlDaoImpl sqlDao = new SqlDaoImpl();

    /**
     * 查询所有
     *
     * @param clazz 类
     * @return 类的所有结果数组
     */
    @Override
    public <T> List<T> findAll(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        T object = null;

        Field[] fields = clazz.getDeclaredFields();
        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        // 2、获取到SQL语句
        String sql = sqlDao.findAllSql(clazz);
        try {
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、执行SQL语句
            System.out.println("findAll:" + statement);
            resultSet = statement.executeQuery();
            //5. 遍历结果集 并添加到list中
            while (resultSet.next()) {
                // 获取执行结果   创建一个空的clazz对象
                object = clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(object, resultSet.getObject(i + 1));
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // 6、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return list;
    }

    /**
     * 根据id查询
     *
     * @param clazz 类
     * @param id    要查询的对象的id
     * @return 查询的对象的所有信息
     */
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

            // 6、获取执行结果  创建一个空的clazz对象
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
        } catch (RuntimeException | SQLException | IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
        } finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return object;
    }

    /**
     * 删除
     *
     * @param clazz 类
     * @param id    需要删除的对象的id
     * @return 是否成功
     */
    @Override
    public boolean delete(Class<?> clazz, Object id) {
        int i = -1;
        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.deleteSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            statement.setObject(1, id);

            // 5、执行SQL语句
            System.out.println("delete:" + statement);
            i = statement.executeUpdate();   //如果执行成功返回更新的行数，即 i > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        if (i > 0) {
            System.out.println("成功删除！");
        }
        return i > 0;
    }

    /**
     * 新增
     *
     * @param object 新增的对象
     * @return 是否成功
     */
    @Override
    public boolean add(Object object) {
        int j = -1;
        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.addSql(object.getClass());
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);

            //4.获取属性值
            Field[] fields = object.getClass().getDeclaredFields();

            // 5、填充参数（可选）
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                // 保证私有属性也能进行操作
                field.setAccessible(true);
                // 设置指定对象属性的值
                Object o = field.get(object);   //获取指定对象的此属性值
                statement.setObject(i + 1, o);
            }

            // 6、执行SQL语句
            System.out.println("add:" + statement);
            j = statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        if (j > 0) {
            System.out.println("成功添加！");
        }
        return j > 0;
    }

    /**
     * 修改
     *
     * @param object 对象
     * @param id     需要修改的对象的id
     * @return 是否成功
     */
    @Override
    public boolean update(Object object, Object id) {
        int j = -1;
        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.updateSql(object.getClass());
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);

            //4.获取属性值
            Field[] fields = object.getClass().getDeclaredFields();

            // 5、填充参数（可选）
            int i = 0;
            for (Field field : fields) {

                // 保证私有属性也能进行操作
                field.setAccessible(true);

                //id 不能更改
                if (field.getAnnotation(Id.class) != null)
                    continue;
                Object o = field.get(object);   //获取指定对象的此属性值
                statement.setObject(i + 1, o);    // 设置指定对象属性的值
                i++;
            }
            statement.setObject(fields.length, id);

            // 6、执行SQL语句
            System.out.println("add:" + statement);
            j = statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        if (j > 0) {
            System.out.println("更新成功！");
        }
        return j > 0;
    }
}
