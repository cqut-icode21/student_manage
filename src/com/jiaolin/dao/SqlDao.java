package com.jiaolin.dao;

/**
 * 获取sql语句接口
 *
 * @author BaoXiangjie
 */
public interface SqlDao {

    /**
     * 查询所有sql语句
     *
     * @param clazz 类对象
     * @return 所有sql语句字符串
     */
    String findAllSql(Class<?> clazz);

    /**
     * 增加
     *
     * @param clazz 类对象
     * @return 增加sql语句
     */
    String addSql(Class<?> clazz);

    /**
     * 删除
     *
     * @param clazz 类对象
     * @return 删除sql语句
     */
    String deleteSql(Class<?> clazz, int length);

    /**
     * 查找
     * 通过id查找数据的sql语句
     *
     * @param clazz 类对象
     * @return 查找sql语句
     */
    String findByIdSql(Class<?> clazz);

    /**
     * 修改
     *
     * @param clazz 类对象
     * @return 修改字符串
     */
    String updateSql(Class<?> clazz);

}

