/*
 * ProgressBarの表示/非表示と進捗状況の表示
 * 　対象URL：https://akira-watson.com/android/progressbar.html
 *
 * プログレスバーの表示、非表示の切り替えと進捗状況の表示のサンプルを一つにまとめました。
 *
 * [プログレスバーの表示・非表示の切り替え]
 * // 表示
 * android.widget.ProgressBar.VISIBLE
 * // 非表示
 * android.widget.ProgressBar.INVISIBLE
 *
 * [進捗状況の表示]
 * 進捗を分かるようにしたいときは、ProgressBar.Horizontalを使うとそのprogressをユーザーに提示できます。
 * ただし、レイアウトでindeterminateをfalseにする必要があります。
 * android:indeterminate="false"
 *
 * また進捗はメソッドの「setProgress()」を使います。
 *
 */
package com.example.androidsample.bar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * ProgressBarのサンプル02
 * 「2.ProgressBarの表示/非表示と進捗状況の表示」に対応するアクティビティです。
 *
 * プログレスバーの表示、非表示の切り替えと進捗状況の表示のサンプルを一つにまとめました。
 *
 * 対象URL：https://akira-watson.com/android/progressbar.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ProgressBarSampe0102 extends AppCompatActivity {

    private ProgressBar bar1;
    private ProgressBar bar2;
    private int horizontalValue = 0;
    private boolean flg = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar_sampe0102);

        this.bar1 = findViewById(R.id.progressbar_sample0102_progressbar1);
        this.bar2 = findViewById(R.id.progressbar_sample0102_progressbar2);
        // 水平プログレスバーの最大値を設定
        this.bar2.setMax(100);

        Button btn1 = findViewById(R.id.progressbar_sample0102_button1);
        btn1.setOnClickListener( v-> setVisible());
        Button btn2 = findViewById(R.id.progressbar_sample0102_button2);

        btn2.setOnClickListener(v -> setHorizontalValue());

    }

    private void setVisible() {
        if(this.flg) {
            this.bar1.setVisibility(ProgressBar.VISIBLE);
            this.flg = false;
        } else {
            this.bar1.setVisibility(ProgressBar.INVISIBLE);
            this.flg = true;
        }
    }

    private void setHorizontalValue() {
        this.horizontalValue += 10;
        this.bar2.setProgress(this.horizontalValue);
        // セカンダリ値(意味はサンプルを動かせばわかりますです)
        this.bar2.setSecondaryProgress(70);

    }
}
