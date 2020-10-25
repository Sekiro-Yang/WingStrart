package main.controller.Update;

import main.dao.competitorDao;
import main.dao.judgeDao;
import main.entity.Competitor;
import main.entity.Judge;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 修改评委信息
 */
@WebServlet("/UpdCompetitorServlet")
public class UpdCompetitorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从manage_sinPro.jsp中获取AccountNum
        String competitorAccountNum = req.getParameter("competitorAccountNum");
        //设置competitorAccountNum属性
        req.setAttribute("competitorAccountNum",competitorAccountNum);
        //从数据库中得到对应的competitorAccountNum的评委
        if (competitorAccountNum != null && !competitorAccountNum.equals("")){
            //从jsp中获取competitorAccountNum
            String comAccNum = competitorAccountNum;
            //获取judAccNum对应的judge
            competitorDao comdao = new competitorDao();
            Competitor competitor = comdao.selectCompetiorByComAcc(comAccNum);
            //设置judge属性
            req.setAttribute("competitor",competitor);
        }
        req.getRequestDispatcher("view/update_competitor.jsp").forward(req,resp);//重定向到update_judge.jsp
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必须
        PrintWriter writer = resp.getWriter();
        //获取用户输入值
        String competitorName = req.getParameter("competitorName");
        String competitorPassword = req.getParameter("competitorPassword");
        //新建competitor对象并设置属性值
        Competitor competitor = new Competitor();
        competitor.setCompetitorName(competitorName);
        competitor.setCompetitorPassword(competitorPassword);
        //从update_competitor.jsp中获取信息
        String comAccNum = req.getParameter("competitorAccountNum");
        competitor.setCompetitorAccountNum(comAccNum);
        //判断信息是否有空缺
        if (competitorName.length() == 0 || competitorPassword.length()==0){
            writer.print("<script language='javascript'>alert('信息填写空缺，请重写输入');window.history.go(-1);</script>");
            return;
        }
        //更新competitor属性值
        competitorDao comDao = new competitorDao();
        comDao.updateCom(competitor);
        //从数据库中得到对应的judgeAccountNum的评委
        if (comAccNum != null && !comAccNum.equals("")){
            //获取judAccNum对应的judge
            competitorDao competitorDao = new competitorDao();
            Competitor temp_competitor = competitorDao.selectCompetiorByComAcc(comAccNum);
            //获得judge对应的projectId
            String projectId = temp_competitor.getProjectId();
            req.setAttribute("projectId",projectId);
        }
        req.getRequestDispatcher("ManSinProServlet").forward(req,resp);
    }
}
