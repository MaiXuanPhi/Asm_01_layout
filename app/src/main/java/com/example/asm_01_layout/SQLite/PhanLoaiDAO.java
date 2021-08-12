package com.example.asm_01_layout.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asm_01_layout.model.PhanLoai;

import java.util.ArrayList;

public class PhanLoaiDAO {

    SQLiteDatabase db;

    public PhanLoaiDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<PhanLoai> xemPL(String trangthai){
        ArrayList<PhanLoai> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM PHANLOAI WHERE Trangthai=?",new String[]{trangthai});
        if (c.moveToFirst()){
            do {
                int maloai = c.getInt(0);
                String tenloai = c.getString(1);
                String trangthai1 = c.getString(2);
                PhanLoai phanLoai1 = new PhanLoai(maloai, tenloai, trangthai1);
                list.add(phanLoai1);
            }while(c.moveToNext());
        }
        return list;
    }

    public void themPL(PhanLoai phanLoai){
        ContentValues values = new ContentValues();
        values.put("Tenloai", phanLoai.getTenLoai());
        values.put("Trangthai", phanLoai.getTrangThai());
        db.insert("PHANLOAI",null, values);
    }

    public void suaPL (PhanLoai phanLoai){
        ContentValues values = new ContentValues();
        values.put("Tenloai", phanLoai.getTenLoai());
        db.update("PHANLOAI",values, "Maloai=?",new String[]{phanLoai.getMaLoai()+""});
    }

    public void xoaPL(PhanLoai phanLoai){
        db.delete("PHANLOAI","Maloai=?",new String[]{phanLoai.getMaLoai()+""});
    }

    public ArrayList<String> xemPL2(String trangthai){
        ArrayList<String> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM PHANLOAI WHERE Trangthai=?",new String[]{trangthai});
        if (c.moveToFirst()){
            do {
                String tenloai = c.getString(1);
                list.add(tenloai);
            }while(c.moveToNext());
        }
        return list;
    }

    public int xemPL3(String tenloai){
        int maloai = 0;
        Cursor c = db.rawQuery("SELECT * FROM PHANLOAI WHERE Tenloai=?",new String[]{tenloai});
        if (c.moveToFirst()){
            do {
                maloai = c.getInt(0);
            }while(c.moveToNext());
        }
        Log.e("maloai",maloai+"");
        return maloai;
    }

    public String xemPL4(int maloai){
        String tenloai = "";
        Cursor c = db.rawQuery("SELECT * FROM PHANLOAI WHERE Maloai=?",new String[]{maloai+""});
        if (c.moveToFirst()){
            do {
                tenloai = c.getString(1);
            }while(c.moveToNext());
        }
        Log.e("maloai",maloai+"");
        return tenloai;
    }
}
