/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 5.通知(トースト, Snackbar)のサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 7.Toast
 * 8.Snackbar
 *
 */
package com.example.androidsample.notify;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.androidsample.AbstractMenuListFragment;
import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.R;

/**
 * トップメニューにて以下選択時に対応するフラグメントです
 * 「5. 通知(トースト, スナックバー)」
 *
 * メニューリストの表示項目設定と項目選択時のリスナーを実装します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.トップメニュー、サブメニューをフラグメントに変更
 *    AbstractMenuList継承にてNotifySampeMenuをフラグメントに変更
 * 2.javadoc追加対応
 * ver2.01 更新
 * ・タイトルとメニューリストのリソースID設定メソッドの名前変更
 *
 */
public class NotifySampeMenuFragment extends AbstractMenuListFragment {

    private String title;

    /**
     * このフラグメントのインスタンスを生成して返します
     *
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static NotifySampeMenuFragment newInstance(String title) {
        NotifySampeMenuFragment flagment = new NotifySampeMenuFragment();
        // パラメータを設定
        flagment.title = title;
        return flagment;
    }

    @Override
    protected String createTitleMessage() {
        return title;
    }

    @Override
    protected int createTextArrayResId() {
        return R.array.notify_menu;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // Toast(トースト)の表示
                intent = new Intent(getContext(), ToastSampe0101.class);
                break;
            case 1:
                // Snackbar(スナックバー)を表示する
                intent = new Intent(getContext(), SnackbarSampe0101.class);
                break;
            case 2:
                // Snackbar(スナックバー)で簡単なアクションを実装する
                intent = new Intent(getContext(), SnackbarSampe0102.class);
                break;
            default:
                intent = new Intent(getContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);

    }
}
