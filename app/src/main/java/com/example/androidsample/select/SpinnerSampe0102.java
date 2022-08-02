package com.example.androidsample.select;
/*
 * Spinner(スピナー)でプルダウンを表示する(文字列リソースxml版)
 *
 * 　対象URL：https://akira-watson.com/android/spinner.html
 *
 * 表示するプルダウン項目を文字列リソースで指定するバージョンです。
 *
 * 文字列リソースで作成するときは以下のようになります。
 * リソースはarrayディレクトリを作成
 * res\values\array
 * その下にplanets_array.xmlを作成して文字列リソースを定義します。
 *
 *
 */
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class SpinnerSampe0102 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_sampe0101);

        this.textView = findViewById(R.id.text_view);
        Spinner spinner = findViewById(R.id.spinner);

        // 表示項目のAdapterを生成
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_sampe0102_array2,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // spinnerにアダプタをセット
        spinner.setAdapter(adapter);

        // リスナーを設定
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /* アイテムが選択された時 */
            @Override
            public void onItemSelected(
                    AdapterView<?> adapterView, View view, int position, long id) {
                Spinner selectSpinner = (Spinner)adapterView;
                getTextView().setText((String)selectSpinner.getSelectedItem());
            }

            /* アイテムが選択されなかった時 */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private TextView getTextView() {
        return textView;
    }
}
