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
package com.example.androidsample.datastorage;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.androidsample.AbstractMenuListFragment;
import com.example.androidsample.R;
import com.example.androidsample.common.CallUnderConstructionActivity;

/**
 * トップメニューにて以下選択時に対応するフラグメントです
 * 「9. データストレージ(I/O)」
 *
 * メニューリストの表示項目設定と項目選択時のリスナーを実装します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.トップメニュー、サブメニューをフラグメントに変更
 *    AbstractMenuList継承にてDataStorageSampeMenuをフラグメントに変更
 * 2.javadoc追加対応
 * ver2.01 更新
 * ・タイトルとメニューリストのリソースID設定メソッドの名前変更
 *
 */
public class DataStorageSampeMenuFragment extends AbstractMenuListFragment {

    private String title;

    /**
     *  このフラグメントのインスタンスを生成して返します。
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static DataStorageSampeMenuFragment newInstance(String title) {
        DataStorageSampeMenuFragment fragment = new DataStorageSampeMenuFragment();
        // パラメータを設定
        fragment.title = title;
        return fragment;
    }

    @Override
    protected String createTitleMessage() {
        return title;
    }

    @Override
    protected int createTextArrayResId() {
        return R.array.data_storage_menu;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch(position) {
            case 0:
                // 1.ファイルの入出力(アプリ固有の内部ストレージとgetFilesDir)
                intent = new Intent(getContext(), InternalStorageSample0101.class);
                break;
            case 1:
                // 2.ファイルの入出力(アプリ固有の内部ストレージとopenFileInput・Output)
                intent = new Intent(getContext(), InternalStorageSample0102.class);
                break;
            case 2:
                // 3.ファイルの入出力(アプリ固有の外部ストレージ)
                intent = new Intent(getContext(), ExternalStorageSample0101.class);
                break;
            case 3:
                // 4.MediaStore：ファイル出力
                intent = new Intent(getContext(), MediaStoreSample0101.class);
                break;
            case 4:
                // 5.MediaStore：ファイル参照
                intent = new Intent(getContext(), MediaStoreSample0201.class);
                break;
            case 5:
                // 6.SAF(Storage Access Framework)でフォトアプリから画像を取り出す
                intent = new Intent(getContext(), StorageAccessFrameworkSample0101.class);
                break;
            case 6:
                // 7.SAF(Storage Access Framework)でテキストファイルを保存する
                intent = new Intent(getContext(), StorageAccessFrameworkSample0201.class);
                break;
            case 7:
                // 8.SAF(Storage Access Framework)でイメージファイルを保存する
                intent = new Intent(getContext(), StorageAccessFrameworkSample0202.class);
                break;
            case 8:
                // 9.アプリ設定データの保存(SharedPreferences)
                intent = new Intent(getContext(), SharedPreferencesSample0101.class);
                break;
            case 9:
                // 10.SQLite(データベース)の簡単なサンプル
                intent = new Intent(getContext(), SQLiteSample0101.class);
                break;
            default:
                intent = new Intent(getContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }
        startActivity(intent);
    }
}
