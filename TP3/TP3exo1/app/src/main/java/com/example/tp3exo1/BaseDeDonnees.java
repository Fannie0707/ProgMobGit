package com.example.tp3exo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class BaseDeDonnees extends SQLiteOpenHelper implements Serializable {
    private static final String DATABASE_NAME = "UserDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_PRENOM = "prenom";
    private static final String COLUMN_DATE_NAISSANCE = "date_naissance";
    private static final String COLUMN_TELEPHONE = "telephone";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_INTERETS = "interets";
    private static final String COLUMN_CRENEAU1 = "creneau1";
    private static final String COLUMN_CRENEAU2 = "creneau2";
    private static final String COLUMN_CRENEAU3 = "creneau3";
    private static final String COLUMN_CRENEAU4 = "creneau4";

    public SQLiteDatabase db;

    public BaseDeDonnees(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LOGIN + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_NOM + " TEXT, " +
                COLUMN_PRENOM + " TEXT, " +
                COLUMN_DATE_NAISSANCE + " TEXT, " +
                COLUMN_TELEPHONE + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_INTERETS + " TEXT," +
                COLUMN_CRENEAU1 + " TEXT, " +
                COLUMN_CRENEAU2 + " TEXT, " +
                COLUMN_CRENEAU3 + " TEXT, " +
                COLUMN_CRENEAU4 + " TEXT " + ")";
        this.db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void onDelete(){
        db.execSQL("DROP TABLE IF EXISTS 'users';");
    }

    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }

    public boolean insertUser(String login, String password, String nom, String prenom, String dateNaissance, String telephone, String email, String interets) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGIN, login);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_NOM, nom);
        values.put(COLUMN_PRENOM, prenom);
        values.put(COLUMN_DATE_NAISSANCE, dateNaissance);
        values.put(COLUMN_TELEPHONE, telephone);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_INTERETS, interets);
        values.put(COLUMN_CRENEAU1, "");
        values.put(COLUMN_CRENEAU2, "");
        values.put(COLUMN_CRENEAU3, "");
        values.put(COLUMN_CRENEAU4, "");

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public Cursor getLastUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1", null);
    }

    public Cursor getUserInfo(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_LOGIN + " = ?", new String[]{login});
    }


    public String getUserExistence(String login, String mdp) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_LOGIN + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{login, mdp});

        if (c.moveToFirst()) {
            String foundLogin = c.getString(c.getColumnIndexOrThrow(COLUMN_LOGIN));
            c.close();
            return foundLogin;
        } else {
            c.close();
            return "Error!";
        }
    }


    public boolean getUserExistenceLogin(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_LOGIN + " = ? ", new String[]{login});

        if (c.moveToFirst()) {
            String foundLogin = c.getString(c.getColumnIndexOrThrow(COLUMN_LOGIN));
            c.close();
            return true;
        } else {
            c.close();
            return false;
        }
    }

    public boolean insertPlanning(String login, String creneau1, String creneau2, String creneau3, String creneau4) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CRENEAU1, creneau1);
        values.put(COLUMN_CRENEAU2, creneau2);
        values.put(COLUMN_CRENEAU3, creneau3);
        values.put(COLUMN_CRENEAU4, creneau4);


        int result = db.update(TABLE_NAME, values, COLUMN_LOGIN + " = ?", new String[]{login});
        db.close();


        return result > 0;
    }

}
