package com.example.asm_01_layout.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "mydb", null, 4);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE PHANLOAI(" +
                "Maloai integer primary key autoincrement," +
                "Tenloai text," +
                "Trangthai text)";
        db.execSQL(sql);
        sql = "CREATE TABLE GIAODICH(" +
                "Magd integer primary key autoincrement," +
                "Tieude text," +
                "Ngay text," +
                "Tien integer," +
                "Mota text," +
                "Maloai integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
