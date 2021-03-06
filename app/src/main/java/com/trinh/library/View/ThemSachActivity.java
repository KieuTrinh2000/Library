package com.trinh.library.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.trinh.library.DatabaseHepler.DatabaseHelper;
import com.trinh.library.R;

public class ThemSachActivity extends AppCompatActivity {

    DatabaseHelper db;
    TextView tvThemSach;
    EditText etMaSach, etTenTG,etViTri,etSoLuong,etTinhTrang,etNamxb,etTenS;
    Button btnThem, btnHuy;
    ImageView imgSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sach);
        getViews();
        db=new DatabaseHelper(this);
        db.CreateTableDsSach();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSach();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThemSachActivity.this, DSSachActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AddSach() {
        String MaS = etMaSach.getText().toString().trim();
        String TenS = etTenS.getText().toString().trim();
        String TenTG = etTenTG.getText().toString().trim();
        String Vitri = etViTri.getText().toString().trim();
        String SoLuong =etSoLuong.getText().toString().trim();
        String Namxb= etNamxb.getText().toString().trim();
        String TinhTrang= etTinhTrang.getText().toString().trim();
        
        
        if(MaS.length()>0 ||TenS.length()>0||TenTG.length()>0||Vitri.length()>0||SoLuong.length()>0||Namxb.length()>0||TinhTrang.length()>0){
            Boolean checkSach = db.CheckSach(MaS);
            if(!checkSach){
                boolean check=db.insertSach(MaS,TenS,TenTG,Vitri,SoLuong,Namxb,TinhTrang);
                if(check==true){
                    Toast.makeText(this, "Th??m th??nh c??ng", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemSachActivity.this, DSSachActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Th??m th???t b???i", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "S??ch ???? t???n t???i", Toast.LENGTH_SHORT).show();
            }
            
        }else{
            Toast.makeText(this, "Vui l??ng nh???p ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
        }
    }

    private void getViews() {
        etMaSach =  findViewById(R.id.etMasach);
        etTenTG = findViewById(R.id.etTenTG);
        etViTri =  findViewById(R.id.etVitri);
        etSoLuong = findViewById(R.id.etSoluong);
        etTenS =  findViewById(R.id.ettensach);
        etNamxb =  findViewById(R.id.etNamXB);
        etTinhTrang = findViewById(R.id.etTinhtrang);
        tvThemSach =  findViewById(R.id.tvThemSach);
        btnThem = findViewById(R.id.btnThem);
        btnHuy =  findViewById(R.id.btnHuy);
        imgSach= findViewById(R.id.imgSach);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}