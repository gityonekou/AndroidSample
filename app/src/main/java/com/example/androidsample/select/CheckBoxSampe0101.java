/*
 * CheckBoxの配置
 *
 * 　対象URL：https://akira-watson.com/android/checkbox.html
 *
 * チェックボックスを配置するサンプルです。
 * 選択時のリスナー、またチェックボックスとしてのテキストを表示できます。
 *
 */
package com.example.androidsample.select;

import android.os.Bundle;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * CheckBox サンプル01
 *
 * 「1.CheckBoxの配置」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/checkbox.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class CheckBoxSampe0101 extends AppCompatActivity {

    private final CheckBox[] checkBox = new CheckBox[2];
    private static final String CHECKED = "チェックされた";
    private static final String NON_CHECK = "チェックされていない";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_sampe0101);

        // チェックボックス1
        checkBox[0] = findViewById(R.id.checkbox_sample0101_checkbox1);
        checkBox[0].setOnClickListener( v -> isChecked(checkBox[0]));
        // チェックボックス2
        checkBox[1] = findViewById(R.id.checkbox_sample0101_checkbox2);
        checkBox[1].setOnClickListener( v -> isChecked(checkBox[1]));
    }

    private void isChecked(CheckBox checkbox) {
        if(checkbox.isChecked()) {
            checkbox.setText(CHECKED);
        } else {
            checkbox.setText(NON_CHECK);
        }
    }
}
