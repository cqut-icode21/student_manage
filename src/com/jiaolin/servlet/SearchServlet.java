package com.jiaolin.servlet;

import com.alibaba.fastjson.JSONObject;
import com.jiaolin.dao.imp.BaseDaoImp;
import com.jiaolin.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        String str = req.getParameter("data");
        BaseDaoImp search = new BaseDaoImp();
        List<Object> students = search.findById(Student.class, str);
        int length = students.size();
        JSONObject json = new JSONObject();
        json.put("students", students);
        json.put("length", length);
        resp.getWriter().write(json.toString());
    }
}
