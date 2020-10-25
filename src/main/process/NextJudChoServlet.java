package main.process;

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
import java.util.List;

@WebServlet("/NextJudChoServlet")
public class NextJudChoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到传入数据
        String projectId = req.getParameter("projectId");
        //设置必须
        projectDao projectDao = new projectDao();
        judgeDao judgeDao = new judgeDao();
        Project project = projectDao.getProjectByPId(projectId);
        //将该项目所有的评委的权限设置为0（未选）
        projectDao.changeRight(projectId);
        //将属性加入会话中
        req.setAttribute("project",project);
        List<Judge> judList = judgeDao.getAllJudge();
        req.setAttribute("judList",judList);
        req.getRequestDispatcher("view/next_choose.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
