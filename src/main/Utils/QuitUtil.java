package main.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/quit") //QuitServlet文件的虚拟路径 /quit
public class QuitUtil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("经过QuitServlet文件");
        req.getSession().setAttribute("founderName",null); //从会话中移除username
        resp.sendRedirect("home");//重定向到home
    }
}
