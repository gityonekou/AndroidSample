package com.example.androidsample.app;
/*
 * 複数のFragment間の画面遷移
 *
 * 　対象URL：https://akira-watson.com/android/fragment-fragment.html
 *
 * 複数のFragment間で画面への切り替えを行うサンプルです。
 * このサンプルではBackStackに状態を保存しながらカウントアップで２つのFragmentを遷移させています。
 *  ・ActivityからFragment01を表示
 *  ・Fragment01からFragment02へ遷移: 0 (カウンター）
 *  ・Fragment02からFragment01へ遷移: 1
 *  ・Fragment01からFragment02へ遷移: 2
 *  ・…
 *  ・Fragment02からFragment01へ遷移: 10
 *  ・
 *  ・BackStackを使って戻ります
 *  ・Fragment01からFragment02へ戻る: 9
 *  ・…
 *  ・Fragment01からFragment02へ戻る: 1
 *  ・Fragment02からFragment01へ戻る: 0
 *  ・Fragment01からActivityへ戻る
 *
 * 「BackStackを使って戻るには」
 * バックスタックを使って積み上げたスタックを戻る操作はpopBackStack()を使います。
 * // BackStackに積む
 * FragmentManager fragmentManager = getParentFragmentManager();
 * fragmentManager.addToBackStack(null)※この画面のみをバックスタッフに積む
 * // １つ前に戻る
 * FragmentManager fragmentManager = getParentFragmentManager();
 * fragmentManager.popBackStack();
 *
 * サンプルにあるgetFragmentManager()は非推奨となったため注意
 * 今回のサンプルのようなフラグメントの切り替えは上位(MainActivity)にイベントを通知して
 * 切り替えてもらう形のほうがフラグメントとしての独立性が保たれるという観点もあり、サンプル0402にてコーディング
 * ※ただ、FragmentにViewの処理を分割するという観点でいえばその分アクティビティの処理が増えてメンテしにくいコードになる
 * ケースバイケースではあるが、Fragment側のほうが自分的には良いような気がする
 * また、getParentFragmentManagerで取得する場合はgetSupportFragmentManager同様に必ず
 * インスタンスが帰る(nullになることはない)のでif文によるnull判定は不要です
 *
 */
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidsample.R;

public class FragmentSampe0401 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sample0401);

        if(savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.fragment_sample0401_container,
                    FragmentSampe0401Fragment01.newInstance(0));
            transaction.commit();
        }
    }

    /* アプリデフォルトの戻るボタン押下時 */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(getSupportFragmentManager().getBackStackEntryCount() < 1) {
            finish();
        }
    }
}
