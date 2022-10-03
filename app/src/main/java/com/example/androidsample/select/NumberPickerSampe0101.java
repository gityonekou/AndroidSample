/*
 * NumberPicker
 *
 * 　対象URL：https://akira-watson.com/android/numberpicker.html
 *
 * 数値の入力のPickerは小数点の入った数値などはNumberPickerを複数並べることで可能です。
 * NumberPickerはButtonなどと同様にレイアウトの属性として指定することができます。
 *
 */
package com.example.androidsample.select;

import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.util.Locale;

/**
 * NumberPicker サンプル01
 *
 * 「8.NumberPicker」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/numberpicker.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class NumberPickerSampe0101 extends AppCompatActivity {

    private TextView pickerTextView;
    private NumberPicker numPicker0, numPicker1, numPicker2, numPicker3, numPicker4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberpicker_sample0101);

        this.pickerTextView = findViewById(R.id.numberpicker_sample0101_textview);
        this.numPicker0 = getNumberPicker(R.id.numberpicker_sample0101_numpicker0);
        this.numPicker1 = getNumberPicker(R.id.numberpicker_sample0101_numpicker1);
        this.numPicker2 = getNumberPicker(R.id.numberpicker_sample0101_numpicker2);
        this.numPicker3 = getNumberPicker(R.id.numberpicker_sample0101_numpicker3);
        this.numPicker4 = getNumberPicker(R.id.numberpicker_sample0101_numpicker4);
        Button btn = findViewById(R.id.numberpicker_sample0101_button);
        btn.setOnClickListener( v -> {
            String numStr = String.format(Locale.US, "%d%d%d.%d%d",
                    this.numPicker0.getValue(),
                    this.numPicker1.getValue(),
                    this.numPicker2.getValue(),
                    this.numPicker3.getValue(),
                    this.numPicker4.getValue());
            // 090.90などの数値を0を削って表示(90.9と表示)するためいったんFloatに変換してから文字列を出力
            this.pickerTextView.setText(String.valueOf(Float.parseFloat(numStr)));
        });
    }

    private NumberPicker getNumberPicker(int id) {
        NumberPicker picker = findViewById(id);
        picker.setMinValue(0);
        picker.setMaxValue(9);
        return picker;
    }
}
