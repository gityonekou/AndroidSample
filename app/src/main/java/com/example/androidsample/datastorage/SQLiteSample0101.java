/*
 * SQLite(データベース)の簡単なサンプル
 * 　対象URL：https://akira-watson.com/android/sqlite.html
 * 　対象URL：https://developer.android.com/training/data-storage/sqlite?hl=ja#java
 *
 * 大量データの読み書き、さらに検索したい場合はデータベースが便利で、AndroidではSQLite、または Roomを使います。
 * ここではSQLiteを使う簡単な例を試してみます。
 * 最初にGoogleはデータベースとして Room を推奨しています。
 * Room は、SQLite全体を対象とする抽象化レイヤを提供して、SQLiteを最大限に活用しつつ、スムーズなデータベースアクセスを
 * 可能にしてくれるそうです。SQLiteが使えないわけではないのですが、色々面倒な操作が簡単になったRoomを使ってくれ
 * とのことです。
 *
 * 【SQLiteOpenHelper】
 * SQLiteOpenHelperはデータベースの作成、データの追加・削除などを行う(管理)するクラスで実際の実装はこれを継承した
 * クラスを作成して行います。
 * パラメータのDBバージョンはテーブルをドロップして新しく作成しなおす必要がある場合にその値を加算します
 * [DBアクセスへの参照]
 * ・SQLiteOpenHelper#getWritableDatabase()：書込用のデータベースへの参照を取得します。
 * ・SQLiteOpenHelper#getReadableDatabase()：読込用のデータベースへの参照を取得します。
 * ・Cursor cursor = SQLiteOpenHelper#query(＊＊＊)：検索：クエリの結果はCursorオブジェクトで返されます
 * ・cursor.close()：検索結果全体に対して反復処理が完了したら、カーソルに対してclose()を呼び出しリソースを解放します。
 * 　　　　　　　　　　　→カーソルのクローズは非常に忘れやすいので注意しましょう。
 * ・SQLiteOpenHelper#insert(＊＊＊)：追加：戻り値は新しく作成された行のID(long値)になります。データの挿入中に
 *  エラーが発生した場合は「-1」を返します。このエラーは、データベース内の既存のデータと競合している場合に発生します。
 * ・SQLiteOpenHelper#delete(＊＊＊)：削除：戻り値は削除された行の数
 * ・SQLiteOpenHelper#update(＊＊＊)：更新：戻り値は、データベース内で影響を受けた行の数
 * ・SQLiteOpenHelper#execSQL(SQL文)：指定のSQL文を実行します：戻り値はvoidなので、テーブル作成・削除など
 * 　上記以外のSQL文を実行するときに使います。
 *
 * 　※注意：
 * DBへのアクセス(検索処理・更新処理)は長時間にわたって実行される可能性があるため、DBへのアクセス処理は
 * バックグラウンド スレッド(非同期処理)で getWritableDatabase() または getReadableDatabase() を呼び出すように
 * 実装する必要があります。
 * 今回のサンプルでは非同期処理は割愛しています。
 *
 * [DBのクローズ]
 * getWritableDatabase()とgetReadableDatabase() は、データベースを閉じているときに呼び出すと高コストになるため、
 * アクセスする必要性がある限り、データベース接続は開いたままにしておくことが重要です。
 * 一般的に、データベースを閉じるのは、呼び出しアクティビティの onDestroy() の際に行うのが最適です。
 *
 * テーブルの作成方法、ドロップ方法、Insert/更新方法は以下サンプルかにゃんホームページ、デベロッパーガイドにて
 * 確認とします。(長くなるので。。
 *
 */
package com.example.androidsample.datastorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * SQLite(データベース)の簡単なサンプル
 * 「10.SQLite(データベース)の簡単なサンプル」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/sqlite.html
 * 　対象URL：https://developer.android.com/training/data-storage/sqlite?hl=ja#java
 *
 *【サンプルについて】
 * 入力した銘柄、株価を保存ボタンでデータベースに登録します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class SQLiteSample0101 extends AppCompatActivity {

    private TextView textView;
    private EditText editCompany, editStockprice;
    private SQLiteSample0101OpenHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_sample0101);

        this.textView = findViewById(R.id.sqlite_sample0101_textview);
        this.editCompany = findViewById(R.id.sqlite_sample0101_edittext_company);
        this.editStockprice = findViewById(R.id.sqlite_sample0101_edittext_stockprice);
        Button registerBtn = findViewById(R.id.sqlite_sample0101_register_btn);
        registerBtn.setOnClickListener( V -> insertData());
        Button readBtn = findViewById(R.id.sqlite_sample0101_read_btn);
        readBtn.setOnClickListener( v -> readData());
    }

    /* 入力データをDBに登録します */
    private void insertData() {
        // 入力値を取得しチェック
        String company = this.editCompany.getText().toString().trim();
        String stockpriceStr = this.editStockprice.getText().toString().trim();
        if(company.equals("") || stockpriceStr.equals("")) {
            // 入力値が不正(指定なし)
            this.textView.setText(R.string.sqlite_sample0101_entry_fail);
            return;
        }
        int stockprice = Integer.parseInt(stockpriceStr);
        if(stockprice <= 0) {
            // 入力値が不正(株価が0以下)
            this.textView.setText(R.string.sqlite_sample0101_entry_fail);
            return;
        }

        // 登録データをDBに登録します
        if(this.helper == null) {
            this.helper = new SQLiteSample0101OpenHelper(getApplicationContext());
        }
        SQLiteDatabase wdb = this.helper.getWritableDatabase();
        ContentValues insertData = new ContentValues();
        insertData.put(SQLiteSample0101TableContentsContract.COLUMN_NAME_COMPANY, company);
        insertData.put(SQLiteSample0101TableContentsContract.COLUMN_NAME_STOCKPRICE, stockprice);
        if(wdb.insert(
                SQLiteSample0101TableContentsContract.TABLE_NAME, null, insertData)
            == -1) {
            // 何らかの理由で追加に失敗
            this.textView.setText(R.string.register_failed);
        } else {
            this.textView.setText(R.string.register_commit);
        }
    }

    /* DBデータを読み込んでテキストViewに表示します */
    private void readData() {
        if(this.helper == null) {
            this.helper = new SQLiteSample0101OpenHelper(getApplicationContext());
        }
        SQLiteDatabase rdb = this.helper.getReadableDatabase();
        Cursor cursor = rdb.query(
                // search teble
                SQLiteSample0101TableContentsContract.TABLE_NAME,
                // search columns
                new String[] {
                        SQLiteSample0101TableContentsContract.COLUMN_NAME_COMPANY,
                        SQLiteSample0101TableContentsContract.COLUMN_NAME_STOCKPRICE},
                // WHERE clause
                null,
                // WHERE clause values
                null,
                // don't group the rows
                null,
                // don't filter by row groups
                null,
                //sort order
                SQLiteSample0101TableContentsContract.COLUMN_NAME_COMPANY +" DESC"
        );
        StringBuilder result = new StringBuilder();
        while(cursor.moveToNext()) {
            result.append(cursor.getString(0));
            result.append(" : ");
            result.append(cursor.getInt(1));
            result.append("\n");
        }
        // 忘れずに。。
        cursor.close();

        // 検索結果を表示
        if(result.length() > 0) {
            this.textView.setText(result);
        } else {
            this.textView.setText(R.string.no_search_data);
        }
    }

    @Override
    protected void onDestroy() {
        if(this.helper != null) {
            // DBのクローズを忘れずに、、
            this.helper.close();
        }
        super.onDestroy();
    }
}
