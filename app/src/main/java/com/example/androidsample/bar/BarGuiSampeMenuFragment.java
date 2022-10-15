/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 4.各種進捗バーのサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 5.ProgressBar
 * 6.SeekBar
 *
 */
package com.example.androidsample.bar;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.androidsample.AbstractMenuListFragment;
import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.R;
/**
 * トップメニューにて以下選択時に対応するフラグメントです
 * 「4. 進捗バー(ProgressBar, SeekBar)」
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
 * ver2.01 更新
 * ・タイトルとメニューリストのリソースID設定メソッドの名前変更
 *
 */
public class BarGuiSampeMenuFragment extends AbstractMenuListFragment {

    private String title;

    /**
     * このフラグメントのインスタンスを生成して返します。
     *
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static BarGuiSampeMenuFragment newInstance(String title) {
        BarGuiSampeMenuFragment fragment = new BarGuiSampeMenuFragment();
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
        return R.array.bar_gui_menu;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // ProgressBarで進捗状況を表示する(表示のみ)
                intent = new Intent(getContext(), ProgressBarSampe0101.class);
                break;
            case 1:
                // ProgressBarの表示/非表示と進捗状況の表示
                intent = new Intent(getContext(), ProgressBarSampe0102.class);
                break;
            case 2:
                // SeekBarでボリューム入力
                intent = new Intent(getContext(), SeekBarSampe0101.class);
                break;
            case 3:
                // [SeekBar]SeekBarのツマミ(thumb)とprogress(bar)のカスタマイズのレイアウト版
                intent = new Intent(getContext(), SeekBarSampe0201.class);
                break;
            case 4:
                // [SeekBar]SeekBarのツマミ(thumb)とprogress(bar)のカスタマイズのコード版
                intent = new Intent(getContext(), SeekBarSampe0202.class);
                break;
            default:
                intent = new Intent(getContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);
    }
}
