package com.example.androidsample.button;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/*
 * 「簡単なButtonアプリを作る」のサンプルをレイアウトファイル「layout.activity_button_sample0101.xml」を使わずに
 * javaコードだけで設定します。
 * レイアウトをコードだけで記述することで例えば、ボタンの位置や大きさを自由に「動的」に変えることができます。
 * 対象URL：https://akira-watson.com/android/button-hardcoding.html
 *
 * LinearLayout(リニアレイアウト)サンプルの最初の分(ver1)です。このサンプルでは各種GUI部品の縦幅・横幅しか指定して
 * いないため画面左上に表示されてしまいます。LinearLayoutの次のサンプル(レイアウト中央寄せ、ボタンの位置を動的に変える
 * サンプル(ver2)はButtonSampe0202にて作成しています。
 * 画面構成はサンプル01と同じなので、同じString ID、各種キーをそのまま使用します。
 *
 */
public class ButtonSampe0201 extends AppCompatActivity {
    private TextView textView;
    private boolean buttonTap = false;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* リニアレイアウトのViewを設定する */
        LinearLayout layout = new LinearLayout(this);
        // オペレーション(画面の方向？？)は垂直方向
        layout.setOrientation(LinearLayout.VERTICAL);
        //レイアウトの横幅・縦幅を指定
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        // 作成したViewをスクリーン画面に設定
        setContentView(layout);

        /* テキストViewを設定、配置する */
        this.textView = new TextView(this);
        this.textView.setText(R.string.hello);
        LinearLayout.LayoutParams textLayoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        this.textView.setLayoutParams(textLayoutParams);
        layout.addView(this.textView);

        /* ボタンを設定、配置する */
        Button btn = new Button(this);
        btn.setText(R.string.button_sample0101_button);
        LinearLayout.LayoutParams btnLayoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        btn.setLayoutParams(btnLayoutParams);
        layout.addView(btn);
        btn.setOnClickListener( v-> {
            if(this.buttonTap) {
                this.textView.setText(R.string.hello);
                this.buttonTap = false;
            } else {
                this.textView.setText(R.string.world);
                this.buttonTap = true;
            }
        });
    }

}
