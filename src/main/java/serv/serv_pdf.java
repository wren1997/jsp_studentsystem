package serv;





import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.stu_view_stuinfo_all;
import utils.pdf;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "serv_pdf")
public class serv_pdf extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /*
        Document document = new Document(PageSize.A4, 36,36,36,36);
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        pdf np=new pdf();
        HttpSession session=request.getSession();
        ArrayList scores= (ArrayList) session.getAttribute("scores");
        stu_view_stuinfo_all svsa=(stu_view_stuinfo_all) session.getAttribute("stuinfo");
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, ba);
            np.init(document,writer);
            np.makefile(scores,svsa);
        }
        catch(DocumentException de)
        {
            de.printStackTrace();
            System.err.println
                    ("A Document error:" +de.getMessage());
        }

        response.setContentType
                ("application/pdf");
        response.setContentLength(ba.size());
        ServletOutputStream out
                = response.getOutputStream();
        ba.writeTo(out);
        out.flush();
        */
    }
}
