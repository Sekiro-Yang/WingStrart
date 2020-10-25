package main.Utils;

import main.dao.founderDao;

import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * @ClassName: MailUtils
 * @Description: 发送邮件辅助类
 * @date 2018年09月5日 下午4:17:25
 */
public class MailUtils {

    /**
     * @param email：接收邮件的地址
     * @param emailMsg：发送邮件的内容
     * @throws AddressException
     * @throws MessagingException
     * @Title: sendMail
     * @Description: 实现发送邮件
     */

    public static void sendMail(String email, String emailMsg)throws AddressException, MessagingException {
        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");

        //qq邮箱设置
        props.setProperty("mail.host", "smtp.qq.com");

        //指定验证为true
        props.setProperty("mail.smtp.auth", "true");

        // 创建验证器
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
            //自己的邮箱账号和密码（发送者）{qq邮箱开启pop3/smtp时会给你一个授权码此授权码就是你登入邮箱的密码}
            return new PasswordAuthentication("2206726208", "wiciwmzxudqneced");//授权码
            }
        };

        Session session = Session.getInstance(props, auth);

        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress("2206726208@qq.com")); // 设置发送者（自己的邮箱账号）

        message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者

        message.setSubject("川农投票系统修改信息激活");

        message.setContent(emailMsg, "text/html;charset=utf-8");

        // 3.创建 Transport用于将邮件发送
        Transport.send(message);
    }
}