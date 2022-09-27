/*
 * shapeを使ってカスタムボタンを作成します。
 * レイアウトの設定はxmlになります。
 * 対象URL：https://akira-watson.com/android/custom-button.html
 *
 * shape(シェイプ)とは
 * Bitmapなどの画像そのものを使わずにグラフィック的な画像を表現するものです。
 * 円や四角形など単純なものはshapeを使ったほうがメモリ使用量が少なくなります。
 *
 */
package com.example.androidsample.button;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * shape(シェイプ)サンプル02
 * 「6. shape(シェイプ)を使ってカスタムボタンを作る」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/custom-button.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ButtonSampe0302 extends AppCompatActivity {

    private TextView textView;
    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_sample0302);

        this.textView = findViewById(R.id.button_sample0302_text_view);
        Button btn = findViewById(R.id.button_sample0302_button);
        btn.setOnClickListener( v -> {
            this.count++;
            StringBuilder str = new StringBuilder(getString(R.string.button_sample0302_tapped));
            str.append(" ");
            str.append(this.count);
            this.textView.setText(str);
        });
    }
}
