/*
 * Picassoでネット上の画像をGridViewで表示
 * 　対象URL：https://akira-watson.com/android/gridview-picasso.html
 *
 * ギャラリーやフォトアプリを作るにはGridViewを使いますが、画像の扱いはうっかりするとOutOfMemoryにはまってしまいます。
 * そこでそういったところを簡単にしてくれるライブラリーPicassoを使ってGridViewを作ってみます。
 * また、ネット上の画像をもってくる場合、通常であれば非同期でのhttp接続を考えなければいけませんが
 * Picassoを使えばこちらが明示的に非同期処理を実装する必要がなくなりコードがとても簡単になります。
 *
 * 【画面サイズの取得方法】
 * 画面サイズの取得方法がAPIレベル30から変更となったため今回OSのAPIレベルによる処理の分岐を追加しています。
 * 詳しくはにゃんのサンプルを参照
 * 対象URL：https://akira-watson.com/android/screen-size.html
 *
 *
 */
package com.example.androidsample.list;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowMetrics;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * GridViewサンプル02
 * 「13.Picassoでネット上の画像をGridViewで表示」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/gridview-picasso.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class GridViewSample0201 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_sample0101);

        // 画面の横幅の半分を計算
        int screenWHalf;
        int screenHHalf;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            // APIレベル30以上の場合
            WindowMetrics windowMetrics = getWindowManager().getCurrentWindowMetrics();
            Rect rect = windowMetrics.getBounds();
            screenWHalf = rect.width() / 2;
            screenHHalf = rect.height() / 2;
        } else {
            // APIレベル29以下の場合
            Display disp = getWindowManager().getDefaultDisplay();
            Point realSize = new Point();
            disp.getRealSize(realSize);
            screenWHalf = realSize.x / 2;
            screenHHalf = realSize.y / 2;
        }

        GridView gridView = findViewById(R.id.gridview_sample0101_gridview);
        gridView.setAdapter(new GridViewSample0201Adapter(
                getApplicationContext(),
                R.layout.gridview_sampe0101_grid_items,
                screenWHalf,
                screenHHalf,
                getResources().getStringArray(R.array.listview_sampe0301_name_array),
                getResources().getStringArray(R.array.gridview_sampe0201_url_array)
                ));
    }
}
