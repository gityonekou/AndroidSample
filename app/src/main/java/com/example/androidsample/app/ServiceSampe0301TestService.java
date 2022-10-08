package com.example.androidsample.app;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * IntentServiceはアンドロイド11から非推奨となり代わりにJoIntentServiceを使うようになりました。
 * 今サンプルではJobIntentServiceにてコードを書き換えています。
 * 変更：JobIntentServiceも非推奨となっためWorkManager/Workerを用いて実装に変更します
 *
 * [処理内容]
 * ServiceSampe0301では1秒のスリープを入れて10回カウントしたら終了させる簡単なサンプルです
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.Javadoc追加対応
 * 2.JobIntentService非推奨化対応:Workerの実装
 *
 */
public class ServiceSampe0301TestService extends Worker {

    /** 出力結果を取得するためのキー */
    public static final String RESULT_DATA_KEY = "result_data";

    // 停止が呼び出された場合
    private boolean stopFlg = false;

    /**
     * コンストラクタ：アンドロイドフレームワークにて呼び出されます。
     *
     * @param context コンテキスト
     * @param workerParams　パラメータ
     */
    public ServiceSampe0301TestService(
            @NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("debug", "doWork start");

        StringBuilder resultDataStr = new StringBuilder(100);
        resultDataStr.append("doWork start\n");

        try {
            for(int i = 0; i < 10 && !stopFlg; i++ ) {
                Thread.sleep(1000);

                // 何回目のスリープ完了かを出力
                resultDataStr.append("sleep:");
                resultDataStr.append(i);
                resultDataStr.append("\n");

                Log.d("debug", "sleep: " + i);

            }
        } catch (InterruptedException ex) {
            resultDataStr.append("InterruptedException\n");
            Log.d("debug", "InterruptedException");

            // このスレッドを止める
            Thread.currentThread().interrupt();
            return Result.failure();
        }
        resultDataStr.append("doWork end\n");
        Log.d("debug", "doWork end");

        // 出力結果のデータを作成
        Data resultData = new Data.Builder().putString(RESULT_DATA_KEY, resultDataStr.toString())
                .build();

        return Result.success(resultData);
    }

    @Override
    public void onStopped() {
        // 停止を呼び出された場合
        Log.d("debug", "onStopped　call");
        stopFlg = true;
    }

}
