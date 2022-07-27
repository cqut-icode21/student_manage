package dao;

import dao.sql.SqlDao;
import dao.sql.SqlDaoImpl;
import utils.DatebaseUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpDateImpl implements UpDate {
    @Override
    public void update(Object student, Object id) {

        Field[] obj = student.getClass().getDeclaredFields();
        Connection connection = DatebaseUtil.getConnection();
        PreparedStatement pre = null;
        ResultSet res = null;
        try {

            String sql = SqlDaoImpl.updateSql(student.getClass());

            pre = connection.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                obj[i].setAccessible(true);
                pre.setObject(i + 1, obj[i].get(student));
            }
            pre.setObject(9, id);
            int updateRow = pre.executeUpdate();
            if (updateRow != 0) {
                System.out.println("修改成功");

            } else {
                System.out.println("修改失败");

            }

        } catch (RuntimeException | SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);

        }
    }

}