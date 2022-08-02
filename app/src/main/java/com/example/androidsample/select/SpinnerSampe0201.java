package com.example.androidsample.select;
/*
 * Spinner(スピナー)をカスタマイズして画像リストを表示する。
 * また、フォントサイズ、背景色などを変更する。
 * 画像はdrawable配下のイメージを表示するタイプとassets配下のイメージを表示するタイプになります。
 *
 * 　対象URL：https://akira-watson.com/android/checkbox.html
 *
 * Spinner はただリスト項目を選択するだけではなく、画像とテキスト(フォントサイズ変更など)をレイアウトすることも可能です。
 * ListViewと同じようにadapterをカスタマイズして使い、ちょっとリッチなspinnerにすることもできます。
 *
 * [Spinnerのカスタマイズ＝Adapterのカスタマイズ]
 * 作成手順としては基本のSpinnerと同じですが、カスタマイズするのはAdapterになります。(Spinnerではないので注意)
 * このあたりはListViewのAdapterのカスタマイズと同様です。
 *
 * 今回は以下のカスタマイズを行います。
 * 1.選択肢を設定する
 * 2.Spinnerのインスタンスを生成して、Adapterを設定
 * 3.選択肢が選択された時の処理を記述
 *
 * 「1.選択肢を設定する」
 * 画像とその名前を選択肢にします。画像の場合はdrawableに入れてそのresourceの画像名をリストにします。
 *
 * 「2.Spinnerのインスタンスを生成して、Adapterを設定」
 * TestAdapterというBaseAdapterを継承したAdapterクラスを作り、これをカスタマイズします。
 * 基本のSpinnerではデフォルトで設定されているレイアウトを使いましたが、TestAdapterではレイアウトも自由にカスタマイズしていきます。
 *
 * 「3.選択肢が選択された時の処理を記述」
 * リスナーを設定して選択されたセルの位置を取得して画像を表示します。
 * @drawableに置いた画像をその名前から引き出すためにsetImageResource( Resource ID )を使います。
 * imageView.setImageResource(
 *      getResources().getIdentifier(spinnerImages[position],
 *      "drawable",
 *      getPackageName())
 * );
 *
 *
 */

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.io.InputStream;

public class SpinnerSampe0201 extends AppCompatActivity {
    public static final String TYPE="type";
    public static final String TYPE_DRAWABLE="drawable";
    public static final String TYPE_ASSETS = "assets";

    private ImageView imageView;
    private String[] spinnerImages;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_sampe0201);

        // 各種初期値を設定
        this.type = getIntent().getStringExtra(TYPE);
        Resources res = this.getResources();
        BaseAdapter adapter;
        if(this.type.equals(TYPE_ASSETS)) {
            this.spinnerImages = res.getStringArray(R.array.spinner_sampe0201_assets_array);
            adapter = new SpinnerSampeAssetsAdapter(
                    this.getApplicationContext(),
                    R.layout.spinner_sample0201_list,
                    res.getStringArray(R.array.spinner_sampe0201_assets_text_array2),
                    this.spinnerImages
            );
        } else {
            this.spinnerImages = res.getStringArray(R.array.spinner_sampe0201_drawable_array);
            adapter = new SpinnerSampeDrawableAdapter(
                    this.getApplicationContext(),
                    R.layout.spinner_sample0201_list,
                    res.getStringArray(R.array.spinner_sampe0201_drawable_text_array1),
                    this.spinnerImages
            );
        }

        // 各種GUIを設定
        this.imageView = findViewById(R.id.image_view);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> adapterView, View view, int position, long id) {
                if(getType().equals(TYPE_ASSETS)) {
                    AssetManager assetManager = getResources().getAssets();
                    try (InputStream in = assetManager.open(
                            "rizero_image/" + getSpinnerImages()[position])) {
                        Bitmap btmp = BitmapFactory.decodeStream(in);
                        getImageView().setImageDrawable(new BitmapDrawable(getResources(), btmp));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    getImageView().setImageResource(res.getIdentifier(
                            getSpinnerImages()[position], "drawable", getPackageName()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private ImageView getImageView() {
        return imageView;
    }
    private String[] getSpinnerImages() {
        return spinnerImages;
    }
    private String getType() {
        return type;
    }
}
