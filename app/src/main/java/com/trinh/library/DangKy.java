package com.trinh.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DangKy extends AppCompatActivity {

    EditText etTenDN, etMatkhau, etXacnhan;
    TextView tvDangky;
    Button btnDangky, btnDangnhap;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        db=new DatabaseHelper(this);// khoi tao ham helper
        db.CreateTableUser();// tao dang user
        getViews();
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    private void getViews() {
        etTenDN = (EditText) findViewById(R.id.etTenDN);
        etMatkhau = (EditText) findViewById(R.id.etMatkhau);
        etXacnhan = (EditText) findViewById(R.id.etXacnhan);
        tvDangky = (TextView) findViewById(R.id.tvDangky);
        btnDangky = (Button) findViewById(R.id.btnDangky);
        btnDangnhap = (Button) findViewById(R.id.btnDangnhap);
    }

    private void Register(){
        String MaSV = etTenDN.getText().toString().trim();
        String Matkhau = etMatkhau.getText().toString().trim();
        String Xacnhan = etXacnhan.getText().toString().trim();
        if (MaSV.equals("")||Matkhau.equals("")||Xacnhan.equals("")){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        }
        else {
            if(Matkhau.equals(Xacnhan)){
                Boolean checkMaSV = db.CheckMaSV(MaSV);
                if (checkMaSV == true){
                    Boolean insert = db.insertTK(MaSV,Matkhau);
                    if (insert == true){
                        Toast.makeText(getApplicationContext(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangKy.this, DangNhapActivity.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Mã sinh viên đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}