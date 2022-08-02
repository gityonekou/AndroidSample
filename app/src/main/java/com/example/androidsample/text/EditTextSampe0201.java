package com.example.androidsample.text;
/*
 * EditTextを使って文字を入力する。(おなじみのコードで直接イアウトを指定するver)
 * 　対象URL：https://akira-watson.com/android/edittext-code.html
 *
 * Androidアプリで文字入力を扱うにはEditTextを使います。
 * 単純な文字入力ですが、実は色々と奥深いものがあります。
 * 入力の文字について、終了のタイミングとその入力の取得、フォーカスの外し方などなどです。
 *
 */
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class EditTextSampe0201 extends AppCompatActivity {

    private TextView textView;
    private EditText editText;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* 初期値設定 */
        float dp = getResources().getDisplayMetrics().density;
        int margins = (int)(30 * dp);

        /* リニアレイアウトでレイアウトを設定 */
        LinearLayout layout = new LinearLayout(this);
        // 配置順：垂直方向
        layout.setOrientation(LinearLayout.VERTICAL);
        // レイアウト：横方向：中央寄せ
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        // 背景色
        layout.setBackgroundColor(Color.rgb(0xdd, 0xff, 0xee));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        setContentView(layout);

        /* テキストビューを設定/配置する */
        this.textView = new TextView(this);
        // テキストサイズ:30sp
        this.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        // テキストカラー
        this.textView.setTextColor(Color.rgb(0x0, 0x0, 0x0));
        // LayoutParams
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        // マージン:30dp
        textLayoutParams.setMargins(margins, margins, margins, margins);
        layout.addView(this.textView, textLayoutParams);

        /* テキスト入力を設定:配置する */
        this.editText = new EditText(this);
        this.editText.setHint(R.string.hint);
        this.editText.setBackgroundColor(Color.rgb(0xff, 0xff,0xff));
        // レイアウト：横幅:150dp, 縦幅:WRAP_CONTENT
        layout.addView(this.editText, new LinearLayout.LayoutParams(
                (int)(150 * dp), LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        /* 登録ボタンを設定：配置する */
        Button btn = new Button(this);
        btn.setText(R.string.register_button);
        // LayoutParams:横幅:150dp, 縦幅:100dp,マージン:30dp
        LinearLayout.LayoutParams btnLayoutParams = new LinearLayout.LayoutParams(
                (int)(150 * dp), LinearLayout.LayoutParams.WRAP_CONTENT
        );
        btnLayoutParams.setMargins(margins, margins, margins, margins);
        layout.addView(btn, btnLayoutParams);
        btn.setOnClickListener( v -> {
            //　入力された文字列を表示
            this.textView.setText(this.editText.getText().toString());
            // 入力をクリアする
            this.editText.setText("");
        });

    }
}
