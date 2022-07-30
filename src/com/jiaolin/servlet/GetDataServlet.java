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


@WebServlet("/getData")
public class GetDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        JSONObject json = new JSONObject();
        BaseDaoImp getData = new BaseDaoImp();
        List<Object> data = getData.findAll(Student.class);
        System.out.println(data);
        json.put("data", data);

        resp.getWriter().write(json.toString());

    }
}
