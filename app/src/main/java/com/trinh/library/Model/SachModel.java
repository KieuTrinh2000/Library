package com.trinh.library.Model;

public class SachModel {
    private String MaS;
    private String TenS;
    private String TenTG;
    private String ViTri;
    private int sl;
    private String Namxb;
    private String tinhtrang;

    public SachModel(String maS, String tenS, String tenTG, String viTri, int sl, String namxb, String tinhtrang) {
        MaS = maS;
        TenS = tenS;
        TenTG = tenTG;
        ViTri = viTri;
        this.sl = sl;
        Namxb = namxb;
        this.tinhtrang = tinhtrang;
    }
    public SachModel(){

    }

    public String getMaS() {
        return MaS;
    }

    public String getTenS() {
        return TenS;
    }

    public String getTenTG() {
        return TenTG;
    }

    public String getViTri() {
        return ViTri;
    }

    public int getSl() {
        return sl;
    }

    public String getNamxb() {
        return Namxb;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    @Override
    public String toString() {
        return "" +
                "\nMã Sách  :     " + MaS  +"\n"+
                "\nTên Sách :     " + TenS  +"\n"+
                "\nTác Giả :      " + TenTG  +"\n"+
                "\nVị Trí :       " + ViTri  +"\n"+
                "\nSố Lượng  :    " + sl +"\n"+
                "\nNăm Xuất Bản : " + Namxb  +"\n"+
                "\nTình Trạng :   " + tinhtrang;
    }
}

