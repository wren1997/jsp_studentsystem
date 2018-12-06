package entities;

import java.util.Date;

public class Score {
    private int sco_id;
    private int sco_sub_id;
    private int sco_value;
    private int sub_id;
    private String sub_name;
    private String sco_stu_id;

    public String getSco_stu_id() {
        return sco_stu_id;
    }

    public void setSco_stu_id(String sco_stu_id) {
        this.sco_stu_id = sco_stu_id;
    }

    public int getSco_id() {
        return sco_id;
    }

    public void setSco_id(int sco_id) {
        this.sco_id = sco_id;
    }

    public int getSco_sub_id() {
        return sco_sub_id;
    }

    public void setSco_sub_id(int sco_sub_id) {
        this.sco_sub_id = sco_sub_id;
    }

    public int getSco_value() {
        return sco_value;
    }

    public void setSco_value(int sco_value) {
        this.sco_value = sco_value;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

}
