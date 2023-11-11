package com.techja.fodenew.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static final String MY_SHARED_PREFERENCES = "MY_SHARED_PREFERENCES";
    private Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
    }
    public void putBooleanValue(String key,boolean value){
        SharedPreferences sharedPreferences=context.getSharedPreferences(MY_SHARED_PREFERENCES,0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(key, value);//put key và value truyền vào,nhớ phải đúng tên
        editor.apply();
    }
    public Boolean getBooleanValue(String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(MY_SHARED_PREFERENCES,0);
        return sharedPreferences.getBoolean(key,false);//mặc định fale,chạy vào nhánh fale đầu tiên
        //sau khi put true sẽ vào nhánh thứ 2
    }
    //tạo phương thức để put token vào
    public void PutToken(String key,String token){
        SharedPreferences sharedPreferences=context.getSharedPreferences("keytoken",0);
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key, token);
        editor.apply();

    }
    //pt get token ra.chú ý truyền vào và get ra các tham số phải giống nhau
    public String getToken(String key, String s){
        SharedPreferences sharedPreferences=context.getSharedPreferences("keytoken",0);
        return sharedPreferences.getString(key,"");
    }
    //put data api
    public void PutDataApi(String key,String dataApi){
        SharedPreferences sharedPreferences=context.getSharedPreferences("keydataapi",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key, dataApi);
        editor.apply();
    }
    public String getStringDataApi(String key,String dataapi){
        SharedPreferences sharedPreferences=context.getSharedPreferences("keydataapi",0);
        return sharedPreferences.getString(key, dataapi);
    }
}
