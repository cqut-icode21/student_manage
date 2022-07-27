package reflect.impl;

import reflect.annotation.Column;
import reflect.annotation.Id;
import reflect.annotation.Table;
import reflect.dao.SqlDao;
import reflect.entities.Student;

import java.lang.reflect.Field;

/**
 * 动态拼接SQL语句的实现
 */
public class SqlDaoImpl implements SqlDao {
    /**
     * 获取学生数据
     *
     * @param currentPage 类
     * @return 指定学生数据的SQL语句
     */
    @Override
    public String getDataOfStudent(int currentPage) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");
        Class<?> clazz = Student.class;
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        sql.append(tableName).append(" limit ?,?");
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
        sql.append("insert into ");
        // 获取类中注解的表名
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName);
        sql.append(" (");

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
        sql.append(") values(");
        // 获取各个字段
        for (Field ignored : fields) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        System.out.println("addSql:" + sql);
        return sql.toString();
    }

    /**
     * 删除
     *
     * @param clazz 类
     * @return 删除的SQL语句字符串
     */
    @Override
    public String deleteSql(Class<?> clazz, int length) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ");
        // 获取类中注解的表名
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                String idName = id.idName();
                sql.append(tableName).append(" where ").append(idName).append(" in (");//根据id删除对应行
            }
        }
        for (int i = 0; i < length; i++) {
            sql.append("?").append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
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
        StringBuilder sql = new StringBuilder();
        sql.append("update ");
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        sql.append(tableName).append(" set ");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Column col = field.getAnnotation(Column.class);
            if (!col.columnName().equals("id")) {
                String colName = col.columnName();
                sql.append(colName).append(" = ?,");
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                String idName = id.idName();
                sql.append(" where ").append(idName).append(" = ?");
                break;
            }
        }
        return sql.toString();
    }

    /**
     * 查找行数的SQL语句
     *
     * @return 查找行数的SQL语句字符串
     */
    @Override
    public String findAllSql() {
        return "select count(*) from students";
    }

    /**
     * 模糊搜索
     *
     * @param clazz 类
     * @return 搜索的SQL语句
     */
    public String search(Class<?> clazz, String tr) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");
        //获得表名
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        sql.append(tableName).append(" where ");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Column col = field.getAnnotation(Column.class);
            String columnName = col.columnName();
            sql.append(columnName).append(" like '%").append(tr).append("%'").append(" or ");
        }
        sql.deleteCharAt(sql.length() - 1);//删除"or"
        sql.deleteCharAt(sql.length() - 1);
        sql.deleteCharAt(sql.length() - 1);
        return sql.toString();
    }

}
