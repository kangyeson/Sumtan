package kr.hs.emirim.sumtan.shelter;

public class MainData_check {
    private int iv_bookmark;
    private int iv_profile;
    private String tv_name;
    private String tv_tele;

    public MainData_check(int iv_bookmark, int iv_profile, String tv_name, String tv_tele) {
        this.iv_bookmark = iv_bookmark;
        this.iv_profile = iv_profile;
        this.tv_name = tv_name;
        this.tv_tele = tv_tele;
    }

    public int getIv_bookmark() {
        return iv_bookmark;
    }

    public void setIv_bookmark(int iv_bookmark) {
        this.iv_bookmark = iv_bookmark;
    }

    public int getIv_profile() {
        return iv_profile;
    }

    public void setIv_profile(int iv_profile) {
        this.iv_profile = iv_profile;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getTv_tele() {
        return tv_tele;
    }

    public void setTv_tele(String tv_tele) {
        this.tv_tele = tv_tele;
    }
}
