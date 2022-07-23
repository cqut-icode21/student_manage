package com.Simon.servlet;

import com.Simon.service.AddService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private AddService addService ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String college = req.getParameter("college");
        String professional = req.getParameter("professional");
        String grade = req.getParameter("grade");
        String CLASS = req.getParameter("CLASS");
        String age = req.getParameter("age");

        addService = new AddService(id,name,sex,college,professional,grade,CLASS,age);


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
