package main.controller.Create;

import main.Utils.ProIdGen;
import main.dao.founderDao;
import main.dao.projectDao;
import main.entity.Founder;
import main.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/create_project")
public class CreProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建必须
        ProIdGen proIdGen = new ProIdGen();
        PrintWriter writer = resp.getWriter();
        //获取传入信息
        String founderId = req.getParameter("founderId");
        founderDao founderDao = new founderDao();
        Founder founder = founderDao.getFounderByFId(founderId);
        //通过founder  get到founderName和projectNum
        String founderName = founder.getFounderName();
        int projectNum = founder.getProjectNum();
        //判断用户的项目数是否超过了1个
        if (projectNum >= 1){
            writer.print("<script language='javascript'>alert('抱歉，你只能创建一个项目');window.history.go(-1);</script>");
            return;
        }
        String projectId = req.getParameter("projectId");
        //如果传入的projectId没有数值，那就新建一个projectId
        if (projectId==null){
            try {
                projectId = proIdGen.GetProId();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //加入会话
        req.setAttribute("founderId",founderId);
        req.setAttribute("founderName",founderName);
        req.setAttribute("projectId",projectId);
        //重定位到jsp
        req.getRequestDispatcher("view/create_project.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //建立必需
        PrintWriter writer = resp.getWriter();
        projectDao projectDao = new projectDao();
        //获得传入的项目信息
        String projectId = req.getParameter("projectId");
        String projectName = req.getParameter("projectName");
        int endSheave = Integer.parseInt(req.getParameter("endSheave"));
        String founderId = req.getParameter("founderId");
        String founderName = req.getParameter("founderName");
        //获得judgeNum判定走向
        int judgeNum =  projectDao.judgeLegal(projectId,endSheave);
        if (judgeNum == 1){
            writer.print("<script language='javascript'>alert('填写信息有空缺，请重写输入');window.history.go(-1);</script>");
        }else if(judgeNum == 2){
            writer.print("<script language='javascript'>alert('轮数填写有误，请重写输入');window.history.go(-1);</script>");
        }else{
            //新建project对象
            projectDao.createProject(projectId,projectName,endSheave,founderId,founderName);
            //传入信息到会话汇总
            req.setAttribute("projectId",projectId);
            //传递到create_judge中
            req.getRequestDispatcher("/create_judge").forward(req,resp);
        }
    }
}
