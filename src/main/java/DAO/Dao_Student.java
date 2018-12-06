package DAO;

import entities.Student;
import regfile.reginfo;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;

public class Dao_Student{
    private Connection conn=null;
    public void initConnection() throws Exception{
        reginfo infos=new reginfo();
        Class.forName(infos.getMysqldriverclassname());
        conn = DriverManager.getConnection(infos.getConnstr(),infos.getDbusername(),infos.getDbpassword());
    }

    public void closeConnection() throws Exception{
        conn.close();
    }


    public ArrayList queryStudents()
    {  //查询所有学生
        System.out.println("---queryStudent--All--");
        return queryStudents_result("select * from students");
    }

    public ArrayList queryStudents(int style,String sth)
    {//包括根据ID查询和模糊查询
        String sql;
        System.out.println("---queryStudent--with:"+style+" as "+ sth);
        switch (style)
        {
            case 1://ID查询
            {
                sql="select * from students where stu_id='" + sth + "'";
                break;
            }
            case 2://模糊查询
            {
                sql="select * from students where stu_id like '%" + sth + "%' or stu_name like '%"+sth+"%' or stu_sex='" + sth + "' or stu_nation like '%" + sth
                        + "%' or stu_phone='" +sth + "' or stu_room like '%"+sth+"%' or stu_college like '%" + sth + "%' or stu_major like '%"+sth+"%' or stu_email like '%"
                        +sth+"%'";
                break;
            }
            default:
                return null;
        }
        return queryStudents_result(sql);
    }

    public int queryStudent_login(String stu_id,String stu_pwd)
    {
        int i=0;
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from students where stu_id='" + stu_id + "' and stu_pwd='" + stu_pwd + "'");
            if(rs.next())
            {
                i=1;
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return i;

    }

    public ArrayList queryStudents_result(String sql)
    {
        //String sql="select * from students";
        ArrayList students =new ArrayList();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery(sql);
            while(rs.next())
            {
                Student student=new Student(
                        rs.getString("stu_id"),
                        rs.getString("stu_name"),
                        rs.getString("stu_sex"),
                        rs.getDate("stu_birth"),
                        rs.getString("stu_nation"),
                        rs.getString("stu_phone"),
                        rs.getString("stu_room"),
                        rs.getString("stu_img"),
                        rs.getString("stu_college"),
                        rs.getString("stu_major"),
                        rs.getInt("stu_labid"),
                        rs.getString("stu_pwd"),
                        rs.getString("stu_email"));
                System.out.println(student);
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                this.closeConnection();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    public int executeStudent(String student_id)
    {//删除记录
        int i=0;
        try
        {
            this.initConnection();
            Statement stat=conn.createStatement();
            i=stat.executeUpdate("delete from students where stu_id='" + student_id + "'");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public int insertStudent(Student instudent)
    {
        String sql="insert into  students values ('" +instudent.getStu_id()
                + "','" + instudent.getStu_name()
                + "','" + instudent.getStu_sex()
                + "','" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(instudent.getStu_birth())
                + "','" + instudent.getStu_nation()
                + "','" + instudent.getStu_phone()
                + "','" + instudent.getStu_room()
                + "','" + instudent.getStu_img()
                + "','" + instudent.getStu_college()
                + "','" + instudent.getStu_major()
                + "','" + instudent.getStu_pwd()
                + "','"+instudent.getStu_email()
                + "'," + instudent.getStu_labid()
                + ")";
        //System.out.println("日期最后的格式是这样的"+DateFormat.getDateInstance(DateFormat.DEFAULT).format(instudent.getStu_birth()));
        System.out.println("SQL语句为："+sql);
        int i=0;
        try
        {
            this.initConnection();
            Statement stat=conn.createStatement();
            i=stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public int executeStudent(Student instudent)
    {
        String sql="update students set stu_name='" + instudent.getStu_name()
                + "',stu_sex='" + instudent.getStu_sex()
                + "',stu_birth='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(instudent.getStu_birth())
                + "' ,stu_nation='" + instudent.getStu_name()
                + "',stu_phone='" + instudent.getStu_phone()
                + "',stu_room='" + instudent.getStu_room()
                + "',stu_img='" + instudent.getStu_img()
                + "',stu_college='" + instudent.getStu_college()
                + "',stu_major='" + instudent.getStu_major()
                + "',stu_labid="+instudent.getStu_labid()
                + ",stu_pwd='" + instudent.getStu_pwd()
                + "',stu_email='" + instudent.getStu_email()
                + "' where stu_id='" + instudent.getStu_id() + "'";
        System.out.println("日期最后的格式是这样的"+DateFormat.getDateInstance(DateFormat.DEFAULT).format(instudent.getStu_birth()));
        System.out.println("SQL语句为："+sql);
        int i=0;
        try
        {
            this.initConnection();
            Statement stat=conn.createStatement();
            i=stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public String query_student_pwd(String stuid)
    {
        String pwd="";
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from students where stu_id='" + stuid + "'");
            while(rs.next())
            {
                pwd=rs.getString("stu_pwd");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                this.closeConnection();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pwd;
    }

    public String query_student_email(String stuid)
    {
        String email="";
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from students where stu_id='" + stuid + "'");
            while(rs.next()) {

                email = rs.getString("stu_email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                this.closeConnection();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return email;
    }

}
