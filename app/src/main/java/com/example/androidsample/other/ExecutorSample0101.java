/*
 * 非同期処理 Executorの使い方
 * 　対象URL：https://akira-watson.com/android/asynctask.html
 *
 * AsyncTask が非推奨になったので代わりに、Executorを使った非同期処理はどう実装するのか見てみます。
 * 非同期処理が必要なケースは、メインスレッドでアプリがUIを表示させたり、ユーザーが入力したりしている裏でいろいろと
 * 処理をして欲しい場合です。画像などの大きなファイルをダウンロードする場合も非同期を使います。
 * Google AsyncTask によれば、代わりに
 * java.util.concurrent 、 Kotlinであれば concurrency utilities が推奨されています。
 * ここでは、java.util.concurrent.Executor を使っていきます。
 *
 * AsynTaskの基本構造はにゃんサンプルにて確認してください。
 * 上記をExecutorに当てはめていきますが、java.util.concurrent.Executorを継承したExecutorServiceから、
 * 単一のワーカー・スレッドだけを設定するnewSingleThreadExecutor()のメソッドを使っていきます。
 *
 * ExecutorService executorService = Executors.newSingleThreadExecutor();
 * ...
 * // 現在ではラムダ式でインナークラスの記述をやめるのが普通です。サンプルの書き方で見やすいのでこちらを記述しています
 * private class MyTaskRun implements Runnable {
 *      @Override
 *      public void run() {
 *          // 非同期処理
 *          ****
 *          // 処理完了後ハンドリング(パターン1とパターン2があるのでどちらかで実装)
 *          // パターン1：このサンプルで使っています：newのインスタンスを代入しているので、もしかしたら下の方がいいかも
 *          new Handler(Looper.getMainLooper()).post(() -> onPostExecute());
 *          // パターン2：こちらはHttpGetSample0101で使っています
 *          HandlerCompat.createAsync(Looper.getMainLooper()).post(() -> onPostExecute());
 *
 *      }
 * }
 * void execute() {
 *      onPreExecute();
 * 　　　// 現在ではラムダ式でインナークラスの記述をやめるのが普通です。
 *      executorService.submit(MyTaskRun());
 * }
 * void onPreExecute() {
 *      // 前処理
 * }
 * void onPostExecute() {
 *      // 非同期処理が終了後、結果をメインスレッドに返す
 * }
 *
 */
package com.example.androidsample.other;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * 非同期処理 Executorの使い方 サンプル01
 * 「1.非同期処理 Executorの使い方」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/asynctask.html
 *
 *【サンプルについて】
 * ボタン押下でタスクを実行し、結果をTextViewに表示します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ExecutorSample0101 extends AppCompatActivity
        implements ExecutorSample0101Task.ExecutorSample0101TaskListener {

    private TextView textView;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executor_sample0101);

        this.textView = findViewById(R.id.executor_sample0101_textview);
        this.btn = findViewById(R.id.executor_sample0101_button);
        this.btn.setOnClickListener( v ->{
            this.btn.setEnabled(false);
            ExecutorSample0101Task.getInstance(this).execute();
            this.textView.setText(R.string.running);
        });
    }

    @Override
    public void onPostExecute(double number) {
        this.btn.setEnabled(true);
        String message = "Total:" + number;
        this.textView.setText(message);
    }
}
