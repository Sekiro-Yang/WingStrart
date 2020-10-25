package main.controller.login;

import main.Utils.DBUtil;
import main.dao.judgeDao;
import main.dao.projectDao;
import main.entity.Judge;
import main.entity.Project;

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

@WebServlet("/LogJudgeServlet")
public class LogJudgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必须
        PrintWriter writer = resp.getWriter();
        projectDao projectDao = new projectDao();
        judgeDao judgeDao = new judgeDao();
        //获取用户输入的用户名和密码
        String input_judgeAccountNum = req.getParameter("judgeAccountNum");
        String input_judgePassword = req.getParameter("judgePassword");
        String input_projectId = req.getParameter("projectId");
        Judge judge = judgeDao.getJudgeByJAcc(input_judgeAccountNum);
        Project project = projectDao.getProjectByPId(input_projectId);
        //设置新的Cookie
        Cookie cookie = new Cookie("JSESSIONID",req.getSession().getId());
        cookie.setMaxAge(60*100);
        cookie.setPath("/");
        resp.addCookie(cookie);
        //检测评委输入的账号和密码和数据库的用户名和密码一致
        try {
            Connection conn = DBUtil.getConnection();//传入数据库
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM judge WHERE judgeAccountNum = ?");//防止sql注入
            stmt.setString(1,input_judgeAccountNum);//传入参数
            ResultSet resultSet = stmt.executeQuery();//返回一个结果集，是judgename对应的所有参数集合

            if (!resultSet.next()){
                writer.print("<script language='javascript'>alert('该评委不存在，点击确定返回登录界面');window.history.go(-1);</script>");
                return;
            }
            //获得数据库中的数据
            String sql_judgePassword = resultSet.getString("judgePassword");
            String sql_projectId = resultSet.getString("projectId");
            //判断是否有误
            if (input_judgeAccountNum.length()==0 || input_judgePassword.length() == 0 || input_projectId.length()==0) {
                writer.print("<script language='javascript'>alert('填写信息有空缺，请重写输入');window.history.go(-1);</script>");
                return;
            }else{
                if (sql_judgePassword.equals(input_judgePassword) && input_projectId.equals(sql_projectId)){//登录成功
                    //在会话中设置信息
                    req.setAttribute("project",project);
                    req.setAttribute("judge",judge);
                    //重定位
                    req.getRequestDispatcher("VoteServlet").forward(req,resp);
                }else{
                    writer.print("<script language='javascript'>alert('信息有误，请重写输入');window.history.go(-1);</script>");
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
