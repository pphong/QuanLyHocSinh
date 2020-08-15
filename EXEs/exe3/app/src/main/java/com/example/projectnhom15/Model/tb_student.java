package com.example.projectnhom15.Model;

public class tb_student {
    String sMAHS,sHoTen,sNgaySinh,sLop;
    boolean GioiTinh;

    public String getsMAHS() {
        return sMAHS;
    }

    public void setsMAHS(String sMAHS) {
        this.sMAHS = sMAHS;
    }

    public String getsHoTen() {
        return sHoTen;
    }

    public void setsHoTen(String sHoTen) {
        this.sHoTen = sHoTen;
    }

    public String getsNgaySinh() {
        return sNgaySinh;
    }

    public void setsNgaySinh(String sNgaySinh) {
        this.sNgaySinh = sNgaySinh;
    }

    public String getsLop() {
        return sLop;
    }

    public void setsLop(String sLop) {
        this.sLop = sLop;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        GioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "tb_student{" +
                "sMAHS='" + sMAHS + '\'' +
                ", sHoTen='" + sHoTen + '\'' +
                ", sNgaySinh='" + sNgaySinh + '\'' +
                ", sLop='" + sLop + '\'' +
                ", GioiTinh=" + GioiTinh +
                '}';
    }
}
