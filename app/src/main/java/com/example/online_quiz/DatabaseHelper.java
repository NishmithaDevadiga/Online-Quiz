package com.example.online_quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database information
    private static final String DATABASE_NAME = "OnlineQuiz.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_USERS = "users";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";


    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Create database table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";

        db.execSQL(createTable);
    }


    // Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(db);
    }


    // =========================================================
    // INSERT NEW USER
    // =========================================================

    public boolean insertUser(String name, String phone,
                              String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);

        db.close();

        return result != -1;
    }


    // =========================================================
    // CHECK LOGIN
    // =========================================================

    public boolean checkLogin(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_EMAIL + " = ? AND " +
                COLUMN_PASSWORD + " = ?";

        Cursor cursor = db.rawQuery(
                query,
                new String[]{email, password}
        );

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }


    // =========================================================
    // CHECK WHETHER EMAIL EXISTS
    // =========================================================

    public boolean checkEmail(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_EMAIL + " = ?";

        Cursor cursor = db.rawQuery(
                query,
                new String[]{email}
        );

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }


    // =========================================================
    // UPDATE PASSWORD
    // =========================================================

    public boolean updatePassword(String email, String newPassword) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PASSWORD, newPassword);

        int result = db.update(
                TABLE_USERS,
                values,
                COLUMN_EMAIL + " = ?",
                new String[]{email}
        );

        db.close();

        return result > 0;
    }
}
