package com.example.androidsample.app;
/*
 * IntentService(JobIntentService)でバックグラウンド処理を簡単に実行させる
 *
 * 　対象URL：https://akira-watson.com/android/intentservice.html
 *
 * Serviceはバックグラウンドで動作してくれるので別スレッドで実行しているのかと思ってしまいますが実際はメインスレッドを
 * 使っています。(つまり、重い画像処理をメインでやっている場合、Serviceの処理まで影響を受けてしまいます)
 * IntentService(JobIntentService)は最初からそれを想定して作られています。またワークキューを使った順次処理を行えます。
 * すべてのキューが終了すると自ら終了してくれます。
 * IntentServiceはアンドロイド11から非推奨となり代わりにJoIntentServiceを使うようになりました。
 * 今サンプルではJobIntentServiceにてコードを書き換えています。
 * ::::
 * ServiceSampe0301では1秒のスリープを入れて10回カウントしたら終了させる簡単なサンプルです
 * onCreate(), onStartCommand(), onDestroy() などは特にこのケースでは必要ないのですが
 * ワーカースレッドの生存状態を正しく処理できるよう、必ず super を実装しないといけないことに注意してください。
 *　
 * JobIntentServiceを使う場合、以下をAndroidManifest.xmlに追加する必要があります
 *
 * <uses-permission android:name="android.permission.WAKE_LOCK" />
 *　android.permission.WAKE_LOCK permissionを登録する理由は、Jobが実行されるとき、Wake Lockをキャッチ
 * することができるからです。
 *
 *　<service android:name="作成にサービスクラス"　android:permission="android.permission.BIND_JOB_SERVICE"/>
 * サービスに android.permission.BIND_JOB_SERVICEパーミッションを登録する理由は、JobServiceのみが
 * サービスにアクセスできるように作るためです。
 *
 * 【要注意】
 * JobIntentServiceはJobSchedulerで動作することになりますが、JobServiceはstartService()で実行していた一方で、
 * JobIntentServiceはenqueueWork()で実行します。そのためstartService()を呼び出してはいけません。
 * ⇒どこかで不具合がおき、onDestroy()が呼び出されなくなります。
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ServiceSampe0301 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_sample0301);
        ServiceSampe0301TestService.restart();
        Button startBtn = findViewById(R.id.service_sample0301_start_button);
        startBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ServiceSampe0301TestService.class);
            //startService(intent);
            ServiceSampe0301TestService.enqueueWork(getApplicationContext(), intent);
        });
        Button stopBtn = findViewById(R.id.service_sample0301_stop_button);
        // 現状、IntentService、JobIntentServiceなどのバックグランドサービスを外部で安全に停止させる方法がない気がする
        // Serviceクラスにフラグを設けてonHandleWork内の最初の処理で値を監視し終了になるとstopSelf();を呼び出す方法もためしたが
        // stopSelf()の呼び出しは無視されるようだ。
        // とりあえず、この方法で実装するが後で調べる必要あり
        //stopBtn.setOnClickListener( v ->
        //    stopService(new Intent(getApplicationContext(), ServiceSampe0301TestService.class)));
        // TODO:backgroundサービスをここで停止させる方法を探す(上と下の方法では停止できない
        stopBtn.setOnClickListener(v -> {
            ServiceSampe0301TestService.stop();
            Toast.makeText(this,
                    "フォアグラウンドサービスと違い、バックグラウンドサービスはActivity側から停止できません。方法を探すこと。",
                    Toast.LENGTH_LONG).show();
            stopBtn.setEnabled(false);
        });
    }
}
