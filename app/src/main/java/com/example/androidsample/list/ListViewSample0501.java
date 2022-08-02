package com.example.androidsample.list;
/*
 * ListView アイテム個々の背景、高さなどを変える
 *
 * 対象URL:https://akira-watson.com/android/listview-item-adjust.html
 *
 * 表示しているリスト項目を個々の順番などで変更(背景色を変える、レイアウトを変えるなど)する場合は作成するAdapterの
 * getViewメソッドでpositionの値を判定して個々のViewを返すことで実現可能です。
 *
 */
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ListViewSample0501 extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
        listView.setAdapter(new ListViewSample0501Adapter(
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
