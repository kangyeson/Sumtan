package kr.hs.emirim.sumtan.user;

public class User {
    private String name;
    private String tele;
    private String userid;

    public User(){

    }

    public User(String name, String tele, String userid) {
        this.name = name;
        this.tele = tele;
        this.userid=userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}