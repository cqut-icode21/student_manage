package ljq.dao.impl;

/*增删改查sql语句接口*/
public interface SqlDao {

//    String findDataSql(Class<?> clazz, int currentPage, int pageIndex);
//String findAllIdSql(Class<?> clazz);

    /*根据id查找*/
    String findByIdSql(Class<?> clazz);

    /*删除对象*/
//    String deleteSql(Class<?> clazz);

    String deleteSql(Class<?> clazz, int length);

    /*新增对象*/
    String addSql(Class<?> clazz);

    /*修改对象*/
    String updateSql(Class<?> clazz);

//    String searchSql(Class<?> clazz,String str);
}
