package kr.hs.emirim.sumtan;

public class UserInfo {
    private String userName;
    private String userTele;
    private String shelterName;
    private String shelterTele;
    private String shelterPre;
    private String shelterAddress;

    public UserInfo(){

    }

    public UserInfo(String userName, String userTele, String shelterName, String shelterTele, String shelterPre, String shelterAddress) {
        this.userName = userName;
        this.userTele = userTele;
        this.shelterName = shelterName;
        this.shelterTele = shelterTele;
        this.shelterPre = shelterPre;
        this.shelterAddress = shelterAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTele() {
        return userTele;
    }

    public void setUserTele(String userTele) {
        this.userTele = userTele;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getShelterTele() {
        return shelterTele;
    }

    public void setShelterTele(String shelterTele) {
        this.shelterTele = shelterTele;
    }

    public String getShelterPre() {
        return shelterPre;
    }

    public void setShelterPre(String shelterPre) {
        this.shelterPre = shelterPre;
    }

    public String getShelterAddress() {
        return shelterAddress;
    }

    public void setShelterAddress(String shelterAddress) {
        this.shelterAddress = shelterAddress;
    }
}
