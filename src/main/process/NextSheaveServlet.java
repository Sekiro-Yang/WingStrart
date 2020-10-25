package main.process;

import main.dao.judgeDao;
import main.dao.projectDao;
import main.entity.Judge;
import main.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/NextSheaveServlet")
public class NextSheaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必须
        judgeDao judgeDao = new judgeDao();
        projectDao projectDao = new projectDao();
        List<Judge> judList = judgeDao.getAllJudge();
        PrintWriter writer = resp.getWriter();
        //得到必要数据
        String projectId = req.getParameter("projectId");
        Project project = projectDao.getProjectByPId(projectId);
        int projectSheave = project.getProjectSheave();
        int projectEndSheave = project.getEndSheave();
        int projectRight = project.getProjectRight();
        //判断当前轮数是否超过最终轮数
        if(projectRight == 0){
            writer.print("<script language='javascript'>alert('该项目为失效状态，不能进入下一轮');window.history.go(-1);</script>");
            return;
        }else if(projectSheave >= projectEndSheave){//当前轮数大于等于最终轮数
            writer.print("<script language='javascript'>alert('当前为最终轮数，不能进入下一轮');window.history.go(-1);</script>");
        }else{//当前小于最终轮数
            //设置属性到会话中
            req.setAttribute("project",project);
            req.setAttribute("judList",judList);
            //重定位
            req.getRequestDispatcher("view/next_choose.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
