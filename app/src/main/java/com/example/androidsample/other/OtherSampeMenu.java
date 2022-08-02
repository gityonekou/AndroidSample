package com.example.androidsample.other;
/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 99.その他のサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 25. Async:「非同期処理 Executorの使い方」
 * 26. HttpURLConnection
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;
import com.example.androidsample.common.CallUnderConstructionActivity;

public class OtherSampeMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // メニューリストを設定
        ListView menuList = findViewById(R.id.list_view);
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.other_menu,
                android.R.layout.simple_list_item_1
        ));
        menuList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // 1.非同期処理 Executorの使い方
                intent = new Intent(getApplicationContext(), ExecutorSample0101.class);
                break;
            case 1:
                // 2.HTTP POSTのデータ送信をVolleyで実装する
                intent = new Intent(getApplicationContext(), HttpPostSample0101.class);
                break;
            case 2:
                // 3.HTTP 非同期で(GETで要求した)画像をダウンロードする
                intent = new Intent(getApplicationContext(), HttpGetSample0101.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }
        startActivity(intent);
    }
}
