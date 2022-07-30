package com.jiaolin.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * sql语句方法实现接口
 *
 * @author BaoXiangjie
 */
public interface BaseDao {

    /**
     * 查询所有内容
     *
     * @param clazz 类对象
     * @return 类的所有结果数组
     */
    <T> List<T> findAll(Class<?> clazz);

    /**
     * 新增
     *
     * @param clazz 类对象
     * @return 是否成功
     */
    boolean add(Class<?> clazz, ArrayList<String> aStudent);

    /**
     * 通过id删除
     *
     * @param clazz 类对象
     * @param id    需要删除的对象的id
     * @return 是否成功
     */
    boolean delete(Class<?> clazz, String[] id);

    /**
     * 通过id查询
     *
     * @param clazz 类对象
     * @param id    要查询的对象的id
     * @return 查询的对象的所有信息
     */
    <T> List<T> findById(Class<?> clazz, Object id);

    /**
     * 通过id修改
     *
     * @param clazz    对象
     * @param aStudent 需要修改的数据
     * @return 是否成功
     */
    boolean update(Class<?> clazz, ArrayList<String> aStudent);

}
