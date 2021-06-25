package com.trinh.library.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trinh.library.DatabaseHepler.DatabaseHelper;
import com.trinh.library.R;

public class ThemNguoiMuonActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText etMaSV, etTenSV,etLop,etSdt;
    Button btnThemSV, btnHuySV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nguoi_muon);
        getView();
        db=new DatabaseHelper(this);
        db.CreateTableDsSach();
        btnThemSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNguoiMuon();
            }
        });
        btnHuySV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThemNguoiMuonActivity.this, DSNguoiMuonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AddNguoiMuon() {
        String MaSV = etMaSV.getText().toString().trim();
        String TenSV = etTenSV.getText().toString().trim();
        String Lop = etLop.getText().toString().trim();
        String sdt = etSdt.getText().toString().trim();



        if(MaSV.length()>0 ||TenSV.length()>0||Lop.length()>0||sdt.length()>0){
            Boolean checkSV = db.CheckMaSinhVien(MaSV);
            if(!checkSV){
                boolean check=db.insertNguoiMuon(MaSV,TenSV,Lop,sdt);
                if(check==true){
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemNguoiMuonActivity.this, DSNguoiMuonActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Sinh viên đã tồn tại", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    private void getView(){
        etMaSV = (EditText) findViewById(R.id.etMaSV);
        etTenSV = (EditText) findViewById(R.id.etTenSV);
        etLop = (EditText) findViewById(R.id.etLop);
        etSdt = (EditText) findViewById(R.id.etSDT);
        btnThemSV = (Button) findViewById(R.id.btnThemSV);
        btnHuySV = (Button) findViewById(R.id.btnHuySV);
    }
}