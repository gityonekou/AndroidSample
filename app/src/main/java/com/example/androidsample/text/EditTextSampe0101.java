package com.example.androidsample.text;

/*
 * EditTextを使って文字を入力する。(レイアウトファイルでレイアウトを指定するver)
 * 　対象URL：https://akira-watson.com/android/edittext.html
 *
 * Androidアプリで文字入力を扱うにはEditTextを使います。
 * 単純な文字入力ですが、実は色々と奥深いものがあります。
 * 入力の文字について、終了のタイミングとその入力の取得、フォーカスの外し方などなどです。
 *
 */

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class EditTextSampe0101 extends AppCompatActivity {
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext_sample0101);

        this.textView = findViewById(R.id.edittext_sample0101_text_view);
        this.editText = findViewById(R.id.edittext_sample0101_edit_text);
        Button btn = findViewById(R.id.edittext_sample0101_button);
        btn.setOnClickListener( v -> this.textView.setText(this.editText.getText().toString()));

    }
}
