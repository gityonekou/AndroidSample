/*
 * ScrollView:画面の縦スクロール(コード版)
 * 　対象URL：https://akira-watson.com/android/scrollview.html
 *
 * ScrollViewの設定自体はとても簡単です。スクロールさせたいところを ScrollView で挟み込むだけです。
 * 注意点として通常、画面にUIが一つというのはあまりありませんが、複数のUIをScrollViewで挟むと
 * 「ScrollView can host only one direct child」というエラーになります。
 * つまり、ScrollViewは子Viewを1つしか持てないということです。
 * どうするかというと、テキストやボタンなどをまとめてスクロールさせたい場合は、LinearLayout等でくくり、
 * ScrollさせるのはLinearLayoutだけという体裁をとればOKです。
 *
 * こちらはScrollViewSampe0101のレイアウトをjavaコードで記述するバージョンです。
 *
 */
package com.example.androidsample.imageview;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * ScrollView:画面の縦スクロール(コード版)
 * 「11. ScrollView(縦スクロール)コード版」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/scrollview.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ScrollViewSampe0102 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT
        ));
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.moon6);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        scrollView.addView(imageView);
        setContentView(scrollView);
        Toast.makeText(this, "centerCrop指定なし", Toast.LENGTH_LONG).show();
    }
}
