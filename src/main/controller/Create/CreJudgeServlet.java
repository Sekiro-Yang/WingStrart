package main.controller.Create;

import main.Utils.JudIdGen;
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
import java.sql.SQLException;

@WebServlet("/create_judge")
public class CreJudgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取信息
        String projectId = (String) req.getAttribute("projectId");
        //建立必须
        PrintWriter writer = resp.getWriter();
        projectDao projectDao = new projectDao();
        Project project=null;
        //建立必要参数
        String projectName = null;
        if(projectId!=null){
            //得到project
            project = projectDao.getProjectByPId(projectId);
            projectName = project.getProjectName();
        }
        //通过project得到projectName
        if (projectId==null){
            //获得输入信息
            projectId = req.getParameter("projectId");
            projectName = req.getParameter("projectName");
            String judgeName = req.getParameter("judgeName");
            String judgePassword = req.getParameter("judgePassword");
            JudIdGen judIdGen = new JudIdGen();
            String judgeAccountNum = null;
            try {
                judgeAccountNum = judIdGen.GetJudId();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //判断是否有空缺
            if (judgeName.length()==0 || judgePassword.length()==0){
                writer.print("<script language='javascript'>alert('填写信息有空缺，请重写输入');window.location.href='view/create_judge.jsp';</script>");
            }else{
                //新建裁判
                Judge judge = new Judge();
                judge.setProjectId(projectId);
                judge.setProjectName(projectName);
                judge.setJudgeAccountNum(judgeAccountNum);
                judge.setJudgeName(judgeName);
                judge.setJudgePassword(judgePassword);
                //将裁判信息加入信息库
                judgeDao judgeDao = new judgeDao();
                judgeDao.addJudge(judge);
                //设置属性
                req.setAttribute("projectId",projectId);
                req.setAttribute("projectName",projectName);
                req.setAttribute("judgeAccountNum",judgeAccountNum);
                //重定位
                req.getRequestDispatcher("view/create_judge.jsp").forward(req,resp);
            }
        }else{
            JudIdGen judIdGen = new JudIdGen();
            String judgeAccountNum = null;
            try {
                judgeAccountNum = judIdGen.GetJudId();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //设置会话属性
            req.setAttribute("projectId",projectId);
            req.setAttribute("projectName",projectName);
            req.setAttribute("judgeAccountNum",judgeAccountNum);
            //重定位
            req.getRequestDispatcher("view/create_judge.jsp").forward(req,resp);
        }
    }
}
