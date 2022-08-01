package ljq.dao.impl;

import ljq.entity.Students;

import java.util.ArrayList;
import java.util.List;

/*增删改查方法接口*/
public interface BaseDao {

    /*找到页面数据*/
    int allTr();

    /*通过id查询对象*/
    Object findById(Class<?> clazz, Object id);

    /*删除指定对象*/
    void delete(Class<?> clazz, String[] id);

    /*新增对象*/
     void add(Class<?> clazz, ArrayList<String> student);

    /*修改指定对象*/
    void update(Class<?> clazz, ArrayList<String> student);

     ArrayList<Students> search(Class<?> clazz, String str);
}
