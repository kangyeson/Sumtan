package kr.hs.emirim.sumtan.shelter;

public class Shelter {
    private String sname;
    private String tele;
    private String pre;
    private String address;
    private String fsaddress;
    private String shelterid;


    public Shelter() {}

    public Shelter(String sname, String tele, String pre, String address, String fsaddress, String shelterid) {
        this.sname = sname;
        this.tele = tele;
        this.pre = pre;
        this.address = address;
        this.fsaddress = fsaddress;
        this.shelterid=shelterid;
    }

    public String getSName() {
        return sname;
    }

    public void setSName(String sname) {
        this.sname = sname;
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

    public String getFsaddress() { return fsaddress;}

    public void setFsaddress(String fsaddress) { this.fsaddress = fsaddress; }

    public String getShelterid() {
        return shelterid;
    }

    public void setShelterid(String shelterid) {
        this.shelterid = shelterid;
    }
}
