package com.example.androidsample.app;
/*
 * IntentServiceでバックグラウンド処理を簡単に実行させる
 *
 * 　対象URL：https://akira-watson.com/android/intentservice.html
 *
 * ServiceSampe0302ではSoundPoolを使ってゲームの効果音の再生を試してみます。
 * IntentService(JobIntentServce)についてはサンプルServiceSampe0301を参照ください。
 *
 * 注意点は、MediaPlayerでもそうですが、音声データがダウンロードされてから再生を始めるという点です。
 * ロードが終わったかどうかを調べるのには、リスナーをセットしてonLoadCompleteから返されるステータスが0の時です。
 * SoundPool.OnLoadCompleteListener
 *
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ServiceSampe0302 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_sample0101);

        Button startBtn = findViewById(R.id.service_sample0101_start_button);
        startBtn.setOnClickListener( v -> {
            Intent intent = new Intent(getApplicationContext(), ServiceSampe0302TestService.class);
            ServiceSampe0302TestService.enqueueWork(getApplicationContext(), intent);
        });

        Button stopBtn = findViewById(R.id.service_sample0101_stop_button);
        // 現状、IntentService、JobIntentServiceなどのバックグランドサービスを外部で安全に停止させる方法がない気がする
        // Serviceクラスにフラグを設けてonHandleWork内の最初の処理で値を監視し終了になるとstopSelf();を呼び出す方法もためしたが
        // stopSelf()の呼び出しは無視されるようだ。
        // とりあえず、この方法で実装するが後で調べる必要あり
        //stopBtn.setOnClickListener( v ->
        //    stopService(new Intent(getApplicationContext(), ServiceSampe0302TestService.class)));
        // TODO:backgroundサービスをここで停止させる方法を探す(上の方法では停止できない
        stopBtn.setOnClickListener(v -> {
            Toast.makeText(this,
                    "フォアグラウンドサービスと違い、バックグラウンドサービスはActivity側から停止できません。方法を探すこと。",
                    Toast.LENGTH_LONG).show();
            stopBtn.setEnabled(false);
        });

    }
}
