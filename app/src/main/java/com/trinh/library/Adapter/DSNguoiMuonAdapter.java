package com.trinh.library.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trinh.library.Model.DSNguoiMuon;
import com.trinh.library.R;
import com.trinh.library.View.DSNguoiMuonActivity;
import com.trinh.library.View.DSSachActivity;

import java.util.ArrayList;

public class DSNguoiMuonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DSNguoiMuon> listNguoiMuon;

    public DSNguoiMuonAdapter(Context context, ArrayList<DSNguoiMuon> listNguoiMuon) {
        this.context = context;
        this.listNguoiMuon = listNguoiMuon;
    }

    @Override
    public int getCount() {
        return listNguoiMuon.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
    ViewHolder viewHolder;
    if(view==null){
        view= LayoutInflater.from(context).inflate(R.layout.dong_ds_sv,null);
        viewHolder=new ViewHolder();
        viewHolder.txtMa=view.findViewById(R.id.txtMaNM);
        viewHolder.txtTen=view.findViewById(R.id.txtTenNM);
        viewHolder.txtSdt=view.findViewById(R.id.txtSdtNM);
        viewHolder.imgEdit=view.findViewById(R.id.SuaNM);
        viewHolder.imgDel=view.findViewById(R.id.XoaNM);
        view.setTag(viewHolder);
    }else {
        viewHolder= (ViewHolder) view.getTag();
    }
        viewHolder.txtMa.setText(listNguoiMuon.get(i).getMaSv());
        viewHolder.txtTen.setText(listNguoiMuon.get(i).getTenSv());

        viewHolder.txtSdt.setText(listNguoiMuon.get(i).getLop()+"-"+listNguoiMuon.get(i).getSdt());


        viewHolder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DSNguoiMuonActivity.pos=i;
                DSNguoiMuonActivity.countDownTimerXoa.start();
            }
        });
        viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DSNguoiMuonActivity.pos=i;
                DSNguoiMuonActivity.countDownTimer.start();
            }
        });
        return view;

    }

    public class ViewHolder{
        TextView txtMa,txtTen,txtSdt;
        ImageView imgEdit,imgDel;

    }
}
