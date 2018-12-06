package entities;

import java.util.Date;

public class Note {
    private int note_id;
    private String note_title;
    private String note_publisher;
    private Date note_publish_time;
    private String note_img;
    private String note_path;
    private String note_lab;
    private String note_tags;

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_publisher() {
        return note_publisher;
    }

    public void setNote_publisher(String note_publisher) {
        this.note_publisher = note_publisher;
    }

    public Date getNote_publish_time() {
        return note_publish_time;
    }

    public void setNote_publish_time(Date note_publish_time) {
        this.note_publish_time = note_publish_time;
    }

    public String getNote_img() {
        return note_img;
    }

    public void setNote_img(String note_img) {
        this.note_img = note_img;
    }

    public String getNote_path() {
        return note_path;
    }

    public void setNote_path(String note_path) {
        this.note_path = note_path;
    }

    public String getNote_lab() {
        return note_lab;
    }

    public void setNote_lab(String note_lab) {
        this.note_lab = note_lab;
    }

    public String getNote_tags() {
        return note_tags;
    }

    public void setNote_tags(String note_tags) {
        this.note_tags = note_tags;
    }
}
