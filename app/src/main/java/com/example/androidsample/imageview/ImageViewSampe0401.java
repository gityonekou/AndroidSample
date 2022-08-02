package com.example.androidsample.imageview;
/*
 * Matrixで画像を回転、フリップ、縮小させる
 * 　対象URL：https://akira-watson.com/android/matrix.html
 *
 * 画像を拡大、回転させたりするときは、Matrixを使うことができます。
 * ただし基本的に画像処理はパフォーマンス、メモリを使いますので注意しましょう。
 *
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ImageViewSampe0401 extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap btmp;
    private int imageWidth, imageHeight;
    private Matrix matrix;
    private int degree = 0;
    private int counter = 1;
    private float ratio = 1.0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_sample0401);

        this.imageView = findViewById(R.id.imageview_sample0401_imageview);
        this.btmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_image);

        // 生成したBitmapから画面の横幅、縦幅を取得
        this.imageWidth = this.btmp.getWidth();
        this.imageHeight = this.btmp.getHeight();
        Log.d("debug","start image width=" + this.imageWidth);
        Log.d("debug","start image height=" + this.imageHeight);

        // Matrixインスタンスを生成
        this.matrix = new Matrix();

        // 各種ボタンの設定
        Button rotationBtn = findViewById(R.id.imageview_sample0401_rotation_btn);
        rotationBtn.setOnClickListener(view -> imageRotation());
        Button resizeBbtn = findViewById(R.id.imageview_sample0401_resize_btn);
        resizeBbtn.setOnClickListener(v -> imageResize());
        Button flipBtn = findViewById(R.id.imageview_sample0401_flip_btn);
        flipBtn.setOnClickListener(v -> imageFlip());

    }

    private void imageRotation() {
        // 回転角度
        this.degree += 90;
        if(this.degree > 360) this.degree = 90;

        // 画像中心を起点に回転
        // 下で設定した縮小率などを常時反映しないといけないのでクラス変数でマトリックスを設定しないといけないことに注意
        this.matrix.setRotate(
                this.degree,
                (float)this.imageWidth / 2,
                (float)this.imageHeight / 2);
        // 回転したBitmapを生成しDrowableに設定
        this.imageView.setImageDrawable(new BitmapDrawable(getResources(), Bitmap.createBitmap(
                this.btmp, 0, 0, this.imageWidth, this.imageHeight, this.matrix, true
        )));

    }

    private void imageResize() {
        // 縮小率
        if(this.counter < 4) {
            this.ratio -= 0.1f;
        } else if(this.counter < 8) {
            this.ratio += 0.2f;
        } else {
            this.counter = 0;
            this.ratio = 1.0f;
        }
        Log.d("debug","counter=" + this.counter);
        Log.d("debug","ratio=" + this.ratio);

        // 画像をリサイズし作成した画像を設定
        // 上で設定した回転角度を常時反映しないといけないのでクラス変数でマトリックスを設定しないといけないことに注意
        this.matrix.preScale(this.ratio, this.ratio);
        this.imageView.setImageDrawable(new BitmapDrawable(getResources(), Bitmap.createBitmap(
                this.btmp, 0, 0, this.imageWidth, this.imageHeight, this.matrix, true
        )));

        this.counter++;
    }

    private void imageFlip() {

        //this.matrix.preScale(1.0f, 1.0f);

        // 水平方向にフィリップを設定
        this.matrix.preScale(-1.0f, 1.0f);
        // 垂直方向にフィリップを設定
        //this.matrix.preScale(1.0f, -1.0f);

        this.imageView.setImageDrawable(new BitmapDrawable(getResources(), Bitmap.createBitmap(
                this.btmp, 0, 0, this.imageWidth, this.imageHeight, this.matrix, true
        )));
    }
}
