/*
 * ListViewタグを使う(＋レイアウトでヘッダー、フッター)
 * 対象URL:https://akira-watson.com/android/arrayadapter.html
 *
 * 今回のサンプルはsetContentViewで設定するレイアウトxmlでヘッダー部、ListView部(ListViewタグを使う)、フッター部と
 * 分けてレイアウトを作る方法です。
 * (⇒にゃん先生談)
 * フッター部に広告を張り付けるなどする場合はListViewのフッターメソッドをつかうよりレイアウトで直接区切ったほうが
 * いいでしょうとのこと。。。。
 * また、この場合、リストの項目数が画面最後まで行く場合は最後のデータがフッターに隠れて見えなくなるため
 * 最後に空データを追加する必要がある点に注意です。⇒リストの項目数でヘッダー部に隠れるデータの対策をする必要あり・なしを
 * 判定する必要があり実用的ではない気がする。おそらく、この方法は商用では採用できないかと。。
 *
 *
 */
package com.example.androidsample.list;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.util.Locale;

/**
 * ListViewサンプル04
 * 「4.ListViewタグを使う(＋レイアウトでヘッダー、フッター)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/arrayadapter.html
 *
 * [処理内容]
 * 今回のサンプルはsetContentViewで設定するレイアウトxmlでヘッダー部、ListView部(ListViewタグを使う)、フッター部と
 * 分けてレイアウトを作る方法です。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ListViewSample0201 extends AppCompatActivity {

    private String[] listItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_sample0201);

        this.listItems = getResources().getStringArray(R.array.listview_sampe0102_array);
        ListView list = findViewById(R.id.listview_sample0201_listview);
        // リストアイテムを最後に追加する必要があるのでここでは空のAdapterを作成
        // 注意：ここでリストアイテムを指定すると後でリストアイテムを追加できなくなる。
        // adapter.add("");のところでjava.lang.UnsupportedOperationExceptionが発生する
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.listview_sampe0102_list
        );
        // リストアイテムを追加
        for(String item : this.listItems) {
            adapter.add(item);
        }
        //　フッターに隠れる空データ
        adapter.add("");

        // 作成したアダプタをリストにセットしリスナを設定
        list.setAdapter(adapter);
        list.setOnItemClickListener((adapterView, view, position, id) -> {
            if(position < listItems.length) {
                Toast.makeText(this,
                        String.format(Locale.US, "%sがtapされました", listItems[position]),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
