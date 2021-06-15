package com.trinh.library.Model;

import java.io.Serializable;

public class DSNguoiMuon implements Serializable {
    private String MaSv;
    private String TenSv;
    private String Lop;
    private String Sdt;

    public DSNguoiMuon(){

    }

    public DSNguoiMuon(String maSv, String tenSv, String lop, String sdt) {
        MaSv = maSv;
        TenSv = tenSv;
        Lop = lop;
        Sdt = sdt;
    }

    public String getMaSv() {
        return MaSv;
    }

    public String getTenSv() {
        return TenSv;
    }

    public String getLop() {
        return Lop;
    }

    public String getSdt() {
        return Sdt;
    }

    @Override
    public String toString() {
        return "DSNguoiMuon{" +
                "MaSv='" + MaSv + '\'' +
                ", TenSv='" + TenSv + '\'' +
                ", Lop='" + Lop + '\'' +
                ", Sdt='" + Sdt + '\'' +
                '}';
    }
}
