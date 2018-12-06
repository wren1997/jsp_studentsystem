package utils;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.stu_view_stuinfo_all;

public class pdf {
    private Document document;
    private PdfWriter writer;
    private ArrayList scores;
    private stu_view_stuinfo_all svsa;

    public void init(Document indoc,PdfWriter inw)
    {
        document=indoc;
        writer=inw;
    }

    public void makefile(ArrayList ins,stu_view_stuinfo_all insv)
    {
        scores=ins;
        svsa=insv;
        try{
            document.open();
            BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese, 12,Font.NORMAL);


            PdfPTable table = new PdfPTable(3); // 3 columns.
            table.setWidthPercentage(100); // Width 100%
            table.setSpacingBefore(10f); // Space before table
            table.setSpacingAfter(10f); // Space after table

            // Set Column widths
            float[] columnWidths = { 1f, 1f, 1f };
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("单元格1",font));
            cell1.setBorderColor(BaseColor.BLUE);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell2 = new PdfPCell(new Paragraph(svsa.getStu_name()));
            cell2.setBorderColor(BaseColor.GREEN);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
            cell3.setBorderColor(BaseColor.RED);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            // To avoid having the cell border and the content overlap, if you
            // are having thick cell borders
            // cell1.setUserBorderPadding(true);
            // cell2.setUserBorderPadding(true);
            // cell3.setUserBorderPadding(true);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);

            document.add(table);


        }catch(DocumentException de)
        {
            de.printStackTrace();
            System.err.println
                    ("A Document error:" +de.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public Document getDocument()
    {
        return document;
    }
    public PdfWriter getWriter()
    {
        return writer;
    }


}
