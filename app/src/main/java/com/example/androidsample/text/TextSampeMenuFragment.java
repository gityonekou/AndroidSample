/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 2.TextViewとEditTextのサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 2.TextView
 * 3.EditText
 *
 */
package com.example.androidsample.text;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.androidsample.AbstractMenuListFragment;
import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.R;

/**
 * トップメニューにて以下選択時に対応するフラグメントです
 * 「2. TextViewとEditText)」
 *
 * メニューリストの表示項目設定と項目選択時のリスナーを実装します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.トップメニュー、サブメニューをフラグメントに変更
 *    AbstractMenuList継承にてTextSampeMenuをフラグメントに変更
 * 2.javadoc追加対応
 *
 */
public class TextSampeMenuFragment extends AbstractMenuListFragment {

    private String title;

    /**
     * このフラグメントのインスタンスを生成して返します
     *
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static TextSampeMenuFragment newInstance(String title) {
        TextSampeMenuFragment fragment = new TextSampeMenuFragment();
        // パラメータ設定
        fragment.title = title;
        return fragment;
    }

    @Override
    protected String getTitleMessage() {
        return title;
    }

    @Override
    protected int getTextArrayResId() {
        return R.array.text_menu;
    }

    /* メニュー選択時の動作：対象のサンプルを呼びだし */
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // TextViewで文字を表示
                intent = new Intent(getContext(), TextViewSampe0101.class);
                break;
            case 1:
                // レイアウトファイルを使わないでコードでTextViewを設定(LinearLayout版)
                intent = new Intent(getContext(), TextViewSampe0201.class);
                break;
            case 2:
                // レイアウトファイルを使わないでコードでTextViewを設定(ConstraintLayout版)
                intent = new Intent(getContext(), TextViewSampe0301.class);
                break;
            case 3:
                // Text Selectionの実装(テキストのコピペ)
                intent = new Intent(getContext(), TextViewSampe0401.class);
                break;
            case 4:
                // EditTextを使って文字を入力する(レイアウトを使う)
                intent = new Intent(getContext(), EditTextSampe0101.class);
                break;
            case 5:
                // EditTextを使って文字を入力する(コードですべて書く)
                intent = new Intent(getContext(), EditTextSampe0201.class);
                break;
            case 6:
                // EditTextの文字入力制限と表示制限
                intent = new Intent(getContext(), EditTextSampe0301.class);
                break;
            case 7:
                // TextWatcherで入力を監視する
                intent = new Intent(getContext(), EditTextSampe0401.class);
                break;
            default:
                intent = new Intent(getContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);
    }
}
