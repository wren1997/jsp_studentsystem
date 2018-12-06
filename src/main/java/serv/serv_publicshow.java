package serv;

import DAO.Dao_publicshow;
import DAO.Dao_score_subject;
import entities.Score;
import entities.stu_view_stuinfo_all;
import regfile.reginfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "serv_publicshow")
public class serv_publicshow extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        reginfo regs=new reginfo();
        System.out.println("---post---");
        HttpSession session=request.getSession();
        Dao_publicshow dps=new Dao_publicshow();
        String func=request.getParameter("func");
        switch (func)
        {
            case "changepwd":
            {
                String stu_id=request.getParameter("stuid");
                String stu_pwd=request.getParameter("stu_pwd");
                if(dps.changepwd(stu_id,stu_pwd)==1)
                {
                    //说明修改成功
                    System.out.println("修改成功！");
                    session.setAttribute("result","修改成功！");
                    session.setAttribute("gonexturl","/serv_publicshow?stuid="+ stu_id +"&stu_pwd="+ stu_pwd);
                    RequestDispatcher rd=request.getRequestDispatcher("/pages/info_show.jsp");
                    rd.forward(request,response);
                }
                break;
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        reginfo regs=new reginfo();
        System.out.println("---doGet---");
        HttpSession session=request.getSession();
        String stu_id=request.getParameter("stuid");
        String stu_pwd=request.getParameter("stu_pwd");
        Dao_publicshow dps=new Dao_publicshow();
        RequestDispatcher rd=null;
        //if(dps.getAccess(stu_id,stu_pwd)==1)
        //{
            //说明学生信息正确
            stu_view_stuinfo_all svsa=dps.query_Stu_info(stu_id,stu_pwd);
            Dao_score_subject dss=new Dao_score_subject();
            ArrayList scores=dss.queryscores_bystuid(stu_id);
            session.setAttribute("scores",scores);
            session.setAttribute("stuinfo",svsa);
            rd=request.getRequestDispatcher("/pages/pg_publicshow.jsp");
        //}
        //else{
        //    rd=request.getRequestDispatcher("/pages/pg_login.jsp");
        //}
        rd.forward(request,response);
    }
}
