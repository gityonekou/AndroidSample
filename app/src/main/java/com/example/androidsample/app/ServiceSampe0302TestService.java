package com.example.androidsample.app;
/*
 * IntentServiceはアンドロイド11から非推奨となり代わりにJoIntentServiceを使うようになりました。
 * 今サンプルではJobIntentServiceにてコードを書き換えています。
 *
 * ServiceSampe0302ではSoundPoolを使ってゲームの効果音の再生します。
 *
 *****
 *  ver2.00.00 更新
 * ・JobIntentService非推奨化対応:WorkManagerの実装
 *
 */
import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.androidsample.R;

public class ServiceSampe0302TestService extends Worker {


    private final SoundPool soundPool;
    private final int soundOne;
    private boolean loadFlg = false;

    public ServiceSampe0302TestService(
            @NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        Log.d("debug", "instance new called.");
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
                getApplicationContext(), R.raw.omoidenohamabe, 1);
        // ロードが終わったかどうかを判定するリスナー
        this.soundPool.setOnLoadCompleteListener((soundPool1, sampleId, status) -> {
            // status=0でロード完了
            if(status == 0) {
                this.loadFlg = true;
            }
        });
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("debug", "doWork start");
        int count = 1;
        try {
            // ロードが終わるまで時間を潰す
            do {
                // ビジー状態の警告は無視する
                Thread.sleep(500);
                Log.d("debug", "sleep: " + count);
                count++;
            } while(!this.loadFlg && count < 200);

            if(this.loadFlg) {
                Log.d("debug", "soundPool start");
                //　再生
                this.soundPool.play(this.soundOne, 1.0f, 1.0f, 0, 0, 1);
                Log.d("debug", "soundPool end");
            } else {
                Log.d("debug", "soundPool download time out");
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
