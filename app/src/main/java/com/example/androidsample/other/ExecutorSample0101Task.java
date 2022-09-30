/*
 * ExecutorSample0101で使用する非同期処理タスクです。
 *
 * 現状、シングルトンで実装していますが、シングルトンにする必要ないかも、、？？
 *
 */
package com.example.androidsample.other;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorSample0101で使用する非同期処理タスクです。
 * 結果が終了後、結果を呼び出し元に返す必要があるので呼び出し元ではこのクラスのインターフェースメソッドを実装してください。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ExecutorSample0101Task {

    /** タスk実行結果を返すリスナー */
    public interface ExecutorSample0101TaskListener {
        void onPostExecute(double number);
    }

    private static ExecutorSample0101Task myInstance;
    private final ExecutorSample0101TaskListener listener;
    private final ExecutorService service;
    private double num;

    /**
     * このクラスのインスタンスを返します。タスク実行結果を返す必要があるので呼び出し元でこのクラスのリスナーを実装し、
     * パラメーターとして渡してください。
     *
     * @param listener 実行結果を返すためのリスナー
     * @return このインスタンス
     */
    public static ExecutorSample0101Task getInstance(ExecutorSample0101TaskListener listener) {
        if(myInstance == null) {
            myInstance = new ExecutorSample0101Task(listener);
        }
        return myInstance;
    }

    /**
     * このクラス内で呼び出されるコンストラクタです。
     *
     * @param listener 実行結果を返すためのリスナー
     */
    private ExecutorSample0101Task(ExecutorSample0101TaskListener listener) {
        this.listener = listener;
        this.service = Executors.newSingleThreadExecutor();
    }

//    // 実行するタスクです:ラムダ式に変更のためコメントアウト
//    private class TaskRun implements Runnable {
//
//        @Override
//        public void run() {
//            Log.d("debug", "doInBackground()");
//            for(int i = 0; i < 100000000; i++) {
//                num = (double) i / 7 + 0.3;
//            }
//            // 処理完了後。結果を呼び出し元にハンドリング
//            new Handler(Looper.getMainLooper()).post(() -> onPostExecute(num));
//        }
//    }

    /**
     * タスクを開始します。
     */
    public void execute() {
        onPreExecute();

        // this.service.submit(new TaskRun());
        // この部分はラムダ式で書くこともOKです(上のインナークラスが不要になります)
        this.service.submit(() -> {
            Log.d("debug", "doInBackground()");
            // 一億回ループ：パソだと2秒くらいだけど、携帯だといくらくらいだろう・・
            for(int i = 0; i < 100000000; i++) {
                num = (double) i / 7 + 0.3;
            }
            // 処理完了後。結果を呼び出し元にハンドリング
            // TODO:Handler(Looper.getMainLooper()).postについての解説を後で調べること
            // HandlerCompat.createAsyncメソッドを使うという手もある。この場合、シングルトンなので
            // newがいらなくなる。こっちの方がいいかもしれない。詳細はHttpGetSample0101にて
            new Handler(Looper.getMainLooper()).post(() -> onPostExecute(num));
        });
    }
    // 前処理です。
    private void onPreExecute() {
        this.num = 0;
    }

    // 非同期処理が終了後、結果をメインスレッドに返します
    private void onPostExecute(double number) {
        this.listener.onPostExecute(number);
    }

}
