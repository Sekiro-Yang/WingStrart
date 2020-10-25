package main.controller.Add;

import main.Utils.ComIdGen;
import main.dao.competitorDao;
import main.dao.judgeDao;
import main.entity.Competitor;
import main.entity.Judge;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/AddCompetitorServlet")
public class AddCompetitorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取项目Id和名称
        String projectId = req.getParameter("projectId");
        String projectName = req.getParameter("projectName");
        ComIdGen comIdGen = new ComIdGen();
        String addComAcc = null;
        try {
            addComAcc = comIdGen.GetComId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (projectId==null || projectName==null){
            projectId = (String) req.getAttribute("temp_projectId");
            projectName = (String) req.getAttribute("temp_projectName");
        }
        //将Id和Name加入会话
        req.setAttribute("projectId",projectId);
        req.setAttribute("projectName",projectName);
        req.setAttribute("addComAcc",addComAcc);
        //重定位
        req.getRequestDispatcher("view/add_competitor.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必须
        PrintWriter writer = resp.getWriter();
        //从会话中得到Id和Name
        String projectId = req.getParameter("projectId");
        String projectName = req.getParameter("projectName");
        //设置参数到req和resp中
        req.setAttribute("temp_projectId",projectId);
        req.setAttribute("temp_projectName",projectName);
        //获取输入的选手的用户名和密码
        ComIdGen comIdGen = new ComIdGen();
        String addComPas = req.getParameter("competitorPassword");
        String addComName = req.getParameter("competitorName");
        //判断输入选手的用户名和名称是否有空缺
        if(addComName.length() == 0 || addComPas.length() == 0){
            writer.print("<script language='javascript'>alert('填写信息有空缺，请重写输入');window.history.go(-1);</script>");
            return;
        }
        String addComAcc = null;
        try {
            addComAcc = comIdGen.GetComId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //新增裁判对象
        Competitor competitor = new Competitor();
        competitor.setCompetitorAccountNum(addComAcc);
        competitor.setCompetitorPassword(addComPas);
        competitor.setCompetitorName(addComName);
        competitor.setProjectId(projectId);
        competitor.setCompetitorVotesNum(0);
        //将用户信息加入数据库
        competitorDao comDao = new competitorDao();
        comDao.addCom(competitor);
        //将Id和Name加入会话
        req.setAttribute("projectId",projectId);
        req.setAttribute("projectName",projectName);
        req.setAttribute("addComAcc",addComAcc);
        //返回添加评委过渡界面
        req.getRequestDispatcher("view/add_competitor.jsp").forward(req,resp);
    }
}

