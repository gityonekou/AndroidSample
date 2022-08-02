package com.example.androidsample.app;
/*
 * Activity(main⇔sub)間のデータ受け渡し
 *
 * 　https://akira-watson.com/android/activity-2.html
 *
 * Activity間でデータを渡す用途として以下が考えられます。
 * １．遷移先のActivityにデータを渡したい。
 *      ・putExtra()⇒startActivity()
 *      ・呼び出し先ではget？？Extra()でデータを取り出し、画面を閉じるタイミングでfinish()を忘れずに
 * ２．遷移先のActivityから戻るタイミングでデータを受け取りたい。
 * 　　つまり、呼び出し元のActivityで子から渡されたデータを受け取りたい。
 *      ・呼び出し元：registerForActivityResult()でランチャーを生成：putExtra()⇒launchでサブを呼び出し。
 *      ・呼び出し先：get？？Extra()でデータを取り出し、putExtra()⇒setResult()で返却データを設定、finish()を忘れずに
 *
 * どちらの場合もIntentを使ってActivity間でデータを渡すことができます。
 * ただ、呼び出し元のActivityで呼び出し先でIntentに設定したデータを受け取るには
 * 以前は、startActivityForResult(Intent, int)やonActivityResult(int, int, Intent)を使っていましたが
 * 今はregisterForActivityResult()を使うことが推奨されています。⇒基本こちらでコーディングすること
 *
 *　また、Intent.putExtra()やIntent.get？？Extra()で指定するキー(name)はパッケージ名をprefixとして
 * 含めることを強く推奨しています。
 *
 *
 *【サブアクティビティに渡すデータを設定する】
 * <呼び出し先にただデータを渡すのみの場合>
 * Intent intent = new Intent(getApplication(), SubActivity.class);
 * intent.putExtra(EXTRA_DATA, data1);
 * startActivity(intent)
 *
 * --------------------------------------------------------------------
 * <呼び出し先からデータを受け取りたい場合>
 * SubActivity からの返しを受け取りたい場合はregisterForActivityResult() を使います。
 *<ランチャー変数を生成時>
 *ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
 *	new ActivityResultContracts.StartActivityForResult(),
 *	new ActivityResultCallback<ActivityResult>() {
 *	    @Override
 *	    public void onActivityResult(ActivityResult result) {
 *	        if (result.getResultCode() == Activity.RESULT_OK) {
 *	            ...
 *	        }
 *	    }
 *});
 *
 *
 *<Intent生成時(リスナー内など)>
 *Intent intent = new Intent(getApplication(), SubActivity.class);
 *intent.putExtra(EXTRA_DATA, data1);
 *resultLauncher.launch(intent);★ポイント：startActivity(intent)ではない点に注意
 *
 *
 *registerForActivityResult() は、
 *「ActivityResultContract」と「ActivityResultCallback」を引数に渡し、
 *他のアクティビティを開始するために使用するActivityResultLauncherを返します。
 *(つまり、startActivity(intent)では出来ないので注意
 *
 * 【サブアクティビティでデータを受け取る】
 * 渡されたデータを受け取るには
 * Intent intent = getIntent();
 * data1 = intent.get？？Extra(MainActivity.EXTRA_DATA);
 *
 * 受け取るときはデータ型に対応するgetメソッドと取得する値のキーを指定します。
 * データが「int」の場合など、初期値設定が必要なときは初期値を入れます「intent.getIntExtra(name, defaultValue)」
 *
 * 【SubActivity から戻るとき】
 * SubActivity から戻るときにデータを返すには同様に「putExtra(name, value)」を使います
 *
 * <呼び出し元にデータを渡す必要がない場合>
 *  画面を閉じるタイミングでfinish()する。
 * <呼び出し元にデータを渡す必要がある場合>
 *  Intent intent = new Intent();
 *  intent.putExtra(MainActivity.EXTRA_DATA, result);
 *  setResult(RESULT_OK, intent);
 *  finish();
 *
 *【元の画面でデータを受け取る】
 * SubActivity から ActivityResultLauncher でデータを受け取り、RESULT_OKであるか判定してデータを受け取ります。
 * ソース自体は上記<ランチャー変数を生成時>のonActivityResultメソッドオーバーライド時の処理となります。
 *　ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
 *	new ActivityResultContracts.StartActivityForResult(),
 *	new ActivityResultCallback<ActivityResult>() {
 *	    @Override
 *	    public void onActivityResult(ActivityResult result) {
 *	        if (result.getResultCode() == Activity.RESULT_OK) {
 *	            ...
 *	        }
 *	    }
 *});
 *
 * 【サンプルコードの説明】
 * 以下のような機能でまとめてみます。
 * １．MainActivityで文字入力
 * ２．SubActivityで受け取って表示
 * ３．SubActivityでは、受け取った文字列にさらに追加してmainに戻す
 * ４．MainActivityではSubActivityから戻された文字列を表示
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ActivitySampe0201 extends AppCompatActivity {

    public static final String EXTRA_MESSAGE
            = "com.example.androidsample.app.ActivitySampe0201.MESSAGE";

    private TextView textView;

//    ActivityResultLauncher<Intent> resultLancher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if(result.getResultCode() == Activity.RESULT_OK) {
//                        Intent intent = result.getData();
//                        if(intent != null) {
//                            getTextView().setText(intent.getStringExtra(EXTRA_MESSAGE));
//                        }
//                    }
//                }
//            }
//    );
    // call backをラムダ式で記述。リスナーをnewする場合は上を参照
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if(intent != null) {
                        this.textView.setText(intent.getStringExtra(EXTRA_MESSAGE));
                    }
                }
            }
    );
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sample0201_main);
        this.textView = findViewById(R.id.text_view);
        final EditText editText = findViewById(R.id.edit_text);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener( v -> {
            Intent intent
                    = new Intent(getApplicationContext(), ActivitySampe0201SubActivity.class);
            if(editText.getText() != null) {
                intent.putExtra(EXTRA_MESSAGE, editText.getText().toString());
            }
            resultLauncher.launch(intent);
            // edittextをクリア
            editText.setText("");
        });
    }
}
