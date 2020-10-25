package main.controller.homepage;

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
import java.util.List;

@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户输入内容
        String projectId = req.getParameter("projectId");
        //设置必须
        projectDao projectDao = new projectDao();
        competitorDao competitorDao = new competitorDao();
        Project project = projectDao.getProjectByPId(projectId);
        //获得要加入会话的传递信息
        String projectName = project.getProjectName();
        int projectSheave = project.getProjectSheave();
        String founderId = project.getFounderId();
        String founderName = project.getFounderName();
        //设置属性到会话中
        req.setAttribute("projectId",projectId);
        req.setAttribute("projectName",projectName);
        req.setAttribute("projectSheave",projectSheave);
        List<Competitor> comList = competitorDao.getAllCom();
        req.setAttribute("comList",comList);
        req.setAttribute("founderId",founderId);
        req.setAttribute("founderName",founderName);
        //跳转到show.jsp页面
        req.getRequestDispatcher("view/show.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
