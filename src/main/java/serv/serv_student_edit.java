package serv;

import DAO.Dao_Student;
import DAO.Dao_lab;
import DAO.Dao_score_subject;
import entities.Student;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import regfile.reginfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "serv_student_edit")
public class serv_student_edit extends HttpServlet {

    private reginfo regs=new reginfo();
    private String filepath;
    private String tempFilePath;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(";charset=utf-8");
        reginfo regs=new reginfo();
        filepath=regs.getImg_save_path();
        tempFilePath=regs.getUpload_temp_path();
        System.out.println("--运行到此处--filepath=" + filepath);
        System.out.println("--运行到此处--tempFilePath=" + tempFilePath);
        filepath=getServletContext().getRealPath(filepath);
        System.out.println("--运行到此处--real_filepath=" + filepath);
        tempFilePath=getServletContext().getRealPath(tempFilePath);
        System.out.println("--运行到此处--real_temp_path=" + filepath);
        PrintWriter outNet=response.getWriter();
        String getfilename="null";
        System.out.println("--运行到此处--初始化");
        DiskFileItemFactory factory=new DiskFileItemFactory();
        System.out.println("--运行到此处--初始化factory");
        factory.setSizeThreshold(4*1024);
        System.out.println("--运行到此处--设置缓存大小");
        factory.setRepository(new File(tempFilePath));
        System.out.println("--运行到此处--设置临时目录");
        ServletFileUpload upload=new ServletFileUpload(factory);
        System.out.println("--运行到此处--初始化upload");
        upload.setSizeMax(4*1024*1024);
        List items=null;
        try{
            items=upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        String functype=(String)request.getParameter("func");
        HttpSession session=request.getSession();
        ArrayList students=new ArrayList();

        switch (functype)
        {
            case "submitedit":
            {
                try{

                    System.out.println("--运行到此处--List items size:" + items.size());
                    System.out.println("--运行到此处--List items 为：" + items);
                    Iterator iter=items.iterator();
                    System.out.println("--运行到此处--准备进while");
                    while(iter.hasNext())
                    {
                        FileItem item=(FileItem)iter.next();
                        System.out.println("--运行到此处--提取出的item为：" + item);
                        if(item.isFormField())
                        {
                            System.out.println("--运行到此处--form表单");
                            processFormField(item,outNet);
                        }
                        else{
                            System.out.println("--运行到此处--文件:"+item);
                            String fileName = item.getName();// 文件名称
                            fileName = FilenameUtils.getName(fileName);
                            getfilename=processUploadedFile(item,outNet);
                        }
                        System.out.println("--运行到此处--while完成了一次");
                    }
                    outNet.close();
                }catch (Exception e)
                {
                    throw new ServletException(e);
                }
                //上传图片结尾

                DateFormat dformate=new SimpleDateFormat("yyyy-MM-dd");
                Date date=null;
                try{
                    date=dformate.parse((String)request.getParameter("student_birth"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Student student=new Student(
                        (String)request.getParameter("student_id"),
                        (String)request.getParameter("student_name"),
                        (String)request.getParameter("student_sex"),
                        date,
                        (String)request.getParameter("student_nation"),
                        (String)request.getParameter("student_phone"),
                        (String)request.getParameter("student_room"),
                        getfilename,
                        (String)request.getParameter("student_college"),
                        (String)request.getParameter("student_major"),
                        Integer.parseInt((String)request.getParameter("student_labid")),
                        (String)request.getParameter("student_pwd"),
                        (String)request.getParameter("student_email"));
                Dao_Student studentDao =new Dao_Student();
                if(studentDao.executeStudent(student)==1)
                {
                    System.out.println("更新成功！");
                }
                String isadmin="";
                RequestDispatcher rd=null;
                isadmin = (String) request.getParameter("isadmin");
                if(isadmin=="false") //说明是学生
                {
                    session.setAttribute("result", "修改成功！");
                    session.setAttribute("gonexturl", "/serv_publicshow?stuid=" + (String) request.getParameter("student_id") + "&stu_pwd=" + (String) request.getParameter("student_pwd"));
                    rd = request.getRequestDispatcher("/pages/info_show.jsp");
                }
                else{
                    response.sendRedirect("/serv_student?func=viewall&pageIndex=1");
                }


                break;
            }
            case "submitinsert":
            {
                DateFormat dformate=new SimpleDateFormat("yyyy-MM-dd");
                Date date=null;
                try{
                    date=dformate.parse((String)request.getParameter("student_birth"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Student student=new Student(
                        (String)request.getParameter("student_id"),
                        (String)request.getParameter("student_name"),
                        (String)request.getParameter("student_sex"),
                        date,
                        (String)request.getParameter("student_nation"),
                        (String)request.getParameter("student_phone"),
                        (String)request.getParameter("student_room"),
                        (String)request.getParameter("student_img"),
                        (String)request.getParameter("student_college"),
                        (String)request.getParameter("student_major"),
                        Integer.parseInt((String)request.getParameter("student_labid")),
                        (String)request.getParameter("student_pwd"),
                        (String)request.getParameter("student_email"));
                Dao_Student studentDao =new Dao_Student();
                if(studentDao.insertStudent(student)==1)
                {
                    System.out.println("插入成功！");

                    response.sendRedirect("/pages/pg_student/pg_stu_listview.jsp");
                    return;
                }
                else
                {
                    System.out.println("插入失败！");
                }

                break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("multipart/form-data;charset=utf-8");
        reginfo regs=new reginfo();

        String functype=(String)request.getParameter("func");
        HttpSession session=request.getSession();
        ArrayList students=new ArrayList();
        Dao_lab dl=new Dao_lab();
        session.setAttribute("labstudent",dl.querylabs());
        switch (functype)
        {
            case "edit":
            {

                RequestDispatcher rd=null;
                session.setAttribute("function","edit");
                session.setAttribute("donext","submitedit");
                String get_stu_id=request.getParameter("stu_id");
                Dao_Student studentDao=new Dao_Student();
                students=studentDao.queryStudents(1,get_stu_id);
                session.setAttribute("student",students.get(0));
                session.setAttribute("isadmin",(String) request.getParameter("isadmin"));

                rd=request.getRequestDispatcher("/pages/pg_student/pg_stu_editone.jsp");
                rd.forward(request,response);
                break;
            }
            case "insert":
            {
                session.setAttribute("function","insert");
                session.setAttribute("donext","submitinsert");
                Student instudent=new Student();
                session.setAttribute("isadmin","true");
                session.setAttribute("student",instudent);
                RequestDispatcher rd=request.getRequestDispatcher("/pages/pg_student/pg_stu_editone.jsp");
                rd.forward(request,response);
                break;
            }
            case "delete":
            {
                String get_stu_id=request.getParameter("stu_id");
                Dao_Student studentDao=new Dao_Student();
                session.setAttribute("isadmin","true");
                if(studentDao.executeStudent(get_stu_id)==1)
                {
                    session.setAttribute("result","删除成功！");
                    session.setAttribute("gonexturl","/serv_student?func=viewall&pageIndex=1");
                    System.out.println("成功删除！");
                }
                else{
                    session.setAttribute("result","删除失败！");
                    session.setAttribute("gonexturl","/serv_student?func=viewall&pageIndex=1");
                    System.out.println("删除失败！");
                }
                RequestDispatcher rd=request.getRequestDispatcher("/pages/info_show.jsp");
                rd.forward(request,response);

            }
        }
    }

    private void processFormField(FileItem item,PrintWriter outNet)
    {
        String name=item.getFieldName();
        String value=item.getString();
        outNet.println(name+":"+value+"\r\n");
    }

    private String processUploadedFile(FileItem item,PrintWriter outNet) throws Exception
    {
        System.out.println("--运行到此处--进入processUploadedFile函数");
        String filename=item.getName();
        int index=filename.lastIndexOf("\\");
        filename=filename.substring(index+1,filename.length());
        //filename=UUID.randomUUID().toString()+"_"+FilenameUtils.getName(filename);
        filename= UUID.randomUUID().toString()+"_"+FilenameUtils.getName(filename);
        long fileSize=item.getSize();
        if(filename.equals("") && fileSize==0)
        {
            System.out.println("--运行到此处--文件名为空且大小为0");
            return "";
        }

        String uploadfilepath = filepath + "/" + filename;
        System.out.println("--运行到此处--文件保存在了"+uploadfilepath);
        item.write(new File(uploadfilepath));
        System.out.println("--运行到此处--文件保存好了");
        return filename;
    }
}
