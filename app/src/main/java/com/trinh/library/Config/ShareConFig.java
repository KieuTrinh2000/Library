package com.trinh.library.Config;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareConFig {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public ShareConFig(Context context){
        sharedPreferences=context.getSharedPreferences("INFO",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public  void PutTaiKhoan(String tk){
        editor.putString("TK",tk);
        editor.commit();
    }
    public  String getTK(){
        String tk = sharedPreferences.getString("TK","");
        return  tk;
    }
}
