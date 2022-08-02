package com.example.androidsample.imageview;
/*
 * ImageViewを使って画像を表示する：(android:srcを使う場合)
 * 　対象URL：https://akira-watson.com/android/imageview.html
 *
 * 今回のサンプルでのlayout.xmlの重要点
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"
 * 上記で画面の縦横一杯の領域で画像を表示する設定になりますが、実際の画像ピクセルと表示領域は必ずしも合ってはいません。
 * scaleTypeは centerCrop を使うと画面に合うように拡大縮小してくれます。
 * android:scaleType="centerCrop"
 *
 */
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ImageViewSampe0101 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_sample0101);
    }
}
