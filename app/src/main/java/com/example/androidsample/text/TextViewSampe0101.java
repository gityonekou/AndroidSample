package com.example.androidsample.text;

/*
 * TextView で文字を表示するサンプルです。
 * 画面構成はレイアウトファイルを使います。
 * 　対象URL：https://akira-watson.com/android/textview.html
 *
 */

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class TextViewSampe0101 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_sample0101);

        TextView textView = findViewById(R.id.textview_sample0101_textview);
        textView.setText(R.string.textview_sample_text);
    }
}
