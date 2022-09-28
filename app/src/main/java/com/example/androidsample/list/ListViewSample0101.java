/*
 * ListView と ArrayAdapter で簡単なテキストリストを表示する
 * 対象URL:https://akira-watson.com/android/listview_1.html
 *
 * スマホでは色々な場面でリスト表示が使われます。リストのitemはダイナミックに増減できてスクロールすれば限られた画面内で
 * 多くの情報を扱えるためとても便利なわけです。
 *
 * [ListViewとAdapter]
 * ListViewはスクロール可能なアイテムのリストを表示するViewグループです。
 * Adapterは配列またはデータベースなどからそれぞれの要素をリストに入れられるように変換し自動的に挿入してくれます。
 * つまり、データソース(DataBase、ファイル、配列など)とListViewとの間に位置します。
 * 【データソース】⇒【Adapter】⇒【ListView】
 * アダプタに位置するところは昔はドライバ、コネクタともいわれていたところではあるが、区別は難しいような。
 * とりあえず、アダプタということで覚えておくことにする。
 *
 * ListViewにつかうアダプタには用途に応じてArrayAdapter、BaseAdapter、SimpleAdapterなどその他いろいろあります。
 *
 * [ArrayAdapter]
 * BaseAdapterを継承しており、簡単な TextView のリストを表示する（これを継承してカスタムでアダプターを作ることも可能）
 *
 * [BaseAdapter]
 * レイアウトをカスタマイズする場合はこれを継承したカスタムアダプターを作る
 *
 * [SimpleAdapter]
 * Mapクラスを使ってリスト項目のレイアウトをカスタマイズする
 *
 * [その他]
 * また、アダプタを生成時に指定しているレイアウト「android.R.layout.simple_list_item_1」は
 * アンドロイドにあらかじめ用意されている定義済みのレイアウトファイルのIDです。これを使うとリスト用のレイアウトファイルは
 * 不要です。
 *
 */
package com.example.androidsample.list;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * ListViewサンプル01
 * 「1.簡単なテキストリストの表示(simple_list_item_1)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/listview_1.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ListViewSample0101 extends AppCompatActivity {
    private static final String[] listItems = {
            "abc ", "bcd", "cde", "def", "efg", "fgh", "ghi", "hij", "ijk", "jkl"
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        setContentView(listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listItems
        );
        listView.setAdapter(adapter);
    }
}
