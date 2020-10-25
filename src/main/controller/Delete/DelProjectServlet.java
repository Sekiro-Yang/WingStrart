package main.controller.Delete;

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

@WebServlet("/DelProjectServlet")
public class DelProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传入信息
        String projectId = req.getParameter("projectId");
        //确立必须
        projectDao projectDao = new projectDao();
        founderDao founderDao = new founderDao();
        Project project = projectDao.getProjectByPId(projectId);
        String founderId = project.getFounderId();
        Founder founder = founderDao.getFounderByFId(founderId);
        int projectNum = founder.getProjectNum();
        //实现删除信project功能
        projectDao.deleteProject(projectId);
        founderDao.deleteProject(projectNum,founderId);
        //将信息设置到会话中
        req.setAttribute("founderId",project.getFounderId());
        req.setAttribute("founderName",project.getFounderName());
        //重定向文件
        req.getRequestDispatcher("ManAllProServlet").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
