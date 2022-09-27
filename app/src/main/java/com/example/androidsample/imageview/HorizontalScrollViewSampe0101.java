package com.example.androidsample.imageview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * HorizontalScrollView(横スクロール)
 * 「12. HorizontalScrollView(横スクロール)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/horizontal-scrollview.html
 *
 *【サンプルについて】
 * 縦スクロールの場合はHorizontalScrollViewを使います。(横スクロールはScrollView)
 * 使い方はScrollViewと同じですのでScrollViewSampe0101(レイアウト版)、ScrollViewSampe0102(コード版)を参照
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class HorizontalScrollViewSampe0101 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontalscrollview_sample0101);
        Toast.makeText(this, "レイアウト版", Toast.LENGTH_LONG).show();
    }
}
