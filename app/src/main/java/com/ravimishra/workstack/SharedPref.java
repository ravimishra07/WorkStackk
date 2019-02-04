package com.ravimishra.workstack;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private Context context;
public static   final String PREFS_NAME="stan";
    public SharedPref(Context context) {
        this.context = context;
    }
    public void saveInt(Context context, String key, int value) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();

    }
    public  int getInt(String key,Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(key, 0);
    }
}
