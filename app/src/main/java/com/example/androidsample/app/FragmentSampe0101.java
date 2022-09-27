package com.example.androidsample.app;
/*
 * Fragmentを「Hello World」から始める
 *
 * 　対象URL：https://akira-watson.com/android/fragment-simple.html
 *
 * 【Fragment】
 * AndroidではActivityは重要な機能を司っていますが、勢いでなんでもかんでもActivityに機能を入れてしまっては
 * 巨大なActivityが出来上がってしまいます。
 * これではメンテナンスがやりにくいコードになってしまい、チームでの開発がより困難になります。
 * Fragmentは「断片」という意味ですが、ActivityのUI部分を肩代わりができ、いくつものFragmentをActivityから
 * 開いたり閉じたりできます。また、複数のActivityから同一のFragmentを使いまわすような再利用も可能です。
 *
 * Activityには以下の役割があります
 * １．Viewの生成と制御(⇒ここをFragmentに任せることができる)
 * ２．リソースを取得したりするContextのタスク
 * ３．コンポーネントとしてIntentを受け取る
 *
 * ActivityにはActivityにしか出来ない事を担わせて、View生成と制御の部分はFragmentに任せようということです。
 *
 *
 * 【タグ<fragment>】
 * レイアウトに <fragment> タグを使ってHello Worldを表示させます。ただしこの方法は静的なので拡張性があまりないです。。
 * <fragment>のname属性は「パッケージ名 + Fragmentクラス名」となるようにします。
 *
 */
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * Fragmentのサンプル01
 * 「5.Fragmentを「Hello World」から始める」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/fragment-simple.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class FragmentSampe0101 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sample0101);
    }
}
