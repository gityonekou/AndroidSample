package com.example.androidsample.other;
/*
 * HTTP POST のデータ送信をVolleyで実装する
 * 　対象URL：https://akira-watson.com/android/httpurlconnection-post.html
 *
 * サーバーにデーターを送信するためには、HTTP のPOSTを使って送信できます。
 * 非同期処理の実装は以前はAsyncTaskなどを使っていましたが、Android 11で非推奨となったためVolley使います。
 * Volleyを使うとPOST時の非同期処理を簡単に実装できます。
 *
 * [ネットワーク接続処理は非同期で実装すること]
 * Android 3.0 以降では、メインスレッドでネットワーク処理を行うとエラーとなるように仕様変更されています。
 * また、非同期は以前はAsyncTaskを使っていましたが、Android 11から非推奨となりました。
 * 代案としてはExecutorsやHandlerを使用する等がありますが、Volleyを使うとシンプルです。
 *
 * [Volley]
 * Volleyは非同期機能が提供され、ネットワーク操作を容易化、高速化する HTTP ライブラリで次のような利点があります。
 * ・ネットワーク リクエストの自動スケジューリング。
 * ・複数のネットワークの並行接続。
 * ・標準の HTTP キャッシュ コヒーレンシを備えた透過的ディスク / メモリ レスポンス キャッシュ。
 * ・リクエストの優先順位付けのサポート。
 * ・Cancellation request API。単一のリクエストのキャンセル、ブロックの設定、キャンセル リクエストの範囲の設定ができます。
 * ・容易なカスタマイズ（再試行、バックオフなど）。
 * ・強力な順序付けにより、ネットワークから非同期に取得されるデータを UI に正しく簡単に入力できます。
 * ・デバッグツールとトレースツール。
 *
 * Volleyの概要については以下デベロッパーガイドにて確認ください *
 * 対象URL：https://developer.android.com/training/volley?hl=ja
 *
 *　build.gradleにVolleyのライブラリをインプリメントが必要です。
 * implementation 'com.android.volley:volley:1.2.1'
 *
 * また、インターネット接続を行うのでAndroidManifest.xml にインターネットのパーミッションを追加が必要です
 * <uses-permission android:name="android.permission.INTERNET" />
 *
 *
 *【サンプルについて】
 * 入力内容をサーバに送信し、結果を受け取ります。サーバー側(php)では受け取った内容をもとにhtmlファイルを作成しておきます。
 * ドロイド側では結果受け取り後に確認ボタン押下で対象のhtmlを表示します。
 *
 * また、無料ドメインの場合はhttps接続(SSL)を利用できないので自分のドメインのみhttp接続を許可します
 * 詳細は以下URLにて確認してください。
 * 対象URL：https://backport.net/blog/2018/12/27/how_to_allow_http_on_android_9/
 *
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidsample.R;

import java.util.HashMap;
import java.util.Map;

public class HttpPostSample0101 extends AppCompatActivity {
    private static final String TAG = "HttpPostSample0101";
    private TextView textView;
    private EditText editText;
    private Button postBtn;
    private RequestQueue queue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httppost_sample0101);

        this.textView = findViewById(R.id.httppost_sample0101_textview);
        this.editText = findViewById(R.id.httppost_sample0101_edittext);
        this.postBtn = findViewById(R.id.httppost_sample0101_post_btn);
        this.postBtn.setOnClickListener( v -> httpPost());
        Button browserBtn = findViewById(R.id.httppost_sample0101_browser_btn);
        browserBtn.setOnClickListener( v -> {
            Uri uri = Uri.parse(getResources().getString(R.string.httppost_sample0101_html_url));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }

    /* 入力内容をもとにHttpPostを送信します */
    private void httpPost() {
        String text = this.editText.getText().toString().trim();
        if(text.equals("")) {
            this.textView.setText(R.string.httppost_sample0101_entry_fail);
            return;
        }
        // リクエストキューを生成
        if(this.queue == null) {
            this.queue = Volley.newRequestQueue(getApplicationContext());
        }
        String url = getResources().getString(R.string.httppost_sample0101_php_url);
        StringRequest request = new StringRequest(Request.Method.POST, url, this::onResponse,
                error -> {
                    this.textView.setText(R.string.httppost_sample0101_strerror);
                    this.postBtn.setEnabled(true);
                }) {
            // StringRequestをオーバーライドしリクエストパラメータのmapを返します。
            // オーバーラドするメソッドが1つの場合はこのような書き方もあるのですわ。。。ムズイ。。。
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("word", text);
                return params;
            }
        };
        // リクエストをキューに追加し、送信します。戻るでアクティビティを閉じた際などの処理はここでは割愛します。
        // 詳しくはデベロッパーガイドにて確認
        request.setTag(TAG);
        this.textView.setText(R.string.httppost_sample0101_postsend);
        this.postBtn.setEnabled(false);
        this.queue.add(request);
    }

    /* httppostのレスポンスをtextviewに表示します */
    private void onResponse(String response) {
        StringBuilder message = new StringBuilder(
                getResources().getString(R.string.httppost_sample0101_postsendcommit));
        message.append("[");
        message.append(response);
        message.append("]");
        this.textView.setText(message);
        this.postBtn.setEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // アプリ停止時、リクエストをすべてキャンセルする
        if(this.queue != null) {
            this.queue.cancelAll(TAG);
        }
    }
}
