package com.example.androidsample.app;
/*
 * グローバル変数(アプリケーション内の共有データ)を使ってActivity間でデータを渡す
 *
 * 　対象URL：https://akira-watson.com/android/global-val.html
 *
 *  ActivitySampe0301で呼び出されるサブアクティビティです。
 * MainActivityでグローバルに設定したデータを表示します。
 *
 */
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ActivitySampe0301SubActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sample0301_sub);

        Log.d("debug", "sub onCreate()");

        ActivitySampe0301AppData appData = (ActivitySampe0301AppData)getApplication();
        this.textView = findViewById(R.id.text_view);
        this.textView.setText(appData.getTestString());

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener( v -> finish());

        Button bBtn = findViewById(R.id.b_add_button);
        bBtn.setOnClickListener(v2 -> {
            String str = this.textView.getText().toString() + "bbb";
            this.textView.setText(str);
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug", "sub onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "sub onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "sub onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "sub onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "sub onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "sub onDestroy()");
    }
}
