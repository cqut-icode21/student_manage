package com.jiaolin.dao.imp;

import com.jiaolin.annotation.Column;
import com.jiaolin.annotation.ID;
import com.jiaolin.annotation.Table;
import com.jiaolin.dao.SqlDao;

import java.lang.reflect.Field;

/**
 * 获取sql语句方法实现
 *
 * @author BaoXiangjie
 */
public class SqlDaoImp implements SqlDao {

    /**
     * 获取表中全部信息sql语句实现
     *
     * @param clazz 类对象
     * @return 获取表中全部信息sql语句
     */
    @Override
    public String findAllSql(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName);
        return sql.toString();
    }

    /**
     * 获取添加信息sql语句实现
     *
     * @param clazz 类对象
     * @return 添加信息sql语句
     */
    @Override
    public String addSql(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append(" (");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            sql.append(columnName).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(") values (");
        for (Field ignored : fields) {
            sql.append("?");
        }
        sql.append(")");
        return sql.toString();
    }

    /**
     * 获取删除信息sql语句实现
     *
     * @param clazz 类对象
     * @return 删除信息sql语句
     */
    @Override
    public String deleteSql(Class<?> clazz, int length) {
        StringBuilder sql = new StringBuilder();

        sql.append("delete from ");
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ID id = field.getAnnotation(ID.class);
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

    /**
     * 获取通过id查找信息sql语句实现
     *
     * @param clazz 类对象
     * @return 通过id查找信息sql语句
     */
    @Override
    public String findByIdSql(Class<?> clazz) {
//        ID idAnnotation;
//        StringBuilder sql = new StringBuilder();
//        sql.append("select ");
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            Column fieldAnnotation = field.getAnnotation(Column.class);
//            String fieldName = fieldAnnotation.columnName();
//            sql.append(fieldName).append(",");
//        }
//        sql.deleteCharAt(sql.length() - 1).append(" from ");
//        Table tableAnnotation = clazz.getAnnotation(Table.class);
//        String tableName = tableAnnotation.tableName();
//        sql.append(tableName).append(" where ");
//        for (Field field : fields) {
//            idAnnotation = field.getAnnotation(ID.class);
//            if (idAnnotation != null) {
//                String idName = idAnnotation.idName();
//                sql.append(idName).append(" = ?");
//                break;
//            }
//        }
//        return sql.toString();
        return null;
    }

    /**
     * 获取修改信息sql语句实现
     *
     * @param clazz 类对象
     * @return 修改信息sql语句
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
            ID id = field.getAnnotation(ID.class);
            if (id != null) {
                String idName = id.idName();
                sql.append(" where ").append(idName).append(" = ?");
                break;
            }
        }
        return sql.toString();
    }
}

//public class SqlDaoImp {
//
//    public String getAllTr() {
//        return "select count(*) from students";
//    }
//
//    public String getDataOfStudent(int currentPage) {
//
//        StringBuilder sql = new StringBuilder();
//        sql.append("select * from ");
//
//        Class<?> clazz = Student.class;
//        Table table = clazz.getAnnotation(Table.class);
//        String tableName = table.tableName();
//        sql.append(tableName).append(" limit ?,?");
//        return sql.toString();
//    }
//
//    public String add(Class<?> clazz) {
//        StringBuilder sql = new StringBuilder();
//
//        sql.append("insert into ");
//        Table table = clazz.getAnnotation(Table.class);
//        String tableName = table.tableName();
//        sql.append(tableName).append("(");
//
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            Column col = field.getAnnotation(Column.class);
//            String columnName = col.columnName();
//            sql.append(columnName).append(",");
//        }
//        sql.deleteCharAt(sql.length() - 1);
//
//        sql.append(") VALUES (?,?,?,?,?,?,?,?)");
//        return sql.toString();
//    }
//
//    public String delete(Class<?> clazz, int length) {
//        StringBuilder sql = new StringBuilder();
//
//        sql.append("delete from ");
//        Table table = clazz.getAnnotation(Table.class);
//        String tableName = table.tableName();
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            ID id = field.getAnnotation(ID.class);
//            if (id != null) {
//                String idName = id.idName();
//                sql.append(tableName).append(" where ").append(idName).append(" in (");
//            }
//        }
//
//        for (int i = 0; i < length; i++) {
//            sql.append("?").append(",");
//        }
//        sql.deleteCharAt(sql.length() - 1);
//        sql.append(")");
//        return sql.toString();
//    }
//
//    public String update(Class<?> clazz) {
//        StringBuilder sql = new StringBuilder();
//        sql.append("update ");
//
//        Table table = clazz.getAnnotation(Table.class);
//        String tableName = table.tableName();
//        sql.append(tableName).append(" set ");
//
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            Column col = field.getAnnotation(Column.class);
//            if (!col.columnName().equals("id")) {
//                String colName = col.columnName();
//                sql.append(colName).append(" = ?,");
//            }
//        }
//        sql.deleteCharAt(sql.length() - 1);
//        for (Field field : fields) {
//            ID id = field.getAnnotation(ID.class);
//            if (id != null) {
//                String idName = id.idName();
//                sql.append(" where ").append(idName).append(" = ?");
//                break;
//            }
//        }
//        return sql.toString();
//    }
//
//
//    public String search(Class<?> clazz, String tr) {
//        StringBuilder sql = new StringBuilder();
//        sql.append("select * from ");
//        //获得表名
//        Table table = clazz.getAnnotation(Table.class);
//        String tableName = table.tableName();
//
//        sql.append(tableName).append(" where ");
//
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            Column col = field.getAnnotation(Column.class);
//            String columnName = col.columnName();
//            sql.append(columnName).append(" like '%").append(tr).append("%'").append(" or ");
//        }
//        sql.deleteCharAt(sql.length() - 1);
//        sql.deleteCharAt(sql.length() - 1);
//        sql.deleteCharAt(sql.length() - 1);
//        return sql.toString();
//    }
//
//
//}
