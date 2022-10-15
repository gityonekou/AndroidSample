/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 99.その他のサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 25. Async:「非同期処理 Executorの使い方」
 * 26. HttpURLConnection
 *
 */
package com.example.androidsample.other;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.androidsample.AbstractMenuListFragment;
import com.example.androidsample.R;
import com.example.androidsample.common.CallUnderConstructionActivity;

/**
 * トップメニューにて以下選択時に対応するフラグメントです
 * 「99. その他」
 *
 * メニューリストの表示項目設定と項目選択時のリスナーを実装します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.トップメニュー、サブメニューをフラグメントに変更
 *    AbstractMenuList継承にてOtherSampeMenuをフラグメントに変更
 * 2.javadoc追加対応
 * ver2.01 更新
 * ・タイトルとメニューリストのリソースID設定メソッドの名前変更
 *
 */
public class OtherSampeMenuFragment extends AbstractMenuListFragment {

    private String title;

    /**
     * このフラグメントのインスタンスを生成して返します
     *
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static OtherSampeMenuFragment newInstance(String title) {
        OtherSampeMenuFragment fragment = new OtherSampeMenuFragment();
        // パラメータを設定
        fragment.title = title;
        return fragment;
    }

    @Override
    protected String onCreateTitleMessage() {
        return title;
    }

    @Override
    protected int onCreateTextArrayResId() {
        return R.array.other_menu;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // 1.非同期処理 Executorの使い方
                intent = new Intent(getContext(), ExecutorSample0101.class);
                break;
            case 1:
                // 2.HTTP POSTのデータ送信をVolleyで実装する
                intent = new Intent(getContext(), HttpPostSample0101.class);
                break;
            case 2:
                // 3.HTTP 非同期で(GETで要求した)画像をダウンロードする
                intent = new Intent(getContext(), HttpGetSample0101.class);
                break;
            default:
                intent = new Intent(getContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }
        startActivity(intent);
    }
}
