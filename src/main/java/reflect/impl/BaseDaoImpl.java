package reflect.impl;

import reflect.dao.BaseDao;
import reflect.entities.Student;
import reflect.utils.DatabaseUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 对数据库增删改查方法的实现
 */
public class BaseDaoImpl implements BaseDao {
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
     * 获取该页学生数据
     *
     * @param currentPage 当前页
     * @return list
     */
    @Override
    public ArrayList<Student> getData(int currentPage, int temp) {
        ArrayList<Student> result = new ArrayList<>();
        connection = DatabaseUtil.getConnection();
        String sql = sqlDao.getDataOfStudent(currentPage);
        Class<Student> clazz = Student.class;
        Field[] fields = clazz.getDeclaredFields();

        int starIndex;
        int endIndex;
        int allPage = (temp / 10) + 1;
        if (currentPage == allPage) {
            endIndex = temp;
        } else {
            endIndex = currentPage * 10;
        }
        starIndex = (currentPage - 1) * 10;

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
                    // 保证私有属性也能进行操作·
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(result.get(j), resultSet.getObject(i + 1));
                }
                j++;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return result;
    }

    /**
     * 新增
     *
     * @param aStudent 新增的对象
     * @return 是否成功
     */
    @Override
    public boolean add(Class<?> clazz, ArrayList<String> aStudent) {
        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.addSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            for (int i = 0; i < aStudent.size(); i++) {
                statement.setObject(i + 1, aStudent.get(i));
            }
            // 5、执行SQL语句
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("增加成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("增加失败");
        } finally {
            // 6、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return false;
    }

    /**
     * 删除
     *
     * @param clazz 类
     * @param arrayId    需要删除的对象的id数组
     */
    @Override
    public void delete(Class<?> clazz, String[] arrayId) {

        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.deleteSql(clazz, arrayId.length);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            for (int i = 0; i < arrayId.length; i++) {
                statement.setObject(i + 1, arrayId[i]);//占位符填入要删除的对应行id
            }
            // 5、执行SQL语句
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("删除成功。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
    }

    /**
     * 修改
     *
     * @param clazz    对象
     * @param aStudent 需要修改的对象的list
     */
    @Override
    public void update(Class<?> clazz, ArrayList<String> aStudent) {
        connection = DatabaseUtil.getConnection();
        String sql = sqlDao.updateSql(clazz);
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < aStudent.size(); i++) {
                if (i == 7) {
                    statement.setString(i + 1, aStudent.get(0));
                } else {
                    statement.setString(i + 1, aStudent.get(i + 1));
                }
            }
            int i = statement.executeUpdate();
            if (i > 0) {
                System.out.println("修改成功。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
    }

    /**
     * 查询所有的行数
     *
     * @return 返回所有结果
     */
    @Override
    public int findAllTr() {
        int all = 0;
        connection = DatabaseUtil.getConnection();
        String sql = sqlDao.findAllSql();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.next();
            all = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return all;
    }

    @Override
    public ArrayList<Student> search(Class<?> clazz, String tr) {
        connection = DatabaseUtil.getConnection();
        String sql = sqlDao.search(clazz, tr);
        ArrayList<Student> result = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            Field[] fields = clazz.getDeclaredFields();
            resultSet = statement.executeQuery();
            int j = 0;
            while (resultSet.next()) {
                result.add((Student) clazz.newInstance());//添加每一行学生的数据
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(result.get(j), resultSet.getObject(i + 1));
                }
                j++;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return result;
    }
}
