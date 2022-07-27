package com.he.dao.impl;

import com.he.annotation.Column;
import com.he.dao.DataOperation;
import com.he.dao.DataSqlOperation;
import com.he.util.DbUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 13253
 * @date 2021/7/29 19:16
 * @className DbReflectUtil
 */
public class DbReflectUtil<c> implements DataOperation<c> {

    private final DataSqlOperation<c> SQL_OPERATION = new DbReflectSqlUtil<>();

    /**
     * 得到数据
     *
     * @param clazz
     * @param currentPage
     * @param pageIndex
     * @return 返回需要的数据
     */
    @Override
    public List<c> findData(c clazz, int currentPage, int pageIndex) {
        String findDataSQL = SQL_OPERATION.findDataSql(clazz, currentPage, pageIndex);
        DbUtil dbUtil = new DbUtil();
        Connection connection = dbUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<c> entities = new ArrayList<c>();
        try {
            statement = connection.prepareStatement(findDataSQL);
            resultSet = statement.executeQuery(findDataSQL);
            while (resultSet.next()) {
                Object obj = clazz.getClass().newInstance();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    Field field = null;
                    Field[] declaredFields = obj.getClass().getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        String s = declaredField.getAnnotation(Column.class).columnName();
                        if (s.equals(columnName)) {
                            field = declaredField;
                            break;
                        }
                    }
                    field.setAccessible(true);
                    field.set(obj, resultSet.getObject(columnName));
                }
                entities.add((c) obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("findData出错");
            release(connection, statement, resultSet);
        }
        release(connection, statement, resultSet);
        return entities;
    }

    public void release(Connection con, Statement sta, ResultSet res) {

        try {
            if (res != null) {
                res.close();
            }


            if (sta != null) {
                sta.close();
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
