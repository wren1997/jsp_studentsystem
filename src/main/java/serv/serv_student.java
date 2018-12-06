package serv;

import DAO.Dao_Student;
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

@WebServlet(name = "serv_student")
public class serv_student extends HttpServlet {
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
        ArrayList students=(ArrayList)session.getAttribute("students");
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
                    Dao_Student studentDao=new Dao_Student();
                    students=studentDao.queryStudents();
                    System.out.println("---queryStudent--res:"+students.size());
                    session.setAttribute("students",students);
                    int pageCount;
                    if(students.size()%countPerPage==0)
                    {
                        pageCount=students.size()/countPerPage;
                    }
                    else{
                        pageCount=students.size()/countPerPage+1;
                    }
                    session.setAttribute("pageCount",pageCount);
                    session.setAttribute("studentCount",students.size());
                }
                break;

            }
            case "findStudents":
            {
                System.out.println("--findStudents--");

                Dao_Student studentDao=new Dao_Student();
                String somet=(String)request.getParameter("somet");
                System.out.println("你在查找的是：" + somet);
                students=studentDao.queryStudents(2,somet);
                session.setAttribute("students",students);
                int pageCount;
                System.out.println("查询的结果有：" + students.size());
                if(students.size()%countPerPage==0)
                {
                    pageCount=students.size()/countPerPage;
                }
                else{
                    pageCount=students.size()/countPerPage+1;
                }
                session.setAttribute("pageCount",pageCount);
                session.setAttribute("studentCount",students.size());

                break;
            }
        }
        int currentPageIndex=pageIndex;
        ArrayList pageStudents=new ArrayList();
        for(int i=0;i<students.size();i++)
        {
            if(i>=(currentPageIndex-1)*countPerPage&&i<(currentPageIndex)*countPerPage){
                pageStudents.add(students.get(i));
            }
        }
        session.setAttribute("pageStudents",pageStudents);
        session.setAttribute("currentPageIndex",currentPageIndex);
        RequestDispatcher rd=request.getRequestDispatcher("/pages/pg_student/pg_stu_listview.jsp");
        rd.forward(request,response);
    }
}
