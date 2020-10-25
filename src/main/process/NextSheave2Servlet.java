package main.process;

import main.dao.founderDao;
import main.dao.judgeDao;
import main.dao.projectDao;
import main.entity.Founder;
import main.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/NextSheave2Servlet")
public class NextSheave2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数信息
        String founderId = req.getParameter("founderId");
        String founderName = req.getParameter("founderName");
        String projectId = req.getParameter("projectId");
        //设置必须
        projectDao projectDao = new projectDao();
        judgeDao judgeDao = new judgeDao();
        Project project = projectDao.getProjectByPId(projectId);
        //获取projectSheave参数
        int projectSheave = project.getProjectSheave();
        projectSheave++;
        //进入下一轮
        projectDao.nextSheave(projectId,projectSheave);
        judgeDao.giveVoteNum(projectId);
        //将参数信息设置到会话中
        req.setAttribute("founderId",founderId);
        req.setAttribute("founderName",founderName);
        req.getRequestDispatcher("ManAllProServlet").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
