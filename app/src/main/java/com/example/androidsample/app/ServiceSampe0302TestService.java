package com.example.androidsample.app;
/*
 * IntentServiceはアンドロイド11から非推奨となり代わりにJoIntentServiceを使うようになりました。
 * 今サンプルではJobIntentServiceにてコードを書き換えています。
 *
 * ServiceSampe0302ではSoundPoolを使ってゲームの効果音の再生します。
 *
 */
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;

import com.example.androidsample.R;

public class ServiceSampe0302TestService extends JobIntentService {

    static final int JOB_ID = 1002;

    private SoundPool soundPool;
    private int soundOne;
    private boolean loadFlg = false;

    static void enqueueWork(Context context, Intent work) {
        Log.d("debug", "enqueueWork");
        enqueueWork(context, ServiceSampe0302TestService.class, JOB_ID, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("debug", "onCreate");
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        this.soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .setMaxStreams(1)
                .build();
        // 再生音楽をCreate時点でロードしておく
        // 思い出の浜辺 (c)Music-Note.jp
        this.soundOne = this.soundPool.load(
                this, R.raw.omoidenohamabe, 1);
        // ロードが終わったかどうかを判定するリスナー
        this.soundPool.setOnLoadCompleteListener((soundPool1, sampleId, status) -> {
            // status=0でロード完了
            if(status == 0) {
                this.loadFlg = true;
            }
        });
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("debug", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d("debug", "onHandleWork start");
        int count = 1;
        try {
            // ロードが終わるまで時間を潰す
            do {
                // ビジー状態の警告は無視する
                Thread.sleep(70);
                Log.d("debug", "sleep: " + count);
                count++;
            } while(!this.loadFlg);

            Log.d("debug", "soundPool start");
            //　再生
            this.soundPool.play(this.soundOne, 1.0f, 1.0f, 0, 0, 1);
            Log.d("debug", "soundPool end");
        } catch (InterruptedException ex) {
            Log.d("debug", "InterruptedException");
            // このスレッドを止める
            Thread.currentThread().interrupt();
        }
        Log.d("debug", "onHandleWork end");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("debug", "onDestroy");
    }
}
