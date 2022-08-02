package com.example.androidsample.text;

/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 2.TextViewとEditTextのサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 2.TextView
 * 3.EditText
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

public class TextSampeMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListViewの設定
        ListView menuList = findViewById(R.id.list_view);
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.text_menu,
                android.R.layout.simple_list_item_1
        ));
        menuList.setOnItemClickListener(this);
    }

    /* メニュー選択時の動作：対象のサンプルを呼びだし */
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // TextViewで文字を表示
                intent = new Intent(getApplicationContext(), TextViewSampe0101.class);
                break;
            case 1:
                // レイアウトファイルを使わないでコードでTextViewを設定(LinearLayout版)
                intent = new Intent(getApplicationContext(), TextViewSampe0201.class);
                break;
            case 2:
                // レイアウトファイルを使わないでコードでTextViewを設定(ConstraintLayout版)
                intent = new Intent(getApplicationContext(), TextViewSampe0301.class);
                break;
            case 3:
                // Text Selectionの実装(テキストのコピペ)
                intent = new Intent(getApplicationContext(), TextViewSampe0401.class);
                break;
            case 4:
                // EditTextを使って文字を入力する(レイアウトを使う)
                intent = new Intent(getApplicationContext(), EditTextSampe0101.class);
                break;
            case 5:
                // EditTextを使って文字を入力する(コードですべて書く)
                intent = new Intent(getApplicationContext(), EditTextSampe0201.class);
                break;
            case 6:
                // EditTextの文字入力制限と表示制限
                intent = new Intent(getApplicationContext(), EditTextSampe0301.class);
                break;
            case 7:
                // TextWatcherで入力を監視する
                intent = new Intent(getApplicationContext(), EditTextSampe0401.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);
    }
}
