package DAO;

import entities.Lab;
import entities.Student;
import entities.echartsinfo;
import entities.stu_view_stuinfo_all;
import regfile.reginfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_publicshow {
    private Connection conn=null;
    public void initConnection() throws Exception{
        reginfo infos=new reginfo();
        Class.forName(infos.getMysqldriverclassname());
        conn = DriverManager.getConnection(infos.getConnstr(),infos.getDbusername(),infos.getDbpassword());
    }

    public void closeConnection() throws Exception{
        conn.close();
    }

    public int getAccess(String stuid,String pwd)
    {
        //如果身份信息不正确，则无法通过
        try {
            this.initConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from students where stu_id='" + stuid + "' and stu_pwd='" + pwd + "'");
            if(rs.next())
            {
                System.out.println("身份信息正确");
                return 1;
            }
        }catch (Exception e)
        {
            return 0;
        }
        finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public stu_view_stuinfo_all query_Stu_info(String stuid,String pwd)
    {
        stu_view_stuinfo_all svsa=new stu_view_stuinfo_all();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from stu_view_stuinfo where stu_id='" + stuid + "' and stu_pwd='" + pwd + "'");
            while (rs.next())
            {
                svsa.setStu_id(rs.getString("stu_id"));
                svsa.setStu_name(rs.getString("stu_name"));
                System.out.println("3");
                svsa.setStu_sex(rs.getString("stu_sex"));
                System.out.println("4");
                svsa.setStu_birth(rs.getDate("stu_birth"));
                svsa.setStu_nation(rs.getString("stu_nation"));
                svsa.setStu_phone(rs.getString("stu_phone"));
                svsa.setStu_room(rs.getString("stu_room"));
                svsa.setStu_img(rs.getString("stu_img"));
                svsa.setStu_college(rs.getString("stu_college"));
                svsa.setStu_major(rs.getString("stu_major"));
                svsa.setStu_labid(rs.getInt("stu_labid"));
                svsa.setStu_pwd(rs.getString("stu_pwd"));
                svsa.setStu_email(rs.getString("stu_email"));
                svsa.setLab_id(rs.getInt("lab_id"));
                svsa.setLab_name(rs.getString("lab_name"));
                svsa.setLab_manager(rs.getString("lab_manager"));

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return svsa;
    }

    public int changepwd(String stuid,String pwd)
    {
        int i=0;
        try {
            this.initConnection();
            String sql = "update students set stu_pwd='" +
                    pwd + "' where stu_id='" + stuid + "'";
            Statement stat = conn.createStatement();
            i = stat.executeUpdate(sql);
        }catch (Exception e) {
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

    public echartsinfo query_echarts_info()
    {
        echartsinfo ei=new echartsinfo();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("SELECT sum(case when stu_sex='男' then 1 else 0 end) as boys,sum(case when stu_sex='女' then 1 else 0 end) as girls from students");
            while (rs.next())
            {
                ei.setBoy_count(rs.getInt("boys"));
                ei.setGirl_count(rs.getInt("girls"));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ei;
    }


}
