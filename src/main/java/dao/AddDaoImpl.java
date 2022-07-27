package dao;

import dao.sql.SqlDao;
import dao.sql.SqlDaoImpl;
import utils.DatebaseUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddDaoImpl implements AddDao {
    @Override
    public void add(Object student) {
        Field[] obj = student.getClass().getDeclaredFields();
        Connection connection = DatebaseUtil.getConnection();
        PreparedStatement pre = null;
        ResultSet res = null;

        try {
            String sql = SqlDaoImpl.addSql(student.getClass());
            pre = connection.prepareStatement(sql);

            for (int i = 0; i < obj.length; i++) {
                obj[i].setAccessible(true);
                pre.setObject(i + 1, obj[i].get(student));
            }
            System.out.println(student);

            int addRow = pre.executeUpdate();
            if (addRow != 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");

            }
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

