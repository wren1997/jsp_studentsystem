package DAO;

import entities.Lab;
import entities.Manager;
import regfile.reginfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_Manager {

    private Connection conn=null;
    public void initConnection() throws Exception{
        reginfo infos=new reginfo();
        Class.forName(infos.getMysqldriverclassname());
        conn = DriverManager.getConnection(infos.getConnstr(),infos.getDbusername(),infos.getDbpassword());
    }

    public void closeConnection() throws Exception{
        conn.close();
    }

    public int loginin(String username,String password)
    {
        int i=-1; //返回曾经的用户
        try {
            this.initConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from managers where man_un='" + username + "' and man_pw='" + password + "'");
            if (rs.next()) {
                //存在，说明正确
                i = rs.getInt("man_level");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return i;
    }

    public ArrayList query_managers()
    {
        ArrayList<Manager> manlist=new ArrayList<Manager>();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from managers");
            while(rs.next())
            {
                Manager tempman =new Manager();
                tempman.setMan_id(rs.getInt("man_id"));
                tempman.setMan_un(rs.getString("man_un"));
                tempman.setMan_pw(rs.getString("man_pw"));
                tempman.setMan_level(rs.getInt("man_level"));
                manlist.add(tempman);
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }finally {
            try{
                this.closeConnection();
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        return manlist;
    }

    public ArrayList find_managers(String somet)
    {
        ArrayList<Manager> manlist=new ArrayList<Manager>();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from managers where man_id='" + somet + "' or man_un like '%" + somet + "%'");
            while(rs.next())
            {
                Manager tempman =new Manager();
                tempman.setMan_id(rs.getInt("man_id"));
                tempman.setMan_un(rs.getString("man_un"));
                tempman.setMan_pw(rs.getString("man_pw"));
                tempman.setMan_level(rs.getInt("man_level"));
                manlist.add(tempman);
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }finally {
            try{
                this.closeConnection();
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        return manlist;
    }

    public int manager_edit(Manager im)
    {
        int i=0;
        try
        {
            this.initConnection();
            String sql="update managers set man_pw='" +
                    im.getMan_pw() + "'  where man_id=" + im.getMan_id();
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

    public int manager_edit_byun(Manager im)
    {
        int i=0;
        try
        {
            this.initConnection();
            String sql="update managers set man_pw='" +
                    im.getMan_pw() + "'  where man_un='" + im.getMan_un()+"'";
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
    public int manager_insert(Manager im)
    {
        int i=0;
        try
        {
            this.initConnection();
            String sql="insert into managers values('"+ im.getMan_id() +"','" + im.getMan_un() + "','" + im.getMan_pw() + "','1')";
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

    public int manager_delete(int im)
    {
        int i=0;
        try
        {
            this.initConnection();
            String sql="delete from managers where man_id="+im;
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
}
