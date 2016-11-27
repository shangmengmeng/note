package com.anew.note.utils;


import android.content.Context;

/**
 * Created by Administrator on 2016/11/26 0026.
 */

public class DbManager {
    private static MySqliteHelper helper;
    public static MySqliteHelper getInstance(Context context){
        if(helper==null){
            helper  = new MySqliteHelper(context);
        }
        return helper;
    }

}
