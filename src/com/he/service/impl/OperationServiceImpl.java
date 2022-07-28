package com.he.service.impl;

import com.he.dao.DataOperation;
import com.he.dao.impl.DbReflectUtil;
import com.he.dao.impl.OperationImpl;
import com.he.entity.Student;
import com.he.entity.User;
import com.he.service.DataService;
import com.he.service.LoginService;

import java.util.List;

/**
 * @author 13253
 * @date 2021/7/29 19:45
 * @className OperationServiceImpl
 */
public class OperationServiceImpl implements LoginService, DataService<Student> {

    private static final Integer PAGE_INDEX = 10;
    private final DbReflectUtil<Student> DATA_OPERATION = new DbReflectUtil<Student>();

    @Override
    public boolean loginSuccess(String username, String password) {
        return new OperationImpl<>().isSuccess(username, password);
    }

    @Override
    public List<Student> findData(int currentPage) {
        return DATA_OPERATION.findData(new Student(), currentPage, PAGE_INDEX);
    }

    @Override
    public int findAllPage() {
        return DATA_OPERATION.findAllPage(new Student());
    }

    @Override
    public boolean addStudent(Student student) {
        return DATA_OPERATION.addStudent(student);
    }

    @Override
    public List<Student> findAllId() {
        return DATA_OPERATION.findAllId(new Student());
    }

    @Override
    public boolean deleteStudent(Student clazz, Object id) {
        return DATA_OPERATION.deleteStudent(clazz,id);
    }

    @Override
    public Student findStudent(Student clazz, Object id) {
        return (Student) DATA_OPERATION.findById(clazz,id);
    }

    @Override
    public boolean setStudent(Student clazz, Object id) {
        return DATA_OPERATION.update(clazz,id);
    }
}
