package main.controller.Update;

import main.dao.judgeDao;
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
@WebServlet("/UpdJudgeServlet")
public class UpdJudgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从manage_sinPro.jsp中获取信息
        String judgeAccountNum = req.getParameter("judgeAccountNum");

        //从数据库中得到对应的judgeAccountNum的评委
        if (judgeAccountNum != null && !judgeAccountNum.equals("")){
            //获取judAccNum对应的judge
            judgeDao juddao = new judgeDao();
            Judge judge = juddao.selectJudgeByJudAcc(judgeAccountNum);

            //设置judge属性
            req.setAttribute("judge",judge);
        }
        req.getRequestDispatcher("view/update_judge.jsp").forward(req,resp);//重定向到update_judge.jsp
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置必须
        PrintWriter writer = resp.getWriter();
        //获取用户输入值
        String judgeName = req.getParameter("judgeName");
        String judgeAcc = req.getParameter("judgeAccountNum");
        String judgePas = req.getParameter("judgePassword");
        String projectId = req.getParameter("projectId");
        //新建judge对象并设置属性值
        Judge judge = new Judge();
        judge.setJudgeAccountNum(judgeAcc);
        judge.setJudgeName(judgeName);
        judge.setJudgePassword(judgePas);
        judge.setProjectId(projectId);
        //更新judge属性值
        judgeDao judDao = new judgeDao();
        //判断信息是否有空缺
        if (judgeName.length() == 0 || judgePas.length()==0){
            writer.print("<script language='javascript'>alert('信息填写空缺，请重写输入');window.history.go(-1);</script>");
            return;
        }
        judDao.updateJud(judge);

        req.setAttribute("projectId", judge.getProjectId());

        req.getRequestDispatcher("ManSinProServlet").forward(req,resp); //传入ManSinProServlet的Post方法中
    }
}
