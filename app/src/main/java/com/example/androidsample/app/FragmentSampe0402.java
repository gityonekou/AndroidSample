/*
 * 複数のFragment間の画面遷移(貼り付け部分の通知版)
 *
 * 　対象URL：https://araramistudio.jimdo.com/2018/03/01/
 * 　上記ブログ一覧の右記事を参照：「AndroidでFragmentの結果をActivityで受け取る」
 *
 * フラグメントの貼り付け部(FragmentManagerを呼び出し、トランザクションを生成、replace、commit部分を
 * フラグメントクラス内に記述するのではなくメインアクティビティに通知して処理を行うサンプルです。
 * イベントを通知して切り替えてもらう形のほうがフラグメントとしての独立性が保たれるので現在の開発ではこちらの方法
 * が主に使われているよう。。。サンプルにはなさそうなので頑張って作るしかない。。
 *
 */
package com.example.androidsample.app;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidsample.R;

/**
 * 複数のFragment間の画面遷移(貼り付け部分の通知版)
 * 「9.画面遷移(Fragment⇔Fragment)貼り付け部分の通知版」に対応するアクティビティです。
 *
 * 　対象URL：https://araramistudio.jimdo.com/2018/03/01/
 * 　上記ブログ一覧の右記事を参照：「AndroidでFragmentの結果をActivityで受け取る」
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 */
public class FragmentSampe0402 extends AppCompatActivity
        implements FragmentSampe0402FragmentActionListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sample0401);

        if(savedInstanceState == null) {
            replaceFragment(FRAGMENT_SAMPLE0402_FRAGMENT01, 0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() < 1) {
            finish();
        }
    }

    @Override
    public void replaceFragment(int fragmentType, int count) {
        // フラグメントタイプによりフラグメントを生成
        Fragment fragment;
        if(fragmentType == FRAGMENT_SAMPLE0402_FRAGMENT01) {
            fragment = FragmentSampe0402Fragment01.newInstance(count);
        } else if(fragmentType == FRAGMENT_SAMPLE0402_FRAGMENT02) {
            fragment = FragmentSampe0402Fragment02.newInstance(count);
        } else {
            Log.d("error", "fragmentType value=" + fragmentType);
            return;
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_sample0401_container, fragment);
        transaction.commit();
    }

    @Override
    public void removeFragment(int fragmentType) {
        FragmentManager manager = getSupportFragmentManager();
        if(manager.getBackStackEntryCount() > 1) {
            manager.popBackStack();
        } else {
            if(fragmentType == FRAGMENT_SAMPLE0402_FRAGMENT01) {
                finish();
            } else if(fragmentType == FRAGMENT_SAMPLE0402_FRAGMENT02) {
                Log.d("error", "不明な画面遷移です。BackStackEntryCount="
                        + manager.getBackStackEntryCount());
            }
        }
    }
}
