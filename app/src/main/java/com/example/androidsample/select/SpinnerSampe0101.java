/*
 * Spinner(スピナー)でプルダウンを表示する
 *
 * 　対象URL：https://akira-watson.com/android/spinner.html
 *
 * まずは選択項目の作成方法ですが、いくつか方法があります。
 * resourceでリストを作ったり、Spinnerに要素をaddしたり。
 * 要素を把握しやすいのでいつもArrayList、あるいは配列にしますが文字列リソースで作成することも可能です。
 *
 * 文字列リソースで作成するときは以下のようになります。
 * リソースはarrayディレクトリを作成
 * res\values\array
 * その下にplanets_array.xmlを作成して文字列リソースを定義します。
 * 文字列リソース版はサンプル2にて作成します。
 *
 * 選択項目の文字列配列からArrayAdapterを生成し、Spinnerに渡します。
 * そうすることで、プルダウン選択肢を良い具合に並べて設定をしてくれます。
 * ArrayAdapter<String> adapter = new ArrayAdapter<>(
 *      this,
 *      android.R.layout.simple_spinner_item,
 *      選択項目の文字列配列
 * );
 * simple_spinner_item はプラットフォームによって提供され、デフォルトのレイアウトとして使用できます。
 * レイアウトを自分で作成する必要はありません。
 *
 * プルダウン時のレイアウトをこのメソッドで指定します。
 * adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 * simple_spinner_dropdown_item もプラットフォームで定義された標準的なレイアウトですので、このレイアウトも作る必要はありません。
 *
 * 選択肢が選択された時の処理
 * Spinner にリスナーを登録、選択された場合の挙動を記述します。
 * Spinnerオブジェクトが on-item-selected イベントを取得する時にAdapterView.OnItemSelectedListener インターフェースと、
 * 対応する onItemSelected() コールバック メソッドを実装します。
 *
 *
 */
package com.example.androidsample.select;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * Spinner(スピナー)サンプル01
 *
 * 「2.Spinner(スピナー)でプルダウンを表示する」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/spinner.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class SpinnerSampe0101 extends AppCompatActivity {

    private TextView textView;
    private static final String[] spinnerItems =
            {"Spinner", "Android", "Apple", "Windows"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_sampe0101);

        this.textView = findViewById(R.id.text_view);
        Spinner spinner = findViewById(R.id.spinner);

        // 表示項目のAdapterを生成
        ArrayAdapter<String> adapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_spinner_item,
                spinnerItems
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
