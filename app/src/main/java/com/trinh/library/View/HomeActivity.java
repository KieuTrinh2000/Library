package com.trinh.library.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.trinh.library.R;

public class HomeActivity extends AppCompatActivity
 implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private CardView c1,c2,c3,c4,c5,c6,c7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        getViews();

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open,R.string.Close);
        toggle.syncState();
        //
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Library");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        c1.setOnClickListener(this::onClick);
        c2.setOnClickListener(this::onClick);
        c3.setOnClickListener(this::onClick);
        c4.setOnClickListener(this::onClick);
        c5.setOnClickListener(this::onClick);
        c6.setOnClickListener(this::onClick);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_sach:
                        startActivity(new Intent(HomeActivity.this, DSSachActivity.class));break;
                    case R.id.nav_sv:
                        startActivity(new Intent(HomeActivity.this, DSNguoiMuonActivity.class));break;
                    case R.id.nav_quydinh:
                        startActivity(new Intent(HomeActivity.this, QuyDinhActivity.class));break;
                    case R.id.nav_info:
                        startActivity(new Intent(HomeActivity.this, ThongtinActivity.class));break;
                    case R.id.nav_DoiMK:
                        startActivity(new Intent(HomeActivity.this, DoiMatKhauActivity.class));break;
                    case R.id.nav_logout:
                        startActivity(new Intent(HomeActivity.this, DangNhapActivity.class));
                    Toast.makeText(HomeActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();break;

                }
                return  true;
            }
        });

    }

    private void getViews() {
        toolbar=findViewById(R.id.toolbar);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);
        c6=findViewById(R.id.c6);

        navigationView=findViewById(R.id.navigationview);
        drawerLayout=findViewById(R.id.drawalelayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.c1:
                startActivity(new Intent(this,DSSachActivity.class));break;
            case R.id.c2:
                startActivity(new Intent(this,DSNguoiMuonActivity.class));break;
            case R.id.c3:
                startActivity(new Intent(this,QuyDinhActivity.class));break;
            case R.id.c4:
                startActivity(new Intent(this,ThongtinActivity.class));break;
            case R.id.c5:
                startActivity(new Intent(this, DoiMatKhauActivity.class));break;
            case R.id.c6:
                startActivity(new Intent(this,DangNhapActivity.class));
                Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();break;
        }
    }
}