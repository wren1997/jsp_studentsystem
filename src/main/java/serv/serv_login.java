package serv;

import DAO.Dao_Manager;
import DAO.Dao_Student;
import entities.Student;
import regfile.reginfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "serv_login")
public class serv_login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        reginfo regs = new reginfo();
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String checkcode = request.getParameter("randomcodecheck");
        RequestDispatcher rd=null;
        String un="null",pw="null";
        if (checkcode.equals("") || checkcode == null) {
            out.print("<script>alert('请输入验证码');window.history.go(-1);</script>");
        } else {
            if (!checkcode.equalsIgnoreCase((String) session.getAttribute("randCheckCode"))) {
                out.print("<script>alert('验证码不正确,请重新输入');window.history.go(-1);</script>");
            } else {
                Dao_Manager dm =new Dao_Manager();
                un=request.getParameter("userEntity.userCode");
                pw=request.getParameter("userEntity.password");
                int mecheck=-1;
                mecheck=dm.loginin(un,pw);
                if(mecheck==1)
                {
                    //说明存在这个用户为一般管理员
                    System.out.println("--" + un +"--" + pw + "--" + dm.loginin(un,pw));
                    session.setAttribute("username",un);
                    session.setAttribute("issuper","admin");
                    rd=request.getRequestDispatcher("/pages/mompage.jsp");
                    //rd.forward(request,response);
                }
                else if(mecheck==0)
                {
                    //说明这个用户为超级管理员
                    System.out.println("超级管理员");
                    session.setAttribute("username",un);
                    session.setAttribute("issuper","superadmin");
                    rd=request.getRequestDispatcher("/pages/mompage.jsp");
                }
                else
                {
                    //不是管理员，开始判断是否是学生用户
                    Dao_Student ds=new Dao_Student();
                    int result=ds.queryStudent_login(un,pw);
                    if(result==1)
                    {
                        //说明找到该用户
                        session.setAttribute("username",un);
                        session.setAttribute("issuper","student");
                        rd=request.getRequestDispatcher("/serv_publicshow?stuid="+ un +"&stu_pwd=" + pw);
                    }
                    else
                    {
                        //真的啥都没
                        System.out.println("!-" + un +"--" + pw + "--" + dm.loginin(un,pw));
                        out.print("<script>alert('用户名或者密码不正确，请检查重试');window.history.go(-1);</script>");
                    }


                }
            }
            //session.setAttribute("username","superadmin");
            //rd=request.getRequestDispatcher("/pages/mompage.jsp");

            rd.forward(request,response);
            //下面内容为测试跳过，发布时注意修改
            //System.out.println("!-" + un +"--" + pw + "--" + checkcode + "?" + session.getAttribute("randCheckCode"));
        }
    }
}
