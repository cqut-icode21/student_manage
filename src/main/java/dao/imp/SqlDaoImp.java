package dao.imp;


import annotation.Column;
import annotation.ID;
import annotation.Table;
import message.Students;

import java.lang.reflect.Field;

public class SqlDaoImp {
    public static void main(String[] args) {
        SqlDaoImp sqlDaoImp = new SqlDaoImp();
        Class<?> clazz = Students.class;
        sqlDaoImp.add(clazz);
    }

    public String getAllTr() {
        return "select count(*) from students";
    }

    public String getDataOfStudent(Class<?> clazz, int currentPage) {

//        StringBuilder sql = new StringBuilder();
////        sql.append("select * from ");
////
////        Class<?> clazz = Student.class;
////        Table table = clazz.getAnnotation(Table.class);
////        String tableName = table.tableName();
////        sql.append(tableName).append(" limit ?,?");
////        return sql.toString();

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

        sql.append(clazz.getSimpleName());
        System.out.println(sql);

        return sql.toString();
    }

    public String add(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();

        sql.append("insert into ");
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        sql.append(tableName).append("(");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Column col = field.getAnnotation(Column.class);
            String columnName = col.columnName();
            sql.append(clazz.getSimpleName()).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);

        sql.append(") VALUES (?,?,?,?,?,?,?,?)");
        System.out.println(sql);
        return sql.toString();
    }

    public String delete(Class<?> clazz, int length) {
        StringBuilder sql = new StringBuilder();

        sql.append("delete from ");
//        Table table = clazz.getAnnotation(Table.class);
//        String tableName = table.tableName();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ID id = field.getAnnotation(ID.class);
            if (id != null) {
                String idName = id.idName();
                sql.append(clazz.getSimpleName()).append(" where ").append(idName).append(" in (");
            }
        }

        for (int i = 0; i < length; i++) {
            sql.append("?").append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        System.out.println(sql);
        return sql.toString();
    }

    public String update(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("update ");

//        Table table = clazz.getAnnotation(Table.class);
//        String tableName = table.tableName();
        sql.append(clazz.getSimpleName()).append(" set ");

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
            ID id = field.getAnnotation(ID.class);
            if (id != null) {
                String idName = id.idName();
                sql.append(" where ").append(idName).append(" = ?");
                break;
            }
        }
        System.out.println(sql);
        return sql.toString();
    }


    public String search(Class<?> clazz, String tr) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");
        //获得表名

        sql.append(clazz.getSimpleName()).append(" where ");

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
