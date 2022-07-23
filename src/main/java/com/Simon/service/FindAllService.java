package com.Simon.service;

import com.Simon.dao.impl.DatabaseReflect;
import com.Simon.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class FindAllService {
    private ArrayList<Student> students ;
    private int page;
    private int amountPerPage;
    private int amountNowPage;

    public FindAllService (int page,int amountPerPage){
        this.page = page;
        this.amountPerPage = amountPerPage;
    }

    public List<Student> findAll(){
        DatabaseReflect databaseReflect = new DatabaseReflect();
        return databaseReflect.findAll(Student.class);
    }




}
