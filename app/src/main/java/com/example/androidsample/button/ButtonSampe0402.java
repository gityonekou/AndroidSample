package com.example.androidsample.button;

/*
 * レイアウトをJavaコードで設定しImageButton に画像を設定します。
 * 対象URL：https://akira-watson.com/android/imagebutton.html
 *
 * 表示する画像は「res\drawable\ 」以下に配置します。
 * 画像の設定には、「background」に指定する方法と「src」に指定する方法があります。
 *
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ButtonSampe0402 extends AppCompatActivity {

    public static final String TYPE = "key";
    public static final String TYPE_SRC = "src";
    public static final String TYPE_BACKGROUND = "background";

    private boolean flg = true;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 共通のマージン幅:10dp
        int margin10 = 10 * TypedValue.COMPLEX_UNIT_DIP;
        // タイトル表示文字列
        StringBuilder title = new StringBuilder(20);
        title.append("区分=");
        Intent intent = getIntent();
        String keyValue = intent.getStringExtra(TYPE);
        title.append(keyValue);
        title.append("\n");

        // リニアレイアウトで画面を設定(縦方向、中央寄せ、背景色指定など
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(Color.parseColor("#ffdfcf"));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        setContentView(layout);

        // テキストビューを設定
        this.textView = new TextView(this);
        StringBuilder text = new StringBuilder(title);
        text.append(getString(R.string.button_sample0401_button));
        this.textView.setText(text);
        this.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        this.textView.setTextColor(Color.rgb(0xff, 0x0, 0x0));
        LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT );
        textViewLayoutParams.setMargins(margin10, margin10, margin10, margin10);
        this.textView.setLayoutParams(textViewLayoutParams);

        layout.addView(this.textView);

        /* イメージボタンを設定 */
        ImageButton imgbtn = new ImageButton(this);
        // 表示する画像を設定
        Bitmap imgBitmap = BitmapFactory.decodeResource(
                getResources(), R.drawable.button_sample0401_image);
        if(keyValue.equals(TYPE_SRC)) {
            /* setImageBitmap と　setImageDrawableの違いについて*/
            // android:srcに画像を設定するにはImageBitmapを指定する方法とBitmapDrawableを指定する方法がある
            // setImageBitmap内でsetImageDrawableを呼び出しているので、表示速度的にはどちらを使っても
            // 変わりはないそうですが、空データを表示する場合は
            // setImageBitmap(null)をすると、からだけど、Bitmapのものが入ってしまうので
            // メモリの関係で空にする場合には、setImageDrawable(null) を使った方がいいそうです。

            //imgbtn.setImageBitmap(imgBitmap);
            imgbtn.setImageDrawable(new BitmapDrawable(getResources(), imgBitmap));

            imgbtn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imgbtn.setBackgroundColor(Color.parseColor("#00000000"));
        } else if(keyValue.equals(TYPE_BACKGROUND)){
            // android:background(背景)に画像を設定する
            imgbtn.setBackground(new BitmapDrawable(getResources(), imgBitmap));
        }
        //レイアウト各種を設定
        int dp400 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                400,
                getResources().getDisplayMetrics());
        int dp267 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                267,
                getResources().getDisplayMetrics());
        LinearLayout.LayoutParams imgbtnLayoutParams = new LinearLayout.LayoutParams(dp400, dp267);
        imgbtnLayoutParams.setMargins(margin10, margin10, margin10, margin10);
        imgbtn.setLayoutParams(imgbtnLayoutParams);

        layout.addView(imgbtn);

        imgbtn.setOnClickListener( v -> {
            StringBuilder clickText = new StringBuilder(title);
            if(flg) {
                clickText.append(getString(R.string.tapped));
                this.flg = false;
            } else {
                clickText.append(getString(R.string.button_sample0401_button));
                this.flg = true;
            }
            this.textView.setText(clickText);
        });
    }
}
