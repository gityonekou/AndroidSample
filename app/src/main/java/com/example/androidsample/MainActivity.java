package com.example.androidsample;
/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * トップページに各種サンプルの動作を確認できるリンクのページを作成しています。
 *
 **************************************
 * 変更履歴:ver2.00.00
 * トップメニュー、サブメニューをフラグメントに変更
 * ・メニュー部の表示本体の処理をMainMenuFragmentに移行しMainActivityの該当処理を削除。
 * ・フラグメントのMainMenuFragment呼び出し処理を追加
 *
 */
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.menu_container, MainMenuFragment.newInstance());
            transaction.commit();
        }
    }
}