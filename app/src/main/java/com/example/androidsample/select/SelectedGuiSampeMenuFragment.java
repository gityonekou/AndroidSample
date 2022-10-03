/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 6.GUI：選択GUI(CheckBox, プルダウン, 各種Picker, Alertダイアログ)のサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 9.チェックボックス
 * 10.Spinner：プルダウン
 * 14.Picker
 * 19.AlertDialog
 *
 */
package com.example.androidsample.select;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.androidsample.AbstractMenuListFragment;
import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.R;

/**
 * トップメニューにて以下選択時に対応するフラグメントです
 * 「6. 選択GUI(CheckBox, プルダウン, 各種Picker, Alertダイアログ)」
 *
 * メニューリストの表示項目設定と項目選択時のリスナーを実装します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.トップメニュー、サブメニューをフラグメントに変更
 *    AbstractMenuList継承にてSelectedGuiSampeMenuをフラグメントに変更
 * 2.javadoc追加対応
 *
 */
public class SelectedGuiSampeMenuFragment extends AbstractMenuListFragment {

    private String title;

    /**
     * このフラグメントのインスタンスを生成して返します
     *
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static SelectedGuiSampeMenuFragment newInstance(String title) {
        SelectedGuiSampeMenuFragment fragment = new SelectedGuiSampeMenuFragment();
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
        return R.array.selected_gui_menu;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent;
        
        switch(position) {
            case 0:
                // [CheckBox]CheckBoxの配置
                intent = new Intent(getContext(), CheckBoxSampe0101.class);
                break;
            case 1:
                // [Spinner]Spinner(スピナー)でプルダウンを表示する
                intent = new Intent(getContext(), SpinnerSampe0101.class);
                break;
            case 2:
                // [Spinner]Spinnerでプルダウンを表示する(文字列リソースxml版)
                intent = new Intent(getContext(), SpinnerSampe0102.class);
                break;
            case 3:
                // [Spinner]Spinnerをカスタマイズする(drawable版)
                intent = new Intent(getContext(), SpinnerSampe0201.class);
                intent.putExtra(SpinnerSampe0201.TYPE, SpinnerSampe0201.TYPE_DRAWABLE);
                break;
            case 4:
                // [Spinner]Spinnerをカスタマイズする(assets版)
                intent = new Intent(getContext(), SpinnerSampe0201.class);
                intent.putExtra(SpinnerSampe0201.TYPE, SpinnerSampe0201.TYPE_ASSETS);
                break;
            case 5:
                // 6.DatePicker
                intent = new Intent(getContext(), DatePickerSampe0101.class);
                break;
            case 6:
                // 7.TimePicker
                intent = new Intent(getContext(), TimePickerSampe0101.class);
                break;
            case 7:
                // 8.NumberPicker
                intent = new Intent(getContext(), NumberPickerSampe0101.class);
                break;
            case 8:
                // 9.AlertDialog
                intent = new Intent(getContext(), AlertDialogSampe0101.class);
                intent.putExtra(AlertDialogSampe0101.TYPE, AlertDialogSampe0101.TYPE_DEFAULT);
                break;
            case 9:
                // 10.AlertDialog(OK,Cancel)
                intent = new Intent(getContext(), AlertDialogSampe0101.class);
                intent.putExtra(AlertDialogSampe0101.TYPE, AlertDialogSampe0101.TYPE_OK_CANCEL);
                break;
            case 10:
                // 11.カスタムAlertDialog
                intent = new Intent(getContext(), AlertDialogSampe0201.class);
                break;
            case 11:
                // 12.カスタムAlertDialog(パスワード入力)
                intent = new Intent(getContext(), PasswordEntrySampe0101.class);
                break;
            default:
                intent = new Intent(getContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }
        startActivity(intent);
    }
}
