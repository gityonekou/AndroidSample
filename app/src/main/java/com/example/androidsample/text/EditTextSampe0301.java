package com.example.androidsample.text;
/*
 * EditTextの文字入力制限と表示制限
 * EditTextの各種文字入力制限パラメータ、表示制限パラメータを試してみます。
 * 　対象URL：https://akira-watson.com/android/edittext_input_display.html
 *
 * ヒント:名前入力のテキストボックスでヒントを表示、例えば「password」などのヒントを入れる
 * android:hint="password"
 * 注意：あくまでも入力前に表示するヒントなので注意：上の例だと入力欄に「password」と表示され、
 * 入力が始まると消える。参考は「EditTextを使って文字を入力する」を参照のこと
 *
 * 入力文字数を制限:入力文字数を4に制限:
 * android:maxLength="4"
 *
 * 行数を制限:行数を１行に固定
 * android:maxLines="1"
 */
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class EditTextSampe0301 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext_sample0301);
    }
}
