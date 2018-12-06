package DAO;

import entities.Score;
import regfile.reginfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_score_subject {
    private Connection conn=null;
    public void initConnection() throws Exception{
        reginfo infos=new reginfo();
        Class.forName(infos.getMysqldriverclassname());
        conn = DriverManager.getConnection(infos.getConnstr(),infos.getDbusername(),infos.getDbpassword());
    }

    public void closeConnection() throws Exception{
        conn.close();
    }

    public ArrayList queryscores()
    {
        ArrayList<Score> sclist=new ArrayList<Score>();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from subjects,scores where scores.sco_sub_id=subjects.sub_id");
            while(rs.next())
            {
                Score tempscore=new Score();
                tempscore.setSco_id(rs.getInt("sco_id"));
                tempscore.setSco_sub_id(rs.getInt("sco_sub_id"));
                tempscore.setSco_value(rs.getInt("sco_value"));
                tempscore.setSub_id(rs.getInt("sub_id"));
                tempscore.setSub_name(rs.getString("sub_name"));
                tempscore.setSco_stu_id(rs.getString("sco_stu_id"));
                sclist.add(tempscore);
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
        return sclist;
    }

    public ArrayList queryscores(String somet)
    {
        ArrayList<Score> sclist=new ArrayList<Score>();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from subjects,scores where (scores.sco_sub_id=subjects.sub_id)" +
                    " and (sco_id=" + somet + " or sub_name='" + somet + "' or sco_stu_id='" + somet + "')");
            while(rs.next())
            {
                Score tempscore=new Score();
                tempscore.setSco_id(rs.getInt("sco_id"));
                tempscore.setSco_sub_id(rs.getInt("sco_sub_id"));
                tempscore.setSco_value(rs.getInt("sco_value"));
                tempscore.setSub_id(rs.getInt("sub_id"));
                tempscore.setSub_name(rs.getString("sub_name"));
                tempscore.setSco_stu_id(rs.getString("sco_stu_id"));
                sclist.add(tempscore);
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
        return sclist;
    }

    public ArrayList queryscores_bystuid(String somet)
    {
        ArrayList<Score> sclist=new ArrayList<Score>();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from subjects,scores where (scores.sco_sub_id=subjects.sub_id)" +
                    " and (sco_stu_id='" + somet + "')");
            while(rs.next())
            {
                Score tempscore=new Score();
                tempscore.setSco_id(rs.getInt("sco_id"));
                tempscore.setSco_sub_id(rs.getInt("sco_sub_id"));
                tempscore.setSco_value(rs.getInt("sco_value"));
                tempscore.setSub_id(rs.getInt("sub_id"));
                tempscore.setSub_name(rs.getString("sub_name"));
                tempscore.setSco_stu_id(rs.getString("sco_stu_id"));
                sclist.add(tempscore);
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
        return sclist;
    }

    public ArrayList querysubjects()
    {
        ArrayList<Score> sclist=new ArrayList<Score>();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from subjects");
            while(rs.next())
            {
                Score tempscore=new Score();
                tempscore.setSub_id(rs.getInt("sub_id"));
                tempscore.setSub_name(rs.getString("sub_name"));
                sclist.add(tempscore);
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
        return sclist;
    }

    public ArrayList querysubjects(String intxt)
    {
        ArrayList<Score> sclist=new ArrayList<Score>();
        try{
            this.initConnection();
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select * from subjects where sub_id='" + intxt + "' or  upper(sub_name) like upper('%" + intxt + "%') ");
            while(rs.next())
            {
                Score tempscore=new Score();
                tempscore.setSub_id(rs.getInt("sub_id"));
                tempscore.setSub_name(rs.getString("sub_name"));
                sclist.add(tempscore);
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
        return sclist;
    }

    public int scores_insert(Score inscore)
    {
        int i=0;
        try
        {
            this.initConnection();
            String sql="insert into scores values('" +
                    inscore.getSco_id() + "','" +
                    inscore.getSco_sub_id() + "','" +
                    inscore.getSco_value() + "','" +
                    inscore.getSco_stu_id() + "')";
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

    public int scores_edit(Score inscore)
    {
        int i=0;
        try
        {
            this.initConnection();
            String sql="update scores set sco_value=" +
                    inscore.getSco_value() + " where sco_id=" + inscore.getSco_id();
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

    public int scores_delete(int sco_id)
    {
        int i=0;
        try
        {
            this.initConnection();
            String sql="delete from scores where sco_id=" + sco_id;
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

    public int subject_insert(Score inscore)
    {
        int i=0;
        try
        {
            this.initConnection();
            String sql="insert into subjects values(" +
                    inscore.getSub_id() + ",'" +
                    inscore.getSub_name() + "')";
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


    public int subjects_delete(int sub_id)
    {
        int i=0;
        try
        {
            this.initConnection();
            String sql="delete from subjects where sub_id=" + sub_id;
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
