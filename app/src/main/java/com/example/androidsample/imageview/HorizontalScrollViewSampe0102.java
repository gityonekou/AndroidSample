package com.example.androidsample.imageview;
/*
 * HorizontalScrollView(横スクロール)コード版
 * 　対象URL：https://akira-watson.com/android/horizontal-scrollview.html
 *
 * 縦スクロールの場合はHorizontalScrollViewを使います。(横スクロールはScrollView)
 * 使い方はScrollViewと同じですのでScrollViewSampe0101(レイアウト版)、ScrollViewSampe0102(コード版)を参照
 *
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class HorizontalScrollViewSampe0102 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HorizontalScrollView view = new HorizontalScrollView(this);
        view.setLayoutParams(new HorizontalScrollView.LayoutParams(
                HorizontalScrollView.LayoutParams.MATCH_PARENT,
                HorizontalScrollView.LayoutParams.MATCH_PARENT
        ));
        ImageView image = new ImageView(this);
        Bitmap btmp = BitmapFactory.decodeResource(getResources(), R.drawable.moon10);
        image.setImageDrawable(new BitmapDrawable(getResources(), btmp));
        image.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        ));
        // 中央寄せ:縦横比オーバー分は切り捨て
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.addView(image);
        setContentView(view);
        Toast.makeText(this, "コード版", Toast.LENGTH_LONG).show();
    }
}
