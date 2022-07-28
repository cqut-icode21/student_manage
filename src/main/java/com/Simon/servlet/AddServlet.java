package com.Simon.servlet;

import com.Simon.service.AddService;
import com.Simon.service.FindAllService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private AddService addService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ExceptionMessage = "";
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String[] values = req.getParameter("values").split(",");
        addService = new AddService(values);
        FindAllService findService = new FindAllService();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", addService.add());

        int totalAmount = findService.getCount();
        jsonObj.put("totalAmount", totalAmount);
        jsonObj.put("ExceptionMessage", ExceptionMessage);
        resp.getWriter().write(jsonObj.toString());

    }
}
