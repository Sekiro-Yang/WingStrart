package main.controller.Activate;

import main.dao.founderDao;
import main.entity.Founder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ActivateEmailcodeServlet")
public class ActivateEmailcodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        功能：判断邮箱验证码输入是否正确
         */
        //设置必须
        founderDao founderDao = new founderDao();
        PrintWriter writer = resp.getWriter();
        //获取传入参数信息
        String input_code = req.getParameter("code");
        System.out.println(input_code);
        String founderId = req.getParameter("founderId");
        System.out.println(founderId);
        String change_founderName = req.getParameter("change_founderName");
        System.out.println(change_founderName);
        String change_password = req.getParameter("change_password");
        System.out.println(change_password);
        String change_sex = req.getParameter("change_sex");
        System.out.println(change_sex);
        int change_age = Integer.parseInt(req.getParameter("change_age"));
        System.out.println(change_age);
        Founder founder = founderDao.getFounderByFId(founderId);
        //获取数据库信息
        String sql_code = founderDao.getCode(founderId);
        //判断传入参数信息是否与数据库信息相同
        if (!input_code.equals(sql_code)) {
            //设置参数到会话中
            req.setAttribute("emailCodeMess", "邮箱验证码输入错误");
            req.setAttribute("founder",founder);
            req.setAttribute("change_founderName",change_founderName);
            req.setAttribute("change_password",change_password);
            req.setAttribute("change_sex",change_sex);
            req.setAttribute("change_age",change_age);
            //重定位到activate
            req.getRequestDispatcher("/view/activate_emailcode.jsp").forward(req, resp);
        }else{//结果成功 — — 跳转到home界面以及修改founder的信息
            founderDao.changeFounder(founderId,change_founderName,change_password,change_sex, change_age);
            writer.print("<script language='javascript'>alert('修改成功，点击确定跳转到登录界面');window.location.href='view/login_founder.jsp';</script>");
        }
    }
}
