package main.process;

import main.dao.projectDao;
import main.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EndProjectServlet")
public class EndProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取信息
        String projectId = req.getParameter("projectId");
        //设置必须
        projectDao projectDao = new projectDao();
        //得到project
        Project project = projectDao.getProjectByPId(projectId);
        //设置属性到会话中
        int projectRight = project.getProjectRight();
        //结束项目
        projectDao.endProject(projectId,projectRight);
        //得到project的projectRight
        req.setAttribute("founderId",project.getFounderId());
        req.setAttribute("founderName",project.getFounderName());
        //重定位
        req.getRequestDispatcher("ManAllProServlet").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
