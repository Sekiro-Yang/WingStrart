package main.controller.login;

import main.Utils.DBUtil;
import main.dao.founderDao;
import main.entity.Founder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login_founder")
public class LogFounderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必需
        founderDao founderDao=new founderDao();
        //获取founderId
        String founderId=req.getParameter("founderId");
        //得到founder
        Founder founder=founderDao.getFounderByFId(founderId);
        //使用homeJudge(founderId)方法
        founderDao.homejudge(founderId);
        //在会话中设置属性
        req.setAttribute("founder",founder);
        //跳转到home
        req.getRequestDispatcher("/home").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必需
        PrintWriter writer = resp.getWriter();
        founderDao founderDao = new founderDao();
        //设置新的Cookie
        Cookie cookie = new Cookie("JSESSIONID",req.getSession().getId());
        cookie.setMaxAge(60*100);
        cookie.setPath("/");
        resp.addCookie(cookie);
        //获取用户输入的用户名和密码
        String input_founderId = req.getParameter("founderId");
        String input_password = req.getParameter("password");
        //检测用户输入的用户名和密码和数据库的用户名和密码一致
        int judgeNum = founderDao.loginJudge(input_founderId,input_password);
        if (judgeNum==1){//密码正确，登录成功
            //通过founderId得到founder
            Founder founder = founderDao.getFounderByFId(input_founderId);
            //设置属性到会话中
            req.setAttribute("founder",founder);
            req.getRequestDispatcher("/home").forward(req,resp);
        }else if(judgeNum==0){//密码错误
            writer.print("<script language='javascript'>alert('密码错误，请重新输入');window.history.go(-1);;</script>");
            return;
        }else if(judgeNum==-1){
            writer.print("<script language='javascript'>alert('账号空缺或错误，请重新输入');window.history.go(-1);</script>");
            return;
        }else if(judgeNum==-2){
            writer.print("<script language='javascript'>alert('密码空缺，请重新输入');window.history.go(-1);</script>");
            return;
        }else{}
    }
}
