package serv;

import DAO.Dao_Manager;
import DAO.Dao_score_subject;
import entities.Manager;
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

@WebServlet(name = "serv_manager_edit")
public class serv_manager_edit extends HttpServlet {
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
                Manager m=new Manager();
                m.setMan_id(Integer.parseInt(request.getParameter("man_id")));
                System.out.println("manid:" + m.getMan_id());
                m.setMan_un(request.getParameter("man_un"));
                m.setMan_pw(request.getParameter("man_pw"));
                m.setMan_level(1);
                Dao_Manager dm=new Dao_Manager();
                if(dm.manager_insert(m)==1)
                {
                    System.out.println("添加成功！");
                    session.setAttribute("result","添加成功！");
                    session.setAttribute("gonexturl","/serv_manager?func=viewall&managers_pageIndex=1");
                }
                break;
            }
            case "delete":
            {
                int delid=Integer.parseInt(request.getParameter("man_id"));
                Dao_Manager dm=new Dao_Manager();
                if(dm.manager_delete(delid)==1)
                {
                    System.out.println("删除成功！");
                    session.setAttribute("result","删除成功！");
                    session.setAttribute("gonexturl","/serv_manager?func=viewall&managers_pageIndex=1");
                }
                break;
            }
            case "edit":
            {
                Manager m=new Manager();
                m.setMan_id(Integer.parseInt(request.getParameter("man_id")));
                m.setMan_pw(request.getParameter("man_pw"));
                Dao_Manager dm=new Dao_Manager();
                if(dm.manager_edit(m)==1)
                {
                    System.out.println("修改成功！");
                    session.setAttribute("result","修改成功！");
                    session.setAttribute("gonexturl","/serv_manager?func=viewall&managers_pageIndex=1");
                }
                break;
            }
            case "changepwd":
            {
                Manager m=new Manager();
                m.setMan_un(request.getParameter("man_un"));
                m.setMan_pw(request.getParameter("man_pw"));
                Dao_Manager dm=new Dao_Manager();
                if(dm.manager_edit_byun(m)==1)
                {
                    System.out.println("修改成功！");
                    session.setAttribute("result","修改成功！");
                    session.setAttribute("gonexturl","/serv_manager?func=viewall&managers_pageIndex=1");
                }
                break;
            }
        }

        RequestDispatcher rd=request.getRequestDispatcher("/pages/info_show.jsp");
        rd.forward(request,response);
    }
}
