/*
 * Snackbar(スナックバー)で簡単なアクションを実装する
 *
 * 　対象URL：https://akira-watson.com/android/snackbar.html
 *
 * 簡単な通知をユーザーに出すには、Toastがありますが、似たようなポップアップ機能として Snackbar(スナックバー) が
 * あります。違いは画面下からせり出すこととユーザーにメッセージと共にアクションを提示できるところです。
 *
 * 今回は表示したSnackbar(スナックバー)にアクションを設定してみます。
 *
 * バーの右横にボタンのようにmessageが設定されてそれをタップするとアクションが実行されます。
 * Snackbar.setAction(CharSequence text, View.OnClickListener listener)
 *
 * その他にバーの背景色を変えたり、Actionの文字列色を変更するメソッドがあります。
 *
 */
package com.example.androidsample.notify;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * Snackbar(スナックバー)サンプル02
 * 「3.Snackbar(スナックバー)で簡単なアクションを実装する」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/snackbar.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class SnackbarSampe0102 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_sampe0102);

        this.textView = findViewById(R.id.snackbar_sample0102_textview);
        Button btn = findViewById(R.id.snackbar_sample0102_button);
        btn.setOnClickListener( v -> {
            this.textView.setText("");
            Snackbar snackbar = Snackbar.make(
                    v, R.string.snackbar_sample0102_ask, Snackbar.LENGTH_SHORT);
            // 表示時間を再設定する：10000ミリ秒=100秒
            snackbar.setDuration(10000);
            snackbar.getView().setBackgroundColor(Color.rgb(32, 125, 98));

            // メッセージ右横に表示される「Replay」をタップすると実行するアクション
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.setAction("Replay", snackbarView ->
                    this.textView.setText(R.string.snackbar_sample0102_message));

            snackbar.show();
        });


    }
}
