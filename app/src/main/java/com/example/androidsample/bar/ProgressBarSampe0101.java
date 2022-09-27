/*
 * ProgressBarで進捗状況を表示する(表示のみ)
 * 　対象URL：https://akira-watson.com/android/progressbar.html
 *
 * ProgressBarはいくつかのstyleがあり、またコードを書かなくてもレイアウトで設定するだけで動きます。
 *
 * Widget.ProgressBar.Horizontal
 * Widget.ProgressBar.Small
 * Widget.ProgressBar.Large
 * Widget.ProgressBar.Inverse
 * Widget.ProgressBar.Small.Inverse
 * Widget.ProgressBar.Large.Inverse
 *
 */
package com.example.androidsample.bar;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * ProgressBarのサンプル01
 * 「1.ProgressBarで進捗状況を表示する(表示のみ)」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/progressbar.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ProgressBarSampe0101 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar_sampe0101);
    }
}
