package com.example.androidsample.imageview;
/*
 * HorizontalScrollView(横スクロール)
 * 　対象URL：https://akira-watson.com/android/horizontal-scrollview.html
 *
 * 縦スクロールの場合はHorizontalScrollViewを使います。(横スクロールはScrollView)
 * 使い方はScrollViewと同じですのでScrollViewSampe0101(レイアウト版)、ScrollViewSampe0102(コード版)を参照
 *
 */
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class HorizontalScrollViewSampe0101 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontalscrollview_sample0101);
        Toast.makeText(this, "レイアウト版", Toast.LENGTH_LONG).show();
    }
}
