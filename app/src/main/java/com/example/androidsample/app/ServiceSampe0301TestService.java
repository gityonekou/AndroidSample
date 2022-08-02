package com.example.androidsample.app;
/*
 * IntentServiceはアンドロイド11から非推奨となり代わりにJoIntentServiceを使うようになりました。
 * 今サンプルではJobIntentServiceにてコードを書き換えています。
 *
 * ServiceSampe0301では1秒のスリープを入れて10回カウントしたら終了させる簡単なサンプルです
 * onCreate(), onStartCommand(), onDestroy() などは特にこのケースでは必要ないのですが
 * ワーカースレッドの生存状態を正しく処理できるよう、必ず super を実装しないといけないことに注意してください。
 *
 */
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;

public class ServiceSampe0301TestService extends JobIntentService {

    static final int JOB_ID = 1001;
    static boolean stopFlg = false;

    static void enqueueWork(Context context, Intent work) {
        Log.d("debug", "enqueueWork");
        enqueueWork(context, ServiceSampe0301TestService.class, JOB_ID, work);
    }
    public static void restart() {
        stopFlg = false;
    }
    public static void stop() {
        stopFlg = true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("debug", "onCreate");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("debug", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d("debug", "onHandleWork start");
        try {
            for(int i = 0; i < 10; i++ ) {
                if(stopFlg) {
                    // サービス終了
                    // stopSelfを呼び出してもサービスの停止は行われずキューにたまったJOBは処理され
                    // stopFlgがtrueのためbreakですぐにループを抜ける形となる
                    Log.d("debug", "stop called.");
                    stopSelf();
                    break;
                }
                Thread.sleep(1000);
                Log.d("debug", "sleep: " + i);
            }
        } catch (InterruptedException ex) {
            Log.d("debug", "InterruptedException");
            // このスレッドを止める
            Thread.currentThread().interrupt();
        }
        Log.d("debug", "onHandleWork end");
    }

    @Override
    public void onDestroy() {
        /* なぜがこのメソッドが呼ばれない・・・どうして・・・
        * 理由判明：Activityのサービスを開始する処理でstartServiceを呼び出してからenqueueWorkを呼び出していた。
        * JobIntentServiceの場合、サービスの開始はあくまでもenqueueWorkでなければならない。
         *
        * */
        super.onDestroy();
        Log.d("debug", "onDestroy");
    }
}
