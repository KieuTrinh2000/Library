package com.trinh.library.View;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trinh.library.Adapter.DSNguoiMuonAdapter;
import com.trinh.library.Adapter.DsSachAdapter;
import com.trinh.library.DatabaseHepler.DatabaseHelper;
import com.trinh.library.Model.DSNguoiMuon;
import com.trinh.library.R;

import java.util.ArrayList;

public class DSNguoiMuonActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    DatabaseHelper db;
    ArrayList<DSNguoiMuon> arrayDsNguoimuon;
    ListView lv;
    DSNguoiMuonAdapter nguoiMuonAdapter;
    public static CountDownTimer countDownTimer,countDownTimerXoa;
    public  static  int pos=0; //vi tri cua phan tu


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

        db= new DatabaseHelper(this);
        db.CreateTableDsNguoiMuon();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DSNguoiMuonActivity.this,ThemNguoiMuonActivity.class);
                startActivity(intent);
            }
        });

        db=new DatabaseHelper(DSNguoiMuonActivity.this);
        arrayDsNguoimuon=db.getDataNM();
        nguoiMuonAdapter = new DSNguoiMuonAdapter(DSNguoiMuonActivity.this,arrayDsNguoimuon);
        lv.setAdapter(nguoiMuonAdapter);

        //hien thong tin nguoi muon
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogChiTietNM(arrayDsNguoimuon.get(i).getMaSv(),arrayDsNguoimuon.get(i).getTenSv(),arrayDsNguoimuon.get(i).getLop(),
                        arrayDsNguoimuon.get(i).getSdt()
                );
            }
        });

        // khi ng dung ấn vào 2 button xoa + sua
        countDownTimer=new CountDownTimer(10,10) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                DialogEdit(arrayDsNguoimuon.get(pos).getMaSv(),
                        arrayDsNguoimuon.get(pos).getTenSv(),arrayDsNguoimuon.get(pos).getLop(),
                        arrayDsNguoimuon.get(pos).getSdt());
            }
        };

        //Xóa
        countDownTimerXoa=new CountDownTimer(10,10) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                AlertDialog.Builder builder =new AlertDialog.Builder(DSNguoiMuonActivity.this);
                builder.setMessage("Bạn có chắc muốn xoá không !");
                AlertDialog alertDialog=builder.create();// ko cho phép ấn ngoài màn hinbhf để tắt màn hình dialog
                alertDialog.setCanceledOnTouchOutside(false);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean check =db.DeleteSach(arrayDsNguoimuon.get(pos).getMaSv());
                        if(check){
                            arrayDsNguoimuon.remove(pos);
                            nguoiMuonAdapter.notifyDataSetChanged();
                            Toast.makeText(DSNguoiMuonActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DSNguoiMuonActivity.this, "Xoa thât bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        };
    }

    private void DialogEdit(String maSv, String tenSv, String lop, String sdt) {
        Dialog dialog=new Dialog(DSNguoiMuonActivity.this);
        dialog.setContentView(R.layout.edit_nguoi_muon);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);// ko cho phép ấn ngoài màn hinbhf để tắt màn hình dialog
       TextView   etMaSV = dialog. findViewById(R.id.etEditMaNM);
        TextView   etTenSV = dialog.findViewById(R.id.etEditTenNM);
        TextView  etLop = dialog.findViewById(R.id.etEditLopNM);
        TextView  etSdt = dialog.findViewById(R.id.etEditPhone);
        ImageView close =dialog. findViewById(R.id.close);
        Button btnSua = dialog.findViewById(R.id.btnSua);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        etMaSV.setText(maSv);
        etTenSV.setText(tenSv);
        etLop.setText(lop);
        etSdt.setText(sdt);

        //Sửa
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MaSV = etMaSV.getText().toString().trim();
                String TenSV = etTenSV.getText().toString().trim();
                String Lop = etLop.getText().toString().trim();
                String sdt = etSdt.getText().toString().trim();
                if(MaSV.length()>0 ||TenSV.length()>0||Lop.length()>0||sdt.length()>0){
                    Boolean checkSV = db.CheckMaSinhVien(MaSV);
                    boolean check=db.upDateSV(MaSV,TenSV,Lop,sdt);
                    if(check==true){
                        Toast.makeText(DSNguoiMuonActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DSNguoiMuonActivity.this, DSNguoiMuonActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(DSNguoiMuonActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(DSNguoiMuonActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Hủy edit
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMaSV.setText("");
                etTenSV.setText("");
                etLop.setText("");
                etSdt.setText("");

            }
        });
// đóng edit
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

    }

    private void DialogChiTietNM(String maSv, String tenSv, String lop, String sdt) {
        Dialog dialog=new Dialog(DSNguoiMuonActivity.this);
        dialog.setContentView(R.layout.dialog_chi_tiet_nguoi_muon);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);// ko cho phép ấn ngoài màn hinbhf để tắt màn hình dialog
        TextView etMaSV = dialog. findViewById(R.id.tvMaSV2);
        TextView   etTenSV = dialog.findViewById(R.id.tvTenSV2);
        TextView  etLop = dialog.findViewById(R.id.tvLop2);
        TextView  etSdt = dialog.findViewById(R.id.tvSDT2);
        Button btnThoat = dialog.findViewById(R.id.btnThoat);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

        etMaSV.setText(maSv);
        etTenSV.setText(tenSv);
        etLop.setText(lop);
        etSdt.setText(sdt);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                arrayDsNguoimuon= db.getDataNM();
                Log.d("TAG", "showsss: "+arrayDsNguoimuon);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayDsNguoimuon.clear();
                arrayDsNguoimuon = db.TimkiemNguoiMuon(arrayDsNguoimuon,s);
                nguoiMuonAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void getViews() {
        toolbar=findViewById(R.id.toolbar);
        floatingActionButton=findViewById(R.id.add_Nguoi_Muon);
        lv = findViewById(R.id.lvNguoiMuon);
    }
}
