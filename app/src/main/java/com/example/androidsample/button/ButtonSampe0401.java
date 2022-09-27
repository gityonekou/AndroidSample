/*
 * レイアウトファイルを使いImageButton に画像を設定します。
 * 対象URL：https://akira-watson.com/android/imagebutton.html
 *
 * 表示する画像は「res\drawable\ 」以下に配置します。
 * 画像の設定には、「background」に指定する方法と「src」に指定する方法があります。
 *
 */
package com.example.androidsample.button;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * ImageButtonサンプル01
 * 「7. ImageButton に画像を設定する(レイアウトを使用)」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/imagebutton.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ButtonSampe0401 extends AppCompatActivity {
    private TextView textView;
    private boolean flg = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_sample0401);

        this.textView = findViewById(R.id.button_sample0401_text_view);
        ImageButton imageBtn = findViewById(R.id.button_sample0401_image_button1);
        imageBtn.setOnClickListener( v -> {
            if(this.flg) {
                this.textView.setText(R.string.tapped);
                this.flg = false;
            } else {
                this.textView.setText(R.string.button_sample0401_button);
                this.flg = true;
            }
        });
    }
}
