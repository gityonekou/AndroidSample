package com.example.androidsample.list;
/*
 * RecyclerViewでテキストリストの表示
 * 対象URL:https://akira-watson.com/android/recyclerview.html
 *
 * RecyclerViewはその名の通りListViewのViewをリサイクルすることに主点を置いたUIコンポーネントでListViewをさらに
 * 進化させて柔軟にしたものとのことです。
 * cellをドラッグ&ドロップさせたり追加・消去などに強みを発揮するようですが、今回のサンプルではここでは簡単な基本設定を
 * 試しています。
 * 基本的なコーディング手順はデベロッパーガイドにて確認とし、ここでは簡単な手順の解説のみとします。
 * デベロッパーガイド：https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=ja
 *
 * [RecyclerViewを使う手順]
 * １．RecyclerViewのライブラリーをbuild.gradleに追加
 * dependencies {
 * ・・・
 * implementation 'androidx.recyclerview:recyclerview:1.2.1'
 * ⇒バージョンはIDEにて自動でリストアップされた値を設定すること
 *
 * ２．Recyckerview のタグを使う
 * <androidx.recyclerview.widget.RecyclerView
 * ・・・ />
 *
 * ３．RecyclerViewオブジェクトのハンドルを取得してレイアウトマネージャーに接続し、アダプターにデータセットを渡します。
 * [アクティビティやフラグメントなど]
 * RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
 * RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(this);
 * recyclerView.setLayoutManager(rLayoutManager);
 * dataset[]　＝｛リスト表示データ｝
 * MyAdapter adapter = new MyAdapter(dataset);
 * recyclerView.setAdapter(adapter);
 * ４．RecyclerView.Adapterを継承したアダプタを作成、また、RecyclerView.ViewHolderwを継承したホルダーを作成
 * ⇒この部分は全コードになるのでにゃんのサンプルか上記デベロッパーガイドを参照
 *
 *
 */
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsample.R;

import java.util.Locale;

public class RecyclerViewSample0101 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_sample0101);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_sample0101_recyclerview);
        // リストアイテムのレイアウトサイズ(高さ、幅)は常に固定なのでHasFixedSizeを設定(リスト描写の性能向上
        // なお、リストアイテムのレイアウトは高さを固定とすること（今回は50dpにしてあります。
        // 高さを指定しない場合、セルの高さがとんでもなく広がってしまうことがあります。
        // recyclerview_sampe0101_list_items.xmlを参照のこと
        recyclerView.setHasFixedSize(true);
        // レイアウトマネージャーを設定
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        // 表示データをもとにアダプタを設定
        String[] dateset = new String[20];
        for(int i = 0; i < dateset.length; i++) {
            dateset[i] = String.format(Locale.US, "Data_%02d", i);
        }
        recyclerView.setAdapter(new RecyclerViewSample0101Adapter(dateset));
    }
}
