package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "siswa.db";
    public static final String TABLE_NAME = "siswa";
    public static final int DATABASE_VERSION = 1;

    // deklarasikan field field tabel siswa
    public static final String COL_1 = "id_siswa";
    public static final String COL_2 = "nama_siswa";
    public static final String COL_3 = "jurusan";
    public static final String COL_4 = "hobi";

    public DataHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table siswa(id_siswa integer primary key autoincrement, "+
                "nama_siswa text not null , jurusan text not null, hobi text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }

    public boolean InsertData(String nama_siswa, String jurusan, String hobi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nama_siswa);
        contentValues.put(COL_3, jurusan);
        contentValues.put(COL_4, hobi);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }

        else {
            return true;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM siswa", null);
        return res;
    }

    public boolean UpdateData(String id_siswa, String nama_siswa, String jurusan, String hobi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id_siswa);
        contentValues.put(COL_2, nama_siswa);
        contentValues.put(COL_3, jurusan);
        contentValues.put(COL_4, hobi);

        db.update(TABLE_NAME, contentValues, "id_siswa = ?", new String[]{id_siswa});
        return true;
    }

    public int DeleteData(String id_siswa)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME, "id_siswa = ?", new String[]{id_siswa});
    }




}
