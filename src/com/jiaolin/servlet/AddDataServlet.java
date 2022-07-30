package com.jiaolin.servlet;

import com.jiaolin.dao.imp.BaseDaoImp;
import com.jiaolin.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addReq")
public class AddDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        ArrayList<String> aStudent = new ArrayList<>();

        aStudent.add(req.getParameter("id"));
        aStudent.add(req.getParameter("name"));
        aStudent.add(req.getParameter("sex"));
        aStudent.add(req.getParameter("college"));
        aStudent.add(req.getParameter("professional"));
        aStudent.add(req.getParameter("grade"));
        aStudent.add(req.getParameter("clazz"));
        aStudent.add(req.getParameter("age"));
        BaseDaoImp add = new BaseDaoImp();
        add.add(Student.class, aStudent);


    }

}
