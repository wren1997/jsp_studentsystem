package serv;

import DAO.Dao_lab;
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

@WebServlet(name = "serv_lab")
public class serv_lab extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        reginfo regs=new reginfo();
        System.out.println("---doGet---");
        String functype=request.getParameter("func");
        int countPerPage=regs.getCountPerPage();
        HttpSession session=request.getSession();
        int pageIndex=Integer.parseInt(request.getParameter("labs_pageIndex"));
        ArrayList labs=(ArrayList)session.getAttribute("labs");
        if(pageIndex==0)
        {
            pageIndex=1;
        }
        switch (functype)
        {
            case "viewall":
            {
                if(true)
                {
                    Dao_lab dl=new Dao_lab();
                    labs=dl.querylabs();
                }
                break;
            }
        }
        session.setAttribute("labs",labs);
        int pageCount;
        if(labs==null)
        {
            System.out.println("没有数据！");
        }
        if(labs.size()%countPerPage==0)
        {
            pageCount=labs.size()/countPerPage;
        }
        else{
            pageCount=labs.size()/countPerPage+1;
        }
        session.setAttribute("labs_pageCount",pageCount);
        session.setAttribute("labsCount",labs.size());
        int currentPageIndex=pageIndex;
        ArrayList pageLabs=new ArrayList();
        for(int i=0;i<labs.size();i++)
        {
            if(i>=(currentPageIndex-1)*countPerPage&&i<(currentPageIndex)*countPerPage){
                pageLabs.add(labs.get(i));
            }
        }
        session.setAttribute("pagelabs",pageLabs);
        session.setAttribute("labs_currentPageIndex",currentPageIndex);
        RequestDispatcher rd=request.getRequestDispatcher("/pages/pg_lab/pg_lab_listview.jsp");
        rd.forward(request,response);
    }
}
