package main.controller.Delete;

import main.dao.judgeDao;
import main.entity.Judge;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DelJudgeServlet")
public class DelJudgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String judgeAccountNum = req.getParameter("judgeAccountNum");
        //获得judge的projectId
        if (judgeAccountNum != null && !judgeAccountNum.equals("")){
            //从jsp中获取judAccNum
            String judAccNum = judgeAccountNum;
            //获取judAccNum对应的judge
            judgeDao juddao = new judgeDao();
            Judge temp_judge = juddao.selectJudgeByJudAcc(judAccNum);
            //获得judge对应的projectId
            String projectId = temp_judge.getProjectId();
            req.setAttribute("projectId",projectId);
        }
        //删除评委
        if (judgeAccountNum!=null && !judgeAccountNum.equals("")){
            judgeDao judDao = new judgeDao();
            judDao.deleteJud(judgeAccountNum);
        }
        req.getRequestDispatcher("ManSinProServlet").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

