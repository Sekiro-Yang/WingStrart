package main.controller.Update;

import  main.dao.projectDao;
import main.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UpdProjectServlet")
public class UpdProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = req.getParameter("projectId");
        System.out.println(projectId);
        projectDao projectDao = new projectDao();
        Project project = projectDao.getProjectByPId(projectId);
        req.setAttribute("project",project);
        req.getRequestDispatcher("view/update_project.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必须
        PrintWriter writer = resp.getWriter();
        //获得必要信息
        String projectId = req.getParameter("projectId");
        String projectName = req.getParameter("projectName");
        //修改信息
        projectDao projectDao = new projectDao();
        if (projectName.length() == 0){
            writer.print("<script language='javascript'>alert('项目名称填写空缺，请重写输入');window.history.go(-1);</script>");
            return;
        }
        projectDao.updateProject(projectId,projectName);
        //将信息加入会话中
        Project project = projectDao.getProjectByPId(projectId);
        req.setAttribute("project",project);
        //跳转页面
        req.getRequestDispatcher("view/update_project.jsp").forward(req,resp);
    }
}
