package com.example.androidsample.app;
/*
 * IntentServiceはアンドロイド11から非推奨となり代わりにJoIntentServiceを使うようになりました。
 * 今サンプルではJobIntentServiceにてコードを書き換えています。
 * 変更：JobIntentServiceも非推奨となっためWorkManagerを用いて実装に変更します
 *
 ****
 * [処理内容]
 * ServiceSampe0301では1秒のスリープを入れて10回カウントしたら終了させる簡単なサンプルです
 *
 * ver2.00.00 更新
 * ・JobIntentService非推奨化対応：Workerの実装
 *
 */
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ServiceSampe0301TestService extends Worker {

    public ServiceSampe0301TestService(
            @NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("debug", "doWork start");
        try {
            for(int i = 0; i < 10; i++ ) {
                Thread.sleep(1000);
                Log.d("debug", "sleep: " + i);
            }
        } catch (InterruptedException ex) {
            Log.d("debug", "InterruptedException");
            // このスレッドを止める
            Thread.currentThread().interrupt();
            return Result.failure();
        }
        Log.d("debug", "doWork end");
        return Result.success();
    }
}
