/*
 *　リスナの設定には無名(匿名)クラスを使ってきましたが、その他にもいろいろな方法があります。
 * 対象URL：https://akira-watson.com/android/button-onclicklistener.html
 *
 * 1.無名(匿名)クラスをつかう
 * 2.変数でまとめる
 * 3.OnClickListenerをimplementsしてonClickを定義する
 * 4.OnClickListenerのonClickを実装したカスタムクラスを作成
 * 5.XMLレイアウトの中でandroid:onClickを定義して対象のメソッドを実行する。
 * 呼び出し対象のメソッドはViewを変数として持つ必要がある。
 *  public void 呼び出しメソッド(View view)
 *
 */
package com.example.androidsample.button;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.util.Locale;

/**
 * onClickListenerの色々な設定
 * 「10. onClickListenerの色々な設定」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/button-onclicklistener.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ButtonSampe0501 extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_sample0501);
        this.textView = findViewById(R.id.button_sample0501_text_view);

        // 1.無名(匿名)クラスをつかう
        Button btn1 = findViewById(R.id.button_sample0501_button1);
        btn1.setOnClickListener(v -> {
            Log.d("debug", "button1, Perform action on click");
            this.textView.setText(String.format(Locale.JAPANESE, "「%s」ボタンタップ",
                    getString(R.string.button_sample0501_button1)));
        });

        // 2.変数でまとめる
        Button btn2 = findViewById(R.id.button_sample0501_button2);
        btn2.setOnClickListener(btn2Click);

        // 3.OnClickListenerをimplementsしてonClickを定義する
        Button btn3 = findViewById(R.id.button_sample0501_button3);
        btn3.setOnClickListener(this);

        // 4.OnClickListenerのonClickを実装したカスタムクラスを作成
        Button btn4 = findViewById(R.id.button_sample0501_button4);
        btn4.setOnClickListener(new Button4Click());

    }

    // 他のインナークラス内での参照用
    private TextView getTextView() {
        return this.textView;
    }

    /*
     * 「2.変数でまとめる」のボタンをクリック時を定義(ラムダ式で定義する)
     */
    private final View.OnClickListener btn2Click = view -> {
        if(view.getId() == R.id.button_sample0501_button2) {
            Log.d("debug", "button2, Perform action on click");
            getTextView().setText(String.format(Locale.JAPANESE, "「%s」ボタンタップ",
                    getString(R.string.button_sample0501_button2)));
        } else {
            Log.d("error", "view.getId() un match <button2>");
        }
    };


    /*
     * 3.OnClickListenerをimplementsしてonClickを定義する
     */
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_sample0501_button3) {
            Log.d("debug", "button3, Perform action on click");
            getTextView().setText(String.format(Locale.JAPANESE, "「%s」ボタンタップ",
                    getString(R.string.button_sample0501_button3)));
        } else {
            Log.d("error", "view.getId() un match <button3>");
        }
    }

    /*
     * 4.OnClickListenerのonClickを実装したカスタムクラス
     */
    class Button4Click implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.button_sample0501_button4) {
                Log.d("debug", "button4, Perform action on click");
                getTextView().setText(String.format(Locale.JAPANESE, "「%s」ボタンタップ",
                        getString(R.string.button_sample0501_button4)));
            } else {
                Log.d("error", "view.getId() un match <button4>");
            }
        }
    }

    /*
     * 5.XMLレイアウトの中でandroid:onClickを定義して対象のメソッドを実行する。
     */
    public void btn5Click(View view) {
        if(view.getId() == R.id.button_sample0501_button5) {
            Log.d("debug", "button5, Perform action on click");
            getTextView().setText(String.format(Locale.JAPANESE, "「%s」ボタンタップ",
                    getString(R.string.button_sample0501_button5)));
        } else {
            Log.d("error", "view.getId() un match <button5>");
        }
    }
}
