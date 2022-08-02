package com.example.androidsample.datastorage;
/*
 * SQLiteSample0101で使用するDBテーブルの定義クラスです。
 *
 */
import android.provider.BaseColumns;

public final class SQLiteSample0101TableContentsContract implements BaseColumns {
    /** テーブル名 **/
    // テーブル名の注意:テーブル名に[_や-]の文字列を入れることはできないので注意
    public static final String TABLE_NAME = "sample0101";
    /** カラム:会社 */
    public static final String COLUMN_NAME_COMPANY = "company";
    /** カラム:株価 */
    public static final String COLUMN_NAME_STOCKPRICE = "stockprice";
}
