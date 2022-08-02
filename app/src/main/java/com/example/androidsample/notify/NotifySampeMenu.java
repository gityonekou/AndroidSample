package com.example.androidsample.notify;
/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 5.通知(トースト, Snackbar)のサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 7.Toast
 * 8.Snackbar
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

import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.R;

public class NotifySampeMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListViewの設定
        ListView menuList = findViewById(R.id.list_view);
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.notify_menu,
                android.R.layout.simple_list_item_1
        ));
        menuList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // Toast(トースト)の表示
                intent = new Intent(getApplicationContext(), ToastSampe0101.class);
                break;
            case 1:
                // Snackbar(スナックバー)を表示する
                intent = new Intent(getApplicationContext(), SnackbarSampe0101.class);
                break;
            case 2:
                // Snackbar(スナックバー)で簡単なアクションを実装する
                intent = new Intent(getApplicationContext(), SnackbarSampe0102.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);

    }
}
