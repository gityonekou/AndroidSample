/*
 * shapeを使ってカスタムボタンを作成します。
 * こちらはデベロッパーにあるサンプルをそのまま試したものになります。
 * レイアウトの設定はxmlになります。
 * 対象URL：https://akira-watson.com/android/custom-button.html
 * デベロッパーURL：
 * https://developer.android.com/guide/topics/resources/drawable-resource.html#Shape
 *
 * shape(シェイプ)とは
 * Bitmapなどの画像そのものを使わずにグラフィック的な画像を表現するものです。
 * 円や四角形など単純なものはshapeを使ったほうがメモリ使用量が少なくなります。
 *
 */
package com.example.androidsample.button;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * shape(シェイプ)サンプル01
 * 「5. shape(シェイプ)を使ってカスタムボタンを作る(デベロッパーサンプルを試す)」に対応するアクティビティです。
 *
 * こちらはデベロッパーにあるサンプルをそのまま試したものになります。
 * レイアウトの設定はxmlになります。
 * 対象URL：https://akira-watson.com/android/custom-button.html
 * デベロッパーURL：
 * https://developer.android.com/guide/topics/resources/drawable-resource.html#Shape
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ButtonSampe0301 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_sample0301);
    }
}
