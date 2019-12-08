package com.example.testnechetainsoft.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteConnector extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private Cursor cursor;
    private Context context;

    public SQLiteConnector(@Nullable Context context) {
        super(context, "MyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table Shop (_id integer primary key autoincrement,"
                    + "nameShop TEXT,"
                    + "descriptions TEXT,"
                    + "image TEXT)");

            db.execSQL("create table Sklad (_id integer primary key autoincrement,"
                    + "nameShop TEXT,"
                    + "descriptions TEXT,"
                    + "image TEXT)");

            db.execSQL("create table Product (_id integer primary key autoincrement,"
                    + "nameShop TEXT,"
                    + "descriptions TEXT,"
                    + "image TEXT)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1) {
            db.execSQL("DROP TABLE IF EXISTS Shop");
            db.execSQL("DROP TABLE IF EXISTS Sklad");
            db.execSQL("DROP TABLE IF EXISTS Product");
        }
    }

    public void insertNameShop(String nameShop, String descriptions, String image) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameShop", nameShop);
        values.put("descriptions", descriptions);
        values.put("image", image);

        db.insert("Shop", null, values);
        db.close();
    }

    public void insertNameSklad(String nameShop, String descriptions, String image) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameShop", nameShop);
        values.put("descriptions", descriptions);
        values.put("image", image);

        db.insert("Sklad", null, values);
        db.close();
    }

    public void insertNameProduct(String nameShop, String descriptions, String image) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameShop", nameShop);
        values.put("descriptions", descriptions);
        values.put("image", image);

        db.insert("Product", null, values);
        db.close();
    }

    public void changeNameShop (String nameShop, String oldName, String descriptions, String image) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameShop", nameShop);
        values.put("descriptions", descriptions);
        values.put("image", image);

        db.update("Shop", values, "nameShop = ?", new String[]{oldName});
        db.close();
    }

    public void changeNameSklad (String nameShop, String oldName, String descriptions, String image) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameShop", nameShop);
        values.put("descriptions", descriptions);
        values.put("image", image);

        db.update("Sklad", values, "nameShop = ?", new String[]{oldName});
        db.close();
    }

    public void changeNameProduct (String nameShop, String oldName, String descriptions, String image) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameShop", nameShop);
        values.put("descriptions", descriptions);
        values.put("image", image);

        db.update("Product", values, "nameShop = ?", new String[]{oldName});
        db.close();
    }

    public void deletinItemShop(String nameShop) {
        db = this.getWritableDatabase();
        db.delete("Shop", "nameShop = ?", new String[]{nameShop});
        db.close();
    }

    public void deletinItemSklad(String nameShop) {
        db = this.getWritableDatabase();
        db.delete("Sklad", "nameShop = ?", new String[]{nameShop});
        db.close();
    }

    public void deletinItemProduct(String nameShop) {
        db = this.getWritableDatabase();
        db.delete("Product", "nameShop = ?", new String[]{nameShop});
        db.close();
    }
}
