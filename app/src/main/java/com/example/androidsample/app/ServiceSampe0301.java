package com.example.androidsample.app;
/*
 * IntentService(JobIntentService)⇒WorkManagerでバックグラウンド処理を簡単に実行させる
 *
 * 　対象URL：https://akira-watson.com/android/intentservice.html
 *
 * Serviceはバックグラウンドで動作してくれるので別スレッドで実行しているのかと思ってしまいますが実際はメインスレッドを
 * 使っています。(つまり、重い画像処理をメインでやっている場合、Serviceの処理まで影響を受けてしまいます)
 * IntentService(JobIntentService)は最初からそれを想定して作られています。またワークキューを使った順次処理を行えます。
 * すべてのキューが終了すると自ら終了してくれます。
 * IntentServiceはアンドロイド11から非推奨となり代わりにJoIntentServiceを使うようになりました。
 * 変更：2.00.00
 * JoIntentServiceがさらに非推奨となったためWorkManagerを用いるよう変更しました。
 *
 * [WorkManager]
 * １．build.gradle ファイルに次の依存関係を追加します。
 * implementation 'androidx.work:work-runtime:＊＊＊'
 * ２.バックグラウンドで動作するタスクを作成する
 *　public class SampleWorker extends Worker {    // ・・・ (1)
 *      public SampleWorker(Context context, WorkerParameters params) {
 *          super(context, params);
 *      }
 *      @Override
 *      public Result doWork() {
 *          バックグランド処理
 *          return Result.success();
 *      }
 *  }
 * ・doWrokの戻り値
 *      Result.success()	タスクが正常に終了したとき
 *      Result.failure()	タスクが失敗したとき
 *      Result.retry()	後でタスクを再試行する必要があるとき
 *
 *　３．タスクを実行する
 * タスクを実行させるには、WorkRequestクラスを利用します。
 * Worker がタスクを定義するのに対し、WorkRequest はタスクを実行する方法とタイミングを定義します。
 * タスクを1回だけ実行するOneTimeWorkRequestと、定期的に実行するPeriodicWorkRequestがあります。
 * (アクティビティのスケジュール設定処理を以下に変更)
 * OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SampleWorker.class)
 *      .setInitialDelay(10, TimeUnit.SECONDS) //10秒後に実行
 *      .build();
 * WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);
 *
 *
 */
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.androidsample.R;

import java.util.concurrent.TimeUnit;

/**
 * IntentService(JobIntentService)⇒WorkManagerでバックグラウンド処理を簡単に実行させる
 * 「12.WorkManagerを使った(簡単な)バックグラウンド処理」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/intentservice.html
 *
 * [処理内容]
 *  ServiceSampe0301では1秒のスリープを入れて10回カウントしたら終了させる簡単なサンプルです
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.Javadoc追加対応
 * 2.JobIntentService非推奨化対応:WorkManagerの実装
 *
 */
public class ServiceSampe0301 extends AppCompatActivity {

    // 実行中のワーカーを識別するためのタグ
    private static final String WORKER_TAG = ServiceSampe0301TestService.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_sample0301);
        Button startBtn = findViewById(R.id.service_sample0301_start_button);
        Button stopBtn = findViewById(R.id.service_sample0301_stop_button);
        stopBtn.setEnabled(false); // 開始ボタン押下時のみ有効とする

        // 開始ボタン押下でバックグランド処理(1度のみ)を実行開始
        startBtn.setOnClickListener(v -> {
            OneTimeWorkRequest workRequest =
                    new OneTimeWorkRequest.Builder(ServiceSampe0301TestService.class)
                    .setInitialDelay(5, TimeUnit.SECONDS) // 5秒後に実行開始
                    .addTag(WORKER_TAG)
                    .build();
            WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);
            stopBtn.setEnabled(true);
        });

        // 停止ボタン押下で実行中のbackground処理を停止
        stopBtn.setOnClickListener(v -> {
            WorkManager.getInstance(getApplicationContext()).cancelAllWorkByTag(WORKER_TAG);
            stopBtn.setEnabled(false);
        });

    }
}
