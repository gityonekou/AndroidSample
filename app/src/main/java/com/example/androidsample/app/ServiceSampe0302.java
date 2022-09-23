package com.example.androidsample.app;
/*
 * IntentService⇒WorkManagerでバックグラウンド処理を簡単に実行させる
 *
 * 　対象URL：https://akira-watson.com/android/intentservice.html
 *
 * ServiceSampe0302ではSoundPoolを使ってゲームの効果音の再生を試してみます。
 * WorkManagerについてはサンプルServiceSampe0301を参照ください。
 *
 * 注意点は、MediaPlayerでもそうですが、音声データがダウンロードされてから再生を始めるという点です。
 * ロードが終わったかどうかを調べるのには、リスナーをセットしてonLoadCompleteから返されるステータスが0の時です。
 * SoundPool.OnLoadCompleteListener
 *
 *****
 *  ver2.00.00 更新
 * ・JobIntentService非推奨化対応:WorkManagerの実装
 *
 */
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.androidsample.R;

public class ServiceSampe0302 extends AppCompatActivity {

    // 実行中のワーカーを識別するためのタグ
    private static final String WORKER_TAG = ServiceSampe0302TestService.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_sample0101);

        Button startBtn = findViewById(R.id.service_sample0101_start_button);
        Button stopBtn = findViewById(R.id.service_sample0101_stop_button);
        stopBtn.setEnabled(false);

        // 開始ボタン押下で効果音再生開始
        startBtn.setOnClickListener( v -> {
            OneTimeWorkRequest workRequest =
                    new OneTimeWorkRequest.Builder(ServiceSampe0302TestService.class)
                    .addTag(WORKER_TAG)
                    .build();
            WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);
            stopBtn.setEnabled(true);
        });

        // 停止ボタン押下時
        stopBtn.setOnClickListener(v -> {
            WorkManager.getInstance(getApplicationContext()).cancelAllWorkByTag(WORKER_TAG);
            stopBtn.setEnabled(false);
        });

    }
}
