package com.example.androidsample.app;
/*
 * Activity のライフサイクルと画面の回転
 *
 * 　対象URL：https://akira-watson.com/android/orientation.html
 *
 * 端末を縦表示しているところから横表示にするとアプリが初期化されて最初からスタートしてしまいます。
 * これはライフサイクルを見るとわかります。
 * これを回避するには、画面を回転させない 、あるいは回転しても初期化されないようにすることができます。
 *
 * このサンプルではアクティビティの各ライフサイクル毎にログを出して確かめてみます。
 *
 * 【画面が回転させてもonDestroy()が呼ばれないようにする】
 * 1.AndroidManifest.xmlに以下を追加(ただし、APIレベルが13以上)
 * android:configChanges="orientation|screenSize"
 *
 *        <activity android:name=".MainActivity"
 *            android:configChanges="orientation|screenSize">
 *            ...
 *        </activity>
 * ただし、このconfigChangesはconfigによるActivityの再生成を止めることで、縦画面から横に変わることにより
 * 画面の変更してほしいものが変更されないという問題を含んでいるので万能ではありません。
 *
 * 2.screenOrientation属性を設定
 * そもそも回転させなければリセットもないのではということで回転させずに固定する方法です。
 * android:configChanges=”orientation|screenSize”の代わりに以下の設定で、縦・横方向の画面固定ができます。
 *
 *  <!-- 縦方向 -->
 *  android:screenOrientation="portrait"
 *  <!-- 横方向 -->
 *  android:screenOrientation="landscape"
 *
 * 状況に応じて動的に縦画面、横画面にしたい場合はActivity.javaに設定をします。
 * // 縦画面
 * setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
 * // 横画面
 * setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
 *
 *
 *
 */
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ActivitySampe0401 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sample0401);

//        // 縦画面固定に設定
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        // 横画面固定に設定
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Log.d("debug", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug", "onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "onDestroy()");
    }
}
