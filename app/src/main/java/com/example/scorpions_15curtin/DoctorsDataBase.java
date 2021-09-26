package com.example.scorpions_15curtin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DoctorsDataBase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PetCare.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "doctor_data";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "doctor_name";
    private static final String COLUMN_PHONE = "doctor_phone";
    private static final String COLUMN_WORK = "working_hours";
    private static final String COLUMN_ADDRESS = "doc_address";
    private static final String COLUMN_PROVINCE = "doc_province";
    private static final String COLUMN_COUNTRY = "doc_country";


     DoctorsDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_PHONE + " TEXT, " +
                        COLUMN_WORK + " TEXT, " +
                        COLUMN_ADDRESS + " TEXT, " +
                        COLUMN_PROVINCE + " TEXT, " +
                        COLUMN_COUNTRY + " TEXT); ";

        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addDoctor(String name, String phone, String work, String address, String province, String country ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_WORK, work);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_PROVINCE, province);
        cv.put(COLUMN_COUNTRY, country);

        long result = db.insert(TABLE_NAME,null,cv);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Succesfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void  updateData(String row_id, String name, String phone, String work, String address, String province, String country){

         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();

         cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_WORK, work);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_PROVINCE, province);
        cv.put(COLUMN_COUNTRY, country);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }
    
    void  deleteOneRow(String row_id){
         SQLiteDatabase db = this.getWritableDatabase();
         long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
         if(result == -1){
             Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
         }else {
             Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
         }
    }

    void deleteAllData(){
         SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL(" DELETE FROM " + TABLE_NAME);
    }
}
