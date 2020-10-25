package main.controller.Create;

import main.Utils.ComIdGen;
import main.Utils.JudIdGen;
import main.dao.competitorDao;
import main.dao.projectDao;
import main.entity.Competitor;
import main.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/create_competitor")
public class CreCompetitorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //从create_judge.jsp的下一步转发得到
        //获取信息
        String projectId = req.getParameter("projectId");
        //设置必须
        projectDao projectDao = new projectDao();
        Project project = projectDao.getProjectByPId(projectId);
        //得到选手的Id
        ComIdGen comIdGen = new ComIdGen();
        String competitorAccountNum = null;
        try {
            competitorAccountNum = comIdGen.GetComId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //设置属性到会话中
        req.setAttribute("competitorAccountNum",competitorAccountNum);
        req.setAttribute("project",project);
        //重定位
        req.getRequestDispatcher("view/create_competitor.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        //获取必要用户输入信息
        String competitorName = req.getParameter("competitorName");
        String competitorPassword = req.getParameter("competitorPassword");
        String projectId = req.getParameter("projectId");
        String competitorAccountNum = req.getParameter("competitorAccountNum");
        //防止填写有空缺
        if (competitorName.length()==0 || competitorPassword.length()==0 ){
            writer.print("<script language='javascript'>alert('填写信息有空缺，请重写输入');window.history.go(-1);</script>");
        }else{
            //新建Competitor对象
            Competitor competitor = new Competitor();
            competitor.setCompetitorName(competitorName);
            competitor.setCompetitorAccountNum(competitorAccountNum);
            competitor.setCompetitorPassword(competitorPassword);
            competitor.setProjectId(projectId);
            //将Competitor加入信息库
            competitorDao com = new competitorDao();
            com.addCom(competitor);
            this.doGet(req, resp);
        }
    }
}
