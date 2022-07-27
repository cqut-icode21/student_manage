package dao.imp;

import message.Students;
import util.DatabaseUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return allTr;
    }

    public ArrayList<Students> getData(int currentPage, int temp) {
        ArrayList<Students> list = new ArrayList<>();
        Class<Students> clazz = Students.class;
        connection = DatabaseUtil.getConnection();
        try {
            String sql = sqlCreat.getDataOfStudent(clazz,currentPage);
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Students students = new Students();
                students.setId(resultSet.getString(1));
                students.setName(resultSet.getString(2));
                students.setSex(resultSet.getString(3));
                students.setCollege(resultSet.getString(4));
                students.setProfessional(resultSet.getString(5));
                students.setGrade(resultSet.getString(6));
                students.setClazz(resultSet.getString(7));
                students.setAge(resultSet.getString(8));
                list.add(students);
//                System.out.println(students);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseUtil.close(connection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;

    }

    public void add(Class<?> clazz, ArrayList<String> aStudent) {
        connection = DatabaseUtil.getConnection();
        String sql = sqlCreat.add(clazz);

        try {
            statement = connection.prepareStatement(sql);

            for (int i = 0; i < aStudent.size(); i++) {
                statement.setString(i + 1,aStudent.get(i));
            }

            int i = statement.executeUpdate();
            if (i > 0) {
                System.out.println("插入成功！");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
    }

    public void delete(Class<?> clazz, String[] id) {
        connection = DatabaseUtil.getConnection();
        String sql = sqlCreat.delete(clazz, id.length);
        try {
            statement = connection.prepareStatement(sql);

            for (int i = 0; i < id.length; i++) {
                statement.setString(i + 1, id[i]);
            }

            int i = statement.executeUpdate();
            if (i > 0) {
                System.out.println("删除成功。");
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
                if (i == 7)
                    statement.setString(i + 1, aStudent.get(0));
                else
                    statement.setString(i + 1, aStudent.get(i + 1));
            }

            int i = statement.executeUpdate();
            if (i > 0) {
                System.out.println("修改成功。");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
    }

    public ArrayList<Students> search(Class<?> clazz, String tr) {
        connection = DatabaseUtil.getConnection();
        String sql = sqlCreat.search(clazz, tr);
        ArrayList<Students> result = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);

            Field[] fields = clazz.getDeclaredFields();

            resultSet = statement.executeQuery();

            int j = 0;
            while (resultSet.next()) {
                result.add((Students) clazz.newInstance());

                for (int k = 0; k < fields.length; k++) {
                    Field field = fields[k];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(result.get(j), resultSet.getObject(k + 1));
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
