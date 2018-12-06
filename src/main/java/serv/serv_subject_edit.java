package serv;

import DAO.Dao_score_subject;
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

@WebServlet(name = "serv_subject_edit")
public class serv_subject_edit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reginfo regs=new reginfo();
        String functype=(String)request.getParameter("func");
        HttpSession session=request.getSession();
        switch (functype)
        {
            case "insert":
            {
                Score ts=new Score();
                ts.setSub_id(Integer.parseInt( request.getParameter("sub_id")));
                ts.setSub_name( request.getParameter("sub_name"));
                Dao_score_subject dss=new Dao_score_subject();
                if(dss.subject_insert(ts)==1)
                {
                    System.out.println("添加成功！");
                    session.setAttribute("result","添加成功！");
                    session.setAttribute("gonexturl","/serv_subject?func=viewall&subject_pageIndex=1");
                }
                break;
            }
            case "delete":
            {
                int delid=Integer.parseInt(request.getParameter("sub_id"));
                Dao_score_subject dss=new Dao_score_subject();
                if(dss.subjects_delete(delid)==1)
                {
                    System.out.println("删除成功！");
                    session.setAttribute("result","删除成功！");
                    session.setAttribute("gonexturl","/serv_subject?func=viewall&subject_pageIndex=1");
                }
                break;
            }
        }

        RequestDispatcher rd=request.getRequestDispatcher("/pages/info_show.jsp");
        rd.forward(request,response);
    }
}
