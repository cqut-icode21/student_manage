package zhz.dao.imp;

import zhz.pojo.Student;
import zhz.util.DatabaseUtil;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 龙
 */
public class BaseDaoImp {

    SqlDaoImp sqlCreat = new SqlDaoImp();
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet = null;

    public int allTr() {
        int allTr = 0;
        connection = DatabaseUtil.getConnection();
        String sql = sqlCreat.getAllTr();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.next();
            allTr = resultSet.getInt(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return allTr;
    }

    public ArrayList<Student> getData() {
        ArrayList<Student> result = new ArrayList<>();
        connection = DatabaseUtil.getConnection();
        String sql = sqlCreat.getDataOfStudent(Student.class);
        Class<Student> clazz = Student.class;
        Field[] fields = clazz.getDeclaredFields();

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int j = 0;
            while (resultSet.next()) {
                result.add(clazz.newInstance());
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(result.get(j), resultSet.getString(i+1));
                }
                j++;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return result;
    }

    public void add(Class<?> clazz, ArrayList<String> aStudent) {
        connection = DatabaseUtil.getConnection();
        String sql = sqlCreat.add(clazz);

        try {
            statement = connection.prepareStatement(sql);

            for (int i = 0; i < aStudent.size(); i++) {
                statement.setString(i + 1,aStudent.get(i));
            }

            System.out.println(statement);
            int i = statement.executeUpdate();
            if (i > 0) {
                System.out.println("插入成功！");
                System.out.println("------------------");
                System.out.println("------------------");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
    }

    public void update(Class<?> clazz, ArrayList<String> aStudent) {
        connection = DatabaseUtil.getConnection();
        String sql = sqlCreat.update(clazz);
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < aStudent.size(); i++) {
                if (i == 7) {
                    statement.setString(i + 1, aStudent.get(0));
                } else {
                    statement.setString(i + 1, aStudent.get(i + 1));
                }
            }

            System.out.println(statement);

            int i = statement.executeUpdate();
            if (i > 0) {
                System.out.println("修改成功");
                System.out.println("------------------");
                System.out.println("------------------");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
    }

    public void delete(Class<?> clazz, String id) {
        connection = DatabaseUtil.getConnection();
        String sql = sqlCreat.delete(clazz);
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            System.out.println(statement);

            int i = statement.executeUpdate();
            if (i > 0) {
                System.out.println("删除成功");
                System.out.println("------------------");
                System.out.println("------------------");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
    }

    public ArrayList<Student> search(Class<?> clazz, String tr) {
        connection = DatabaseUtil.getConnection();
        String sql = sqlCreat.search(clazz, tr);
        System.out.println(sql);
        ArrayList<Student> result = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            Field[] fields = clazz.getDeclaredFields();
            resultSet = statement.executeQuery();
            int j = 0;
            while (resultSet.next()) {
                result.add((Student) clazz.newInstance());

                for (int k = 0; k < fields.length; k++) {
                    Field field = fields[k];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(result.get(j), resultSet.getString(k + 1));
                }
                j++;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return result;
    }
}
