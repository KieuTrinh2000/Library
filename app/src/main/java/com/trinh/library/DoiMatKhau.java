package com.trinh.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DoiMatKhau extends AppCompatActivity {
    DatabaseHelper db;
    Button btnXacNhan;
    EditText etMatKhauCu;
    EditText etMatKhauMoi;
    EditText etXacNhanMK;
    String tkDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        getViews();
        db = new DatabaseHelper(this);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doiMatKhau();
            }
        });

    }

    private void getViews() {
        etMatKhauCu = (EditText)findViewById(R.id.etMKcu);
        etMatKhauMoi= (EditText)findViewById(R.id.etMKmoi);
        etXacNhanMK = (EditText)findViewById(R.id.etXacnhan);
        btnXacNhan= (Button)findViewById(R.id.btnAccept);
    }

    private void doiMatKhau() {
        String MatKhauCu = etMatKhauCu.getText().toString().trim();
        String MatKhauMoi = etMatKhauMoi.getText().toString().trim();
        String XacNhanMK = etXacNhanMK.getText().toString().trim();
        if(MatKhauCu.equals("")||MatKhauMoi.equals("")||XacNhanMK.equals("")){
            Toast.makeText(DoiMatKhau.this, "Hãy nhập đầy đủ thông tin !", Toast.LENGTH_LONG).show();
        }
        else{
            if(MatKhauMoi.equals(XacNhanMK)){

//                Boolean doimatkhau = db.doimatkhau(tkDangNhap,MatKhauMoi);
//                if(doimatkhau==true){
//                    Toast.makeText(DoiMatKhau.this, "Đổi mật khẩu thành công !", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(DoiMatKhau.this, MainActivity.class);
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(DoiMatKhau.this, "Đổi mật khẩu thất bại !", Toast.LENGTH_LONG).show();
//                }
            }else{
                Toast.makeText(DoiMatKhau.this, "Mật khẩu không trùng khớp !", Toast.LENGTH_LONG).show();
            }
        }

    }
}