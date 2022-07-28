package com.Simon.servlet;


import com.Simon.service.FindOneService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findOne")
public class FindOneServlet extends HttpServlet {
    private FindOneService findService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String id = req.getParameter("id");
        findService = new FindOneService(id);
        try {
            List values = findService.find();
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("values", values);
            resp.getWriter().write(jsonObj.toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
