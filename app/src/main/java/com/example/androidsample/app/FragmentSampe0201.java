package com.example.androidsample.app;
/*
 * Fragmentをjavaコードだけで記述する(いつものやつです)
 *
 * 　対象URL：https://akira-watson.com/android/fragment-code.html
 *
 * [手順]
 * ①レイアウトファイルの設定
 * MainActivityのレイアウトファイルでFrameLayoutを設定し、あとでその部分にjavaコードで動的にFragmentを
 * 配置します。
 * ②Actvity.javaの設定
 * ActvityにFragmentを設定していきます。Fragmentの設定には一連のオペレーションであるトランザクション
 * （フラグメントの追加、削除、置換など）を施します。
 *　ソースとして以下処理を行います。
 *  ①FragmentManagerのインスタンス生成
 *      FragmentManager fragmentManager = getSupportFragmentManager();
 *  ②FragmentTransactionのインスタンスを取得
 *      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
 *  ③トランザクションに対して張り付け方を指定する
 *      fragmentTransaction.replace(R.id.container, new SampleFragment());
 *  ④commit()で張り付けを実行する
 *      fragmentTransaction.commit();
 *
 *　また、上記処理はFragmentはActivityのライフサイクル中に何度でも呼ばれてしまうことを避けるため初期時のみ設定するよう
 * する必要があります。具体的にはonCreateの引数「Bundle savedInstanceState」がnullの時だけ上記①～④を行います。
 *
 *[FragmentManager]
 * FragmentManagerは生成されたFragmentのインスタンスの状況を管理して、再度呼ばれると復元してくれます。
 * getSupportFragmentManager()からこのインスタンスを生成できます。
 *
 *[FragmentTransaction]
 *追加、削除、置換などのメソッドが用意されています。
 *
 */
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidsample.R;

/**
 * Fragmentのサンプル02
 * 「6.Fragmentタグ部分をコードで動的に設定する」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/fragment-code.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class FragmentSampe0201 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sample0201);

        /*
        Activityのライフサイクル中に何度でも呼ばれることが可能なので最初(savedInstanceState=null)]
        の時だけ設定をするようにします。
         */
        if(savedInstanceState == null) {
            Log.d("debug", "onCreate()　Bundle=null");
            // FragmentManagerのインスタンス生成
            FragmentManager manager = getSupportFragmentManager();
            // FragmentTransactionのインスタンスを取得
            FragmentTransaction transaction = manager.beginTransaction();
            // トランザクションに対して張り付け方を指定するcontainer
            transaction.replace(R.id.container, new FragmentSampe0101Fragment());
            // 張り付けを実行する
            transaction.commit();

        } else {
            Log.d("debug", "onCreate()　Bundle!=null");
        }
    }
}
