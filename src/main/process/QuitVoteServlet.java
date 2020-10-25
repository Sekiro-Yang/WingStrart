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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/QuitVoteServlet")
public class QuitVoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必须
        PrintWriter writer = resp.getWriter();
        projectDao projectDao = new projectDao();
        competitorDao competitorDao = new competitorDao();
        judgeDao judgeDao = new judgeDao();
        //获得选手信息
        String projectId = req.getParameter("projectId");
        int judgeVotesNum = Integer.parseInt(req.getParameter("judgeVotesNum"));
        String judgeAccountNum = req.getParameter("judgeAccountNum");
        int competitorVotesNum = Integer.parseInt(req.getParameter("competitorVotesNum"));
        String competitorAccountNum = req.getParameter("competitorAccountNum");
        //判断票数是否用完
        if (judgeVotesNum == 0){
            writer.print("<script language='javascript'>alert('您的票数已经用完');window.history.go(-1);</script>");
        }else{
            judgeVotesNum--;
            competitorDao.voteCom(competitorVotesNum,competitorAccountNum);
            judgeDao.voteJud(judgeVotesNum,judgeAccountNum);
            //设置信息到会话中
            Project project = projectDao.getProjectByPId(projectId);
            req.setAttribute("project",project);
            Judge judge = judgeDao.getJudgeByJAcc(judgeAccountNum);
            req.setAttribute("judge",judge);
            List<Competitor> comList = competitorDao.getAllCom();
            req.setAttribute("comList",comList);
            //将信息传入vote.jsp中
            req.getRequestDispatcher("view/vote.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
