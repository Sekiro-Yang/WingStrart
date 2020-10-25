package main.process;

import main.Utils.ActivateCodeGen;
import main.Utils.MailUtils;
import main.dao.founderDao;
import main.entity.Founder;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/GetCodeServlet")
public class GetCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        功能：通过邮箱将验证码发到用户邮箱中
         */
        System.out.println("你已经进入了GetCodeSerlvet，接下来将要发送邮箱验证码");
        //设置必须
        ActivateCodeGen activateCodeGen = new ActivateCodeGen();
        founderDao founderDao = new founderDao();
        //获取信息
        String founderId = req.getParameter("founderId");
        String change_founderName = req.getParameter("change_founderName");
        String change_password = req.getParameter("change_password");
        String change_sex = req.getParameter("change_sex");
        String change_age = req.getParameter("change_age");
        //通过founderId得到founder
        Founder founder=founderDao.getFounderByFId(founderId);
        //得到founderName和email
        String founderName = founder.getFounderName();
        String email = founder.getEmail();
        //设置属性到会话中
        req.setAttribute("founderId",founderId);
        req.setAttribute("change_founderName",change_founderName);
        req.setAttribute("change_password",change_password);
        req.setAttribute("change_sex",change_sex);
        req.setAttribute("change_age",change_age);
        //生成founder的激活码并发送
        String activateCode = activateCodeGen.Random(founderId);
        //新建一个MailUtils对象
        MailUtils mailUtils = new MailUtils();
        //设置传送信息
        String emailMsg = "尊敬的"+ founderName + ",来自管理员的一封激活邮件，你的激活码是：" + activateCode;
        try {
            mailUtils.sendMail(email, emailMsg);
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        req.setAttribute("founder",founder);
        req.getRequestDispatcher("view/activate_emailcode.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
