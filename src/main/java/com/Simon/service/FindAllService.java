package com.Simon.service;

import com.Simon.dao.SqlDao;
import com.Simon.dao.impl.DatabaseReflect;
import com.Simon.dao.impl.SqlDaoImpl;
import com.Simon.entities.Student;
import com.Simon.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindAllService {
    private ArrayList<Student> students;
    private int page;
    private int amountPerPage;
    private int amountNowPage;

    public FindAllService(int page, int amountPerPage) {
        this.page = page;
        this.amountPerPage = amountPerPage;
    }

    public FindAllService() {

    }

    public List<Student> findAll() {


        DatabaseReflect databaseReflect = new DatabaseReflect();
        amountNowPage = getAmountNowPage();
        return databaseReflect.findAll(Student.class, (page - 1) * amountPerPage, amountNowPage);
    }

    public int getAmountNowPage() {
        int count = getCount();
        double pageAmount = Math.ceil((count / (double) amountPerPage));
        if (page != pageAmount || count % amountPerPage == 0)
            return amountPerPage;
        else
            return count % amountPerPage;

    }


    public int getCount() {
        int count = 0;

        //1、链接到数据库
        Connection connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            SqlDao sqlDao = new SqlDaoImpl();
            String sql = sqlDao.findAllSql(Student.class, 0, 0);
            // 3、预编译SQL语句
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // 4、填充参数（可选）

            // 5、执行SQL语句
            System.out.println("findAll:" + statement);
            ResultSet resultSet = statement.executeQuery();
            resultSet.last();
            count = resultSet.getRow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public List<Student> findSearch(String text) {
        DatabaseReflect databaseReflect = new DatabaseReflect();
        amountNowPage = getAmountNowPage();
        return databaseReflect.search(Student.class, text, (page - 1) * amountPerPage, amountNowPage);
    }

}