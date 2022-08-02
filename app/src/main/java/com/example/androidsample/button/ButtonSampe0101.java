package com.example.androidsample.button;
/*
 * 「簡単な Button アプリを作る」のButtonアプリサンプル01です。
 * 　対象URL：https://akira-watson.com/android/button.html
 *　今回のサンプルはActivityは一つなので最後のレイアウトで一つにまとめています。
 *
 */
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ButtonSampe0101 extends AppCompatActivity {
    private TextView textView;
    private boolean buttonTap = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_sample0101);

        this.textView = findViewById(R.id.button_sample0101_text_view);
        Button btn = findViewById(R.id.button_sample0101_button);
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
