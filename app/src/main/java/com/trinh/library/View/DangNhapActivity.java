package com.trinh.library.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.trinh.library.DatabaseHepler.DatabaseHelper;
import com.trinh.library.R;
import com.trinh.library.Config.ShareConFig;

public class DangNhapActivity extends AppCompatActivity {

    EditText etTenDN, etMatkhau;
    TextView tvDangnhap,tvQuenMK;
    Button btnDangky, btnDangnhap;
    ImageView imgAccount;
    private DatabaseHelper db;

    public static final String etAddMasv = "masinhvien";
    private ShareConFig shareConFig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        db = new DatabaseHelper(this);// khoi tao ham helper
        db.CreateTableUser();// tao dang user
        getViews();
        shareConFig=new ShareConFig(this);// lớp khởi tạo của sharefre
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Login();
            }
        });

        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, QuenMatKhauActivity.class);
                startActivity(intent);
            }
        });

    }

        private void getViews(){
            etMatkhau = (EditText) findViewById(R.id.etMatkhau);
            etTenDN = (EditText) findViewById(R.id.etTenDN);
            tvDangnhap = (TextView) findViewById(R.id.tvDangnhap);
            btnDangky = (Button) findViewById(R.id.btnDangky);
            btnDangnhap = (Button) findViewById(R.id.btnDangnhap);
            tvQuenMK = (TextView) findViewById(R.id.tvQuenMK);
            imgAccount = (ImageView) findViewById(R.id.imgAccount);
        }

    private void Login() {
        String MaSV = etTenDN.getText().toString();
        String Matkhau = etMatkhau.getText().toString();

        if (MaSV.equals("") || Matkhau.equals("")) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            Boolean check = db.checkLogin(MaSV, Matkhau);
            if (check == true) {
                shareConFig.PutTaiKhoan(MaSV);// khi đăng bnhaapj thành công sẽ lưu thông tin tài khoản
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DangNhapActivity.this, HomeActivity.class);
                intent.putExtra(etAddMasv, MaSV);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Kiểm tra lại mã sinh viên hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


