package main.controller.homepage;

import main.Utils.CodeGen;
import main.Utils.DBUtil;
import main.Utils.PerIdGen;
import main.dao.founderDao;
import main.entity.Founder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PerIdGen perIdGen = new PerIdGen();
        String new_founderId = null;
        //获得founderId
        try {
            new_founderId = perIdGen.GetPerId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("new_founderId",new_founderId);
        req.getRequestDispatcher("view/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必须
        PrintWriter writer = resp.getWriter();
        //获取信息
        String new_founderName = req.getParameter("new_founderName");
        String new_password1 = req.getParameter("new_password1");
        String new_password2 = req.getParameter("new_password2");
        String new_email = req.getParameter("new_email");
        String new_sex = req.getParameter("new_sex");
        String new_age = req.getParameter("new_age");
        String new_founderId = req.getParameter("new_founderId");
        if (new_founderName.length() == 0 || new_password1.length() == 0|| new_password2.length() == 0 || new_sex.length() == 0 || new_age.length() == 0){
            //如果填写的信息有空缺，则重写输入
            writer.print("<script language='javascript'>alert('填写信息有空缺，请重写输入');window.history.go(-1);</script>");
        }else if(!new_password1.equals(new_password2)){
            //如果填写密码前后不一致，重新输入
            writer.print("<script language='javascript'>alert('两次密码输入不一致，请重新输入');window.history.go(-1);</script>");
        }else{
            //信息无误
            try {
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO founder(founderName,password,email,sex,age,founderId,state,code,projectNum) VALUES (?,?,?,?,?,?,?,?,?)");
                stmt.setString(1,new_founderName);
                stmt.setString(2,new_password1);
                stmt.setString(3,new_email);
                stmt.setString(4,new_sex);
                stmt.setString(5,new_age);
                stmt.setString(6,new_founderId);
                stmt.setInt(7,0);
                stmt.setString(8,"");
                stmt.setInt(9,0);
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //设置参数到会话中
            req.setAttribute("new_founderId",new_founderId);
            //重定向到create_founderId
            req.getRequestDispatcher("view/show_founderId.jsp").forward(req,resp);
        }
    }
}

