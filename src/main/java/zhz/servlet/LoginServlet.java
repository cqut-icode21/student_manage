package zhz.servlet;


import zhz.pojo.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 龙
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        boolean p = user.getUserName().equals(username) && user.getPassword().equals(password);
        try {
            resp.getWriter().write(String.valueOf(p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

