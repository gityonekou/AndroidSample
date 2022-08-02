package com.example.androidsample.imageview;
/*
 * アンドロイド標準アイコンの表示
 * 　対象URL：https://akira-watson.com/android/gridview-icon.html
 *
 * アンドロイドで標準で用意されているアイコンの表示方法のサンプルです。
 *
 * 【アンドロイドの標準アイコン】
 * ・javaコードで記述する場合： android.R.drawable.XXX
 * 例：image.setImageResource(android.R.drawable.alert_dark_frame);
 * ・xmlレイアウトに記述する場合：@android:drawable/XXX
 * 例：android:src="@android:drawable/alert_dark_frame"
 *
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.androidsample.R;

public class ImageViewSampe0601 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_sample0601);
        ImageView imageView = findViewById(R.id.imageview_sample0601_imageview);
        imageView.setImageResource(android.R.drawable.ic_input_add);
    }
}
