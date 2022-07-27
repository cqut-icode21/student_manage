package zhz.dao.imp;

import zhz.annotation.Column;
import zhz.annotation.ID;
import zhz.annotation.Table;

import java.lang.reflect.Field;

/**
 * @author 龙
 */
public class SqlDaoImp {
    public String getAllTr() {
        return "select count(*) from student";
    }

    public String add(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ");
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        sql.append(tableName).append(" ( ");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Column col = field.getAnnotation(Column.class);
            String columnName = col.columnName();
            sql.append(columnName).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);

        sql.append(" ) VALUES (?,?,?,?,?,?,?,?)");
        return sql.toString();
    }

    public String update(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("update ");

        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        sql.append(tableName).append(" set ");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Column col = field.getAnnotation(Column.class);
            if (!"id".equals(col.columnName())) {
                String colName = col.columnName();
                sql.append(colName).append(" = ?,");
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        for (Field field : fields) {
            ID id = field.getAnnotation(ID.class);
            if (id != null) {
                String idName = id.idName();
                sql.append(" where ").append(idName).append(" = ?");
                break;
            }
        }
        return sql.toString();
    }

    public String delete(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();

        sql.append("delete from ");
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ID id = field.getAnnotation(ID.class);
            if (id != null) {
                String idName = id.idName();
                sql.append(tableName).append(" where ").append(idName).append(" = ? ");
            }
        }

        sql.deleteCharAt(sql.length() - 1);
        return sql.toString();
    }

    public String getDataOfStudent(Class<?> clazz) {
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
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName() + " ;";
        sql.append(tableName);
        return sql.toString();
    }

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
        sql.deleteCharAt(sql.length() - 1);
        sql.deleteCharAt(sql.length() - 1);
        sql.deleteCharAt(sql.length() - 1);
        return sql.toString();
    }
}
