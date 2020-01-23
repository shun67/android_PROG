package com.example.jitu_jisu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {
    private final String mTableName = "situation_table";

    public DBHelper(Context context){
    super(context, "situation_table", null, 1);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL("CREATE TABLE situation_table(_id integer primary key autoincrement,name TEXT, time TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
