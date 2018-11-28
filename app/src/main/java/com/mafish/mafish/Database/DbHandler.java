package com.mafish.mafish.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mafish.mafish.model.Fish;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MafishDB"; // NAMA DATABASE
    private static final String TABLE_FISH = "FISH"; // NAMA TABEL
    private static final String COLUMN_ID = "id"; // NAMA KOLOM ID
    private static final String COLUMN_FISH_NAME = "fish_name"; // NAMA KOLOM NAMA
    private static final String COLUMN_PRICE = "price"; // NAMA KOLOM TEMPAT LAHIR
    private static final String COLUMN_DESC = "description"; // NAMA KOLOM TEMPAT LAHIR
    private static final String COLUMN_IMAGE = "image"; // NAMA KOLOM TEMPAT LAHIR

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // FUNGSI UNTUK MEMBUAT DATABASENYA
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_FISH + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_FISH_NAME + " TEXT,"
                + COLUMN_PRICE + " TEXT," + COLUMN_DESC + " TEXT," +  COLUMN_IMAGE + " BLOB);";
        db.execSQL(CREATE_USER_TABLE);
    }

    // FUNGSI UNTUK MENGECEK DATABASE ADA ATAU TIDAK.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FISH);
        onCreate(db);
    }

    // FUNGSI UNTUK TAMBAH DATA MAHASISWA
    public void tambahFish(Fish fish){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FISH_NAME, fish.getFish_name());
        values.put(COLUMN_PRICE, fish.getPrice());
        values.put(COLUMN_DESC, fish.getDescription());
        values.put(COLUMN_IMAGE, fish.getImage());

        db.insert(TABLE_FISH, null, values);
        db.close();
    }

    // FUNGSI UNTUK AMBIL 1 DATA MAHASISWA
    public Fish getFish(int id_fish){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FISH, new String[]{COLUMN_ID, COLUMN_FISH_NAME, COLUMN_PRICE, COLUMN_DESC, COLUMN_IMAGE},
                COLUMN_ID + "=?", new String[]{String.valueOf(id_fish)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Fish fish = new Fish(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4));
        return fish;
    }

    // FUNGSI UNTUK AMBIL SEMUA DATA MAHASISWA
    public List<Fish> getAllFish(){
        List<Fish> fishList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_FISH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Fish fish = new Fish(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4));
                fishList.add(fish);
            } while (cursor.moveToNext());
        }
        return fishList;
    }

    // FUNGSI MENGHITUNG ADA BEBERAPA DATA
    public int getFishCount(){
        String countQuery = "SELECT * FROM " + TABLE_FISH;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    // FUNGSI UPDATE DATA MAHASISWA
    public int updateDataFish(Fish fish) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FISH_NAME, fish.getFish_name());
        values.put(COLUMN_PRICE, fish.getPrice());
        values.put(COLUMN_DESC, fish.getDescription());
        values.put(COLUMN_IMAGE, fish.getImage());
        return db.update(TABLE_FISH, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(fish.getId())});
    }

    // FUNGSI HAPUS DATA 1 MAHASISWA
    public void hapusDataFish(Fish fish) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FISH, COLUMN_ID + " = ?",
                new String[]{String.valueOf(fish.getId())});
        db.close();
    }

    // FUNGSI UNTUK MENGHAPUS SEMUA DATA MAHASISWA
    public void hapusSemuaDataFish(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_FISH);
    }
}
