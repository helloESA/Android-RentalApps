package com.meproduction.rentalapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rental.db";
    private static final int DATABASE_VERSION = 2;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //membuat table database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT, " +
                "password TEXT," +
                "nama TEXT," +
                "noTelp TEXT)");

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table penyewa (" +
                "nama text," +
                "alamat text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table mobil(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        db.execSQL("create table sewa(" +
                "merk text," +
                "nama text," +
                "promo int," +
                "lama int," +
                "total double," +
                "foreign key(merk) references mobil (merk), " +
                "foreign key(nama) references penyewa (nama) " +
                ");" +
                "");

        db.execSQL("insert into mobil values (" +
                "'Avanza'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Xenia'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Ertiga'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'APV'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Innova'," +
                "500000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Xpander'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Pregio'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Elf'," +
                "700000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Alphard'," +
                "1500000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from mobil";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    //menghapus table apabila table sudah ada
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //menambahkan data atau register
    public boolean insert(String email, String password, String nama, String noTelp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("nama", nama);
        contentValues.put("noTelp", noTelp);
        long ins = db.insert("user", null, contentValues);
        if (ins == 1) return false;
        else return true;
    }

    //validasi email jika sudah ada
    public boolean cekEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=?",new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    //validasi login
    public boolean toLogin(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=? AND password=?",new String[]{email,password});
        if (cursor.getCount()>0) return true;
        else return false;
    }
}
