package com.Simon.servlet;

import com.Simon.service.UpdateService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    UpdateService updateService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String[] values = req.getParameter("values").split(",");
        updateService = new UpdateService(values);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", updateService.update());
        resp.getWriter().write(jsonObj.toString());
    }
}
