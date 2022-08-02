package com.example.androidsample.app;
/*
 * Serviceの使い方
 *
 * 　対象URL：https://akira-watson.com/android/service.html
 *
 * 【Servceについて】
 * Servceはバックグラウンドで作業をさせたい場合に使います。システムはすぐにスリープ状態になるが、
 * 裏でいろいろ作業をやりたい場合に有効です。（例えば音楽の再生などがあります）
 * Serviceがバックグラウンドで動作するので、非同期と勘違いしそうですがそうではなく、Activityから表示UIを
 * 無くしたようなコンポーネントになります。ですから別スレッドでの処理が必要なものはActivityと同じように扱います。
 * （例えばバックグラウンドでのネットからのデータダウンロードなど）
 * 別スレッド実装は「IntentService」を使うと簡単になります。(サンプル：ServiceSampe0301を参照
 * また、サービス | Android Developers には以下のようにあるのでIntentServiceを推奨しているようでもあります。
 *******************************************************
 * Android Developersガイドより
 * ※Android Developers：https://developer.android.com/guide/components/services.html?hl=ja
 *「開始されたサービスで同時に複数の要求を処理する必要があることはほとんどないため（実際には危険なマルチスレッド
 * シナリオになります）、IntentServiceクラスを使用してサービスを実装するのが最適だと考えられます。」
 *
 *「サービスとスレッドのどちらを選択するか」
 * サービスは、ユーザーがアプリを操作していない間もバックグラウンドで実行できるコンポーネントにすぎません。
 * そのため、そのような必要がある場合にのみ、サービスを作成します。
 * ユーザーがアプリを操作している間に限り、メインスレッドの外部で作業を行う必要がある場合は、代わりに新しいスレッドを
 * 作成してください。たとえば、アクティビティを実行している間だけ音楽を再生する場合は、onCreate() でスレッドを作成し、
 * onStart() で実行を開始して、onStop() で停止します。
 * また、従来の「Thread」クラスの代わりに「AsyncTask」や「HandlerThread」を使用する方法もあります。
 * スレッドの詳細については、プロセスとスレッドのドキュメントをご覧ください。
 * サービスを使用する際は、デフォルトではアプリのメインスレッドで実行されるため、集中的な作業やブロック操作を
 * 行う場合はサービス内に新しいスレッドを作成する必要があることに注意してください。
 *********************************************************
 *【Serviceの開始】～【Serviceの終了】
 * コーディング方法などは本家URL参照にて。
 *
 * 「サンプルコード」
 * Activityから「startForegroundService()」でServiceを開始し、Service内で５秒以内に「startForeground()」
 * を呼び出します。これでステータスバーにはアイコンが表示されてForgroundにいるかのようになるということでしょう。
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ServiceSampe0101 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_sample0101);

        Button startBtn = findViewById(R.id.service_sample0101_start_button);
        Button stopBtn = findViewById(R.id.service_sample0101_stop_button);
        startBtn.setEnabled(true);
        startBtn.setOnClickListener( v -> {
            Intent intent = new Intent(getApplicationContext(), ServiceSampe0101TestService.class);
            intent.putExtra(ServiceSampe0101TestService.REQUEST_CODE, 1);
            // サービスの開始
            // Oreoから制限が追加されたためstartServiceは将来的に非推奨となる可能性あり。
            // startForegroundService()を使用するようにすること
            // startService(intent);
            startForegroundService(intent);
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
        });
        stopBtn.setEnabled(false);
        stopBtn.setOnClickListener( v -> {
            startBtn.setEnabled(true);
            stopBtn.setEnabled(false);
            // サービスの停止
            stopService(new Intent(getApplicationContext(), ServiceSampe0101TestService.class));
        });
    }
}
