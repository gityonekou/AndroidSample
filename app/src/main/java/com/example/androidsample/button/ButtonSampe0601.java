/*
 *　Button 配列を設定する。
 * 対象URL：https://akira-watson.com/android/button-array.html
 *
 * Buttonやテキストなどを複数配置するようなレイアウトはレイアウトxmlで直接指定することも可能ですが、
 * javaコードの配列を使ってfor文で一気に作ることもできます。レイアウトファイルにちまちま書かずにtagをセットして
 * 作りましょう。
 *
 */
package com.example.androidsample.button;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.util.Locale;

/**
 * Button 配列を設定する。
 * 「11. Button 配列を設定する(ViewにTagでマーキングする)」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/button-array.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ButtonSampe0601 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // dp単位
        float dp = getResources().getDisplayMetrics().density;
        // ボタン幅
        int btnWidth = (int)(250 * dp);
        // マージン
        int margins = (int)(10 * dp);

        // レイアウト設定
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(Color.rgb(0xdd, 0xff, 0xee));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        setContentView(layout);

        // TextViewの設定
        this.textView = new TextView(this);
        this.textView.setText(R.string.button_sample0501_init_message);
        this.textView.setTextColor(0xff000000);
        this.textView.setTextSize(10 * dp);
        layout.addView(this.textView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        for(int i = 1; i <= 5; i++) {
            Button btn = new Button(this);
            // Tagを設定する
            btn.setTag(String.valueOf(i));
            btn.setText(String.format(Locale.US, "Button %d", i));

            LinearLayout.LayoutParams btnLayoutparams = new LinearLayout.LayoutParams(
                    btnWidth, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            btnLayoutparams.setMargins(margins, margins, margins, margins);
            btn.setLayoutParams(btnLayoutparams);
            layout.addView(btn);

            btn.setOnClickListener( v -> this.textView.setText(
                    String.format(Locale.JAPANESE, "ボタンタップ「Tag=%s」",
                    v.getTag().toString())));
        }
    }
}
