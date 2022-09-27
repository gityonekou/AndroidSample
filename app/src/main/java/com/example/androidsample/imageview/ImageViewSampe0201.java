/*
 * ImageViewを使って画像を表示する：(ic_launcherを画像として表示)
 * 　対象URL：https://akira-watson.com/android/imageview2.html
 *
 * レイアウトファイルを使わずコードで直接レイアウトを作成します。
 * いつものサンプルですね。。
 *
 * 【重要】
 * 今回のサンプルはIDEがデフォルトで用意している画像のic_launcherを画像として表示します。
 * ただ、単純な画像ファイルでない(xmlなどで構成されている？？)のでassetにおいて入力ストリームから
 * 取り込むのは自作ではむりかもです。後で勉強が必要かと
 *
 * 【Color.argbメソッド】
 * rgb構成に透明度という要素が新たにあるみたい。これも後で調べないとですわ。。
 *
 */
package com.example.androidsample.imageview;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * ImageViewサンプル04
 * 「4. 上サンプルをLayoutを使わずコードで(ic_launcherを画像として表示)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/imageview2.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ImageViewSampe0201 extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // リニアレイアウトでレイアウトを作成
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        // Color.argbメソッド：透明度(alpha)を指定して色を作成できます。違いはよくわからないので、
        // ボタン押下時に確かめるかサンプルを検索すること
        layout.setBackgroundColor(Color.argb(0xff,0xaa, 0xcc, 0xff));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        setContentView(layout);

        // 表示するイメージビューを設定。配置
        this.imageView = new ImageView(this);
        // ic_launcherってなに？？
        this.imageView.setImageResource(R.mipmap.ic_launcher);
        layout.addView(this.imageView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        /*
         * 画像が表示される前なので上記でイメージをレイアウトに追加しても以下
         * this.imageView.getLayoutParams().width
         * this.imageView.getLayoutParams().height
         * の値は
         * 　LinearLayout.LayoutParams.WRAP_CONTENT＝-2
         * View.getWidth()
         * View.getHeight()
         * の値は初期値：0の値が設定されるだけなので注意
         *
         * onCreateの段階で横幅、縦幅を取得するにはBitmapを生成して取得しないといけないです。
         *
         */
        Log.d("debug",
                String.format("start image layout width:%d", this.imageView.getLayoutParams().width));
        Log.d("debug",
                String.format("start image layout height:%d", this.imageView.getLayoutParams().height));
        Log.d("debug",
                String.format("start image width:%d", this.imageView.getWidth()));
        Log.d("debug",
                String.format("start image height:%d", this.imageView.getHeight()));

        // 表示幅拡大用ボタン
        Button btn = new Button(this);
        btn.setText(R.string.button);
        layout.addView(btn, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        btn.setOnClickListener( v -> {
            int nowLayoutWidth = this.imageView.getLayoutParams().width;
            int nowLayoutHeight = this.imageView.getLayoutParams().height;
            Log.d("debug",
                    String.format("before image layout width:%d", nowLayoutWidth));
            Log.d("debug",
                    String.format("before image layout height:%d", nowLayoutHeight));
            Log.d("debug",
                    String.format("before image width:%d", this.imageView.getWidth()));
            Log.d("debug",
                    String.format("before image height:%d", this.imageView.getHeight()));
            // ボタンタップ毎に画像を100拡大
            if(nowLayoutWidth == LinearLayout.LayoutParams.WRAP_CONTENT &&
                    nowLayoutHeight == LinearLayout.LayoutParams.WRAP_CONTENT) {
                this.imageView.setLayoutParams(new LinearLayout.LayoutParams(
                        this.imageView.getWidth() + 100,
                        this.imageView.getHeight() + 100
                ));
            } else {
                this.imageView.setLayoutParams(new LinearLayout.LayoutParams(
                        nowLayoutWidth + 100, nowLayoutHeight + 100
                ));
            }
        });

    }
}
