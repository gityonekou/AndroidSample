/*
 * SeekBarでボリューム入力
 * 　対象URL：https://akira-watson.com/android/seekbar.html
 *
 * SeekBarでは「draggable thumb」(ドラッグ可能な親指?)と呼ばれるツマミを動かしその位置を判断します。
 *
 * [Seekbarのメソッド]
 * リスナー、setOnSeekBarChangeListenerを設定することでツマミのドラッグ前後とドラッグ中の状態に
 * 設定を追加することができます。
 *
 * // ツマミがドラッグされると呼ばれる
 * public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
 * }
 *
 * //ツマミがタッチされた時に呼ばれる
 * public void onStartTrackingTouch(SeekBar seekBar) {
 * }
 *
 * //ツマミがリリースされた時に呼ばれる
 * public void onStopTrackingTouch(SeekBar seekBar) {
 * }
 *
 */
package com.example.androidsample.bar;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.util.Locale;

/**
 * SeekBarサンプル01
 * 「3.SeekBarでボリューム入力」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/seekbar.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class SeekBarSampe0101 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar_sampe0101);

        this.setTextView(findViewById(R.id.seekbar_sample0101_textview));
        SeekBar seekbar = findViewById(R.id.seekbar_sample0101_seekbar);
        // SeekBarの初期値(0)と最大値(100)を設定
        seekbar.setProgress(0);
        seekbar.setMax(100);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //ツマミがドラッグされると呼ばれる
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 68%のように表示されるようフォーマット。%の表示はLocale.USが汎用的に推奨されます
                getTextView().setText(String.format(Locale.US, "%d %%", progress));

            }
            // ツマミがタッチされた時に呼ばれる
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //ツマミがリリースされた時に呼ばれる
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private TextView getTextView() {
        return textView;
    }

    private void setTextView(TextView textView) {
        this.textView = textView;
    }
}
