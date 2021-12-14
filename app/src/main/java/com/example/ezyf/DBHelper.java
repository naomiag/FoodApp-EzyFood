package com.example.ezyf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "ProductData";
    private static final String COL2 = "namaProduk";
    private static final String COL3 = "hargaProduk";
    private static final String COL4 = "quantity";
    private static final String COL5 = "jumlah";

    public DBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                        COL2+ " TEXT, " +
                        COL3+ " TEXT, " +
                        COL4+ " TEXT, " +
                        COL5+ " TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
    }
    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;
        db.execSQL(query);
    }

    public boolean update(String namaProduk1, String hargaProduk1, String quanProduk1, String jumlahProduk1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL2, namaProduk1);
        cv.put(COL3, hargaProduk1);
        cv.put(COL4, quanProduk1);
        cv.put(COL5, jumlahProduk1);

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE namaProduk = ?";
        Cursor aCur = db.rawQuery(query, new String[]{namaProduk1});

        if(aCur.getCount()>0){
            long newRowId = db.update(TABLE_NAME, cv, "namaProduk = ?", new String[]{namaProduk1});
            if(newRowId==-1)
                return false;
            else
                return true;
        }
        else
            return false;

    }

    public boolean insertData(String namaProduk1, String hargaProduk1, String quanProduk1, String jumlahProduk1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, namaProduk1);
        cv.put(COL3, hargaProduk1);
        cv.put(COL4, quanProduk1);
        cv.put(COL5, jumlahProduk1);

        Long newRowId = db.insert(TABLE_NAME, null, cv);
        if(newRowId==-1)
            return false;
        else
            return true;
    }

    public boolean deleteData(String namaProduk1, String quanProduk1){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE namaProduk = ? AND quantity = ?";

        Cursor cursor = db.rawQuery(query, new String[]{namaProduk1, quanProduk1});
        long newRowId = db.delete(TABLE_NAME, "namaProduk = ? AND quantity = ?", new String[]{namaProduk1, quanProduk1});
        return true;
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor aCur = db.rawQuery(query, null);
        return aCur;
    }

    public Cursor check(String namaProduk1){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE namaProduk = ?";

        Cursor aCur = db.rawQuery(query, new String[]{namaProduk1});

        return aCur;
    }

}
