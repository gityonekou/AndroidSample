package com.example.androidsample.bar;
/*
 * [SeekBar]SeekBarのツマミ(thumb)とprogress(progress bar)のカスタマイズのコード版です。
 * 　対象URL：https://akira-watson.com/android/seekbar.html
 *
 * SeekBarの以下3点をカスタマイズしています。
 * 1. 背景色の変更
 * 2. ツマミを画像に変更(ドロイド君に変更)
 * 3. shapeを使ってツマミ(thumb)とprogress barのカスタマイズ
 *
 * このサンプルではレイアウトをすべてコードで記述しています。
 * 【このサンプルの重要点】
 * shape、ドロイド君などxmlからDrowableを生成する方法がポイントです。
 *
 */
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.androidsample.R;

import java.util.Locale;

public class SeekBarSampe0202 extends AppCompatActivity {

    private TextView textView;
    private final SeekBar[] seekBars = new SeekBar[4];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // レイアウトの設定(LinearLayout)
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(Color.rgb(220, 255, 220));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        setContentView(layout);

        // 各種パラメータの取得
        // dp単位
        float dp = getResources().getDisplayMetrics().density;
        // マージン20dp
        int margins = (int)(20 * dp);
        // seekbarの高さ50dp
        int seekBarHeight = (int)(50 * dp);

        // TextViewの設定
        this.textView = new TextView(this);
        this.textView.setText("0%");
        this.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textViewLayoutParams.setMargins(margins, margins, margins, margins);
        layout.addView(this.textView, textViewLayoutParams);

        // SeekBarの設定
        for(int i = 0; i < seekBars.length; i++) {
            seekBars[i] = new SeekBar(this);
            LinearLayout.LayoutParams seekBarLayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, seekBarHeight
            );
            seekBarLayoutParams.setMargins(margins, margins, margins, margins);
            layout.addView(seekBars[i], seekBarLayoutParams);

            // 種類によりSeekBarをカスタマイズする
            if(i == 0) {
                // ノーマルタイプ
                setSeekBarParams(seekBars[i], "normal seekbar");
            } else if(i == 1) {
                // ドロイド君
                seekBars[i].setThumb(ResourcesCompat.getDrawable(getResources(),
                        R.mipmap.ic_launcher, null));
                setSeekBarParams(seekBars[i], "ic_launcher bar");
            } else if(i == 2) {
                // shapeをソースで作成もできる。。
                ShapeDrawable sdrawable = new ShapeDrawable(new OvalShape());
                sdrawable.getPaint().setColor(Color.BLUE);
                sdrawable.setIntrinsicWidth(30 * (int)dp);
                sdrawable.setIntrinsicHeight(50 * (int)dp);
                seekBars[i].setThumb(sdrawable);
                setSeekBarParams(seekBars[i], "on create shape bar");
            } else if(i == 3) {
                // custom shape thumb
                seekBars[i].setThumb(ResourcesCompat.getDrawable(getResources(),
                        R.drawable.seekbarsampe_custom_thumb, null));
                // custom shape progress
                seekBars[i].setProgressDrawable(ResourcesCompat.getDrawable(getResources(),
                        R.drawable.seekbarsampe_custom_progress, null));
                setSeekBarParams(seekBars[i], "xml shape bar");
            }
        }

    }

    private TextView getTextView() {
        return textView;
    }
    private void setSeekBarParams(SeekBar bar, String val) {
        // 初期値:0、最大値:100、背景色:191,191,191
        bar.setProgress(0);
        bar.setMax(100);
        bar.setBackgroundColor(Color.rgb(191, 191, 191));
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
