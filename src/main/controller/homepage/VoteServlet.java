package main.controller.homepage;

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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必须
        PrintWriter writer = resp.getWriter();
        competitorDao competitorDao = new competitorDao();
        judgeDao judgeDao = new judgeDao();
        projectDao projectDao = new projectDao();
        //获得参数信息
        String projectId = req.getParameter("projectId");
        String judgeAccountNum = req.getParameter("judgeAccountNum");
        int competitorVotesNum = Integer.parseInt(req.getParameter("competitorVotesNum"));
        String competitorAccountNum = req.getParameter("competitorAccountNum");
        //获取judge和project
        Judge judge = judgeDao.getJudgeByJAcc(judgeAccountNum);
        Project project = projectDao.getProjectByPId(projectId);
        int judgeVotesNum = judge.getJudgeVotesNum();
        int judgeRight = judge.getJudgeRight();
        //如果裁判没票，就什么都不变，或者没有权限
        if (judgeVotesNum == 0){
            writer.print("<script language='javascript'>alert('您的票数已经用完');window.history.go(-1);</script>");
        }else if(judgeRight == 0){
            writer.print("<script language='javascript'>alert('抱歉，您在本轮没有权限进行投票');window.history.go(-1);</script>");
        }else{//如果裁判有票，那就做出改变
            competitorVotesNum++;
            judgeVotesNum--;
            competitorDao.voteCom(competitorVotesNum,competitorAccountNum);
            judgeDao.voteJud(judgeVotesNum,judgeAccountNum);
            //设置属性到会话中
            req.setAttribute("project",project);
            judge = judgeDao.getJudgeByJAcc(judgeAccountNum);
            req.setAttribute("judge",judge);
            List<Competitor> comList = competitorDao.getAllCom();
            req.setAttribute("comList",comList);
            //将信息传入vote.jsp中
            req.getRequestDispatcher("view/vote.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得评委对应的projectId(从LogJudgeServlet中得到)
        String judgeVotesNum = (String) req.getAttribute("judgeVotesNum");
        String judgeAccountNum = (String) req.getAttribute("judgeAccountNum");
        String judgeName = (String) req.getAttribute("judgeName");
        String projectName = (String) req.getAttribute("projectName");
        String projectId = (String) req.getAttribute("projectId");
        if (projectId==null){
            projectId = req.getParameter("projectId");
        }
        //设置到会话的属性中
        req.setAttribute("judgeVotesNum",judgeVotesNum);
        req.setAttribute("projectName",projectName);
        req.setAttribute("projectId",projectId);
        req.setAttribute("judgeAccountNum",judgeAccountNum);
        req.setAttribute("judgeName",judgeName);
        //获取数据库中的内容，并将其加入会话中
        competitorDao competitorDao = new competitorDao();
        List<Competitor> comList = competitorDao.getAllCom();
        req.setAttribute("comList",comList);
        //将信息传入vote.jsp中
        req.getRequestDispatcher("view/vote.jsp").forward(req,resp);
    }
}
