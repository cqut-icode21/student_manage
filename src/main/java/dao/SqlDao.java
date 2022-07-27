package dao;

public interface SqlDao {
    String findById(Class<?> clazz);

    String findAll(Class<?> clazz);

    String add(Class<?> clazz);

    String delete(Class<?> clazz);

    String update(Class<?> clazz);


}
