package com.Simon.servlet;

import com.Simon.entities.Student;
import com.Simon.service.FindAllService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findAll")
public class FindAllServlet extends HttpServlet {
    private FindAllService findService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        int page = Integer.parseInt(req.getParameter("page"));
        int amountPerPage = Integer.parseInt(req.getParameter("amountPerPage"));
        String text = req.getParameter("text");
        findService = new FindAllService(page, amountPerPage);
        int count = findService.getCount();
        int totalPage = (int) Math.ceil((count / (double) amountPerPage));
        int totalAmount = count;
        JSONObject jsonObj = new JSONObject();
        List<Student> list;
        if (text.equals(""))
            list = findService.findAll();
        else
            list = findService.findSearch(text);
        jsonObj.put("data", list);
        jsonObj.put("totalPage", totalPage);
        jsonObj.put("totalAmount", totalAmount);
        resp.getWriter().write(jsonObj.toString());
    }
}
