package com.trinh.library.Adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trinh.library.Model.SachModel;
import com.trinh.library.R;
import com.trinh.library.View.DSSachActivity;

import java.util.ArrayList;

public class DsSachAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SachModel> listSach;

    public DsSachAdapter(Context context, ArrayList<SachModel> listSach) {
        this.context = context;
        this.listSach = listSach;
    }

    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_ds_sach,null);
            viewHolder=new ViewHolder();
            viewHolder.txtMa=convertView.findViewById(R.id.txtMaS);
            viewHolder.txtTen=convertView.findViewById(R.id.txtTenS);
            viewHolder.imgEdit=convertView.findViewById(R.id.imgEdit);
            viewHolder.imgDel=convertView.findViewById(R.id.imgDel);
            convertView.setTag(viewHolder);

        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.txtTen.setText(listSach.get(position).getTenS());
        viewHolder.txtMa.setText(listSach.get(position).getMaS());
        viewHolder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DSSachActivity.pos=position;
                DSSachActivity.countDownTimerXoa.start();
            }
        });
        viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DSSachActivity.pos=position;
                DSSachActivity.countDownTimer.start();
            }
        });

        return convertView;
    }

    public class ViewHolder{
        TextView txtMa,txtTen;
        ImageView imgEdit,imgDel;



    }

}
