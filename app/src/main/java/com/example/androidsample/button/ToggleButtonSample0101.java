/*
 * ToggleButtonを使ってON・OFFを設定する。
 * 対象URL：https://akira-watson.com/android/togglebutton.html
 *
 * Buttonの一種でToggleButton(トグルボタン)というのがあります。
 * ユーザーに視覚的にON/OFFをわかり易くするために使えます。
 * また、ToggleButtonではクリックで表示文字とアンダーバーカラーが変わるようにデフォルトで設定されています
 *
 *
 */
package com.example.androidsample.button;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * ToggleButtonを使ってON・OFFを設定する
 * 「12. ToggleButtonを使ってON・OFFを設定する」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/togglebutton.html
 *
 **************************************
 * 変更履歴:
 * ver2.01 新規作成
 *
 */
public class ToggleButtonSample0101 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_button_sample0101);

        TextView textView = findViewById(R.id.toggle_button_sample0101_textView);
        ToggleButton toggleButton1 = findViewById(R.id.toggle_button_sample0101_toggleButton1);
        ToggleButton toggleButton2 = findViewById(R.id.toggle_button_sample0101_toggleButton2);
        // トグルボタン1のオン・オフ時の表示メッセージを設定
        toggleButton1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                textView.setText(R.string.toggle_button_sample0101_btn1_on_text);
            } else {
                textView.setText(R.string.toggle_button_sample0101_btn1_off_text);
            }
        });
        // トグルボタン2のオン・オフ時の表示メッセージを設定
        toggleButton2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                textView.setText(R.string.toggle_button_sample0101_btn2_on_text);
            } else {
                textView.setText(R.string.toggle_button_sample0101_btn2_off_text);
            }
        });
    }
}
