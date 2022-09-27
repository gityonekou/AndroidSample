package com.example.androidsample.app;
/*
 * グローバル変数(アプリケーション内の共有データ)を使ってActivity間でデータを渡す
 * mainとsubのライフサイクル確認
 *
 * 　対象URL1：https://akira-watson.com/android/global-val.html
 * 　対象URL2：https://akira-watson.com/android/orientation.html
 *
 * アクティビティ間でのデータの受け渡しの方法はグローバル変数を使う方法もあります。
 *
 * 「android.app.Applicationを継承したクラス」
 * Activityのライフサイクルとは別にアプリケーション内でのデータ共有を行うためにandroid.app.Applicationがあります。
 * これを継承したクラスを作ってAndroidManifest.xmlに登録して、メソッドとしてsetter(), getter()を作成しておき、
 * Activityから設定、読み出しを行います。
 * ※AndroidManifest.xmlの<application android:name=属性に指定できるのは1つのみです。つまり、アプリケーション
 * で登録できるのは１つのみになる点に注意が必要です。
 *
 * Actvityが複数あり、変数を共有したい場合には役立つかもしれませんが注意点もあります。
 * 今更ですが可読性が落ちるとかバグの温床だとか悪者扱いされやすいのです。簡単なプロジェクトであればそれほどでもありませんが
 * 大規模なプロジェクトでは敬遠されるということを心に留めておきましょう。
 *
 * このサンプルではMainアクティビティとSubアクティビティのライフサイクルも追加で確認します。
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * アクティビティ遷移のサンプル03
 * 「3.グローバル変数(アプリケーション内の共有データ)を参照する＋mainとsubのライフサイクル確認」に対応するアクティビティです。
 *
 * 　対象URL1：https://akira-watson.com/android/global-val.html
 * 　対象URL2：https://akira-watson.com/android/orientation.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 */
public class ActivitySampe0301 extends AppCompatActivity {

    private ActivitySampe0301AppData appData;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sample0301_main);

        Log.d("debug", "main onCreate()");

        this.appData = (ActivitySampe0301AppData)getApplication();
        this.editText = findViewById(R.id.edit_text);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener( v -> {
            this.appData.setTestString(this.editText.getText().toString());
            startActivity(new Intent(getApplicationContext(), ActivitySampe0301SubActivity.class));
        });
        Button aBtn = findViewById(R.id.a_add_button);
        aBtn.setOnClickListener(v2 -> {
            String str = this.editText.getText().toString() + "aaa";
            this.editText.setText(str);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug", "main onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "main onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "main onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "main onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "main onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "main onDestroy()");
    }
}
