package com.example.androidsample.app;
/*
 * ActivityとFragmentの画面遷移
 *
 * 　対象URL：https://akira-watson.com/android/activity-fragment.html
 *
 * ActivityからFragment画面への切り替えを行うサンプルです。
 * いわゆる画面遷移ですが、Activity間の移動ではないので重くありません。
 * このサンプルでは
 * １．任意のタイミングで起動
 * ２．パラメータを渡してインスタンス生成
 * ３．Activityに戻る
 * という観点を考えてみます。
 * 追加でフラグメント表示時はボタンを非アクティブにして戻るボタン押下時にボタンを有効にする処理を追加しています。
 *
 * 【ポイント：Fragmentのインスタンス生成について】
 * フレームワークは必要に応じFragmentを再インスタンス化することが多くあります。その時フレームワークは引数のない
 * コンストラクタ(デフォルトコンストラクタ)を用いてインスタンスを生成するため利用できないと例外が発生してしまいます。
 * 解決方法としては、
 * 「静的な newInstance()を使ってフラグメントをインスタンス化する」
 * これがベストプラクティスと言われています。
 * 【ダメな方法】fragmentTransaction.replace(R.id.container, new SampleFragment("Fragment"));
 * 【ベスト】fragmentTransaction.replace(R.id.container, SampleFragment.newInstance("Fragment"));
 *
 * [フラグメントからActivityに戻る]
 * Activityに戻る必要がある場合（ほとんど該当はするが、、）にはフラグメント設定時にバックスタック設定します。
 * fragmentTransaction.addToBackStack(null);
 * ※スタックに保存するフラグメントが複数ではなく、現在のものだけを保存する場合はnullを指定する
 * これをトランザクションに設定すると、トランザクションの開始時のFragment状態がスタックに積まれて
 * 戻るボタンで前の状態に戻ることが可能になります。
 *
 * [アプリの戻るボタンの処理]
 * アプリの戻るボタン[左下の◀ボタン]押下時はonBackPressed()メソッドをオーバーライドして実装します。
 *
 */
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidsample.R;

public class FragmentSampe0301 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sample0301);

        Button btn = findViewById(R.id.fragment_sample0301_button);
        if(savedInstanceState == null) {
            Log.d("debug", "onCreate()　Bundle=null");
            btn.setOnClickListener( v -> {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                // 「戻る」時のバックスタッフを設定
                // また、スタックに保存するフラグメントが複数ではなく、現在のものだけを保存する場合はnullを指定する
                transaction.addToBackStack(null);
                // フラグメントの貼り付け位置を設定
                transaction.replace(R.id.fragment_sample0301_container,
                        FragmentSampe0301Fragment.newInstance("Fragment"));
                transaction.commit();
                btn.setEnabled(false);

            });
        } else {
            Log.d("debug", "onCreate()　Bundle!");
        }
    }

    /* 左下の戻るボタン押下時にボタンをアクティブにする */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Button btn = findViewById(R.id.fragment_sample0301_button);
        btn.setEnabled(true);
    }
}
