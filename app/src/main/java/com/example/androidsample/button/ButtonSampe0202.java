/*
 * 「簡単なButtonアプリを作る」のサンプルをレイアウトファイル「layout.activity_button_sample0101.xml」を使わずに
 * javaコードだけで設定します。
 * レイアウトをコードだけで記述することで例えば、ボタンの位置や大きさを自由に「動的」に変えることができます。
 * 対象URL：https://akira-watson.com/android/button-hardcoding.html
 *
 * LinearLayout(リニアレイアウト)サンプル(ver2)です。このサンプルではレイアウト中央寄せ、
 * ボタンの位置を動的に変更します。
 * 画面構成はサンプル01と同じなので、同じString ID、各種キーをそのまま使用します。
 *
 */
package com.example.androidsample.button;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * Buttonサンプル03
 * 「3. レイアウトをJavaコードだけで設定する(LinearLayout ver2)」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/button-hardcoding.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ButtonSampe0202 extends AppCompatActivity {
    private TextView textView;
    private Button btn;
    private boolean buttonTap = false;
    private float scale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // db単位を取得
        this.scale = getResources().getDisplayMetrics().density;

        /* リニアレイアウトのViewを設定する */
        LinearLayout layout = new LinearLayout(this);
        // オペレーション(画面の方向？？)は垂直方向
        layout.setOrientation(LinearLayout.VERTICAL);
        //レイアウトの横幅・縦幅を指定
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        // 中央寄せレイアウト
        layout.setGravity(Gravity.CENTER);

        // 作成したViewをスクリーン画面に設定
        setContentView(layout);

        /* テキストViewを設定、配置する */
        this.textView = new TextView(this);
        this.textView.setText(R.string.hello);
        // テキストサイズを30spに設定
        this.textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        // テキストカラーを設定
        this.textView.setTextColor(Color.rgb(0x0, 0x0, 0x0));
        //レイアウトパラメータを作成し設定する
        LinearLayout.LayoutParams textLayoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        this.textView.setLayoutParams(textLayoutParams);
        layout.addView(this.textView);

        /* ボタンを設定、配置する(表示レイアウトはmodel1 */
        this.btn = new Button(this);
        this.btn.setText(R.string.button_sample0101_button);
        this.btn.setLayoutParams(getModel1LayoutParams());
        layout.addView(this.btn);

        this.btn.setOnClickListener( v-> {
            if(this.buttonTap) {
                this.textView.setText(R.string.hello);
                this.btn.setLayoutParams(getModel1LayoutParams());
                this.buttonTap = false;
            } else {
                this.textView.setText(R.string.world);
                this.btn.setLayoutParams(getModel2LayoutParams());
                this.buttonTap = true;
            }
        });
    }

    /* ボタン押下時のレイアウト変更1(初期表示に戻す) */
    private LinearLayout.LayoutParams getModel1LayoutParams() {
        // 横幅150db, 縦幅:WRAP_CONTENTに設定
        LinearLayout.LayoutParams btnLayoutParams =
                new LinearLayout.LayoutParams(
                        (int)(150 * this.scale),
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        // マージン20dbに設定
        int margins = (int)(20 * this.scale);
        btnLayoutParams.setMargins(margins, margins, margins, margins);

        return btnLayoutParams;
    }

    /* ボタン押下時のレイアウト変更2 */
    private LinearLayout.LayoutParams getModel2LayoutParams() {
        // 横幅250db, 縦幅100dbに設定
        LinearLayout.LayoutParams btnLayoutParams =
                new LinearLayout.LayoutParams(
                        (int)(250 * this.scale),
                        (int)(100 * this.scale)
                );
        // マージン(5db, 50db, 50db, 20db)に設定
        btnLayoutParams.setMargins(
                (int)(5 * this.scale),
                (int)(50 * this.scale),
                (int)(50 * this.scale),
                (int)(20 * this.scale));

        return btnLayoutParams;
    }
}
