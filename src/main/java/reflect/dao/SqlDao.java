package reflect.dao;

/**
 * SQL语句拼接方法的接口
 */
public interface SqlDao {

    /**
     * 获取学生数据
     *
     * @param page 类
     * @return 获取当前页学生数据的SQL语句字符串
     */
    String getDataOfStudent(int page);

    /**
     * 新增
     *
     * @param clazz 类
     * @return 新增的SQL语句
     */
    String addSql(Class<?> clazz);

    /**
     * 删除
     *
     * @param clazz 类
     * @return 删除的SQL语句字符串
     */
    String deleteSql(Class<?> clazz, int len);

    /**
     * 修改
     *
     * @param clazz 类
     * @return 修改的SQL语句
     */
    String updateSql(Class<?> clazz);

    /**
     * 查询行数
     *
     * @return 查询行数的SQL语句字符串
     */
    String findAllSql();

    /**
     * 模糊搜索的SQL语句
     *
     * @return 搜索的SQL语句字符串
     */
    String search(Class<?> clazz, String str);
}
