package main.process;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet3", value = "/LoginServlet3")
public class LoginServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编码
        request.setCharacterEncoding("utf-8");
        // 获取参数
        String imageText = request.getParameter("image");
        // 获取图片的验证码
        String text = (String) request.getSession().getAttribute("text");

        if (!text.equalsIgnoreCase(imageText)) {// 如果输入参数和验证码不相同
            //设置参数到会话中
            request.setAttribute("imageMess", "验证码输入错误!");
            //重定位到login3
            request.getRequestDispatcher("/view/activate.jsp").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}