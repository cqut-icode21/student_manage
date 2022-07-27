package service;

import dao.*;
import dao.sql.SqlDao;
import dao.sql.SqlDaoImpl;
import entities.Student;

import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {
    @Override
    public ArrayList<Student> getAllStudent() {


        FileDao fd = new FileDaoImpl();
        return fd.getAllStudent();

    }

    @Override
    public void del(String v) {
        DeleteDao deleteDao = new DeleteDaoImpl();
        deleteDao.del(v);
    }

    @Override
    public void add(Object student) {
        AddDao addDao = new AddDaoImpl();
        addDao.add(student);
    }

    @Override
    public void update(Object student, Object id) {
        UpDate upDate = new UpDateImpl();
        upDate.update(student, id);
    }

}
