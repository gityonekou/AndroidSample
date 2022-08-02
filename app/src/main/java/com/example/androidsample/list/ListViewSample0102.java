package com.example.androidsample.list;
/*
 * ListView と ArrayAdapter で簡単なテキストリストを表示する(ver2:リストのレイアウトを指定する)
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
 * <<<<<<<<<<<<<<<<<<<<<<<<<<
 * 今回のサンプルはArrayAdapterを使った簡単なサンプル(ver2:リストのレイアウトを指定する)になります。
 * 表示する文字列の配列はリソース：StringArrayから取得します。
 *
 *
 */
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ListViewSample0102 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list = new ListView(this);
        setContentView(list);
        list.setAdapter(ArrayAdapter.createFromResource(
                this,
                R.array.listview_sampe0102_array,
                R.layout.listview_sampe0102_list
        ));
    }
}
