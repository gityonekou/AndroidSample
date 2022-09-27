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
 */
package com.example.androidsample.app;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.androidsample.R;

/**
 * IntentService⇒WorkManagerでバックグラウンド処理を簡単に実行させる
 * 「13.WorkManagerを使ってバックグラウンドでゲーム効果音を再生」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/intentservice.html
 *
 * [処理内容]
 * ServiceSampe0302ではSoundPoolを使ってゲームの効果音の再生を試してみます。
 * WorkManagerについてはサンプルServiceSampe0301を参照ください。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.Javadoc追加対応
 * 2.JobIntentService非推奨化対応:WorkManagerの実装
 *
 */
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
