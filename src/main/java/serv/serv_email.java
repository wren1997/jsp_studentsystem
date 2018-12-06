package serv;

import DAO.Dao_Student;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "serv_email")
public class serv_email extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties properties = new Properties();
        // 设置认证属性
        properties.setProperty("mail.smtp.ssl.enable", "true");
        // 设置通信协议
        properties.setProperty("mail.transport.protocol", "smtp");
        // 邮件环境信息
        Session session = Session.getInstance(properties);
        // 调试,打印信息
        session.setDebug(true);
        HttpSession session1=request.getSession();
        String stu_id=(String)request.getParameter("stu_id");
        Dao_Student ds=new Dao_Student();
        String emailaddress=ds.query_student_email(stu_id);
        String stupwd=ds.query_student_pwd(stu_id);
        if(stupwd.length()>2)
        {
            Message message = new MimeMessage(session);
            // 主题
            try {
                message.setSubject("找回密码的通知");
                // 发送人
                message.setFrom(new InternetAddress("me@zhengqiao.wang"));
                // 内容
                message.setText("亲爱的您好，很抱歉听到你忘记了密码。下面是您的临时密码，请登陆后修改哟～\n" +
                        "您的密码是：" + stupwd + "\n" +
                        "记住了嘛～");

                // 邮件传输对象
                Transport transport = session.getTransport();
                // 传输连接：host，port，user，pass/主机，端口，用户名，密码
                transport.connect("smtp.exmail.qq.com", 465, "me@zhengqiao.wang", "***********C");
                // 发送邮件
                transport.sendMessage(message, new Address[]{new InternetAddress(emailaddress)});
                // 关闭连接
                transport.close();
                session1.setAttribute("result","已经向预留邮箱发送找回密码邮件！请注意查收～");
                session1.setAttribute("gonexturl","/pages/pg_login.jsp");
            }
            catch (Exception e)
            {

            }
        }
        // 邮件
        RequestDispatcher rd=request.getRequestDispatcher("/pages/info_show.jsp");
        rd.forward(request,response);

    }
}
