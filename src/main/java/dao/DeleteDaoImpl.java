package dao;

import utils.DatebaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteDaoImpl implements DeleteDao{
    @Override
    public void del(String v) {
        Connection connection= DatebaseUtil.getConnection();
        PreparedStatement pre=null;
        ResultSet res=null;

        String sql="DELETE FROM test.student WHERE id=?";

        try {
            pre= connection.prepareStatement(sql);
            pre.setObject(1,v);
            System.out.println(v);

            int dropRow = pre.executeUpdate();
            if (dropRow != 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
