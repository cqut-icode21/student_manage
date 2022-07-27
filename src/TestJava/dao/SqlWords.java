package TestJava.dao;

import TestJava.com.Col;
import TestJava.com.Table;
import TestJava.com.id;
import java.lang.reflect.Field;

public class SqlWords {
    public String findAllSql(Class<?> clazz){
        //存储sql语句
       StringBuilder sqlBuilder=new StringBuilder();
       sqlBuilder.append("select ");
       //获取Teacher类中id,age,name属性
       Field[] fields= clazz.getDeclaredFields();
       for(Field i :fields){
           Col colAnnotation=i.getAnnotation(Col.class);
           String colName= colAnnotation.colName();
           sqlBuilder.append(colName).append(",");
       }
       sqlBuilder.deleteCharAt(sqlBuilder.length()-1);
       //拼凑Select xxx from 语句
       sqlBuilder.append( " from ");
       //读表名
        Table tableName=(Table)clazz.getAnnotation(Table.class);
        String tableName1=tableName.tableName();
        sqlBuilder.append(tableName1);
        System.out.println("findAllSql"+sqlBuilder);
        return sqlBuilder.toString();
    }
    public  String findSqlById(Class<?> clazz){
        id idAnnotation=null;
         //存储sql语句
        StringBuilder sqlBuilder=new StringBuilder();
        sqlBuilder.append("select ");
        //获取Teacher类中id,age,name属性
        Field[] fields= clazz.getDeclaredFields();
        for(Field i :fields){
            Col colAnnotation=i.getAnnotation(Col.class);
            String colName= colAnnotation.colName();
            sqlBuilder.append(colName).append(",");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length()-1);
        //拼凑Select xxx from 语句
        sqlBuilder.append(" from ");
        //读表名
        Table tableName=(Table)clazz.getAnnotation(Table.class);
        String tableName1=tableName.tableName();
        sqlBuilder.append(tableName1);
        sqlBuilder.append(" where ");
        for(Field i: fields){
            idAnnotation=i.getAnnotation(id.class);
            if(idAnnotation!=null) {
                String idName = idAnnotation.sid();
                sqlBuilder.append(idName).append(" = ?");
                break;
            }
        }
       System.out.println("findById: " +sqlBuilder);
        return sqlBuilder.toString();
    }
    /**
     * 删除
     *
     * @param clazz 类
     * @return 删除的SQL语句字符串
     */
    public String deleteSql(Class<?> clazz) {
        id idAnnotation=null;
        //存储sql语句
        StringBuilder sqlBuilder=new StringBuilder();
        sqlBuilder.append("delete from ");
        //获取Teacher类中id,age,name属性
        Field[] fields= clazz.getDeclaredFields();
        Table tableName=(Table)clazz.getAnnotation(Table.class);
        String tableName1=tableName.tableName();
        sqlBuilder.append(tableName1);
        sqlBuilder.append(" where ");
        for(Field i: fields){
            idAnnotation=i.getAnnotation(id.class);
            if(idAnnotation!=null) {
                String idName = idAnnotation.sid();
                sqlBuilder.append(idName).append(" = ?");
                break;
            }
        }
        System.out.println("deleteSql:" +sqlBuilder);
        return sqlBuilder.toString();
    }

    /**
     * 新增
     *
     * @param clazz 类
     * @return 新增的SQL语句
     */
    public String addSql(Class<?> clazz) {
        StringBuilder sqlBuilder=new StringBuilder();
        sqlBuilder.append("insert into ");
        Field[] fields=clazz.getDeclaredFields();
        Table table=clazz.getAnnotation(Table.class);
        String tableName= table.tableName();
        sqlBuilder.append(tableName).append("(");
        for(Field field: fields){
            Col column=field.getAnnotation(Col.class);
            String colname=column.colName();
            sqlBuilder.append(colname).append(",");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length()-1);

        sqlBuilder.append(")").append(" values ").append("(");
        for(int i=0;i<fields.length;i++){
            sqlBuilder.append("?").append(",");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length()-1);
        sqlBuilder.append(")");
        System.out.println("addSql:"+sqlBuilder.toString());
            return sqlBuilder.toString();
    }

    /**
     * 修改
     *
     * @param clazz 类
     * @return 修改的SQL语句
     */
    public String updateSql(Class<?> clazz) {
        id idAnnotation = null;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("update ");
        Field[] fields = clazz.getDeclaredFields();
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.tableName();
        sqlBuilder.append(tableName).append(" set ");
        for (Field field : fields) {
            Col column = field.getAnnotation(Col.class);
            String columnName = column.colName();
            sqlBuilder.append(columnName).append("=?").append(",");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" where ");
        for (Field i : fields) {
            idAnnotation = i.getAnnotation(id.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.sid();
                sqlBuilder.append(idName);
                break;
            }
        }
            sqlBuilder.append(" = ?");
        System.out.println("updateSql:"+sqlBuilder);
            return sqlBuilder.toString();
    }
    //创表语句
    public String createTable(Class<?> clazz){
        StringBuilder sqlBuilder=new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();
        sqlBuilder.append(" CREATE TABLE IF NOT EXISTS ");
        Table table=clazz.getAnnotation(Table.class);
        String tableName=table.tableName();
        sqlBuilder.append(tableName).append(" (");
        for(int i=0;i<fields.length;i++) {
            Col column = fields[i].getAnnotation(Col.class);
            String columnName = column.colName();
            if(i==0) {
                sqlBuilder.append(columnName).append(" INTEGER PRIMARY KEY  NOT NULL").append(",");
            }else {
                sqlBuilder.append(columnName).append(" VARCHAR(10) NOT NULL").append(",");
            }
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length()-1);
        sqlBuilder.append(")").append("ENGINE = InnoDB");
        System.out.println("Create table:"+sqlBuilder);
        return sqlBuilder.toString();
    }
}
