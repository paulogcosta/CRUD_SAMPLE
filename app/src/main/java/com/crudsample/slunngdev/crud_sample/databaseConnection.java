package com.crudsample.slunngdev.crud_sample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseConnection extends SQLiteOpenHelper {

    private static final String name = "database.db";
    private static final int version = 1;
    public databaseConnection(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table regist(id integer primary key autoincrement," +
                "nome varchar(50),numero varchar(50),status bit)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
