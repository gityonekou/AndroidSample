package com.example.androidsample.datastorage;

import android.provider.BaseColumns;

/**
 * SQLiteSample0101で使用するDBテーブルの定義クラスです。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public final class SQLiteSample0101TableContentsContract implements BaseColumns {
    /** テーブル名 **/
    // テーブル名の注意:テーブル名に[_や-]の文字列を入れることはできないので注意
    public static final String TABLE_NAME = "sample0101";
    /** カラム:会社 */
    public static final String COLUMN_NAME_COMPANY = "company";
    /** カラム:株価 */
    public static final String COLUMN_NAME_STOCKPRICE = "stockprice";
}
