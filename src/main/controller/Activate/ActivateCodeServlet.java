package main.controller.Activate;

import main.dao.founderDao;
import main.entity.Founder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ActivateCodeServlet")
public class ActivateCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        功能：判断验证码输入是否正确
         */
        //设置必须
        founderDao founderDao = new founderDao();
        //获取founderId和改变值
        String founderId = req.getParameter("founderId");
        String change_founderName = req.getParameter("change_founderName");
        String change_password = req.getParameter("change_password");
        String change_sex = req.getParameter("change_sex");
        String change_age = req.getParameter("change_age");
        //通过founderId得到founder
        Founder founder = founderDao.getFounderByFId(founderId);
        // 编码
        req.setCharacterEncoding("utf-8");
        // 获取输入验证码
        String imageText = req.getParameter("image");
        // 获取图片的验证码
        String text = (String) req.getSession().getAttribute("text");
        //设置参数到会话中
        req.setAttribute("founderId",founderId);
        req.setAttribute("founder",founder);
        req.setAttribute("change_founderName",change_founderName);
        req.setAttribute("change_password",change_password);
        req.setAttribute("change_sex",change_sex);
        req.setAttribute("change_age",change_age);
        // 如果输入参数和验证码不相同
        if (!text.equalsIgnoreCase(imageText)) {
            //设置参数到会话中
            req.setAttribute("imageMess", "验证码输入错误!");
            //重定位
            req.getRequestDispatcher("/view/activate_code.jsp").forward(req, resp);
        }else{
            //重定位
            req.getRequestDispatcher("/view/activate_emailcode.jsp").forward(req, resp);
        }
    }
}
