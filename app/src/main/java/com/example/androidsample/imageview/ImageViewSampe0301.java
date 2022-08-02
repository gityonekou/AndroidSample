package com.example.androidsample.imageview;
/*
 * ImageViewをドラッグする：(View.layout()メソッドを使用)
 * 　対象URL：https://akira-watson.com/android/imageview-drag.html
 *
 * 画像をドラッグさせるために使う仕組みは大きく以下の2つになります。
 * １．View.layout(x, y, x+width, y+height)：Viewのメソッドで上下左右の位置を変えることでViewを移動
 * 　　　　x: x方向の移動量
 * 　　　　y: y方向の移動量
 * 　　　　width: viewの横幅
 * 　　　　height: viewの高さ
 * ２．onTouchムーブアクションを取得してそれと同じ座標に画像を配置
 *
 * このサンプルはView.layoutメソッドを使うサンプルです
 *
 */
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ImageViewSampe0301 extends AppCompatActivity {
    private ImageView imageView;
    private int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_sample0301);
        this.imageView = findViewById(R.id.imageview_sample0301_imageview);
        Log.d("debug",
                String.format("start image width:%d", this.imageView.getWidth()));
        Log.d("debug",
                String.format("start image height:%d", this.imageView.getHeight()));
        this.imageView.layout(0, 0, this.imageView.getWidth(), this.imageView.getHeight());

        Button btn = findViewById(R.id.imageview_sample0301_button);
        btn.setOnClickListener(v -> {
            this.counter += 100;
            int addX = this.counter /  2;
            int addY = this.counter;
            this.imageView.layout(
                    addX,
                    addY,
                    this.imageView.getWidth() + addX,
                    this.imageView.getHeight() + addY);
        });
    }
}
