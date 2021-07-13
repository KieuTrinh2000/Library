package com.trinh.library.DatabaseHepler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.trinh.library.Model.DSNguoiMuon;
import com.trinh.library.Model.SachModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Libary.db", null, 1);
        sqLiteDatabase = getWritableDatabase();// //khoi tao sqlitedb - mo csdl ra de doc ghi
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void CreateTableUser() {
        String Sql = "CREATE TABLE IF NOT EXISTS user(MaSV nvarchar(20) primary key, password nvarchar(20))";

        sqLiteDatabase.execSQL(Sql);
    }

    public void CreateTableDsSach() {
        String Sql = "CREATE TABLE IF NOT EXISTS DsSach(MaS nvarchar(10) primary key, TenS nvarchar(50), TacGia nvarchar(30), Vitri nvarchar(30)" +
                ", Soluong int, NamXb nvarchar(30), Tinhtrang nvarchar(50))";

        sqLiteDatabase.execSQL(Sql);
    }

    public void CreateTableDsNguoiMuon() {
        String Sql = "CREATE TABLE IF NOT EXISTS DsNguoiMuon(MaSV nvarchar(20) primary key, TenSV nvarchar(50), " +
                "Lop nvarchar(20), Sdt nvarchar(12))";

        sqLiteDatabase.execSQL(Sql);
    }

    // insert db user
    public Boolean insertTK(String MaSV, String pass) {
        String SQL = "INSERT INTO user(MaSV,password) values('" + MaSV + "','" + pass + "')";
        try {
            sqLiteDatabase.execSQL(SQL);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    //Check MaSV
    public boolean CheckMaSV(String MaSV) {
        sqLiteDatabase = this.getReadableDatabase();//mo csdl ra de doc
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user where MaSV=?", new String[]{MaSV});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    public Boolean checkLogin(String MaSV, String pass) {
        sqLiteDatabase = getReadableDatabase();// docjd bangr ghi
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user where MaSV=? and password=?", new String[]{MaSV, pass});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    //doimatkhau
    public boolean doimatkhau(String MaSV, String NewPassword) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("update user set PassWord = ? where MaSV=?", new String[]{NewPassword, MaSV});

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user where MaSV=? and PassWord = ?", new String[]{MaSV, NewPassword});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    //check Sach
    public boolean CheckSach(String MaS) {
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from DsSach where MaS=?", new String[]{MaS});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    //Them thông tin sách
    // insert db user
    public Boolean insertSach(String MaS, String TenS, String TacGia, String Vitri, String Soluong, String NamXb, String Tinhtrang) {
        String SQL = "INSERT INTO DsSach(MaS,TenS,TacGia,Vitri,Soluong,NamXb,Tinhtrang) " +
                "values('" + MaS + "','" + TenS + "','" + TacGia + "','" + Vitri + "','" + Soluong + "','" + NamXb + "','" + Tinhtrang + "')";
        try {
            sqLiteDatabase.execSQL(SQL);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    //getDataSach
    public ArrayList<SachModel> getDataListSach() {
        getReadableDatabase();
        String sQL = "SELECT * FROM DsSach";
        Cursor cursor = sqLiteDatabase.rawQuery(sQL, null);
        ArrayList<SachModel> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            arrayList.add(new SachModel(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4),
                    cursor.getString(5), cursor.getString(6)));
        }
        return arrayList;
    }

    //Update Sach
    public boolean upDateSach(String MaS, String TenS, String TacGia, String Vitri, String Soluong, String NamXb, String Tinhtrang) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE DsSach SET TenS=?,TacGia=?,Vitri=?,Soluong=?,NamXb=?,Tinhtrang=? WHERE MaS =?", new String[]{TenS, TacGia, Vitri, Soluong, NamXb, Tinhtrang, MaS});
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from DsSach where MaS=?", new String[]{MaS});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    //Delete Sach
    public boolean DeleteSach(String MaS) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM DsSach WHERE MaS =?", new String[]{MaS});
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from DsSach where MaS=?", new String[]{MaS});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    // Tìm kiếm sách


    public ArrayList Timkiemsach(ArrayList<SachModel> dsSach, String s) {
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from DsSach where TenS LIKE " + "'" + "%" + s + "%" + "'", null);
        while (cursor.moveToNext()) {
            String MaS = cursor.getString(0);
            String TenS = cursor.getString(1);
            String TenTG = cursor.getString(2);
            String ViTri = cursor.getString(3);
            int SoLuong = cursor.getInt(4);
            String NamXb = cursor.getString(5);
            String TinhTrang = cursor.getString(6);
            dsSach.add(new SachModel(MaS, TenS, TenTG, ViTri, SoLuong, NamXb, TinhTrang));
        }
        return dsSach;
    }

    //get dataNguoiMuon
    public ArrayList<DSNguoiMuon> getDataNM() {
        sqLiteDatabase = this.getReadableDatabase();
        String sQL = "SELECT * FROM DsNguoiMuon";
        Cursor cursor = sqLiteDatabase.rawQuery(sQL, null);
        ArrayList<DSNguoiMuon> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            arrayList.add(new DSNguoiMuon(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3)
            ));
        }
        return arrayList;
    }

    //Check MaSV
    public boolean CheckMaSinhVien(String MaSV){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from DsNguoiMuon where MaSV=?", new String[]{MaSV});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    //Them SV
    public Boolean insertNguoiMuon(String MaSV,String TenSV, String Lop, String Sdt) {
        String SQL = "INSERT INTO DsNguoiMuon(MaSV,TenSV,Lop,Sdt) " +
                "values('" + MaSV + "','" + TenSV + "','" + Lop + "','" + Sdt + "')";
        try {
            sqLiteDatabase.execSQL(SQL);
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    //Update SV
    public boolean upDateSV(String MaSV,String TenSV, String Lop, String Sdt){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE DsNguoiMuon SET TenSV=?,Lop=?,Sdt=? WHERE MaSV =?", new String[]{TenSV,Lop,Sdt,MaSV});
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from DsNguoiMuon where MaSV=?", new String[]{MaSV});
        if (cursor.getCount()>0) return true;
        else return false;
    }
    //Delete Sinh Viên
    public boolean DeleteSV(String MaSV){
        sqLiteDatabase  = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM DsNguoiMuon WHERE MaSV =?", new String[]{MaSV});
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from DsNguoiMuon where MaSV=?", new String[]{MaSV});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    // Tìm kiếm sv
    public ArrayList TimkiemNguoiMuon(ArrayList<DSNguoiMuon> dsNguoiMuon, String s) {
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from DsNguoiMuon where TenSV LIKE " + "'" + "%" + s + "%" + "'", null);
        while (cursor.moveToNext()) {
            String MaSV = cursor.getString(0);
            String TenSV = cursor.getString(1);
            String Lop = cursor.getString(2);
            String Sdt = cursor.getString(3);

            dsNguoiMuon.add(new DSNguoiMuon(MaSV, TenSV, Lop,Sdt));
        }
        return dsNguoiMuon;
    }

}




