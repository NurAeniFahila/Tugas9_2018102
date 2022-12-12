package com.example.a2018102_tugas5_recyclerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
class DBmain extends SQLiteOpenHelper {
    public static final String DBDEVISI="devisi.db";
    public static final String TABLEDEVISI="devisi";
    public static final int VER=1;
    public DBmain(@Nullable Context context) {
        super(context, DBDEVISI, null, VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+TABLEDEVISI+"(id integer primary key, devisi TEXT, star TEXT, joine TEXT, avatar blob)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists "+TABLEDEVISI+"";
        db.execSQL(query);
        onCreate(db);
    }
}
