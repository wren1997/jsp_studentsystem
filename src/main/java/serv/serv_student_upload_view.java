package serv;

import DAO.Dao_Student;
import entities.Student;
import org.apache.commons.io.FilenameUtils;
import regfile.reginfo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

@WebServlet(name = "serv_student_upload_view")
public class serv_student_upload_view extends HttpServlet {
    private reginfo regs=new reginfo();
    private String filepath;
    private String tempFilePath;
    Student student=new Student();
    private String record_stu_img="";
    private String posttodo="";

    private String record_stu_id;
    private String record_stu_pwd;
    private boolean isadmin=false;

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        //filepath=config.getInitParameter("filepath");
        //tempFilePath=config.getInitParameter("tempFilePath");
        filepath=regs.getImg_save_path();
        tempFilePath=regs.getUpload_temp_path();
        //System.out.println("--运行到此处--filepath=" + filepath);
        //System.out.println("--运行到此处--tempFilePath=" + tempFilePath);
        filepath=getServletContext().getRealPath(filepath);
        //System.out.println("--运行到此处--real_filepath=" + filepath);
        tempFilePath=getServletContext().getRealPath(tempFilePath);
        //System.out.println("--运行到此处--real_temp_path=" + filepath);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        PrintWriter outNet=response.getWriter();
        outNet.println("<a href='/serv_student?func=viewall&pageIndex=1'>Successfull成功！</a>");
        String getfilename="";
        try{
            //System.out.println("--运行到此处--初始化");
            DiskFileItemFactory factory=new DiskFileItemFactory();
            //System.out.println("--运行到此处--初始化factory");
            factory.setSizeThreshold(4*1024);
            //System.out.println("--运行到此处--设置缓存大小");
            factory.setRepository(new File(tempFilePath));
            //System.out.println("--运行到此处--设置临时目录");
            ServletFileUpload upload=new ServletFileUpload(factory);
            //System.out.println("--运行到此处--初始化upload");
            upload.setSizeMax(4*1024*1024);

            List items=upload.parseRequest(request);
            //System.out.println("--运行到此处--List items size:" + items.size());
            //System.out.println("--运行到此处--List items 为：" + items);
            Iterator iter=items.iterator();
            //System.out.println("--运行到此处--准备进while");
            while(iter.hasNext())
            {
                FileItem item=(FileItem)iter.next();
                //System.out.println("--运行到此处--提取出的item为：" + item);
                if(item.isFormField())
                {
                    //System.out.println("--运行到此处--form表单");
                    processFormField(item,outNet);
                }
                else{
                    //System.out.println("--运行到此处--文件:"+item);

                    String fileName = item.getName();// 文件名称
                    fileName = FilenameUtils.getName(fileName);
                    //System.out.println("----------------filename-------:" + fileName);
                    if(fileName.length()>3) {
                        getfilename = processUploadedFile(item, outNet);
                        student.setStu_img(getfilename);
                    }
                    else{
                        student.setStu_img(record_stu_img);
                    }


                }
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }
        Dao_Student studentDao =new Dao_Student();
        if(posttodo.equals("submitedit"))
        {
            if(studentDao.executeStudent(student)==1)
            {
                //System.out.println("更新成功！");
                session.setAttribute("result","更新成功！");
                session.setAttribute("gonexturl","/serv_student?func=viewall&pageIndex=1");
                outNet.println("Successfully update");
            }
        }
        else if(posttodo.equals("submitinsert"))
        {
            if(studentDao.insertStudent(student)==1)
            {
                //System.out.println("插入成功！");
                session.setAttribute("result","插入成功！");
                session.setAttribute("gonexturl","/serv_student?func=viewall&pageIndex=1");
                outNet.println("Successfully insert");
            }
        }
        else{
            //System.out.println("被截胡了");
        }



        System.out.println("isadmin:" + isadmin);
        if(isadmin==false) //说明是学生用户
        {
            session.setAttribute("gonexturl","/serv_publicshow?stuid="+ record_stu_id + "&stu_pwd=" + record_stu_pwd);
        }
        else{
            session.setAttribute("gonexturl","/serv_student?func=viewall&pageIndex=1");
        }


        RequestDispatcher rd=request.getRequestDispatcher("/pages/info_show.jsp");
        rd.forward(request,response);
        //response.sendRedirect("pages/pg_student/pg_stu_listview.jsp");
        //response.sendRedirect("/serv_student?func=viewall&pageIndex=1");
        //return;
        //outNet.println(" <script language=\"javascript\">\n" +
        //        "          alert(\"非法访问！\");\n" +
        //        "          top.location='xx.aspx';\n" +
        //        "   </script>");
        /*
        outNet.print("<jsp:forward page=\"/serv_student?func=viewall&pageIndex=1\"/>");
        //System.out.println("执行到这了0");
        outNet.flush();
        //System.out.println("执行到这了1");
        outNet.close();
        //System.out.println("执行到这了2");
        doGet(request,response);
        */
    }

    private void processFormField(FileItem item,PrintWriter outNet) throws UnsupportedEncodingException {
        String name=item.getFieldName();
        String value=item.getString();
        value= new String(value.getBytes("ISO-8859-1"),"UTF-8");
        //outNet.println(name+":"+value+"\r\n");

        switch (name)
        {
            case "student_id":
            { student.setStu_id(value);
            record_stu_id=student.getStu_id();
            break; }
            case "student_name":
            { student.setStu_name(value);break; }
            case "student_sex":
            { student.setStu_sex(value);break; }
            case "student_birth":
            {
                DateFormat dformate=new SimpleDateFormat("yyyy-MM-dd");
                Date date=null;
                try{
                    date=dformate.parse((String)value);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                student.setStu_birth(date);
                break;
            }
            case "student_nation":
            { student.setStu_nation(value);break; }
            case "student_phone":
            { student.setStu_phone(value);break; }
            case "student_room":
            { student.setStu_room(value);break; }
            case "student_college":
            { student.setStu_college(value);break; }
            case "student_major":
            { student.setStu_major(value);break; }
            case "student_labid":
            { student.setStu_labid(Integer.parseInt(value));break; }
            case "student_pwd":
            { student.setStu_pwd(value);
            record_stu_pwd=student.getStu_pwd();
            break; }
            case "student_email":
            { student.setStu_email(value);break; }
            case "student_img":
            {
                record_stu_img=value;break;
            }
            case "func":
            {
                posttodo=value;
                //System.out.println("posttodo-----" + posttodo);
                break;
            }
            case "isadmin":
            {
                String getisadmin=value;
                System.out.println("getisadmin " + getisadmin);
                if(getisadmin.equals("true")) {
                    isadmin = true;
                    System.out.println("--------fffttrruuee");
                }
                else {
                    isadmin = false;
                }
                System.out.println("in ser_student_upload_view: isadmin: "+isadmin);
                break;
            }
        }

    }

    private String processUploadedFile(FileItem item,PrintWriter outNet) throws Exception
    {
        System.out.println("--运行到此处--进入processUploadedFile函数");
        String filename=item.getName();
        int index=filename.lastIndexOf("\\");
        filename=filename.substring(index+1,filename.length());
        //filename=UUID.randomUUID().toString()+"_"+FilenameUtils.getName(filename);
        filename=UUID.randomUUID().toString()+"_"+FilenameUtils.getName(filename);
        long fileSize=item.getSize();
        if(FilenameUtils.getName(filename).equals("") && fileSize==0)
        {
            System.out.println("--运行到此处--文件名为空且大小为0");
            return "";
        }
        String uploadfilepath = filepath + "/" + filename;
        System.out.println("--运行到此处--文件保存在了"+uploadfilepath);
        item.write(new File(uploadfilepath));
        System.out.println("--运行到此处--文件保存好了");
        String filepathtouse=regs.getImg_save_path() + "/" + filename;
        return filepathtouse;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/serv_student?func=viewall&pageIndex=1");
    }
}
