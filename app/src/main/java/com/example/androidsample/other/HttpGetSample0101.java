package com.example.androidsample.other;
/*
 * HttpURLConnection GET で画像をダウンロードする
 * 　対象URL：https://akira-watson.com/android/httpurlconnection-get.html
 *
 * ネットにHTTPアクセスしてデーターをダウンロードするためには、非同期での処理が必要です。AsyncTask が非推奨になったので
 * 代わりに、代わりにjava.util.concurrent.Executorsを使います。
 * Executorの解説は同じメニューの「ExecutorSample0101」にて確認してください。
 *
 * [ネットワーク接続処理は非同期で実装すること]
 * Android 3.0 以降では、メインスレッドでネットワーク処理を行うとエラーとなるように仕様変更されています。
 * また、非同期は以前はAsyncTaskを使っていましたが、Android 11から非推奨となりました。
 * 代案としてはPOSTならVolley、GETならExecutors＋(Handler or HandlerCompat)を使用する等があります。
 *
 *【サンプルについて】
 * URLをEditTextで入力してボタンをタップ、目的の画像を取得して表示するという流れです
 *
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import com.example.androidsample.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

public class HttpGetSample0101 extends AppCompatActivity {
    private EditText editText;
    private ImageView imageView;
    private Button visibleBtn;
    private String[] urls;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_httpget_sample0101);

        this.editText = findViewById(R.id.edit_uri);
        this.imageView = findViewById(R.id.imageview);
        this.visibleBtn = findViewById(R.id.visible_btn);
        this.visibleBtn.setOnClickListener( v -> dowloadImage());
        Spinner spinner = findViewById(R.id.spinner);

        // 対応するイメージ画像へのURL文字列
        this.urls = getResources().getStringArray(R.array.http_get_url_list);
        // スピナーで表示するアダプタを設定
        String[] nameList = getResources().getStringArray(R.array.http_get_name_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                nameList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 選択項目に対応するURLをEidtTextに設定
                setEditText(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
    // リスナー内で使用するEditTextを返します。
    private void setEditText(int position) {
        this.editText.setText(this.urls[position]);
    }

    // edittextに入力されている値をもとにhttpgetで画像をダウンロードしimageviewに設定します
    private void dowloadImage() {
        // ダウンロード中は表示ボタンを非アクティブとする
        this.visibleBtn.setEnabled(false);
        /* 非同期でeditTextに入力されているURLにGETで接続し画像をダウンロードする */
        // 非同期のスレッドを起動
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                URL accessUrl = new URL(this.editText.getText().toString());
                HttpURLConnection connection = (HttpURLConnection) accessUrl.openConnection();

                // タイムアウトの設定
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(20000);
                // リクエスト
                connection.setRequestMethod("GET");
                // リダイレクトを自動で許可しない
                connection.setInstanceFollowRedirects(false);

                // コネクションストリームよりBitmapを生成
                Bitmap btmp = BitmapFactory.decodeStream(connection.getInputStream());

                // 結果を呼び出し元にハンドリング
                HandlerCompat.createAsync(Looper.getMainLooper()).post(() -> {
                    // 取得したイメージをMainスレッドに渡す
                    this.imageView.setImageDrawable(new BitmapDrawable(getResources(), btmp));
                    // 表示ボタンをアクティブに戻す
                    this.visibleBtn.setEnabled(true);
                });
            } catch (IOException ex) {
                ex.printStackTrace();
                HandlerCompat.createAsync(Looper.getMainLooper()).post(() -> {
                    // ×イメージを表示し、表示ボタンをアクティブに戻す
                    this.imageView.setImageResource(android.R.drawable.ic_delete);
                    this.visibleBtn.setEnabled(true);
                });
            }
        });

    }
}
