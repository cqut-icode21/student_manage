package reflect.dao;

import reflect.entities.Student;

import java.util.ArrayList;

/**
 * 数据库增删改查方法的接口
 */
public interface BaseDao {
    /**
     * 根据当前页数获取学生数据
     *
     * @param page 当前页
     * @return list
     */
    ArrayList<Student> getData(int page, int tem);

    /**
     * 新增
     *
     * @param arrayList 新增的对象
     * @return list
     */
    boolean add(Class<?> clazz, ArrayList<String> arrayList);

    /**
     * 删除
     *
     * @param clazz   类
     * @param arrayId 需要删除的对象的array id
     */
    void delete(Class<?> clazz, String[] arrayId);

    /**
     * 修改
     *
     * @param clazz 对象
     * @param list  需要修改的对象的list
     */
    void update(Class<?> clazz, ArrayList<String> list);

    /**
     * 查询所有行数
     *
     * @return 返回所有结果
     */
    int findAllTr();

    /**
     * 模糊查询
     *
     * @param clazz 类
     * @return 返回所有结果
     */
    ArrayList<Student> search(Class<?> clazz, String tr);
}