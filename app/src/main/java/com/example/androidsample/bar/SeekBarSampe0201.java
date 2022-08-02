package com.example.androidsample.bar;

/*
 * [SeekBar]SeekBarのツマミ(thumb)とprogress(progress bar)のカスタマイズのレイアウト版です。
 * 　対象URL：https://akira-watson.com/android/seekbar.html
 *
 * SeekBarの以下3点をカスタマイズしています。
 * 1. 背景色の変更
 * 2. ツマミを画像に変更(ドロイド君に変更)
 * 3. shapeを使ってツマミ(thumb)とprogress barのカスタマイズ
 *
 * このサンプルではレイアウトはレイアウトファイルにて定義しています。
 *
 */

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.util.Locale;

public class SeekBarSampe0201 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar_sampe0201);

        this.textView = findViewById(R.id.seekbar_sample0201_textview);
        this.textView.setText("0%");
        setSeekBarParams(findViewById(R.id.seekbar_sample0201_seekbar1), "seekbar1");
        setSeekBarParams(findViewById(R.id.seekbar_sample0201_seekbar2), "seekbar2");
        setSeekBarParams(findViewById(R.id.seekbar_sample0201_seekbar3), "seekbar3");
    }
    private TextView getTextView() {
        return textView;
    }
    private void setSeekBarParams(SeekBar bar, String val) {
        // 初期値:0、最大値:100
        bar.setProgress(0);
        bar.setMax(100);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress, boolean fromUser) {
                getTextView().setText(String.format(Locale.US, "%s: %d %%", val, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
