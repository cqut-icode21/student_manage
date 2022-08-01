package ljq.servlet;

import ljq.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+"  "+password);
        User user = new User();
        boolean p = user.getUsername().equals(username) && user.getPassword().equals(password);
        System.out.println(p);
        resp.getWriter().write(String.valueOf(p));
    }
}
