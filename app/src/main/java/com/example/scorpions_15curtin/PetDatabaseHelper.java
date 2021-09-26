package com.example.scorpions_15curtin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class PetDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "MyPets.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_pets";
    private static final String COLUMN_ID = "pet_id";
    private static final String COLUMN_NAME = "pet_name";
    private static final String COLUMN_TYPE = "pet_type";
    private static final String COLUMN_GENDER = "pet_gender";
    private static final String COLUMN_BREED = "pet_breed";
    private static final String COLUMN_BIRTHDAY = "pet_birthday";
    private static final String COLUMN_COLOR = "pet_color";
    private static final String COLUMN_DES = "pet_description";


     PetDatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_BREED + " TEXT, " +
                COLUMN_BIRTHDAY + " TEXT, " +
                COLUMN_COLOR + " TEXT, " +
                COLUMN_DES + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)  {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    void addPet(String name, String type, String gender, String breed, String birthday, String color, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_BREED, breed);
        cv.put(COLUMN_BIRTHDAY, birthday);
        cv.put(COLUMN_COLOR, color);
        cv.put(COLUMN_DES, description);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed to Add", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id, String name, String type, String gender, String breed, String birthday, String color, String description){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(COLUMN_NAME, name);
         cv.put(COLUMN_TYPE, type);
         cv.put(COLUMN_GENDER, gender);
         cv.put(COLUMN_BREED, breed);
         cv.put(COLUMN_BIRTHDAY, birthday);
         cv.put(COLUMN_COLOR, color);
         cv.put(COLUMN_DES, description);

         long result = db.update(TABLE_NAME, cv, "pet_id=?", new String[]{row_id});
         if(result == -1){
             Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
         }else{
             Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
         }
    }
    void deleteOneRow(String row_id){
         SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "pet_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }

    }
    void deleteAllData(){
         SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
