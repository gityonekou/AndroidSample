/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 7. アプリ(Activity, Fragment, Service)のサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 11.Activity
 * 12.Fragment
 * 13.Service
 *
 */
package com.example.androidsample.app;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import com.example.androidsample.AbstractMenuListFragment;
import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.R;

/**
 * トップメニューにて以下選択時に対応するフラグメントです
 * 「7. アプリ(Activity, Fragment, Service)」
 *
 * メニューリストの表示項目設定と項目選択時のリスナーを実装します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.トップメニュー、サブメニューをフラグメントに変更
 *    AbstractMenuList継承にてAppSampeMenuをフラグメントに変更
 * 2.javadoc追加対応
 *
 */
public class AppSampeMenuFragment extends AbstractMenuListFragment {
    private String title;
    /**
     * このフラグメントのインスタンスを生成して返します。
     *
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static AppSampeMenuFragment newInstance(String title) {
        AppSampeMenuFragment fragment = new AppSampeMenuFragment();
        // パラメータを設定
        fragment.title = title;
        return fragment;
    }

    @Override
    protected String getTitleMessage() {
        return title;
    }

    @Override
    protected int getTextArrayResId() {
        return R.array.app_menu;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent;
        switch(position) {
            case 0:
                // [Activity]Activityの画面遷移(main⇔sub)
                intent = new Intent(getContext(), ActivitySampe0101.class);
                break;
            case 1:
                // [Activity]Activity(main⇔sub)間のデータ受け渡し
                intent = new Intent(getContext(), ActivitySampe0201.class);
                break;
            case 2:
                // [Activity]グローバル変数(アプリケーション内の共有データ)を参照する＋mainとsubの
                // ライフサイクル確認
                intent = new Intent(getContext(), ActivitySampe0301.class);
                break;
            case 3:
                // [Activity]Activity Life Cycle と画面回転
                intent = new Intent(getContext(), ActivitySampe0401.class);
                break;
            case 4:
                // [Fragment]Fragmentを「Hello World」から始める
                intent = new Intent(getContext(), FragmentSampe0101.class);
                break;
            case 5:
                // [Fragment]Fragmentタグ部分をコードで動的に設定する
                intent = new Intent(getContext(), FragmentSampe0201.class);
                break;
            case 6:
                // [Fragment]画面遷移(Activity⇔Fragment)
                intent = new Intent(getContext(), FragmentSampe0301.class);
                break;
            case 7:
                // 画面遷移(Fragment⇔Fragment)
                intent = new Intent(getContext(), FragmentSampe0401.class);
                break;
            case 8:
                // 画面遷移(Fragment⇔Fragment)貼り付け部分の通知版
                intent = new Intent(getContext(), FragmentSampe0402.class);
                break;
            case 9:
                // [Service]Serviceの使い方
                intent = new Intent(getContext(), ServiceSampe0101.class);
                break;
            case 10:
                // [Service]WindowManagerを使ってServiceから画像を表示させ続ける
                intent = new Intent(getContext(), ServiceSampe0201.class);
                break;
            case 11:
                // [Service]JobIntentServiceを使った(簡単な)バックグラウンド処理
                intent = new Intent(getContext(), ServiceSampe0301.class);
                break;
            case 12:
                // [Service]JobIntentServiceを使ってバックグラウンドでゲーム効果音を再生
                intent = new Intent(getContext(), ServiceSampe0302.class);
                break;
            default:
                intent = new Intent(getContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }
        startActivity(intent);
    }
}
