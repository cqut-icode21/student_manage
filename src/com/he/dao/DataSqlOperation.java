package com.he.dao;

/**
 * @author 13253
 * @date 2021/7/29 19:18
 * @className DataSqlOperation
 */
public interface DataSqlOperation<c> {
      String findDataSql(c clazz, int currentPage, int pageIndex);
      String findAllPage(c clazz);
      String addStudent(c clazz);
      String findAllId(c clazz);
      String deleteStudent(c clazz);
      String findStudent(c clazz);
      String upDateStudent(c clazz);
}
