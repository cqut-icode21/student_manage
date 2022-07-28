package com.Simon.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 潘琴
 * 增删改查方法接口
 */
public interface BaseDao {
    /**
     * 查询所有
     *
     * @param clazz 类
     * @return 类的所有结果数组
     */
    <T> List<T> findAll(Class<T> clazz, int former, int now);

    /**
     * 根据id查询
     *
     * @param clazz 类
     * @param id    要查询的对象的id
     * @return 查询的对象的所有信息
     */
    Object findById(Class<?> clazz, Object id);

    /**
     * 删除
     *
     * @param clazz 类
     * @param id    需要删除的对象的id
     * @return 是否成功
     */
    boolean delete(Class<?> clazz, Object id) throws SQLException, IllegalAccessException, InstantiationException;

    /**
     * 新增
     *
     * @param object 新增的对象
     * @return 是否成功
     */
    boolean add(Object object);

    /**
     * 修改
     *
     * @param object 对象
     * @param id     需要修改的对象的id
     * @return 是否成功
     */
    boolean update(Object object, Object id);

    <T> List<T> search(Class clazz, String text, int former, int now);

}