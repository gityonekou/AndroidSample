package com.example.androidsample.bar;
/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 4.各種進捗バーのサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 5.ProgressBar
 * 6.SeekBar
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

public class BarGuiSampeMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListViewの設定
        ListView menuList = findViewById(R.id.list_view);
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.bar_gui_menu,
                android.R.layout.simple_list_item_1
        ));
        menuList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // ProgressBarで進捗状況を表示する(表示のみ)
                intent = new Intent(getApplicationContext(), ProgressBarSampe0101.class);
                break;
            case 1:
                // ProgressBarの表示/非表示と進捗状況の表示
                intent = new Intent(getApplicationContext(), ProgressBarSampe0102.class);
                break;
            case 2:
                // SeekBarでボリューム入力
                intent = new Intent(getApplicationContext(), SeekBarSampe0101.class);
                break;
            case 3:
                // [SeekBar]SeekBarのツマミ(thumb)とprogress(bar)のカスタマイズのレイアウト版
                intent = new Intent(getApplicationContext(), SeekBarSampe0201.class);
                break;
            case 4:
                // [SeekBar]SeekBarのツマミ(thumb)とprogress(bar)のカスタマイズのコード版
                intent = new Intent(getApplicationContext(), SeekBarSampe0202.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);
    }
}
