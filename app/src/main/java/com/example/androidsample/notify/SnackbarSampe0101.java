/*
 * Snackbar(スナックバー)を表示する
 *
 * 　対象URL：https://akira-watson.com/android/snackbar.html
 *
 * 簡単な通知をユーザーに出すには、Toastがありますが、似たようなポップアップ機能として Snackbar(スナックバー) が
 * あります。違いは画面下からせり出すこととユーザーにメッセージと共にアクションを提示できるところです。
 *
 * 今回はSnackbar(スナックバー)を表示するサンプルです。
 * SnackbarはToastと同じようにある短い期間だけ表示して消えます。ただ、設定によっては表示期間を決めたり、
 * dismissするまで継続させたりと色々とできます。
 *
 * SnackbarはToastと似たような設定ですが、引数としてViewを取ります。因みにToastはContextでした。
 * Snackbarの設定は、
 * Snackbar.make(view, "メッセージ", Snackbar.LENGTH_SHORT).show();
 *
 * また、サポートライブラリーが必要です。
 * import android.support.design.widget.Snackbar;
 *
 * build.gradleでは
 * implementation 'com.google.android.material:material:x.x.x'
 * が必要です。最近のAndroid Studioではデフォルトで入っています。尚、バージョンは適宜調整してください。
 *
 */
package com.example.androidsample.notify;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * Snackbar(スナックバー)サンプル01
 * 「2.Snackbar(スナックバー)を表示する」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/snackbar.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class SnackbarSampe0101 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_common01);

        Button btn = findViewById(R.id.notify_common01_button);
        btn.setOnClickListener( v ->
                Snackbar.make(v, "スナックバー サンプルです。", Snackbar.LENGTH_SHORT).show());
    }
}
