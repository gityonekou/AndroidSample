package com.example.androidsample.imageview;
/*
 * ImageViewをドラッグする：(onTouchムーブアクション)
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
 * このサンプルはonTouchムーブアクションを使うサンプルです
 *
 * [onTouchでタッチイベントを拾う]
 * タッチイベントを扱うものとして
 * onTouch(View v, MotionEvent event)
 * があります。
 *
 * 似たものとして
 * onTouchEvent(MotionEvent event)
 * があり、これはActivityの画面全体でのタッチイベントを拾うときに使います
 *
 */
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;
import com.example.androidsample.customgui.CustomImageView;

import java.util.Locale;

public class ImageViewSampe0302 extends AppCompatActivity implements View.OnTouchListener {
    private TextView textView;
    private CustomImageView imageView;
    private int preDx, preDy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_sample0302);
        this.textView = findViewById(R.id.imageview_sample0302_textview);
        this.imageView = findViewById(R.id.imageview_sample0302_imageview);
        this.imageView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // X,Yの位置を取得
        int newDx = (int) motionEvent.getRawX();
        int newDy = (int) motionEvent.getRawY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                /* Viewタッチを検出 */
                Log.d("onTouch","ACTION_DOWN called");
                break;
            case MotionEvent.ACTION_MOVE:
                /* ドラッグ */
                this.imageView.performClick();
                // 画像の位置を設定する
                int dX = this.imageView.getLeft() + (newDx - this.preDx);
                int dY = this.imageView.getTop() + (newDy - this.preDy);
                this.imageView.layout(
                        dX,
                        dY,
                        dX + this.imageView.getWidth(),
                        dY + this.imageView.getHeight());
                this.textView.setText(String.format(Locale.US, "dx=%d\ndy=%d", dX, dY));
                Log.d("onTouch",String.format("ACTION_MOVE:dx=%d, dy=%d", dX, dY));

                break;
            case MotionEvent.ACTION_UP:
                /* Viewタッチが終了 */
                Log.d("onTouch","ACTION_UP called");
                break;
            default:

        }
        // タッチした位置を古い位置とする
        this.preDx = newDx;
        this.preDy = newDy;

        return true;
    }
}
