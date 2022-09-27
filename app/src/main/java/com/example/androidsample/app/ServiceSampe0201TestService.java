package com.example.androidsample.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.example.androidsample.R;

/**
 * ServiceSampe0201で開始されるサービスです。
 * 画像をアプリレイア「TYPE_APPLICATION_OVERLAY」上で表示します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ServiceSampe0201TestService extends Service {
    private int dpScale;
    private View view;
    private WindowManager windowManager;

    @Override
    public void onCreate() {
        super.onCreate();
        this.dpScale = (int)getResources().getDisplayMetrics().density;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        /* Foreground Serviceを開始 */
        // API 28からはForeground Serviceでのパーミッシンが必要
        // API 31から PendingIntent の可変性を指定する
        Context context = getApplicationContext();
        // PendingIntent は作成したIntentをタイミングを見て他のアプリケーションに渡す場合に使います。
        // 詳細は[Android] AlarmManagerをBroadcastRecieverと使うを参照
        // https://akira-watson.com/android/alarmmanager-timer.html
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_IMMUTABLE);
        // 画面上部の通知サービスにアクセスしてタイトル内容を通知表示
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        if(notificationManager != null) {
            // 通知内容をもとにチャネルを作成し通知サービスに設定
            String channelId = "default";
            String title = context.getString(R.string.service_sampe0201_title);
            notificationManager.createNotificationChannel(new NotificationChannel(
                    channelId, title,NotificationManager.IMPORTANCE_DEFAULT));
            Notification notification = new Notification.Builder(context, channelId)
                    .setContentTitle(title)
                    // android標準アイコン
                    .setSmallIcon(android.R.drawable.btn_star)
                    .setContentText("APPLICATION_OVERLAY")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .build();
            // start foreground
            startForeground(1, notification);
        }

        /* 表示画像のレイアウト設定 */
        LayoutInflater inflater = LayoutInflater.from(this);
        this.windowManager = (WindowManager) getApplicationContext().getSystemService(
            Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        //  右上に配置
        params.gravity = Gravity.TOP | Gravity.END;
        params.x = 20 * this.dpScale; // x=20dp
        params.y = 80 * this.dpScale; // y=80dp
        // レイアウトファイルからinflateするviewを作成
        // TODO:rootviewがnullなことに警告が出るがここでは無視する
        this.view = inflater.inflate(R.layout.service_service_sample0201_layer, null);
        // 画像タッチ時サービスを終了する
        //CustomImageView image = view.findViewById(R.id.service_sample0201_imageview);
        this.view.setOnTouchListener((View v, MotionEvent me) -> {
            Log.d("debug","onTouch");
            if(me.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d("debug","ACTION_DOWN");
            } else if(me.getAction() == MotionEvent.ACTION_UP) {
                Log.d("debug","ACTION_UP");
                // Viewを独自に作成しperformClickをオーバーライドするように警告が出るがここでは無視する
                // 詳細はImageViewSampe0302を参照(イメージViewに変更したがうまくクリックを認識しなかった・・
                this.view.performClick();
                // サービスを停止
                stopSelf();
            }
            return false;
        });

        // viewを画面上に追加
        this.windowManager.addView(this.view, params);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // viewを削除
        this.windowManager.removeView(this.view);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
