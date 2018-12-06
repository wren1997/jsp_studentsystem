package entities;

public class Manager {
    private int man_id;
    private String man_un;
    private String man_pw;
    private int man_level;

    public int getMan_id() {
        return man_id;
    }

    public void setMan_id(int man_id) {
        this.man_id = man_id;
    }

    public String getMan_un() {
        return man_un;
    }

    public void setMan_un(String man_un) {
        this.man_un = man_un;
    }

    public String getMan_pw() {
        return man_pw;
    }

    public void setMan_pw(String man_pw) {
        this.man_pw = man_pw;
    }

    public int getMan_level() {
        return man_level;
    }

    public void setMan_level(int man_level) {
        this.man_level = man_level;
    }
}
