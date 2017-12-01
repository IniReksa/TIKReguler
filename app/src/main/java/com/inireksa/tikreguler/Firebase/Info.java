package com.inireksa.tikreguler.Firebase;

/**
 * Created by IniReksa on 8/26/2017.
 */

public class Info {
    public String infoId;
    String infojudul;
    String infoIsi;

    public Info() {
    }

    public Info(String infoId, String infojudul, String infoIsi) {
        this.infoId = infoId;
        this.infojudul = infojudul;
        this.infoIsi = infoIsi;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getInfojudul() {
        return infojudul;
    }

    public void setInfojudul(String infojudul) {
        this.infojudul = infojudul;
    }

    public String getInfoIsi() {
        return infoIsi;
    }

    public void setInfoIsi(String infoIsi) {
        this.infoIsi = infoIsi;
    }
}
