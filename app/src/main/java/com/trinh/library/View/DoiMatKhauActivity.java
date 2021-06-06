package com.trinh.library.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.trinh.library.Config.ShareConFig;
import com.trinh.library.DatabaseHepler.DatabaseHelper;
import com.trinh.library.R;

public class DoiMatKhauActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button btnXacNhan;
    EditText etMatKhauCu;
    EditText etMatKhauMoi;
    EditText etXacNhanMK;
    String tkDangNhap;
    private ShareConFig shareConFig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        getViews();
        shareConFig=new ShareConFig(this);
        db = new DatabaseHelper(this);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doiMatKhau();
            }
        });

    }

    private void getViews() {
        etMatKhauCu = findViewById(R.id.etMKcu);
        etMatKhauMoi= findViewById(R.id.etMKmoi);
        etXacNhanMK = findViewById(R.id.etXacnhan);
        btnXacNhan= findViewById(R.id.btnAccept);
    }

    private void doiMatKhau() {
        String MatKhauCu = etMatKhauCu.getText().toString().trim();
        String MatKhauMoi = etMatKhauMoi.getText().toString().trim();
        String XacNhanMK = etXacNhanMK.getText().toString().trim();
        if(MatKhauCu.equals("")||MatKhauMoi.equals("")||XacNhanMK.equals("")){
            Toast.makeText(DoiMatKhauActivity.this, "Hãy nhập đầy đủ thông tin !", Toast.LENGTH_LONG).show();
        }
        else{
            if(MatKhauMoi.equals(XacNhanMK)){
              tkDangNhap=shareConFig.getTK();
                Boolean doimatkhau = db.doimatkhau(tkDangNhap,MatKhauMoi);
                if(doimatkhau==true){
                    Toast.makeText(DoiMatKhauActivity.this, "Đổi mật khẩu thành công !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DoiMatKhauActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(DoiMatKhauActivity.this, "Đổi mật khẩu thất bại !", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu không trùng khớp !", Toast.LENGTH_LONG).show();
            }
        }

    }
}