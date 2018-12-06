package serv;

import DAO.Dao_lab;
import DAO.Dao_score_subject;
import entities.Lab;
import entities.Score;
import regfile.reginfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "serv_lab_edit")
public class serv_lab_edit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reginfo regs=new reginfo();
        String functype=(String)request.getParameter("func");
        HttpSession session=request.getSession();
        switch (functype)
        {
            case "insert": {
                Lab lab = new Lab();
                lab.setLab_id(Integer.parseInt(request.getParameter("lab_id")));
                lab.setLab_name(request.getParameter("lab_name"));
                lab.setLab_manager(request.getParameter("lab_manager"));
                Dao_lab dl = new Dao_lab();
                if (dl.labs_insert(lab) == 1)
                {
                    System.out.println("添加成功！");
                    session.setAttribute("result","添加成功！");
                    session.setAttribute("gonexturl","/serv_lab?func=viewall&labs_pageIndex=1");
                }
                break;
            }
            case "delete":
            {
                int delid=Integer.parseInt(request.getParameter("sub_id"));
                Dao_lab dl=new Dao_lab();
                if(dl.labs_delete(delid)==1)
                {
                    System.out.println("删除成功！");
                    session.setAttribute("result","删除成功！");
                    session.setAttribute("gonexturl","/serv_lab?func=viewall&labs_pageIndex=1");
                }
                break;
            }
            case "edit":
            {
                Lab lab = new Lab();
                lab.setLab_id(Integer.parseInt(request.getParameter("lab_id")));
                lab.setLab_manager(request.getParameter("lab_manager"));
                Dao_lab dl = new Dao_lab();
                if(dl.labs_edit(lab)==1)
                {
                    System.out.println("修改成功！");
                    session.setAttribute("result","修改成功！");
                    session.setAttribute("gonexturl","/serv_lab?func=viewall&labs_pageIndex=1");
                }
                break;
            }
        }

        RequestDispatcher rd=request.getRequestDispatcher("/pages/info_show.jsp");
        rd.forward(request,response);
    }
}
