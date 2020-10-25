package main.controller.manage;

import main.dao.founderDao;
import main.dao.projectDao;
import main.entity.Founder;
import main.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManAllProServlet")
public class ManAllProServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传入参数
        String founderId = req.getParameter("founderId");
        if (founderId==null){
            founderId = (String) req.getAttribute("founderId");
        }
        //设置必须
        founderDao founderDao = new founderDao();
        //通过founderId获取founder
        Founder founder = founderDao.getFounderByFId(founderId);
        //获取founderName
        String founderName = founder.getFounderName();
        //设置必须
        projectDao proDao = new projectDao();
        //获取信息库中的内容，并将其加入会话中
        List<Project> proList = proDao.getAllProject();
        req.setAttribute("proList",proList);
        req.setAttribute("founderName",founderName);
        req.setAttribute("founderId",founderId);
        //重定位
        req.getRequestDispatcher("view/manage_allPro.jsp").forward(req,resp);
    }
}
