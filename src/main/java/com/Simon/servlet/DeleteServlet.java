package com.Simon.servlet;

import com.Simon.service.DeleteService;
import com.Simon.service.FindAllService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private DeleteService deleteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String[] ids = req.getParameter("ids").split(",");
        deleteService = new DeleteService(ids);
        deleteService.delete();
        JSONObject jsonObj = new JSONObject();
        FindAllService findService = new FindAllService();
        int totalAmount = findService.getCount();
        jsonObj.put("totalAmount", totalAmount);
        resp.getWriter().write(jsonObj.toString());
    }

}
