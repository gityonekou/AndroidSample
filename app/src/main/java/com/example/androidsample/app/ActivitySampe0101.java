package com.example.androidsample.app;
/*
 * Activityの画面遷移
 * アプリの画面を遷移させる
 *
 * 　対象URL：https://akira-watson.com/android/activity-1.html
 *
 * アプリの画面を遷移させる方法は主に以下があります。
 * ①Activityを変えて別のActivityへ画面遷移
 * 	画面だけでなく使用する変数も全く異なるActivityへの移行
 * 	Activityの変更のため内部処理が多く、多少の遅れもあり得る
 *
 * ②Fragmentを使用し画面表示を任せる
 * 	Activityは画面管理以外にも色々とtaskがあるので画面の変更だけに使うには
 * 	コスト高です。Activityを変えずにFragmentで画面を複数張り付けることが
 * 	可能です。
 *
 * ③同一Activityでの画面変更
 * 	使用する変数は同じだが値が異なる、名簿などで名前、住所などを
 *	データベースから取り出すだけで表示フォーマットは同じ様なケースなど
 * 	Activityの画面遷移に比べて素早くできる（そうなる場合に有効）
 * 	ただし、１つのActivityが複雑な構成になってメンテナンスに支障が
 * 	出ないように注意する必要がある。
 *
 * このサンプルは上記1の
 * 「Activityを変えて画面遷移」をしたい場合に使う「Intent」のサンプルです。
 *
 *　MainActivity から SubActivity への遷移は以下のように記述します。
 *
 *　Intent intent = new Intent(MainActivity.this, SubActivity.class);
 *　startActivity(intent);
 *
 *　上記ではIntent のインスタンスを、現在のActivityから、遷移先のActivityを
 *　引数に入れて生成します。ただ、「MainActivity.this」の記述だとテストで動かす
 *　ぐらいであれば問題はないのですが、何度も同じ画面移動が繰り返されると
 *　強参照のためメモリがパイルアップし続けてOut of Memoryとなる可能性が
 *　あります。
 *
 *　状況に応じて「getApplication()」、または「getApplicationContext()」などに
 *　変更したほうがいいようです
 *
 *　Intent intent = new Intent(getApplication(), SubActivity.class);
 *　startActivity(intent);
 *
 *　SubActivity からの戻りはActivity を終了させます
 *
 *　finish();
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * アクティビティ遷移のサンプル01
 * 「1.Activityの画面遷移(main⇔sub)」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/activity-1.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ActivitySampe0101 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sample0101_main);

        Button btn = findViewById(R.id.send_button);
        btn.setOnClickListener( v -> startActivity(
                new Intent(getApplication(), ActivitySampe0101SubActivity.class)
        ));

    }
}
