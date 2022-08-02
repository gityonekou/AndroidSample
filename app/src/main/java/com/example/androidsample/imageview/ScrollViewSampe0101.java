package com.example.androidsample.imageview;
/*
 * ScrollView:画面の縦スクロール
 * 　対象URL：https://akira-watson.com/android/scrollview.html
 *
 * ScrollViewの設定自体はとても簡単です。スクロールさせたいところを ScrollView で挟み込むだけです。
 * 注意点として通常、画面にUIが一つというのはあまりありませんが、複数のUIをScrollViewで挟むと
 * 「ScrollView can host only one direct child」というエラーになります。
 * つまり、ScrollViewは子Viewを1つしか持てないということです。
 * どうするかというと、テキストやボタンなどをまとめてスクロールさせたい場合は、LinearLayout等でくくり、
 * ScrollさせるのはLinearLayoutだけという体裁をとればOKです。
 *
 * このアクティビティで以下のサンプルを実装します。
 * type1:ScrollView(縦スクロール)のサンプル
 * type2:ScrollView(複数UI：縦スクロール)のサンプル
 *
 */
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ScrollViewSampe0101 extends AppCompatActivity {
    public static final String KEY = "type";
    // type1:ScrollView(縦スクロール)のサンプル
    public static final int TYPE1 = 1;
    // type2:ScrollView(複数UI：縦スクロール)のサンプル
    public static final int TYPE2 = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getIntExtra(KEY, TYPE1) == TYPE1) {
            setContentView(R.layout.activity_scrollview_sample0101);
        } else {
            setContentView(R.layout.activity_scrollview_sample0102);
        }
        Toast.makeText(this, "centerCrop指定(切り取り)してます", Toast.LENGTH_LONG).show();
    }
}
