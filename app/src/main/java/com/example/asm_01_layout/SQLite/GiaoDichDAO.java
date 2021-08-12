package com.example.asm_01_layout.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asm_01_layout.model.GiaoDich;

import java.util.ArrayList;

public class GiaoDichDAO {
    SQLiteDatabase db;

    public GiaoDichDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<GiaoDich> xemGD(int maloai1){
        ArrayList<GiaoDich> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM GIAODICH WHERE Maloai=?",new String[]{maloai1+""});
        if (c.moveToFirst()){
            do {
                int magd = c.getInt(0);
                String tieude = c.getString(1);
                String ngay = c.getString(2);
                int tien = c.getInt(3);
                String mota = c.getString(4);
                int maloai = c.getInt(5);
                GiaoDich giaoDich = new GiaoDich(magd, tien, maloai, tieude, ngay, mota);
                list.add(giaoDich);
            }while(c.moveToNext());
        }
        return list;
    }

    public void themGD(GiaoDich giaoDich){
        ContentValues values = new ContentValues();
        values.put("Tieude", giaoDich.getTieuDe());
        values.put("Ngay", giaoDich.getNgay());
        values.put("Tien", giaoDich.getTien());
        values.put("Mota", giaoDich.getMoTa());
        values.put("Maloai", giaoDich.getMaLoai());
        db.insert("GIAODICH",null,values);
        Log.e("themgd","run");
    }

    public void suaGD(GiaoDich giaoDich){
        ContentValues values = new ContentValues();
        values.put("Tieude", giaoDich.getTieuDe());
        values.put("Ngay", giaoDich.getNgay());
        values.put("Tien", giaoDich.getTien());
        values.put("Mota", giaoDich.getMoTa());
        values.put("Maloai", giaoDich.getMaLoai());
        db.update("GIAODICH",values,"Magd=?",new String []{giaoDich.getMaGd()+""});
    }

    public void xoaGD(GiaoDich giaoDich){
        db.delete("GIAODICH","Magd=?",new String[]{giaoDich.getMaGd()+""});
    }

    public ArrayList<GiaoDich> xemGD2(String trangthai){
        ArrayList<GiaoDich> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM PHANLOAI a INNER JOIN GIAODICH b ON a.Maloai = b.Maloai WHERE a.Trangthai=?",new String[]{trangthai});
        Log.e("cursor",c+"");
        if (c.moveToFirst()){
            do {
                int magd = c.getInt(3);
                String tieude = c.getString(4);
                String ngay = c.getString(5);
                int tien = c.getInt(6);
                String mota = c.getString(7);
                int maloai = c.getInt(8);
                GiaoDich giaoDich = new GiaoDich(magd, tien, maloai, tieude, ngay, mota);
                list.add(giaoDich);
            }while (c.moveToNext());
        }
        return list;
    }
}
