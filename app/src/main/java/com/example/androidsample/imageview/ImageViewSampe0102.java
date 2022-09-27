/*
 * ImageViewを使って画像を表示する：(ImageView.setImageResourceメソッドを使う場合)
 * 　対象URL：https://akira-watson.com/android/imageview.html
 *
 * ImageView.setImageResourceメソッドを使い画像を表示します。
 * この場合、動的に画像を変更でいます。
 *
 */
package com.example.androidsample.imageview;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * ImageViewサンプル02
 * 「2. ImageViewを使って画像を表示する：(ImageView.setImageResourceメソッドを使う)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/imageview.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ImageViewSampe0102 extends AppCompatActivity {

    private boolean flg = true;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_sample0102);

        this.imageView = findViewById(R.id.imageview_sample0102_imageview);
        this.imageView.setImageResource(R.drawable.imageview_sample_image2);
        this.imageView.setOnClickListener( iv -> {
            if(flg) {
                this.imageView.setImageResource(R.drawable.imageview_sample_image2);
                flg = false;
            } else {
                this.imageView.setImageResource(R.drawable.imageview_sample_image1);
                flg = true;
            }
        });
    }
}
