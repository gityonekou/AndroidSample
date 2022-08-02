package com.example.androidsample.list;
/*
 * ListView と ArrayAdapter で簡単なテキストリストを表示する(ヘッダー、フッターを追加)
 * 対象URL:https://akira-watson.com/android/arrayadapter.html
 *
 * 今回のサンプルはListViewのaddHeaderViewメソッド、addFooterViewメソッドを使った簡単なサンプルになります。
 *
 */
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ListViewSample0103 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list = new ListView(this);

        // ヘッダー部
        TextView headerView = new TextView(this);
        headerView.setText("ヘッダー");
        headerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        headerView.setTextColor(Color.rgb(0x0, 0x0, 0xaa));
        headerView.setBackgroundColor(Color.rgb(0xbb, 0x86, 0xfc));
        list.addHeaderView(headerView);

        // フッター部
        TextView footerView = new TextView(this);
        footerView.setText("フッター");
        footerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        footerView.setTextColor(Color.rgb(0x0, 0x0, 0xaa));
        footerView.setBackgroundColor(Color.rgb(0xbb, 0x86, 0xfc));
        list.addFooterView(footerView);

        list.setAdapter(ArrayAdapter.createFromResource(
                this,
                R.array.listview_sampe0102_array,
                R.layout.listview_sampe0102_list
        ));

        setContentView(list);
    }
}
