package reflect.servlet;

import com.alibaba.fastjson.JSONObject;
import reflect.entities.Student;
import reflect.impl.BaseDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/getData")
public class GetDataServlet extends HttpServlet {
    @Override
    //tomcat将http请求文本接受并解析，然后封装成HttpServletRequest类型的对象，输入数据可通过request对象调用对应方法得到
    //tomcat会将响应文本信息封装成HttpServletResponse类型的对象，通过设置response属性就可以控制要输出到浏览器的内容，然后tomcat就会将其变成响应文本发送给浏览器
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
        BaseDaoImpl baseDao = new BaseDaoImpl();

        String temp = req.getParameter("currentPage");
        int currenPage = Integer.parseInt(temp);

        int all = baseDao.findAllTr();
        ArrayList<Student> data = baseDao.getData(currenPage, all);

        json.put("allTr", all);//创建JSONObject对象使键“allTr”指向allTr
        json.put("data", data);//result,tbody
        //response.getWriter()返回的是PrintWriter，这是一个打印输出流
        resp.getWriter().write(json.toString());//>>>'{"allTr":allTr,"data":data}'

    }
}
