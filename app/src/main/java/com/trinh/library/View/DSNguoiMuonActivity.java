package com.trinh.library.View;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trinh.library.R;

public class DSNguoiMuonActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_nguoi_muon);

        getViews();
        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh sách người mượn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getViews() {
        toolbar=findViewById(R.id.toolbar);
        floatingActionButton=findViewById(R.id.add_Nguoi_Muon);
    }
}
