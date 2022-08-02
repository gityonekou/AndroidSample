package com.example.androidsample.datastorage;
/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 9.データストレージ(I/O)
 * [にゃんのサンプルとのリンク]
 * 20.Data Storage
 *
 * 【補足説明】
 * ストレージは以下に分類されます
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
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;
import com.example.androidsample.common.CallUnderConstructionActivity;

public class DataStorageSampeMenu extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // メニューリストを設定
        ListView menuList = findViewById(R.id.list_view);
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.data_storage_menu,
                android.R.layout.simple_list_item_1
        ));
        menuList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch(position) {
            case 0:
                // 1.ファイルの入出力(アプリ固有の内部ストレージとgetFilesDir)
                intent = new Intent(getApplicationContext(), InternalStorageSample0101.class);
                break;
            case 1:
                // 2.ファイルの入出力(アプリ固有の内部ストレージとopenFileInput・Output)
                intent = new Intent(getApplicationContext(), InternalStorageSample0102.class);
                break;
            case 2:
                // 3.ファイルの入出力(アプリ固有の外部ストレージ)
                intent = new Intent(getApplicationContext(), ExternalStorageSample0101.class);
                break;
            case 3:
                // 4.MediaStore：ファイル出力
                intent = new Intent(getApplicationContext(), MediaStoreSample0101.class);
                break;
            case 4:
                // 5.MediaStore：ファイル参照
                intent = new Intent(getApplicationContext(), MediaStoreSample0201.class);
                break;
            case 5:
                // 6.SAF(Storage Access Framework)でフォトアプリから画像を取り出す
                intent = new Intent(getApplicationContext(), StorageAccessFrameworkSample0101.class);
                break;
            case 6:
                // 7.SAF(Storage Access Framework)でテキストファイルを保存する
                intent = new Intent(getApplicationContext(), StorageAccessFrameworkSample0201.class);
                break;
            case 7:
                // 8.SAF(Storage Access Framework)でイメージファイルを保存する
                intent = new Intent(getApplicationContext(), StorageAccessFrameworkSample0202.class);
                break;
            case 8:
                // 9.アプリ設定データの保存(SharedPreferences)
                intent = new Intent(getApplicationContext(), SharedPreferencesSample0101.class);
                break;
            case 9:
                // 10.SQLite(データベース)の簡単なサンプル
                intent = new Intent(getApplicationContext(), SQLiteSample0101.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }
        startActivity(intent);
    }
}
