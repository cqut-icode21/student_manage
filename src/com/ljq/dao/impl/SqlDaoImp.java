package ljq.dao.impl;

import ljq.annotation.Column;
import ljq.annotation.Id;
import ljq.annotation.Table;
import ljq.entity.Students;

import java.lang.reflect.Field;

/*拼接sql语句*/
public class SqlDaoImp implements SqlDao {
    public String getAllTr() {
        return "select count(*) from students";
    }
    public String getDataOfStudent(int currentPage) {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");

        Class<?> clazz = Students.class;
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        sql.append(tableName).append(" limit ?,?");
        return sql.toString();
    }

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
        System.out.println("findByIdSql:" + sql.toString());
        return sql.toString();
    }

    @Override
    public String deleteSql(Class<?> clazz,int length) {
//        Id idAnnotation = null;
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ");
        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
//        sql.append(tableName).append(" where ");
        // 获取字段id
//        for (Field field : fields) {
//            idAnnotation = field.getAnnotation(Id.class);
//            if (idAnnotation != null) {
//                String idName = idAnnotation.idName();
//                sql.append(idName).append(" = ?");
//                break;
//            }
//        }
//        System.out.println("deleteSql:" + sql.toString());
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                String idName = id.idName();
                sql.append(tableName).append(" where ").append(idName).append(" in (");
            }
        }
        for (int i = 0; i < length; i++) {
            sql.append("?").append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

    @Override
    public String addSql(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ");
        // 获取类中注解的表名
        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
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
        sql.append(" ) values ( ");
        for (Field field : fields) {
            sql.append("?").append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" )");
        System.out.println("deleteSql:" + sql.toString());
        return sql.toString();
    }

    @Override
    public String updateSql(Class<?> clazz) {
        Id idAnnotation;
        StringBuilder sql = new StringBuilder();
        sql.append("update ");
        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append(" set ");
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            System.out.println(columnName);
            //禁止修改主键信息
            if (columnName.equals("id")) continue;
            // 将字段拼接到SQL语句
            sql.append(columnName).append(" = ?").append(",");
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
        System.out.println(sql);
        return sql.toString();
    }

    public String searchSql(Class<?> clazz,String str){
        StringBuilder sql=new StringBuilder();
        sql.append("select * from ");
        Field[] fields = clazz.getDeclaredFields();

        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        sql.append(tableName).append(" where ");
        for (Field field : fields) {
            Column col = field.getAnnotation(Column.class);
            String columnName = col.columnName();
            sql.append(columnName).append(" like '%").append(str).append("%'").append(" or ");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.deleteCharAt(sql.length() - 1);
        sql.deleteCharAt(sql.length() - 1);
        System.out.println("updateSql:" + sql.toString());
        return sql.toString();
    }
}
