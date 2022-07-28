package com.he.servlet;

import com.alibaba.fastjson.JSONObject;
import com.he.entity.Student;
import com.he.service.DataService;
import com.he.service.impl.OperationServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Message")
public class Message extends HttpServlet{
    private final DataService<Student> DATA_SERVICE = new OperationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action=req.getParameter("action");
        if (action.equals("getAllPage"))
            getAllPage(resp);
        if (action.equals("findAllId"))
            getAllId(resp);
    }
    protected void getAllPage(HttpServletResponse resp) throws IOException{
        int allPage=DATA_SERVICE.findAllPage();
        resp.getWriter().write(String.valueOf(allPage));
    }
    protected void getAllId(HttpServletResponse resp) throws IOException{
        List<Student> list=DATA_SERVICE.findAllId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", list);
        resp.getWriter().write(jsonObject.toString());
    }
}
