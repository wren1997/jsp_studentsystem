package serv;

import DAO.Dao_Student;
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

@WebServlet(name = "serv_score")
public class serv_score extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        reginfo regs=new reginfo();
        System.out.println("---doGet---");
        String functype=request.getParameter("func");
        int countPerPage=regs.getCountPerPage();
        HttpSession session=request.getSession();
        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        ArrayList scores=(ArrayList)session.getAttribute("scores");
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
                    Dao_score_subject scoreDao=new Dao_score_subject();
                    scores=scoreDao.queryscores();
                }
                break;
            }
            case "findscores":
            {
                Dao_score_subject scoreDao=new Dao_score_subject();
                String somet=(String)request.getParameter("somet");
                System.out.println("你在查找的是：" + somet);
                scores=scoreDao.queryscores(somet);
                break;
            }

        }
        session.setAttribute("scores",scores);
        Dao_score_subject subjectDao=new Dao_score_subject();
        ArrayList subjects=(ArrayList)subjectDao.querysubjects();
        session.setAttribute("subjectsitems",subjects);
        int pageCount;
        if(scores==null)
        {
            System.out.println("没有数据！");
        }
        if(scores.size()%countPerPage==0)
        {
            pageCount=scores.size()/countPerPage;
        }
        else{
            pageCount=scores.size()/countPerPage+1;
        }
        session.setAttribute("pageCount",pageCount);
        session.setAttribute("scoreCount",scores.size());
        int currentPageIndex=pageIndex;
        ArrayList pageScores=new ArrayList();
        for(int i=0;i<scores.size();i++)
        {
            if(i>=(currentPageIndex-1)*countPerPage&&i<(currentPageIndex)*countPerPage){
                pageScores.add(scores.get(i));
            }
        }
        session.setAttribute("pagescores",pageScores);
        session.setAttribute("currentPageIndex",currentPageIndex);
        RequestDispatcher rd=request.getRequestDispatcher("/pages/pg_score/pg_score_listview.jsp");
        rd.forward(request,response);
    }
}
