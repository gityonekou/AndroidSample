/*
 * (SAF：Storage Access Framework)でドキュメントを保存する
 * 　対象URL：https://akira-watson.com/android/action_create_document.html
 *
 * 共有メモリにファイルを保存するためにSAF(Storage Access Framework)を利用します。
 * ファイル保存に使うのは ACTION_CREATE_DOCUMENTです。
 * 取り出したい場合は ACTION_OPEN_DOCUMENTです。(Sample0101参照)
 *
 * [ACTION_CREATE_DOCUMENT]
 * SAFを介してドキュメントを保存する場合に指定するIntentのキーです。
 * 具体的な使用法は参照時と同じですのでStorageAccessFrameworkSample0101を参照してください。
 *
 */
package com.example.androidsample.datastorage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;
import com.example.androidsample.common.InfoMessageDialog;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * (SAF：Storage Access Framework)でドキュメントを保存する
 * 「7.SAF(Storage Access Framework)でテキストファイルを保存する」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/action_create_document.html
 *
 *【サンプルについて】
 * テキストファイルに「Storage Access Framework sample0201」の文字列を入れてそれを保存してみます。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class StorageAccessFrameworkSample0201 extends AppCompatActivity {

    private TextView textView;
    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK) {
                    Intent resultData = result.getData();
                    if(resultData != null) {
                        outputFile(resultData);
                    } else {
                        this.textView.setText(R.string.saf_sample0101_returndata_null);
                    }
                } else {
                    String message = "ResultCodeがFailです。[ResultCode="
                            + result.getResultCode() + "]";
                    this.textView.setText(message);
                }
            }
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saf_sample0201);

        this.textView = findViewById(R.id.saf_sample0201_textview);
        this.textView.setText(R.string.saf_sample0201_init_text);

        Button btn = findViewById(R.id.saf_sample0201_button);
        btn.setOnClickListener( v -> {
            // ピッカーを使用してファイルを保存するためのIntent
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            // 選択可能なファイルのみを参照するように追加指定
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            // 保存するファイルタイプを指定
            intent.setType("text/plain");
            // ファイル名を設定
            intent.putExtra(Intent.EXTRA_TITLE, "StorageAccessFrameworkSample0201.txt");
            // pikkerを起動
            resultLauncher.launch(intent);
        });
    }

    /* 選択したURIにファイルを出力します。*/
    private void outputFile(Intent resultData) {
        Uri uri = resultData.getData();
        // uriを表示
        this.textView.setText(String.format(Locale.US, "Uri:[%s]", uri.toString()));

        // 出力する文字列
        String outStr = "Storage Access Framework sample0201\n";
        try (BufferedOutputStream bout = new BufferedOutputStream(
                getContentResolver().openOutputStream(uri))) {
            bout.write(outStr.getBytes(StandardCharsets.UTF_8));
            // 完了メッセージをPOPUP
            popupMessage(getResources().getString(R.string.save_commit));
        } catch (IOException ex) {
            ex.printStackTrace();
            popupMessage(getResources().getString(R.string.save_failed));
        }
    }

    /* メッセージ出力 */
    private void popupMessage(String mesage) {
        DialogFragment dialog = new InfoMessageDialog();
        Bundle args = new Bundle();
        args.putString(InfoMessageDialog.MESSAGE_KEY, mesage);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), InfoMessageDialog.class.getName());
    }
}
