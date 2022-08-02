package com.example.androidsample.imageview;
/*
 * Picassoで大きい画像を扱ってみる
 * 　対象URL：https://akira-watson.com/android/picasso.html
 *
 * ImgeViewで画面に表示させるときに、元画像のサイズ（縦横）が大きいと表示しきれない事があります。
 * このPicassoを使うと簡単に表示できます。
 *
 * サンプル以外にもいろいろなオプションを指定できますので詳しくはホームページにて確認のこと
 *
 */
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;
import com.squareup.picasso.Picasso;

public class ImageViewSampe0501 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_sample0501);
        ImageView imageView = findViewById(R.id.imageview_sample0501_imageview);

        // Drawableにて画像を表示
       Picasso.with(this)
               .load(R.drawable.img5760x3760)
               .fit()
               .centerInside()
               .rotate(-270.0f)
               .into(imageView);

        // Assets:パスの指定方法が違うのか表示されない。。
        // 画像が小さいからというわけではないみたい。(上サンプルだと小さい画像も表示かのうなので。。。)
        // 後で調べること。。。
//        Picasso.with(getApplicationContext())
//                .load("file:///android_asset/view_image/imageview_sample_image3.jpg")
//                //.load("file:///assets/view_image/imageview_sample_image3.jpg")
//                .fit()
//                .centerInside()
//                .rotate(-270.0f)
//                .into(imageView);

        // サイトからダウンロードして表示
//        Picasso.with(getApplicationContext())
//                .load("https://hoge-hoge.com/images/img5760x3760.jpg")
//                .fit()
//                .centerInside()
//                .rotate(-270.0f)
//                .into(imageView);

    }
}
