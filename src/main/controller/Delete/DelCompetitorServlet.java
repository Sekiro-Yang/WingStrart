package main.controller.Delete;

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

@WebServlet("/DelCompetitorServlet")
public class DelCompetitorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String comAccNum = req.getParameter("competitorAccountNum");

        //获得judge的projectId
        if (comAccNum != null && !comAccNum.equals("")){
            //获取judAccNum对应的judge
            competitorDao competitorDao = new competitorDao();
            Competitor temp_competitor = competitorDao.selectCompetiorByComAcc(comAccNum);

            //获得judge对应的projectId
            String projectId = temp_competitor.getProjectId();
            req.setAttribute("projectId",projectId);
        }

        //删除评委
        if (comAccNum!=null && !comAccNum.equals("")){
            competitorDao competitorDao = new competitorDao();
            competitorDao.deleteCom(comAccNum);
        }

        req.getRequestDispatcher("ManSinProServlet").forward(req,resp);
    }
}
