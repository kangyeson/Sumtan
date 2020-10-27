package kr.hs.emirim.sumtan;

public class Resume {
    private String info;
    private String career;

    public Resume(){

    }

    public Resume(String info, String career) {
        this.info = info;
        this.career = career;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
