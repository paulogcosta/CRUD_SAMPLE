package com.crudsample.slunngdev.crud_sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class userRegisterDAO {

    private databaseConnection conn;
    private SQLiteDatabase db;

    public userRegisterDAO(Context context){
        conn = new databaseConnection(context);
        db = conn.getWritableDatabase();
    }

    public List<userRegister> getAllReg(){
        List<userRegister> register = new ArrayList<>();
        Cursor cursor = db.query("regist",new String[]{"id","nome","numero","status"},null,null,null,null,null);
        while(cursor.moveToNext()){
            userRegister reg = new userRegister();
            reg.setId(cursor.getInt(0));
            reg.setNome(cursor.getString(1));
            reg.setNumero(cursor.getString(2));
            boolean value = cursor.getInt(3) > 0;
            reg.setStatus(value);
            register.add(reg);

        }
        return register;
    }
    public long insert(userRegister register){
        ContentValues values = new ContentValues();
        values.put("nome",register.getNome());
        values.put("numero",register.getNumero());
        values.put("status",register.getStatus());
        return db.insert("regist",null,values);

    }

    public void delete(userRegister register){
        db.delete("regist","id=?",new String[]{register.getId().toString()});

    }
}
