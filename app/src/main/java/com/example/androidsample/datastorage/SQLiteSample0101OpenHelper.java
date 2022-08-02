package com.example.androidsample.datastorage;
/*
 * SQLiteSample0101で使用するDBアクセスのHelperクラスです。
 *
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteSample0101OpenHelper extends SQLiteOpenHelper {

    // データベースのバージョン
    private static final int DATABASE_VERSION = 1;
    // データーベース定義
    private static final String DATABASE_NAME = "SQLiteSample0101DB.db";

    // テーブルCreate文
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + SQLiteSample0101TableContentsContract.TABLE_NAME
            + " ("
            + SQLiteSample0101TableContentsContract._ID
            + " INTEGER PRIMARY KEY, "
            + SQLiteSample0101TableContentsContract.COLUMN_NAME_COMPANY
            + " TEXT, "
            + SQLiteSample0101TableContentsContract.COLUMN_NAME_STOCKPRICE
            + " INTEGER)";
    // テーブルDelete文
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + SQLiteSample0101TableContentsContract.TABLE_NAME;

    SQLiteSample0101OpenHelper(@Nullable Context context) {
        // param1 access content
        // param2 Databese Name
        // param3 SQLiteDatabase.CursorFactory
        // param4 version
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("debug", "onCreate(SQLiteDatabase db)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // デーブルを削除し、新規に作成します
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
