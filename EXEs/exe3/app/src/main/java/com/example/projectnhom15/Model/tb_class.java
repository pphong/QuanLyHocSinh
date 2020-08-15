package com.example.projectnhom15.Model;

public class tb_class {
    String sLop, sGiaoVien;

    public String getsLop() {
        return sLop;
    }

    public void setsLop(String sLop) {
        this.sLop = sLop;
    }

    public String getsGiaoVien() {
        return sGiaoVien;
    }

    public void setsGiaoVien(String sGiaoVien) {
        this.sGiaoVien = sGiaoVien;
    }

    @Override
    public String toString() {
        return "tb_class{" +
                "sLop='" + sLop + '\'' +
                ", sGiaoVien='" + sGiaoVien + '\'' +
                '}';
    }
}
