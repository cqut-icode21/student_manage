package zhz.dao;

public interface BaseDao {
    int allTr();

    void add(Class<?> clazz, Integer id);

    void delete(Class<?> clazz, Integer id);

    void update(Class<?> clazz, Integer id);
}
