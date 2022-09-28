/*
 * BaseAdapterを継承しカスタムでadapterを作る。＋で次のサンプルになるリストタップ時の画面遷移をここで実装します。
 *
 * 対象URL:https://akira-watson.com/android/baseadapter.html
 * 対象URL:https://akira-watson.com/android/listview_2.html
 *
 * ArrayAdapterを使えば手軽に作れる ListView ですが、画像とテキストのリストなど色々と細かく作りこみたい場合は
 * カスタムでadapterを作成することになります。
 * 今回のサンプルではBaseAdapterを継承してカスタムでadapterを作るケースを試してみます。
 * 次のサンプルで紹介されているリストタップ時の画面遷移もここで合わせて実装、動作確認してみます。
 *
 * ListViewを作るためのBaseAdapterを継承したクラスは３つあります。
 * ・SimpleAdapter
 * ・ArrayAdapter
 * ・CursorAdapter
 * 込み入ったレイアウトにしたい時など、上記で対応できない時はBaseAdapterをカスタム化してアダプタを作成します。
 *
 * 【ポイント】
 * 単純にリストに画像とテキストを並べて表示するならリストのレイアウトをinflateしてAdapterにセットするだけなので
 * ArrayAdapterを継承して作成する方がよいです。その場合、getViewメソッドのオーバーライドのみでよくなります。
 * 参照：com.example.androidsample.list.ListFragmentSampe0101ListViewAdapter
 *
 * BaseAdapterを継承してアダプタを作成する場合は1から作成しないといけないのでその他以下メソッドの実装、
 * クラス内での表示リストの内包が必要になります。
 * getCount()、getItem()、getItemId()
 *
 * 詳しくはListViewSample0301Adapterにて説明します。
 *
 */
package com.example.androidsample.list;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * ListViewサンプル05
 * 「6.BaseAdapterを継承しカスタムでadapterを作る＋画面遷移」に対応するアクティビティです。
 *
 * 対象URL:https://akira-watson.com/android/baseadapter.html
 * 対象URL:https://akira-watson.com/android/listview_2.html
 *
 * [処理内容]
 * 今回のサンプルではBaseAdapterを継承してカスタムでadapterを作るケースを試してみます。
 * 次のサンプルで紹介されているリストタップ時の画面遷移もここで合わせて実装、動作確認してみます。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ListViewSample0301 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // リスト表示内容
    private String[] names;
    private int[] imageIDs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_sample0301);

        // 表示リストを作成
        this.names = getResources().getStringArray(R.array.listview_sampe0301_name_array);
        String[] imageIdWorks = getResources().getStringArray(R.array.listview_sampe0301_drawable_array);
        this.imageIDs = new int[imageIdWorks.length];
        Resources res = getResources();
        for(int i = 0; i < imageIdWorks.length; i++) {
            this.imageIDs[i] = res.getIdentifier(
                    imageIdWorks[i], "drawable", getPackageName());
        }
        ListView listView = findViewById(R.id.listview_sample0301_listview);
        listView.setAdapter(new ListViewSample0301Adapter(
                getApplicationContext(),
                R.layout.listview_sample0301_list_items,
                this.names,
                this.imageIDs
        ));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), ListViewSample0301SubActivity.class);
        intent.putExtra(ListViewSample0301SubActivity.NAME_KEY, this.names[position]);
        intent.putExtra(ListViewSample0301SubActivity.IMAGE_KEY, this.imageIDs[position]);
        startActivity(intent);
    }
}
