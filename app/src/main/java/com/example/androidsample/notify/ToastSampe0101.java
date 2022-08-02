package com.example.androidsample.notify;
/*
 * Toastの表示
 * 　対象URL1：https://akira-watson.com/android/toast.html
 * 　対象URL2：https://akira-watson.com/android/toast-custom.html
 *
 * 画面上にメッセージをポップアップしたい場合、トーストを使うと便利です。
 * また、APIレベル３０以降ではトーストをカスタマイズする場合にViewを指定するsetViewメソッドが非推奨となったため
 * 注意です。次のサンプル[toast-custom.html]で指定するsetViewメソッドが使えなくなったためこのサンプルでそれ以外
 * の部分を合わせて記述しています。
 *
 *
 */
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ToastSampe0101 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_common01);
        Button btn = findViewById(R.id.notify_common01_button);
        btn.setOnClickListener(this::onClick);
    }

    private void onClick(View v) {
        // メッセージ1
        Toast.makeText(
                this, "トーストメッセージ1(時間長いほう)", Toast.LENGTH_LONG).show();

        /* 注意 */
        /*
         トーストは一度に複数表示することはできない仕様です。下記メッセージ2はメッセージ1の表示が終わってから
         表示されるので注意です。

         APIレベル２８だと２回目のsetTextで落ちてしまうのでこの部分はコメントアウトとします。
         今の自分の携帯の合わせるということで。。。
         */
//        // メッセージ2
//        Toast toast2 = new Toast(this);
//        toast2.setText("トーストメッセージ2(時間短いほう)");
//        // 表示時間
//        toast2.setDuration(Toast.LENGTH_SHORT);
//        // 表示位置
//        toast2.setGravity(Gravity.CENTER, 0, 0);
//        toast2.show();
    }
}
