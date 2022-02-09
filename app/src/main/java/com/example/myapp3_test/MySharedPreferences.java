package com.example.myapp3_test;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

public class MySharedPreferences {
    private static MySharedPreferences mySharedPreferences = new MySharedPreferences();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static MySharedPreferences getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(Config.key, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return mySharedPreferences;
    }

    public void setCheckboxChecked(boolean check) {
        editor.putBoolean("chekbox", check);
        editor.commit();
    }

    public boolean getCheckboxChecked() {
        return sharedPreferences.getBoolean("chekbox", false);
    }

    public void setRekord(int rekord){
        editor.putInt("rekord",rekord);
        editor.commit();
    }

    public int getRekord(){
        return sharedPreferences.getInt("rekord",0);
    }

}
