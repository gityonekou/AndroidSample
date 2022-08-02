package com.example.androidsample.text;

/*
 * TextView で文字を表示するサンプルです。
 * レイアウトファイルを使わないでコードでTextViewを設定します。(LinearLayout版)
 * 　対象URL：https://akira-watson.com/android/textview-2.html
 *
 */

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class TextViewSampe0201 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //LinearLayoutで画面構成を設定(垂直方向、中央寄せ)
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        // 表示するメッセージを設定
        TextView text = new TextView(this);
        text.setText(R.string.textview_sample_text);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        text.setTextColor(Color.rgb(0x0, 0x0, 0xaa));
        layout.addView(text, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
    }
}
