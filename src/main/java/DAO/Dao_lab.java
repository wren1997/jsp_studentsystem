package DAO;

import entities.Lab;
import entities.Score;
import regfile.reginfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_lab {
    private Connection conn=null;
    public void initConnection() throws Exception{
        reginfo infos=new reginfo();
        Class.forName(infos.getMysqldriverclassname());
        conn = DriverManager.getConnection(infos.getConnstr(),infos.getDbusername(),infos.getDbpassword());
    }

    public void closeConnection() throws Exception{
        conn.close();
    }

    public ArrayList querylabs()
    {
        ArrayList<Lab> lablist=new ArrayList<Lab>();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from labs");
            while(rs.next())
            {
                Lab templab=new Lab();
                templab.setLab_id(rs.getInt("lab_id"));
                templab.setLab_name(rs.getString("lab_name"));
                templab.setLab_manager(rs.getString("lab_manager"));
                lablist.add(templab);
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
        return lablist;
    }

    public int labs_insert(Lab inlab)
    {
        int i=0;
        try{
            this.initConnection();
            String sql="insert into labs values(" +
                    inlab.getLab_id()+",'" +
                    inlab.getLab_name() + "','" +
                    inlab.getLab_manager() + "')";
            Statement stat=conn.createStatement();
            i=stat.executeUpdate(sql);
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

    public int labs_delete(int labid)
    {
        int i=0;
        try{
            this.initConnection();
            String sql="delete from labs where lab_id=" + labid;
            Statement stat=conn.createStatement();
            i=stat.executeUpdate(sql);
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

    public int labs_edit(Lab inlab)
    {
        int i=0;
        try{
            this.initConnection();
            String sql="update labs set lab_manager='" +
                    inlab.getLab_manager() + "' where lab_id=" + inlab.getLab_id();
            Statement stat=conn.createStatement();
            i=stat.executeUpdate(sql);
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

}
