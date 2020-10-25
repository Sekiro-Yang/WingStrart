package main.controller.Add;

import main.Utils.JudIdGen;
import main.dao.judgeDao;
import main.entity.Judge;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/AddJudgeServlet")
public class AddJudgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取项目Id和名称
        String projectId = req.getParameter("projectId");
        String projectName = req.getParameter("projectName");
        JudIdGen judIdGen = new JudIdGen();
        String judgeAccountNum = null;
        try {
            judgeAccountNum = judIdGen.GetJudId();
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
        req.setAttribute("judgeAccountNum",judgeAccountNum);

        req.getRequestDispatcher("view/add_judge.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必须
        PrintWriter writer = resp.getWriter();
        //从add_judge.jsp中传入参数
        String projectId = req.getParameter("projectId");
        String projectName = req.getParameter("projectName");
        String addJudgeAccountNum = req.getParameter("judgeAccountNum");
        //获取输入的裁判的用户名和密码
        String addJudgePassword = req.getParameter("judgePassword");
        String addJudgeName = req.getParameter("judgeName");
        //判断输入选手的用户名和名称是否有空缺
        if(addJudgePassword.length() == 0 || addJudgeName.length() == 0){
            writer.print("<script language='javascript'>alert('填写信息有空缺，请重写输入');window.history.go(-1);</script>");
            return;
        }
        //新增裁判对象
        Judge judge = new Judge();
        judge.setJudgeAccountNum(addJudgeAccountNum);
        judge.setJudgePassword(addJudgePassword);
        judge.setJudgeName(addJudgeName);
        judge.setProjectId(projectId);
        judge.setProjectName(projectName);

        //将用户信息加入数据库
        judgeDao judDao = new judgeDao();
        judDao.addJudge(judge);

        //设置参数到req和resp中
        req.setAttribute("projectId",projectId);
        req.setAttribute("projectName",projectName);
        JudIdGen judIdGen = new JudIdGen();
        String judgeAccountNum = null;
        try {
            judgeAccountNum = judIdGen.GetJudId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("judgeAccountNum",judgeAccountNum);

        //返回添加评委过渡界面
        req.getRequestDispatcher("view/add_judge.jsp").forward(req,resp);
    }
}

