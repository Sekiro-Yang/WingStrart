package main.controller.manage;

import main.Utils.DBUtil;
import main.dao.competitorDao;
import main.dao.judgeDao;
import main.dao.projectDao;
import main.entity.Competitor;
import main.entity.Judge;
import main.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ManSinProServlet")
public class ManSinProServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        //获取数据库
        Connection connection = DBUtil.getConnection();
        projectDao projectDao = new projectDao();
        //从manage_allPro.jsp或者从会话中获取projectId并设置属性
        String projectId = req.getParameter("projectId");
        if (projectId == null){
            projectId = (String) req.getAttribute("projectId");
        }
        req.setAttribute("projectId",projectId);
        Project project = projectDao.getProjectByPId(projectId);
        //判断当前轮数是否超过最终轮数
        int projectRight = project.getProjectRight();
        if(projectRight == 0){
            writer.print("<script language='javascript'>alert('该项目为失效状态，不能管理');window.history.go(-1);</script>");
            return;
        }
        try {
            //项目信息和创建者信息模块
            String sql = "select * from project where projectId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,projectId);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()){
                writer.println("项目不存在吗？");
                return;
            }else{
                //获取项目详细信息
                String sql_projectName = resultSet.getString("projectName");
                String sql_founderId = resultSet.getString("founderId");
                String sql_founderName = resultSet.getString("founderName");
                String sql_projectSheave = resultSet.getString("projectSheave");
                String sql_endSheave = resultSet.getString("endSheave");
                //设置项目信息属性
                req.setAttribute("projectName",sql_projectName);
                req.setAttribute("founderId",sql_founderId);
                req.setAttribute("founderName",sql_founderName);
                req.setAttribute("projectSheave",sql_projectSheave);
                req.setAttribute("endSheave",sql_endSheave);
            }
            //评委信息模块
            String sql1 = "select * from judge where projectId = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1,projectId);
            ResultSet resultSet1 = statement1.executeQuery();
            if (!resultSet1.next()){
                writer.println("评委不存在吗？");
            }else{
                //获取评委信息
                String sql_judgeName = resultSet1.getString("judgeName");
                String sql_judgeAccountNum = resultSet1.getString("judgeAccountNum");
                String sql_judgePassword = resultSet1.getString("judgePassword");

                //设置评委信息属性
                req.setAttribute("judgeName",sql_judgeName);
                req.setAttribute("judgeAccount",sql_judgeAccountNum);
                req.setAttribute("judgePassword",sql_judgePassword);
            }
            //选手信息模块
            String sql2 = "select * from competitor where projectId=?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1,projectId);
            ResultSet resultSet2 = statement2.executeQuery();

            if (!resultSet2.next()){
                writer.println("选手不存在吗？");
            }else{
                //获取选手信息
                String sql_competitorName = resultSet2.getString("competitorName");
                String sql_competitorAccountNum = resultSet2.getString("competitorAccountNum");

                //设置选手信息属性
                req.setAttribute("competitorName",sql_competitorName);
                req.setAttribute("competitorAccountNum",sql_competitorAccountNum);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        judgeDao judDao = new judgeDao();
        competitorDao comDao = new competitorDao();
        //获取数据库中的内容，并将其加入会话中
        List<Judge> judList = judDao.getAllJudge();
        req.setAttribute("judList",judList);
        List<Competitor> comList = comDao.getAllCom();
        req.setAttribute("comList",comList);
        //将信息传入manage_sinPro.jsp中
        req.getRequestDispatcher("view/manage_sinPro.jsp").forward(req,resp);
    }
}
