package entities;

import java.util.Date;

public class Student {
    protected String stu_id;
    protected String stu_name;
    protected String stu_sex;
    protected Date stu_birth;
    protected String stu_nation;
    protected String stu_phone;
    protected String stu_room;
    protected String stu_email;
    protected String stu_img;
    protected String stu_college;
    protected String stu_major;
    protected String stu_pwd;
    protected int stu_labid;
    public Student(){};
    public Student(String stu_id, String stu_name, String stu_sex, Date stu_birth, String stu_nation, String stu_phone, String stu_room,  String stu_img, String stu_college, String stu_major,int stu_labid, String stu_pwd, String stu_email) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.stu_sex = stu_sex;
        this.stu_birth = stu_birth;
        this.stu_nation = stu_nation;
        this.stu_phone = stu_phone;
        this.stu_room = stu_room;
        this.stu_email = stu_email;
        this.stu_img = stu_img;
        this.stu_college = stu_college;
        this.stu_major = stu_major;
        this.stu_pwd = stu_pwd;
        this.stu_labid = stu_labid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_id='" + stu_id + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", stu_sex='" + stu_sex + '\'' +
                ", stu_birth=" + stu_birth +
                ", stu_nation='" + stu_nation + '\'' +
                ", stu_phone='" + stu_phone + '\'' +
                ", stu_img='" + stu_img + '\'' +
                ", stu_college='" + stu_college + '\'' +
                ", stu_major='" + stu_major + '\'' +
                ", stu_pwd='" + stu_pwd + '\'' +
                ", stu_labid=" + stu_labid +
                '}';
    }

    public String getStu_room() {
        return stu_room;
    }

    public void setStu_room(String stu_room) {
        this.stu_room = stu_room;
    }

    public String getStu_email() {
        return stu_email;
    }

    public void setStu_email(String stu_email) {
        this.stu_email = stu_email;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_sex() {
        return stu_sex;
    }

    public void setStu_sex(String stu_sex) {
        this.stu_sex = stu_sex;
    }

    public Date getStu_birth() {
        return stu_birth;
    }

    public void setStu_birth(Date stu_birth) {
        this.stu_birth = stu_birth;
    }

    public String getStu_nation() {
        return stu_nation;
    }

    public void setStu_nation(String stu_nation) {
        this.stu_nation = stu_nation;
    }

    public String getStu_phone() {
        return stu_phone;
    }

    public void setStu_phone(String stu_phone) {
        this.stu_phone = stu_phone;
    }

    public String getStu_img() {
        return stu_img;
    }

    public void setStu_img(String stu_img) {
        this.stu_img = stu_img;
    }

    public String getStu_college() {
        return stu_college;
    }

    public void setStu_college(String stu_college) {
        this.stu_college = stu_college;
    }

    public String getStu_major() {
        return stu_major;
    }

    public void setStu_major(String stu_major) {
        this.stu_major = stu_major;
    }

    public String getStu_pwd() {
        return stu_pwd;
    }

    public void setStu_pwd(String stu_pwd) {
        this.stu_pwd = stu_pwd;
    }

    public int getStu_labid() {
        return stu_labid;
    }

    public void setStu_labid(int stu_labid) {
        this.stu_labid = stu_labid;
    }
}
