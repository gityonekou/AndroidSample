package com.example.androidsample.button;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/*
 * レイアウトをjavaコードで動的に変更します。
 * 対象URL：https://akira-watson.com/android/button-hardcoding.html
 *
 * RelativeLayout(リラティブレイアウト)サンプルです。
 * 画面構成はサンプル01と同じなので、同じString ID、各種キーをそのまま使用します。
 *
 */
public class ButtonSampe0203 extends AppCompatActivity {
    private TextView textView;
    private Button btn;
    private boolean buttonTap = false;
    private float scale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* リラティブレイアウトの設定 */
        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        ));
        // 背景色
        layout.setBackgroundColor(Color.rgb(220, 255, 240));
        setContentView(layout);

        //db単位を取得
        this.scale = getResources().getDisplayMetrics().density;

        /* テキストViewを設定、配置する */
        this.textView = new TextView(this);
        this.textView.setText(R.string.hello);
        this.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        this.textView.setTextColor(Color.rgb(0x0, 0x0, 0xff));
        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textLayoutParams.setMargins(
                (int)(150 * this.scale), (int)(300 * this.scale) ,0, 0);
        this.textView.setLayoutParams(textLayoutParams);

        layout.addView(this.textView);

        /* ボタンを設定、配置する(表示レイアウトは初期値 */
        this.btn = new Button(this);
        this.btn.setText(R.string.button_sample0101_button);
        this.btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        RelativeLayout.LayoutParams btnLayoutParams = new RelativeLayout.LayoutParams(
                (int)(200 * this.scale), (int)(100 * this.scale)
        );
        btnLayoutParams.setMargins(
                (int)(105 * this.scale), (int)(360 * this.scale), 0, 0);
        this.btn.setLayoutParams(btnLayoutParams);

        layout.addView(this.btn);

        this.btn.setOnClickListener( v -> {
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
    /* ボタン押下時のレイアウト変更1 */
    private RelativeLayout.LayoutParams getModel1LayoutParams() {
        RelativeLayout.LayoutParams btnLayoutParams = new RelativeLayout.LayoutParams(
                (int)(250 * this.scale),
                (int)(100 * this.scale)
        );
        btnLayoutParams.setMargins(
                (int)(150 * this.scale), (int)(360 * this.scale), 0, 0);
        return btnLayoutParams;
    }

    /* ボタン押下時のレイアウト変更2 */
    private RelativeLayout.LayoutParams getModel2LayoutParams() {
        RelativeLayout.LayoutParams btnLayoutParams = new RelativeLayout.LayoutParams(
                (int)(270 * this.scale),
                (int)(200 * this.scale)
        );
        btnLayoutParams.setMargins(
                (int)(5 * this.scale), (int)(100 * this.scale),
                (int)(50 * this.scale), (int)(20 * this.scale)
        );
        return btnLayoutParams;
    }
}
