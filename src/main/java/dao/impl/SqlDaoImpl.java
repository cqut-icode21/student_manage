package dao.impl;



import annotation.Column;
import annotation.Id;
import annotation.Table;
import dao.SqlDao;

import java.lang.reflect.Field;

/**
 * @author 潘琴
 * @date:2021/8/2
 * @description: 动态拼接SQL语句
 */
public class SqlDaoImpl implements SqlDao {
    /**
     * 查询所有的SQL语句
     *
     * @param clazz 类
     * @return 查询所有的SQL语句字符串:select sid,sname,sage from student
     */
    @Override
    public String findAllSql(Class<?> clazz) {
        // SQL语句
        StringBuilder sql = new StringBuilder();
        sql.append("select ");

        // 获取类的所有属性
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
        sql.append(" from ");

        // 获取类中注解的表名
        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName);
        System.out.println("查看所有信息的sql语句 findAllSql:" + sql.toString());
        return sql.toString();
    }

    /**
     * 根据id查询
     *
     * @param clazz 类
     * @return 根据id查询的SQL语句字符串:select sid,sname,sage from student where sid = ?
     */
    @Override
    public String findByIdSql(Class<?> clazz) {
        Id idAnnotation = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select ");

        // 获取类的所有属性
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
        sql.append(" from ");

        // 获取类中注解的表名
        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append(" where ");

        // 获取字段id
        for (Field field : fields) {
            idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.idName();
                sql.append(idName).append(" = ?");
                break;
            }
        }
        System.out.println("通过id查找的sql语句 findByIdSql:" + sql.toString());
        return sql.toString();
    }

    /**
     * 删除
     *
     * @param clazz 类
     * @return 删除的SQL语句字符串
     */
    @Override
    public String deleteSql(Class<?> clazz) {
        Id idAnnotation = null;

        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder sql = new StringBuilder();
        sql.append("delete from ");

        // 获取类中注解的表名
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();

        sql.append(tableName).append(" where ");

        // 获取字段id
        for (Field field : fields) {
            idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.idName();
                sql.append(idName);
                sql.append(" = ?");
                break;
            }
        }
        System.out.println("删除的sql语句 deleteSql:" + sql.toString());
        return sql.toString();
    }

    /**
     * 新增
     *
     * @param clazz 类
     * @return 新增的SQL语句
     */
    @Override
    public String addSql(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");

        // 获取类中注解的表名
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName);

        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        sql.append("( ");
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

        sql.append(" ) VALUES ( ");

        //遍历所有属性，占坑
        for (Field field : fields) {

            sql.append("?").append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" )");

        System.out.println("添加信息的sql语句 addSql:" + sql.toString());
        return sql.toString();
    }

    /**
     * 修改
     *
     * @param clazz 类
     * @return 修改的SQL语句
     */
    @Override
    public String updateSql(Class<?> clazz) {
        Id idAnnotation = null;
        StringBuilder sql = new StringBuilder();
        sql.append("update ");

        // 获取类中注解的表名
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append(" set ");

        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();

        // 遍历属性，获取类中注解的字段、表名
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();

            //id 不能更改
            if (field.getAnnotation(Id.class) != null)
                continue;

            // 将字段拼接到SQL语句
            sql.append(columnName).append(" = ? ,");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" where ");

        // 获取字段id
        for (Field field : fields) {
            idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.idName();
                sql.append(idName).append(" = ?");
                break;
            }
        }
        System.out.println("更新的sql语句 updateSql:" + sql.toString());
        return sql.toString();
    }
}
