/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 各種ボタンサンプルの動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 1.Button
 *
 */
package com.example.androidsample.button;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.androidsample.AbstractMenuListFragment;
import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.R;
/**
 * トップメニューにて以下選択時に対応するフラグメントです
 * 「1. Button」
 *
 * メニューリストの表示項目設定と項目選択時のリスナーを実装します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.トップメニュー、サブメニューをフラグメントに変更
 *    AbstractMenuList継承にてButtonSampeMenuをフラグメントに変更
 * 2.javadoc追加対応
 * ver2.01 更新
 * １．タイトルとメニューリストのリソースID設定メソッドの名前変更
 * ２．ToggleButtonサンプル01の追加
 * ３．Switchサンプル01、02、・・の追加
 *
 */
public class ButtonSampeMenuFragment extends AbstractMenuListFragment {

    private String title;

    /**
     * このフラグメントのインスタンスを生成して返します。
     *
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static ButtonSampeMenuFragment newInstance(String title) {
        ButtonSampeMenuFragment fragment = new ButtonSampeMenuFragment();
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
        return R.array.button_menu;
    }

    /* メニュー選択時の動作：対象のサンプルを呼びだし */
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // 簡単な Button アプリを作る
                intent = new Intent(getContext(), ButtonSampe0101.class);
                break;
            case 1:
                // レイアウトをJavaコードだけで設定する(LinearLayout ver1)
                intent = new Intent(getContext(), ButtonSampe0201.class);
                break;
            case 2:
                // レイアウトをJavaコードだけで設定する(LinearLayout ver2)
                intent = new Intent(getContext(), ButtonSampe0202.class);
                break;
            case 3:
                // レイアウトをJavaコードだけで設定する(RelativeLayout ver)
                intent = new Intent(getContext(), ButtonSampe0203.class);
                break;
            case 4:
                // shape(シェイプ)を使ってカスタムボタンを作る(デベロッパーサンプルを試す)
                intent = new Intent(getContext(), ButtonSampe0301.class);
                break;
            case 5:
                // shape(シェイプ)を使ってカスタムボタンを作る
                intent = new Intent(getContext(), ButtonSampe0302.class);
                break;
            case 6:
                // ImageButton に画像を設定する(レイアウトを使用)
                intent = new Intent(getContext(), ButtonSampe0401.class);
                break;
            case 7:
                // ImageButton に画像を設定する(Javaコードで設定(android:src))
                intent = new Intent(getContext(), ButtonSampe0402.class);
                intent.putExtra(ButtonSampe0402.TYPE, ButtonSampe0402.TYPE_SRC);
                break;
            case 8:
                // ImageButton に画像を設定する(Javaコードで設定(android:background))
                intent = new Intent(getContext(), ButtonSampe0402.class);
                intent.putExtra(ButtonSampe0402.TYPE, ButtonSampe0402.TYPE_BACKGROUND);
                break;
            case 9:
                // onClickListenerの色々な設定
                intent = new Intent(getContext(), ButtonSampe0501.class);
                break;
            case 10:
                // Button 配列を設定する(ViewにTagでマーキングする)
                intent = new Intent(getContext(), ButtonSampe0601.class);
                break;
            case 11:
                // ToggleButtonを使ってON・OFFを設定する
                intent = new Intent(getContext(), ToggleButtonSample0101.class);
                break;
            default:
                intent = new Intent(getContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);
    }
}
