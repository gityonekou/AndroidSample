/*
 * TextView で文字を表示するサンプルです。
 * レイアウトファイルを使わないでコードでTextViewを設定します。(ConstraintLayout版)
 * 　対象URL：https://akira-watson.com/android/textview-code-constarintlayout.html
 *
 */
package com.example.androidsample.text;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.androidsample.R;

/**
 * TextView サンプル03
 *
 * 「3. レイアウトファイルを使わないでコードでTextViewを設定(ConstraintLayout版)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/textview-code-constarintlayout.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class TextViewSampe0301 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* ConstraintLayout でレイアウトを設定する */
        ConstraintLayout layout = new ConstraintLayout(this);
        layout.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        ));
        setContentView(layout);

        // 表示するTextを設定(idはViewから生成し設定する
        // ConstraintLayoutの場合はＩＤが必須設定項目になるので注意
        TextView text = new TextView(this);
        text.setId(View.generateViewId());
        text.setText(R.string.textview_sample_text);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        text.setTextColor(Color.rgb(0x00, 0x00, 0xaa));
        layout.addView(text);

        // 表示テキストの表示位置の制約を設定(基本レイアウト設定時の意味合いとおなじ？？
        ConstraintSet constraintSet = new ConstraintSet();
        // レイアウトのクローンを作成しいったんクローンに値を設定
        constraintSet.clone(layout);

        // android:layout_width="wrap_content"
        constraintSet.constrainWidth(text.getId(), ConstraintSet.WRAP_CONTENT);
        // android:layout_height="wrap_content"
        constraintSet.constrainHeight(text.getId(), ConstraintSet.WRAP_CONTENT);
        // app:layout_constraintBottom_toBottomOf="parent"
        constraintSet.connect(
                text.getId(),
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM,
                0);
        // app:layout_constraintTop_toTopOf="parent"
        constraintSet.connect(
                text.getId(),
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                0);
        // app:layout_constraintLeft_toLeftOf="parent"
        constraintSet.connect(
                text.getId(),
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                0);
        // app:layout_constraintRight_toRightOf="parent"
        constraintSet.connect(
                text.getId(),
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                0);
        // レイアウトにクローンのデータを反映する？
        constraintSet.applyTo(layout);

    }
}
