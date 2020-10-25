package main.controller.homepage;

import main.dao.founderDao;
import main.entity.Founder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/change")
public class ChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传入信息
        String founderId = req.getParameter("founderId");
        //建立必须
        founderDao founderDao = new founderDao();
        //通过founderId得到founder
        Founder founder = founderDao.getFounderByFId(founderId);
        //将信息加入到会话中
        req.setAttribute("founder",founder);
        //重定位
        req.getRequestDispatcher("view/change.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传入信息
        String founderId = req.getParameter("founderId");
        //设置必须
        PrintWriter writer = resp.getWriter();
        founderDao founderDao = new founderDao();
        /*
        判断用户输入的个人信息是否有误
         */
        //获取用户输入的信息
        //得到先前的founderName和改变后的founderName
        String change_founderName = req.getParameter("change_founderName");
        //得到先前的password和改变后的password
        String change_password1 = req.getParameter("change_password1");
        String change_password2 = req.getParameter("change_password2");
        //获得先前的、改变后的sex和age
        String change_sex = req.getParameter("change_sex");
        String change_age = req.getParameter("change_age");
        //设置判断数字
        int judge = founderDao.judgeFounder(change_founderName,change_password1,change_password2,change_sex,change_age);
        if (judge==1){//填写信息有空缺
            writer.print("<script language='javascript'>alert('填写信息有空缺，请重写输入');window.history.go(-1);</script>");
        }else if (judge==2){//两次密码输入不一致
            writer.print("<script language='javascript'>alert('两次密码输入不一致，请重新输入');window.history.go(-1);</script>");
        }else{//个人信息没有问题
            //设置参数到会话中
            req.setAttribute("founderId",founderId);
            req.setAttribute("change_founderName",change_founderName);
            req.setAttribute("change_password",change_password1);
            req.setAttribute("change_sex",change_sex);
            req.setAttribute("change_age",change_age);
            //重定向
            req.getRequestDispatcher("view/activate_code.jsp").forward(req, resp);
        }
    }
}
