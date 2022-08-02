package com.example.androidsample.text;
/*
 * TextWatcherで入力を監視する
 * 　対象URL：https://akira-watson.com/android/textwatcher.html
 *
 * TextWatcherはEditTextの文字入力を監視します。
 * 例えば入力パスワードの制限文字数がある場合に「文字数オーバー」と表示するなどです。
 *
 */
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class EditTextSampe0401 extends AppCompatActivity implements TextWatcher {
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext_sample0401);

        this.textView = findViewById(R.id.edittext_sample0401_text_view);
        EditText editText = findViewById(R.id.edittext_sample0401_edit_text);
        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence str, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence str, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable str) {
        // 変更されたテキストを取得
        String inputStr = str.toString();
        // 入力文字長が8文字を超えている場合は「オーバー」とする
        if(inputStr.length() > 8) {
            StringBuilder outStr = new StringBuilder(inputStr);
            outStr.append(" 文字数オーバー");
            this.textView.setText(outStr);
            this.textView.setTextColor(Color.RED);
        } else {
            this.textView.setText(inputStr);
            this.textView.setTextColor(Color.BLACK);
        }
    }
}
