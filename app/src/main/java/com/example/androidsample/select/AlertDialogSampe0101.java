/*
 * AlertDialog
 * サンプル1：AlertDialogSampe0101AlertDialog
 *   ダイアログで選択可能なリストボックスを表示し選択した項目を呼び出し元画面で表示する
 * 　対象URL：https://akira-watson.com/android/alertdialog.html
 *
 * サンプル2：AlertDialogSampe0101OkCancelDialog
 * 　ダイアログにOK、キャンセルのボタンを表示しどちらを押下したかを呼び出し元画面で表示する
 * 　対象URL：https://developer.android.com/guide/topics/ui/dialogs?hl=ja
 *
 *
 * ユーザーに注意を喚起したり何かを選択させたりするのに、ポップアップでAlertDialogを使うと便利です。
 * AlertDialogはDialogFragmentを継承したクラスを使って作成します。
 *
 * 【AlertDialog】
 * AlertDialogはFragment を使って、別クラスで作ります。
 * ボタンがタップされたら、DialogFragmentを継承したクラスを呼びその中でonCreateDialog()を使って、
 * AlertDialogを作成します。詳細はDatePickerDialogやTimePickerDialogと同じですのでそちらを参照
 * DatePickerDialog：DatePickerSampe0101
 * TimePickerDialog：TimePickerSampe0101
 *
 * このサンプルではAlertDialogで選択された項目に対応する文字列をAlertDialogからMainActivityに返し表示します。
 * AlertDialogSampe0101AlertDialog
 * １．受け渡しに使用するインターフェース(NoticeDialogListener)を定義しActivity側でそれを実装する
 * ２．onAttachをオーバーライドし渡されたcontext(呼び出し元のActivity)が上記インターフェースを実装しているかどうかを確認。
 * ３．ダイアログのアイテム選択時、選択値を渡すリスナー経由で選択値を呼び出し元に返す
 *
 * AlertDialogSampe0101
 * １．NoticeDialogListenerのメソッドを実装し、ダイアログの選択値を表示する
 *
 */
package com.example.androidsample.select;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;

/**
 * AlertDialog サンプル01
 * 「9.AlertDialog」
 * 「10.AlertDialog(OK,Cancel)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/alertdialog.html
 * 　対象URL：https://developer.android.com/guide/topics/ui/dialogs?hl=ja
 *
 * 9.10の切り替えは呼び出し元にてフラグ[type]の値をIntentに設定することにより行います。
 *
 *【サンプルについて】
 * このサンプルではAlertDialogで選択された項目に対応する文字列をAlertDialogからMainActivityに返し表示します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class AlertDialogSampe0101 extends AppCompatActivity
        implements AlertDialogSampe0101AlertDialog.NoticeDefaultDialogListener,
        AlertDialogSampe0101OkCancelDialog.NoticeOkCancelDialogListener {

    public static final String TYPE="type";
    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_OK_CANCEL = 2;
    private int type;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertdialog_sample0101);

        this.type = getIntent().getIntExtra(TYPE, TYPE_DEFAULT);
        this.textView = findViewById(R.id.alertdialog_sample0101_textview);
        Button btn = findViewById(R.id.alertdialog_sample0101_button);
        btn.setOnClickListener( v -> {
            if(this.type == TYPE_OK_CANCEL) {
                DialogFragment fragment = new AlertDialogSampe0101OkCancelDialog();
                fragment.show(getSupportFragmentManager(), AlertDialogSampe0101OkCancelDialog.class.getName());
            } else {
                DialogFragment fragment = new AlertDialogSampe0101AlertDialog();
                fragment.show(getSupportFragmentManager(), AlertDialogSampe0101AlertDialog.class.getName());
            }
        });

    }

    @Override
    public void onDialogItemClick(DialogFragment fragment, String selectText) {
        this.textView.setText(selectText);
    }

    @Override
    public void choiceButton(DialogFragment fragment, String choiceText) {
        this.textView.setText(choiceText);
    }
}
