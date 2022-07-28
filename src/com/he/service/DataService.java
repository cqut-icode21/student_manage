package com.he.service;

import java.util.List;

/**
 * @author 13253
 * @date 2021/7/29 19:45
 * @className DataService
 */
public interface DataService<c> {
    List<c> findData(int currentPage);
    int findAllPage();
    boolean addStudent(c clazz);
    List<c> findAllId();
    boolean deleteStudent(c clazz,Object id);
    c findStudent(c clazz,Object id);
    boolean setStudent(c clazz,Object id);
}