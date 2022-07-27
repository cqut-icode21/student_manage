package dao.sql;

import annotation.Column;
import annotation.Id;
import annotation.Table;

import java.lang.reflect.Field;

public class SqlDaoImpl implements SqlDao{

    public static String updateSql(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("update ");
        sql.append("student").append(" set ");

        sql.append("id").append("=?").append(",");
        sql.append("name").append("=?").append(",");
        sql.append("gender").append("=?").append(",");
        sql.append("college").append("=?").append(",");
        sql.append("major").append("=?").append(",");
        sql.append("grade").append("=?").append(",");
        sql.append("class").append("=?").append(",");
        sql.append("age").append("=?");

        sql.append(" WHERE ");
        sql.append("id").append("=?");
        return sql.toString();
    }

    public static String  addSql(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert ");
        sql.append(" into ");
        sql.append(clazz.getSimpleName());
        sql.append("(");

        Field[] fields = clazz.getDeclaredFields();
        // 遍历属性，获取类中注解的字段、表名
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            // 将字段拼接到SQL语句
            sql.append(columnName).append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        sql.append("values");
        sql.append("(");

        for (int i=0;i< fields.length;i++){
            sql.append("?" + ",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();

    }

}

