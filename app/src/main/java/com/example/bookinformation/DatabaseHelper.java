package com.example.bookinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "UserInfo.db";
    private static final int DATABASE_VERSION = 1;

    // Define table and column names
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_REVIEW = "review";
//    private static final String TABLE_BOOKINFORMATION = "bookinformation";


    // Define the SQL create table statement
//    private static final String TABLE_CREATE =
//            "CREATE TABLE " + TABLE_USER + " (" +
//                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    COLUMN_USERNAME + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USER +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_REVIEW + " TEXT);";
        db.execSQL(createTable);
//        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
    public void insertData(String author, String title, String review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_AUTHOR, author);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_REVIEW, review);
        long result = db.insert(TABLE_USER, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        }

//        db.insert(TABLE_BOOKINFORMATION, null, values2);
//        db.close();
    }
//    public Cursor getAllBooks(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        return db.query(TABLE_BOOKINFORMATION, null, null, null, null, null, null);
//    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
