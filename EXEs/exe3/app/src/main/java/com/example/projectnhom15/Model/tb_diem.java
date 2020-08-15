package com.example.projectnhom15.Model;

public class tb_diem {
    String sMAHS,sMAMH,sDiem;

    public String getsMAHS() {
        return sMAHS;
    }

    public void setsMAHS(String sMAHS) {
        this.sMAHS = sMAHS;
    }

    public String getsMAMH() {
        return sMAMH;
    }

    public void setsMAMH(String sMAMH) {
        this.sMAMH = sMAMH;
    }

    public String getsDiem() {
        return sDiem;
    }

    public void setsDiem(String sDiem) {
        this.sDiem = sDiem;
    }

    @Override
    public String toString() {
        return "tb_diem{" +
                "sMAHS='" + sMAHS + '\'' +
                ", sMAMH='" + sMAMH + '\'' +
                ", sDiem='" + sDiem + '\'' +
                '}';
    }
}
