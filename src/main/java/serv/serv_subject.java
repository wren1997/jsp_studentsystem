package serv;

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

@WebServlet(name = "serv_subject")
public class serv_subject extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        reginfo regs=new reginfo();
        System.out.println("---doGet---");
        String functype=request.getParameter("func");
        int countPerPage=regs.getCountPerPage();
        HttpSession session=request.getSession();
        int pageIndex=Integer.parseInt(request.getParameter("subject_pageIndex"));
        ArrayList subjects=(ArrayList)session.getAttribute("subjects");
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
                    Dao_score_subject dss=new Dao_score_subject();
                    subjects=dss.querysubjects();
                }
                break;
            }
            case "findsubjects":
            {
                Dao_score_subject dss=new Dao_score_subject();
                String somet=(String)request.getParameter("somet");
                System.out.println("你在查找的是：" + somet);
                subjects=dss.querysubjects(somet);
                break;
            }
        }
        session.setAttribute("subjects",subjects);
        int pageCount;
        if(subjects==null)
        {
            System.out.println("没有数据！");
        }
        if(subjects.size()%countPerPage==0)
        {
            pageCount=subjects.size()/countPerPage;
        }
        else{
            pageCount=subjects.size()/countPerPage+1;
        }
        session.setAttribute("subject_pageCount",pageCount);
        session.setAttribute("subjectsCount",subjects.size());
        int currentPageIndex=pageIndex;
        ArrayList pageSubjects=new ArrayList();
        for(int i=0;i<subjects.size();i++)
        {
            if(i>=(currentPageIndex-1)*countPerPage&&i<(currentPageIndex)*countPerPage){
                pageSubjects.add(subjects.get(i));
            }
        }
        session.setAttribute("pagesubjects",pageSubjects);
        session.setAttribute("subject_currentPageIndex",currentPageIndex);
        RequestDispatcher rd=request.getRequestDispatcher("/pages/pg_subject/pg_subject_listview.jsp");
        rd.forward(request,response);
    }
}
