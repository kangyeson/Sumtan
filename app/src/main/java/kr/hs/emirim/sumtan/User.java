package kr.hs.emirim.sumtan;

import com.google.firebase.firestore.PropertyName;

public class User {
    private String name;
    private String tele;

    public User(){

    }

    public User(String name, String tele) {
        this.name = name;
        this.tele = tele;
    }

    @PropertyName("name")
    public String getName() {
        return name;
    }

    @PropertyName("name")
    public void setName(String name) {
        this.name = name;
    }

    @PropertyName("tele")
    public String getTele() {
        return tele;
    }

    @PropertyName("name")
    public void setTele(String tele) {
        this.tele = tele;
    }
}
