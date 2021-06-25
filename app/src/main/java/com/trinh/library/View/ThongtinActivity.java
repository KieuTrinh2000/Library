package com.trinh.library.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.trinh.library.R;

public class ThongtinActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtnoidung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin);

        toolbar=findViewById(R.id.toolbar);
        txtnoidung=findViewById(R.id.txtnoidung);
        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông tin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String noidung="Sinh viên thực hiện: \n 1. Kiều Việt Trinh - 1810A02 \n" +
                " 2. Nguyễn Thị Duyên - 1810A01 \n\n" +
                "Giảng viên hướng dẫn: Mai Thị Thúy Hà";
        txtnoidung.setText(noidung);
    }
}