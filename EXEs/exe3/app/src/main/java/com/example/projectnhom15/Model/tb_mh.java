package com.example.projectnhom15.Model;

public class tb_mh {
    String sMAMH,sTenMH,sHeSo;

    public String getsMAMH() {
        return sMAMH;
    }

    public void setsMAMH(String sMAMH) {
        this.sMAMH = sMAMH;
    }

    public String getsTenMH() {
        return sTenMH;
    }

    public void setsTenMH(String sTenMH) {
        this.sTenMH = sTenMH;
    }

    public String getsHeSo() {
        return sHeSo;
    }

    public void setsHeSo(String sHeSo) {
        this.sHeSo = sHeSo;
    }

    @Override
    public String toString() {
        return "tb_mh{" +
                "sMAMH='" + sMAMH + '\'' +
                ", sTenMH='" + sTenMH + '\'' +
                ", sHeSo='" + sHeSo + '\'' +
                '}';
    }
}
