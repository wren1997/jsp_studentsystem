package serv;

import DAO.Dao_lab;
import DAO.Dao_publicshow;
import entities.echartsinfo;
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

@WebServlet(name = "serv_echarts")
public class serv_echarts extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        reginfo regs=new reginfo();
        System.out.println("---doGet---");
        HttpSession session=request.getSession();
        Dao_publicshow dp =new Dao_publicshow();
        echartsinfo ei=new echartsinfo();
        ei=dp.query_echarts_info();
        session.setAttribute("echarts_data",ei);

        RequestDispatcher rd=request.getRequestDispatcher("/pages/info_view_all.jsp");
        rd.forward(request,response);
    }
}
