package com.example.androidsample.app;
/*
 * ServiceSampe0101で開始されるサービスです。
 * MediaPlayerを使ってmp3を再生します。
 *
 * 【あとで調べる必要ありのコード】
 * return START_NOT_STICKY;
 * return START_STICKY;
 * return START_REDELIVER_INTENT;
 *
 * 呼び出し元の[開始]ボタン、[停止]ボタンは実際には音楽再生されるタイミングで通知を出して[開始]を非アクティブにして[停止]
 * をアクティブにするなど、サービスから呼び出し元のActivityに通知を出す方法を実装する必要があります。
 * 今回のサンプルではその部分は未コーディング状態です。
 */
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.androidsample.R;

public class ServiceSampe0101TestService extends Service {

    public static final String REQUEST_CODE = "REQUEST_CODE";
    private MediaPlayer mediaPlayer;
    private int counter = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("debug", "onCreate()");
        this.mediaPlayer = MediaPlayer.create(this, R.raw.yuruyuri01);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /* このメソッドでサービスで処理する内容をガリガリします。superは上書きするので呼ばなくてもOKです。 */
        Log.d("debug", "onStartCommand()");

        int requestCode = intent.getIntExtra(REQUEST_CODE, 0);
        Context context = getApplicationContext();
        String channelId = "default";
        //String title = context.getString(R.string.service_sampe0101_title);
        String title = getString(R.string.service_sampe0101_title);

        // ・API31から PendingIntent の可変性を指定する必要が生じました。詳細はサンプル02で
        // PendingIntent は作成したIntentをタイミングを見て他のアプリケーションに渡す場合に使います。
        // 詳細は[Android] AlarmManagerをBroadcastRecieverと使うを参照
        // https://akira-watson.com/android/alarmmanager-timer.html
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent,
                PendingIntent.FLAG_IMMUTABLE);
        /* 画面上部の通知サービスにアクセスして再生音楽タイトル(titleの値)を通知表示する */
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        if(notificationManager != null) {
            // 通知内容をもとにチャネルを作成し通知サービスに設定
            notificationManager.createNotificationChannel(new NotificationChannel(
                    channelId, title, NotificationManager.IMPORTANCE_DEFAULT ));
            Notification notification = new Notification.Builder(context, channelId)
                    .setContentTitle(title)
                    // アンドロイド標準アイコンより
                    .setSmallIcon(android.R.drawable.ic_media_play)
                    .setContentText("MediaPlay")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .build();
            // start foreground
            startForeground(1, notification);

            audioStart();
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(this.mediaPlayer != null) {
            Log.d("debug", "end of audio.");
            audioStop();
        }
        // サービス終了
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * オーディオを再生します。
     */
    private void audioStart() {
        this.counter++;
        Log.d("debug", "audioStart:" + this.counter);
        if(this.mediaPlayer != null) {
            // ループ再生する(しない場合はfalseを指定)
            this.mediaPlayer.setLooping(true);
            // 再生
            this.mediaPlayer.start();

            // 再生内容をトーストで表示
            Toast.makeText(this,
                    getString(R.string.service_sampe0101_toast),
                    Toast.LENGTH_LONG).show();

            // 終了を検知するリスナー
            this.mediaPlayer.setOnCompletionListener( mp -> {
                Log.d("debug", "end of audio.");
                audioStop();
                // サービス終了
                stopSelf();
            });
        } else {
            Log.d("error", "MediaPlayer is null");
        }
    }
    /**
     * オーディオを終了します
     */
    private void audioStop() {
        // 再生終了
        this.mediaPlayer.stop();
        // リセット
        this.mediaPlayer.reset();
        // リソースの開放
        this.mediaPlayer.release();
        this.mediaPlayer = null;
    }
}
