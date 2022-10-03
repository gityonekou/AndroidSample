/*
 * Text Selectionでテキストのコピペを実装する。
 * 　対象URL：https://akira-watson.com/android/text-selection.html
 *
 * テキストを触っていると時々選択されてコピーできるようなメニューが現れますがこれが「Text Sellection」です。
 * これを実装してみます。
 *
 * 「ActionMode」
 *　ActionModeはユーザーが操作していることに関連したメニューを表示したりしてくれる仕組みです。
 *　ユーザーがそれにより何かを選択した場合はCallbackを使って取り出します。
 *　このサンプルでは、既存のActionMode.CallbackをActionMode.Callback2に拡張変更し実現します。
 *
 */
package com.example.androidsample.text;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * TextView サンプル04
 *
 * 「4. Text Selectionの実装(テキストのコピペ)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/text-selection.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class TextViewSampe0401 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_sample0401);

        TextView textView = findViewById(R.id.textview_sample0401_text);
        TextView copiedView = findViewById(R.id.textview_sample0401_copied);

        textView.setText(R.string.textview_sample0401_text);
        // Text Selection をenableにする
        textView.setTextIsSelectable(true);
        textView.setCustomSelectionActionModeCallback(
                new TextViewSampe0401TextActionMode(textView, copiedView));
    }
}
