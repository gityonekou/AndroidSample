package com.example.androidsample.app;
/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 7. アプリ(Activity, Fragment, Service)のサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 11.Activity
 * 12.Fragment
 * 13.Service
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

public class AppSampeMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // リストを設定
        ListView menuList = findViewById(R.id.list_view);
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.app_menu,
                android.R.layout.simple_list_item_1
        ));
        menuList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent;
        switch(position) {
            case 0:
                // [Activity]Activityの画面遷移(main⇔sub)
                intent = new Intent(getApplicationContext(), ActivitySampe0101.class);
                break;
            case 1:
                // [Activity]Activity(main⇔sub)間のデータ受け渡し
                intent = new Intent(getApplicationContext(), ActivitySampe0201.class);
                break;
            case 2:
                // [Activity]グローバル変数(アプリケーション内の共有データ)を参照する＋mainとsubの
                // ライフサイクル確認
                intent = new Intent(getApplicationContext(), ActivitySampe0301.class);
                break;
            case 3:
                // [Activity]Activity Life Cycle と画面回転
                intent = new Intent(getApplicationContext(), ActivitySampe0401.class);
                break;
            case 4:
                // [Fragment]Fragmentを「Hello World」から始める
                intent = new Intent(getApplicationContext(), FragmentSampe0101.class);
                break;
            case 5:
                // [Fragment]Fragmentタグ部分をコードで動的に設定する
                intent = new Intent(getApplicationContext(), FragmentSampe0201.class);
                break;
            case 6:
                // [Fragment]画面遷移(Activity⇔Fragment)
                intent = new Intent(getApplicationContext(), FragmentSampe0301.class);
                break;
            case 7:
                // 画面遷移(Fragment⇔Fragment)
                intent = new Intent(getApplicationContext(), FragmentSampe0401.class);
                break;
            case 8:
                // 画面遷移(Fragment⇔Fragment)貼り付け部分の通知版
                intent = new Intent(getApplicationContext(), FragmentSampe0402.class);
                break;
            case 9:
                // [Service]Serviceの使い方
                intent = new Intent(getApplicationContext(), ServiceSampe0101.class);
                break;
            case 10:
                // [Service]WindowManagerを使ってServiceから画像を表示させ続ける
                intent = new Intent(getApplicationContext(), ServiceSampe0201.class);
                break;
            case 11:
                // [Service]JobIntentServiceを使った(簡単な)バックグラウンド処理
                intent = new Intent(getApplicationContext(), ServiceSampe0301.class);
                break;
            case 12:
                // [Service]JobIntentServiceを使ってバックグラウンドでゲーム効果音を再生
                intent = new Intent(getApplicationContext(), ServiceSampe0302.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }
        startActivity(intent);
    }
}
