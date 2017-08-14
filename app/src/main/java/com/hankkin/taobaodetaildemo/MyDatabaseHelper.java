package com.hankkin.taobaodetaildemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ASUS on 2017/8/8.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_INFO = "create table info ("
            + "id integer primary key autoincrement,"
            + "message text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INFO);
        Toast.makeText(mContext,"success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
