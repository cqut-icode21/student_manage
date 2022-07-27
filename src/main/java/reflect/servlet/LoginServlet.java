package reflect.servlet;


import reflect.entities.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        boolean bool = user.getUserName().equals(username) && user.getPassword().equals(password);
        resp.getWriter().write(String.valueOf(bool));//返回true或false
    }


}

