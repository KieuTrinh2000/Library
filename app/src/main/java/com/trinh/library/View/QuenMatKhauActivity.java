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

public class QuenMatKhauActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button btnXacNhan;
    EditText etMaSV;
    EditText etMatKhauMoi;
    EditText etXacNhanMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau);
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
        etMaSV = findViewById(R.id.etMaSV);
        etMatKhauMoi= findViewById(R.id.etMKmoi);
        etXacNhanMK = findViewById(R.id.etXacnhan);
        btnXacNhan= findViewById(R.id.btnAccept);
    }

    private void doiMatKhau() {
        String Masv = etMaSV.getText().toString().trim();
        String MatKhauMoi = etMatKhauMoi.getText().toString().trim();
        String XacNhanMK = etXacNhanMK.getText().toString().trim();
        if(Masv.equals("")||MatKhauMoi.equals("")||XacNhanMK.equals("")){
            Toast.makeText(QuenMatKhauActivity.this, "Hãy nhập đầy đủ thông tin !", Toast.LENGTH_LONG).show();
        }
        else{
            if(MatKhauMoi.equals(XacNhanMK)){

                Boolean doimatkhau = db.quenmatkhau(Masv,MatKhauMoi);
                if(doimatkhau){
                    Toast.makeText(QuenMatKhauActivity.this, "Đổi mật khẩu thành công !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(QuenMatKhauActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(QuenMatKhauActivity.this, "Đổi mật khẩu thất bại !", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(QuenMatKhauActivity.this, "Mật khẩu không trùng khớp !", Toast.LENGTH_LONG).show();
            }
        }

    }
}