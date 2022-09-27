package com.example.androidsample.app;
/*
 * WindowManagerを使ってServiceから画像を表示させ続ける
 *
 * 　対象URL：https://akira-watson.com/android/windowmanager.html
 *
 * 他のアプリ画面上にアイコン画像などを表示させることが、ServiceとWindowManagerを組み合わせるとできます。
 *
 * [このサンプルでは]
 * 通常、別のアプリが起動すると、当初あったアプリはバックグラウンドに移動させられてしまいますが、
 * ここではそのバックグラウンドから画像を表示させて後から起動したアプリの上にかぶせて表示します。
 * Serviceなので確かにバックグラウンドで長期間のタスクを実行してくれますが、システムがユーザーに電池を消費していると
 * 知らせたりしますので、アプリをアンインストールされないように注意しましょう。
 *
 * 【SYSTEM_ALERT_WINDOW】
 * ・API23からPermissionによるユーザーの許可が必要
 * ・API26からは使えるレイヤーがTYPE_APPLICATION_OVERLAYになりそれまでの上位レイヤーは非推奨
 * ・API28からはForeground Serviceでのパーミッシンが必要
 * ・API31からstartActivityForResult()とonActivityResult()が非推奨
 * 　⇒Acticityへの通知はregisterForActivityResult()を使う
 * ・API31から PendingIntent の可変性を指定する
 * 　⇒FLAG_IMMUTABLE：一般的なケースではこれで足りる
 * 　⇒FLAG_MUTABLE：アプリ側との連携で情報を更新してもらう場合
 *
 * 【Settings.canDrawOverlays()】
 * SYSTEM_ALERT_WINDOWのPermissionの設定が必要です。
 * ⇒アクティビティのpublic void checkPermission()内処理に該当
 * SYSTEM_ALERT_WINDOW によるとAPI23以上では明示的なユーザー許可を「permission management screen」から
 * 取得します。
 * intent action 「ACTION_MANAGE_OVERLAY_PERMISSION」を投げて、 Settings.canDrawOverlays() から
 * 許可の有無を確認するようにします。
 *
 * 【TYPE_APPLICATION_OVERLAY】
 *　Androidの表示レーヤーはたくさんあり、それぞれ情報のpriorityによって上位から設定されています。
 * API level26まではいくつかのレイヤーが使えたのですが、残念ながら使用することは非推奨となりました。
 * 代わりにTYPE_APPLICATION_OVERLAYを使います。
 * レイヤー表示はWindowManager.LayoutParamsを使って設定していきます。
 * 　WindowManager.LayoutParams (
 * 　　int w, // 幅
 * 　　int h, // 高さ
 * 　　int xpos, // x位置
 * 　　int ypos, // y位置
 * 　　int _type,  // 表示するレイヤータイプ(TYPE_SYSTEM_ALERTなど)
 * 　　int _flags,  // viewのonTouch等の設定
 * 　　int _format // pixel formatなど
 * );
 * レイヤータイプとflagsは様々ありますのでWindowManager.LayoutParams Referenceを確認してください。
 *
 *
 */
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * WindowManagerを使ってServiceから画像を表示させ続ける
 * 「11.WindowManagerを使ってServiceから画像を表示させ続ける」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/windowmanager.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ServiceSampe0201 extends AppCompatActivity {

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Log.d("debug", "registerForActivityResult ResultCode=" + result.getResultCode());
                if(result.getResultCode() == Activity.RESULT_OK) {
                    Log.d("debug", "registerForActivityResult ResultCode=RESULT_OK");
                } else if(result.getResultCode() == Activity.RESULT_CANCELED) {
                    Log.d("debug", "registerForActivityResult ResultCode=RESULT_CANCELED");
                }
                // 「他のアプリの上に重ねて表示」の設定が許可されたかどうかを判定し、まだ未設定の場合はトーストで表示
                if(!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this,
                            getString(R.string.service_sampe0201_invalid_permission),
                            Toast.LENGTH_LONG).show();
                }
            }
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_sample0201);

        Button startBtn = findViewById(R.id.service_sample0201_start_button);
        // 開始ボタン押下でサービスを開始する(API26以上対象)
        startBtn.setOnClickListener(v -> checkPermission());
    }

    private void checkPermission() {
        Log.d("debug", "called checkPermission()");
        /* API23からPermissionによるユーザーの許可が必要 */
        // 上記解説のAPI23からPermissionによるユーザーの許可が必要の部分に該当するコードです。
        // ACTION_MANAGE_OVERLAY_PERMISSIONを投げて許可の有無を判定します。
        // このパーミッションを取得せずに、TYPE_APPLICATION_OVERLAY のレイヤにビューを表示しようとすると、
        // WindowManager$BadTokenException という RuntimeException がServiceSampe0201TestServiceの
        // this.windowManager.addView(this.view, params);のところで発生するので注意
        if(!Settings.canDrawOverlays(this)) {
            //「他のアプリの上に重ねて表示」の設定が未許可の場合は設定画面に遷移する
            Uri uri = Uri.parse("package:" + getPackageName());
            Log.d("debug", "DrawOverlays is false[uri:" + uri.toString() + "]");
           resultLauncher.launch(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri));
        } else {
            // 「他のアプリの上に重ねて表示」の設定が許可されている場合はサービスを開始
            Log.d("debug", "DrawOverlays is true");
            startForegroundService(new Intent(
                    getApplicationContext(), ServiceSampe0201TestService.class));
        }
    }
}
