package regfile;

public class reginfo {

    //全局变量
    private String connstr = "jdbc:mysql://localhost:3306/zrladb?useUnicode=true&characterEncoding=utf-8";
    private String mysqldriverclassname="com.mysql.cj.jdbc.Driver";
    public String getConnstr() {
        return connstr;
    }

    public String getMysqldriverclassname() {
        return mysqldriverclassname;
    }

    public String getDbusername() {
        return dbusername;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    private String dbusername="zzurla";
    private String dbpassword="Zzummhjd4321@";

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    private int countPerPage=5; //每页显示多少个信息

    private String img_save_path="/upload/img";

    public String getImg_save_path() {
        return img_save_path;
    }

    private String upload_temp_path="/upload/temp";

    public String getUpload_temp_path() {
        return upload_temp_path;
    }
}
