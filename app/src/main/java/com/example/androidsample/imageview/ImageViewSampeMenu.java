package com.example.androidsample.imageview;
/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 3.ImageViewとScrollView、GridView：標準アイコンをGridViewで表示のサンプル動作を確認できる
 * リンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 4.ImageView
 * 15.ScrollView
 * 18.GridView:「標準アイコンをGridViewで表示」
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

public class ImageViewSampeMenu extends AppCompatActivity
        implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListViewの設定
        ListView menuList = findViewById(R.id.list_view);
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.image_view_menu,
                android.R.layout.simple_list_item_1
        ));
        menuList.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // ImageViewを使って画像を表示する：(android:srcを使う)
                intent = new Intent(getApplicationContext(), ImageViewSampe0101.class);
                break;
            case 1:
                // ImageViewを使って画像を表示する：(ImageView.setImageResourceメソッドを使う)
                intent = new Intent(getApplicationContext(), ImageViewSampe0102.class);
                break;
            case 2:
                // ImageViewを使って画像を表示する：(assetsに画像を置きそれを取り込む)
                intent = new Intent(getApplicationContext(), ImageViewSampe0103.class);
                break;
            case 3:
                // 上サンプルをLayoutを使わずコードで(ic_launcherを画像として表示)
                intent = new Intent(getApplicationContext(), ImageViewSampe0201.class);
                break;
            case 4:
                // 画像をドラッグする：(View.layout()メソッド)
                intent = new Intent(getApplicationContext(), ImageViewSampe0301.class);
                break;
            case 5:
                // 画像をドラッグする：(onTouchムーブアクション)
                intent = new Intent(getApplicationContext(), ImageViewSampe0302.class);
                break;
            case 6:
                // Matrixで画像を回転、フリップ、縮小させる
                intent = new Intent(getApplicationContext(), ImageViewSampe0401.class);
                break;
            case 7:
                // Picassoで大きい画像を扱ってみる
                intent = new Intent(getApplicationContext(), ImageViewSampe0501.class);
                break;
            case 8:
                // ScrollView(縦スクロール)
                intent = new Intent(getApplicationContext(), ScrollViewSampe0101.class);
                intent.putExtra(ScrollViewSampe0101.KEY, ScrollViewSampe0101.TYPE1);
                break;
            case 9:
                // ScrollView(複数UI：縦スクロール)
                intent = new Intent(getApplicationContext(), ScrollViewSampe0101.class);
                intent.putExtra(ScrollViewSampe0101.KEY, ScrollViewSampe0101.TYPE2);
                break;
            case 10:
                // ScrollView(縦スクロール)コード版
                intent = new Intent(getApplicationContext(), ScrollViewSampe0102.class);
                break;
            case 11:
                // HorizontalScrollView(横スクロール)
                intent = new Intent(getApplicationContext(), HorizontalScrollViewSampe0101.class);
                break;
            case 12:
                // HorizontalScrollView(横スクロール)コード版
                intent = new Intent(getApplicationContext(), HorizontalScrollViewSampe0102.class);
                break;
            case 13:
                // Android標準アイコン
                intent = new Intent(getApplicationContext(), ImageViewSampe0601.class);
                break;
            case 14:
                // Android標準アイコン(一覧表示)
                intent = new Intent(getApplicationContext(), ImageViewSampe0602.class);
                intent.putExtra(ImageViewSampe0602.TYPE_KEY, ImageViewSampe0602.TYPE1);
                break;
            case 15:
                // Android標準アイコン(一覧表示:getIdentifierを使う)
                intent = new Intent(getApplicationContext(), ImageViewSampe0602.class);
                intent.putExtra(ImageViewSampe0602.TYPE_KEY, ImageViewSampe0602.TYPE2);
                break;
            default:
                intent = new Intent(getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);

    }
}
