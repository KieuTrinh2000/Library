package com.trinh.library.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trinh.library.DatabaseHepler.DatabaseHelper;
import com.trinh.library.Adapter.DsSachAdapter;
import com.trinh.library.Model.SachModel;
import com.trinh.library.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class DSSachActivity extends AppCompatActivity {
  Toolbar toolbar;
  DatabaseHelper db;
  ArrayList<SachModel> list;
  DsSachAdapter adapter;
  ListView lv;
  FloatingActionButton floatingActionButton;
  public static CountDownTimer countDownTimer,countDownTimerXoa;
  public  static  int pos=0; //vi tri cua phan tu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_sach);
        getViews();
        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh sách sách");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Spinner spinner =findViewById(R.id.btnsapxep);
        String[] s = {"Chon","Tang Dan","Giam Dan","Tro ve"};
        ArrayAdapter arrayAdapter =new ArrayAdapter(this, android.R.layout.simple_list_item_1,s);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    switch (position){
                        case  1 :
                            list=db.getDataListSach();
                            Comparator<SachModel> comparator = new Comparator<SachModel>() {
                                @Override
                                public int compare(SachModel o1, SachModel o2) {
                                    return o1.getSl() - o2.getSl();
                                }
                            };


                adapter=new DsSachAdapter(DSSachActivity.this,list);
                lv.setAdapter(adapter);
                Collections.sort(list,comparator);break;
                        case  2 :
                            list=db.getDataListSach();
                            Comparator<SachModel> comparator1 = new Comparator<SachModel>() {
                                @Override
                                public int compare(SachModel o1, SachModel o2) {
                                    return o2.getSl() - o1.getSl();
                                }
                            };
                            adapter=new DsSachAdapter(DSSachActivity.this,list);
                            lv.setAdapter(adapter);
                            Collections.sort(list,comparator1);break;
                        case  3:
                            list=db.getDataListSach();
                            adapter=new DsSachAdapter(DSSachActivity.this,list);
                            lv.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        db=new DatabaseHelper(this);
        db.CreateTableDsSach();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DSSachActivity.this, ThemSachActivity.class);
                startActivity(intent);
            }
        });
        // cách sử dugj listview : b1 : tao class
         //b2: tao class adapter
        //b3 : khoi tao va do du lieu ra lv
        db=new DatabaseHelper(DSSachActivity.this);
        list=db.getDataListSach();
//        Comparator<SachModel> comparator = new Comparator<SachModel>() {
//            @Override
//            public int compare(SachModel o1, SachModel o2) {
//                return o1.getSl() - o2.getSl();
//            }
//        };
//        Collections.sort(list,comparator);
        adapter=new DsSachAdapter(DSSachActivity.this,list);
        lv.setAdapter(adapter);
//        findViewById(R.id.btnsapxep).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                list=db.getDataListSach();
//                Comparator<SachModel> comparator = new Comparator<SachModel>() {
//                    @Override
//                    public int compare(SachModel o1, SachModel o2) {
//                        return o1.getSl() - o2.getSl();
//                    }
//                };
//                adapter=new DsSachAdapter(DSSachActivity.this,list);
//                lv.setAdapter(adapter);
//                Collections.sort(list,comparator);
//
//            }
//        });
//        //Chi tiết sách
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                AlertDialog.Builder builder=new AlertDialog.Builder(DSSachActivity.this);
//                builder.setTitle("Chi tiết sách");
//                builder.setIcon(R.drawable.book);
//                builder.setMessage(list.get(position).toString());
//                builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        AlertDialog alertDialog=builder.create();
//                        alertDialog.cancel();
//                    }
//                });
//                builder.show();
//            }
//        });

        //hien thong tin sach
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogChiTiet(list.get(i).getMaS(),list.get(i).getTenS(),list.get(i).getTenTG(),list.get(i).getViTri(),
                        list.get(i).getSl(),list.get(i).getNamxb(),list.get(i).getTinhtrang());
            }
        });

        // khi ng dung ấn vào 2 button xoa + sua
        countDownTimer=new CountDownTimer(10,10) {
            @Override
            public void onTick(long millisUntilFinished) {
                
            }

            @Override
            public void onFinish() {
                DiaLogEdit(list.get(pos).getMaS(),
                         list.get(pos).getTenS(),list.get(pos).getTenTG(),
                         list.get(pos).getViTri(),list.get(pos).getSl(),list.get(pos).getNamxb(),
                         list.get(pos).getTinhtrang());
            }
        };

        //Xóa
        countDownTimerXoa=new CountDownTimer(10,10) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
              AlertDialog.Builder builder =new AlertDialog.Builder(DSSachActivity.this);
              builder.setMessage("Bạn có chắc muốn xoá không !");
               AlertDialog alertDialog=builder.create();// ko cho phép ấn ngoài màn hinbhf để tắt màn hình dialog
                alertDialog.setCanceledOnTouchOutside(false);
              builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      boolean check =db.DeleteSach(list.get(pos).getMaS());
                      if(check){
                          list.remove(pos);
                          adapter.notifyDataSetChanged();
                          Toast.makeText(DSSachActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                      }else{
                          Toast.makeText(DSSachActivity.this, "Xoa thât bại", Toast.LENGTH_SHORT).show();
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

    //Dialog Edit
    private void DiaLogEdit(String maS, String tenS, String tenTG, String viTri, int sl, String namxb, String tinhtrang) {

        Dialog dialog=new Dialog(DSSachActivity.this);
        dialog.setContentView(R.layout.dialog_update_delete_sach);
        dialog.show();
       dialog.setCanceledOnTouchOutside(false);// ko cho phép ấn ngoài màn hinbhf để tắt màn hình dialog
        EditText etMaSach = dialog. findViewById(R.id.etMasach);
        EditText   etTenTG = dialog.findViewById(R.id.etTenTG);
        EditText  etViTri = dialog.findViewById(R.id.etVitri);
        EditText  etSoLuong = dialog.findViewById(R.id.etSoluong);
        EditText  etTenS =  dialog.findViewById(R.id.ettensach);
        EditText  etNamxb =  dialog.findViewById(R.id.etNamXB);
        EditText  etTinhTrang =dialog. findViewById(R.id.etTinhtrang);
        ImageView close =dialog. findViewById(R.id.close);
        Button btnSua = dialog.findViewById(R.id.btnSua);
        Button  btnHuy = dialog. findViewById(R.id.btnHuy);

        etMaSach.setText(maS);
        etTenS.setText(tenS);
        etTenTG.setText(tenTG);
        etViTri.setText(viTri);
        etSoLuong.setText(sl+"");
        etNamxb.setText(namxb);
        etTinhTrang.setText(tinhtrang);


    //Sửa
    btnSua.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String MaS = etMaSach.getText().toString().trim();
        String TenS = etTenS.getText().toString().trim();
        String TenTG = etTenTG.getText().toString().trim();
        String Vitri = etViTri.getText().toString().trim();
        String SoLuong =etSoLuong.getText().toString().trim();
        String Namxb= etNamxb.getText().toString().trim();
        String TinhTrang= etTinhTrang.getText().toString().trim();
        if(MaS.length()>0 ||TenS.length()>0||TenTG.length()>0||Vitri.length()>0||SoLuong.length()>0||Namxb.length()>0||TinhTrang.length()>0){
            Boolean checkSach = db.CheckSach(MaS);
            boolean check=db.upDateSach(MaS,TenS,TenTG,Vitri,SoLuong,Namxb,TinhTrang);
            if(check==true){
                Toast.makeText(DSSachActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DSSachActivity.this, DSSachActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(DSSachActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(DSSachActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } 
    }
});
    //Hủy edit
btnHuy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        etMaSach.setText("");
        etTenS.setText("");
        etTenTG.setText("");
        etViTri.setText("");
        etSoLuong.setText("");
        etNamxb.setText("");
        etTinhTrang.setText("");
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

    //thông tin sách
    private void DialogChiTiet(String maS, String tenS, String tenTG, String viTri, int sl, String namxb, String tinhtrang){
        Dialog dialog=new Dialog(DSSachActivity.this);
        dialog.setContentView(R.layout.dialog_chi_tiet_sach);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);// ko cho phép ấn ngoài màn hinbhf để tắt màn hình dialog
        TextView etMaSach = dialog. findViewById(R.id.tvMaS2);
        TextView   etTenTG = dialog.findViewById(R.id.tvTacGia2);
        TextView  etViTri = dialog.findViewById(R.id.tvViTri2);
        TextView  etSoLuong = dialog.findViewById(R.id.tvSoLuong2);
        TextView  etTenS =  dialog.findViewById(R.id.tvTenS2);
        TextView  etNamxb =  dialog.findViewById(R.id.tvNamXB2);
        TextView  etTinhTrang =dialog. findViewById(R.id.tvTinhTrang2);
        ImageView closeSach =dialog. findViewById(R.id.closeSach);
        Button btnThoat = dialog.findViewById(R.id.btnThoat);


        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

        etMaSach.setText(maS);
        etTenS.setText(tenS);
        etTenTG.setText(tenTG);
        etViTri.setText(viTri);
        etSoLuong.setText(sl+"");
        etNamxb.setText(namxb);
        etTinhTrang.setText(tinhtrang);

        // đóng thong tin sach
        closeSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

    }


    //Tìm kiếm
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                 list= db.getDataListSach();
                Log.d("TAG", "showsss: "+list);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                list.clear();
                list = db.Timkiemsach(list,s);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void getViews() {
        toolbar=findViewById(R.id.toolbar);
        floatingActionButton=findViewById(R.id.add_Sach);
        lv = findViewById(R.id.lvSach);

    }

    @Override
    public void onBackPressed() {
       Intent intent = new Intent(this, HomeActivity.class);
       startActivity(intent);
    }

}