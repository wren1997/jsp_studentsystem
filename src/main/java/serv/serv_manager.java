package serv;

import DAO.Dao_Manager;
import DAO.Dao_score_subject;
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

@WebServlet(name = "serv_manager")
public class serv_manager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        reginfo regs=new reginfo();
        System.out.println("---doGet---");
        String functype=request.getParameter("func");
        int countPerPage=regs.getCountPerPage();
        HttpSession session=request.getSession();
        String issuper =session.getAttribute("issuper").toString();
        if(issuper.equals("admin"))
        {
            //一般的管理员只能修改自己的密码
            RequestDispatcher rd=request.getRequestDispatcher("/pages/pg_system/pg_changepw.jsp");
            rd.forward(request,response);
        }
        else {
            //超级管理员
            int pageIndex = Integer.parseInt(request.getParameter("managers_pageIndex"));
            ArrayList managers = (ArrayList) session.getAttribute("managers");
            if (pageIndex == 0) {
                pageIndex = 1;
            }
            switch (functype) {
                case "viewall": {
                    if (true) {
                        Dao_Manager dm = new Dao_Manager();
                        managers = dm.query_managers();
                    }
                    break;
                }
                case "findmanager": {
                    Dao_Manager dm = new Dao_Manager();
                    String somet = (String) request.getParameter("somet");
                    System.out.println("你在查找的是：" + somet);
                    managers=dm.find_managers(somet);
                    break;
                }

            }
            session.setAttribute("managers",managers);
            int pageCount;
            if(managers==null)
            {
                System.out.println("没有数据！");
            }
            if(managers.size()%countPerPage==0)
            {
                pageCount=managers.size()/countPerPage;
            }
            else{
                pageCount=managers.size()/countPerPage+1;
            }
            session.setAttribute("managers_pageCount",pageCount);
            session.setAttribute("managersCount",managers.size());
            int currentPageIndex=pageIndex;
            ArrayList pageManagers=new ArrayList();
            for(int i=0;i<managers.size();i++)
            {
                if(i>=(currentPageIndex-1)*countPerPage&&i<(currentPageIndex)*countPerPage){
                    pageManagers.add(managers.get(i));
                }
            }
            session.setAttribute("pagemanagers",pageManagers);
            session.setAttribute("managers_currentPageIndex",currentPageIndex);
            RequestDispatcher rd=request.getRequestDispatcher("/pages/pg_system/pg_manager_listview.jsp");
            rd.forward(request,response);
        }
    }
}
