package kr.hs.emirim.sumtan.shelter;

public class Shelter {
    private String name;
    private String tele;
    private String pre;
    private String address;

    public Shelter(String name, String tele, String address) {

    }

    public Shelter() {}

    public Shelter(String name, String tele, String pre, String address) {
        this.name = name;
        this.tele = tele;
        this.pre = pre;
        this.address = address;
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

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
