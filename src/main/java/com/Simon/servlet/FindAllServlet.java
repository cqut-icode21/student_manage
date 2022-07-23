package com.Simon.servlet;

import com.Simon.service.FindAllService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    @WebServlet("/findAll")
    public class FindAllServlet extends HttpServlet {
        private FindAllService findService ;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           /* int page = Integer.parseInt(req.getParameter("page"));
            int amountPerPage = Integer.parseInt(req.getParameter("amountPerPage"));*/
            findService = new FindAllService(1,1);
            findService.findAll();

        }
}
