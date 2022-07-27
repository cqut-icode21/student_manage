package TestJava.servlet;
import com.alibaba.fastjson2.JSON;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/load")
public class LoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             resp.setContentType("text/html; charset=UTF-8");
             resp.setCharacterEncoding("utf-8");
             String user=req.getParameter("user");
             String password=req.getParameter("password");
             if(user.equals("123")&&password.equals("123456") ){
                 String url = JSON.toJSONString("html/arrangePeople.html");
                 resp.getWriter().write(url);
             }else {
                 resp.getWriter().write("1");
             }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
