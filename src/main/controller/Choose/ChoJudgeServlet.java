package main.controller.Choose;

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
import java.util.List;

@WebServlet("/ChoJudgeServlet")
public class ChoJudgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数信息
        String judgeAccountNum = req.getParameter("judgeAccountNum");
        String projectId = req.getParameter("projectId");
        //设置必须
        judgeDao judgeDao = new judgeDao();
        //获取其他参数
        Judge judge = judgeDao.selectJudgeByJudAcc(judgeAccountNum);
        //获得judge对应的judgeRight
        int judgeRight = judge.getJudgeRight();
        //改变裁判的权限
        judgeDao.choJudge_sinPro(judgeRight,judgeAccountNum);
        //设置属性到会话中
        req.setAttribute("projectId",projectId);
        //重定位
        req.getRequestDispatcher("ManSinProServlet").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
