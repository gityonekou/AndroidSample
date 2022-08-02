package com.example.androidsample.datastorage;
/*
 * アプリ設定要データをSharedPreferencesで保存・参照する
 * 　対象URL：https://akira-watson.com/android/sharedpreferences.html
 *
 * アプリで使うちょっとしたデータを保存したい場合に「SharedPreferences」を使うと簡単にできます。
 * 但し、データが多くなるような場合は データベースを使うことが推奨されています。単純な初期化のための環境変数など、
 * アプリケーションの初期データを保存するための仕組みと考えましょう。
 *
 * [SharedPreferences]
 * このSharedPreferencesは「共有の環境設定」という日本語訳がされています。標準JavaでいうところのPropertiesに位置する
 * 機能かな。。
 * ・共有の環境設定(SharedPreferences)
 * 　　キーと値のペアでプリミティブ データを保存します。
 * ・内部ストレージ(InternalStorage)
 * 　　端末のメモリにプライベート データを保存します。
 * ・外部ストレージ(ExternalStorage)
 * 　　共有外部ストレージにパブリック データを保存します。
 * ・SQLite データベースなど
 * 　　プライベート データベースに構造化データを保存します。
 * ・ネットワーク接続(google driveなどのクラウド)
 * 　　ネットワーク サーバーを使用してウェブにデータを保存します。
 *
 * 後で出てきますが、SharedPreferencesの実体はxmlファイルです。アプリ内のメモリにxmlファイルでの書き込み、
 * 読出しをする訳なので、大量のデータのやり取り、プロセスをまたがったデータ取得は問題が起きる可能性があるようです。
 * 簡単な初期起動が済んだかどうか、true/false 程度のデータを保存するためには手軽です。
 * 補足として、まだ開発段階のようですがDataStoreというのがあります。SharedPreferencesの問題を解消できるようです
 * のでそのうちこちらが主流になるかもしれません。
 *
 * [DataStore:デベロッパーガイドより]
 * 一貫して非同期で、トランザクションとしてデータを保存し、SharedPreferences の欠点の一部を解消します。
 * 対象URL：https://developer.android.com/jetpack/androidx/releases/datastore?hl=ja
 *
 * 【SharedPreferences, Editor, MODE の設定】
 * 書き込みを行う手順は以下となります。
 * 1.SharedPreferences のインスタンス生成
 * SharedPreferences dataStore = getSharedPreferences("DataStore", MODE_PRIVATE); // DataStoreがファイル名
 * 2.edit() を呼び出して SharedPreferences.Editor を取得
 * Editor editor = dataStore.edit();
 * 3.putString() などのメソッドを使用して値を追加
 * editor.putString("input", "abcdefg");　// key="input" value="abcdefg"
 * 4.apply()あるいはcommit() を使用して新しい値を書き込む
 * editor.apply();
 *
 * 書き込み方法にはapply()とcommit()があります。applyは非同期(排他制御なし)でファイル出力、commit()は同期して
 * ファイル出力します。ですので複数の書き込みが別スレッドで起きるような場合がなければapply()でOKです。
 *
 * [SharedPreferences のインスタンス生成時のモード]
 * ・MODE_PRIVATE：このアプリからのみ読み書き可で上書きモード(前の値は消されます)
 * ・MODE_APPEND：上記で追記モードで開きたい場合はこちらを使用(前の値に追加で値を追加)
 * ・MODE_MULTI_PROCESS/こちらはAndroid6.0で非推奨のため使用不可/
 *
 *【サンプルについて】
 * 入力したテキストを保存ボタン押下時キー:"input"で保存し、読込ボタン押下でファイルを読み込んで表示します
 *
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class SharedPreferencesSample0101 extends AppCompatActivity {

    private EditText editText;
    private TextView writeTextView, readTextView;
    private SharedPreferences dataStore;
    private static final String KEY = "input";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferences_sample0101);

        this.dataStore = getSharedPreferences("SharedPreferencesSample0101", MODE_PRIVATE);

        this.editText = findViewById(R.id.sharedpreferences_sample0101_edittext);
        this.writeTextView = findViewById(R.id.sharedpreferences_sample0101_write_textview);
        this.readTextView = findViewById(R.id.sharedpreferences_sample0101_read_textview);

        Button saveBtn = findViewById(R.id.sharedpreferences_sample0101_save_btn);
        saveBtn.setOnClickListener( v -> {
            // テキスト入力の内容を取得出力。出力内容をそのままwriteテキストに出力
            String writeText = this.editText.getText().toString();
            // SharedPreferencesにキー[input]で入力内容を出力
            SharedPreferences.Editor editor = this.dataStore.edit();
            editor.putString(KEY, writeText);
            // commit
            editor.apply();

            // 出力内容をそのままwriteテキストに出力
            StringBuilder writeStr = new StringBuilder("以下の内容を出力しました\n");
            writeStr.append(writeText);
            this.writeTextView.setText(writeStr);

        });
        Button readBtn = findViewById(R.id.sharedpreferences_sample0101_read_btn);
        readBtn.setOnClickListener( v -> {
            String value;
            if((value = this.dataStore.getString(KEY, null)) != null)  {
                this.readTextView.setText(value);
            }
        });
    }
}
