package com.he.dao;

/**
 * @author 13253
 * @date 2021/7/29 19:18
 * @className DataSqlOperation
 */
public interface DataSqlOperation<c> {
      String findDataSql(c clazz, int currentPage, int pageIndex);
}
