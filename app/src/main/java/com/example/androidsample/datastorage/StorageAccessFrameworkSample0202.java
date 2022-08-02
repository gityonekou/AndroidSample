package com.example.androidsample.datastorage;
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
 *【サンプルについて】
 * こちらのサンプルでは下に表示した画像をSAFを解して指定した保存先に出力します。
 *
 */
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;
import com.example.androidsample.common.InfoMessageDialog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class StorageAccessFrameworkSample0202 extends AppCompatActivity {

    private static final String OPEN_ASSET_FILE = "rizero_image/rizero2.jpg";
    private static final String CREATE_FILE_NAME = "rizero2.jpg";
    private TextView textView;
    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent resultData = result.getData();
                    if (resultData != null) {
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
        setContentView(R.layout.activity_saf_sample0202);

        this.textView = findViewById(R.id.saf_sample0202_textview);
        Button btn = findViewById(R.id.saf_sample0202_button);
        ImageView imageView = findViewById(R.id.saf_sample0202_imageview);

        // 保存ボタン押下時、保存先選択のPikker(SAF)を表示
        btn.setOnClickListener(v -> {
            // ピッカーを使用してファイルを保存するためのIntent
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            // 選択可能なファイルのみを参照するように追加指定
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            // 保存するファイルタイプを指定
            intent.setType("image/jpeg");
            // ファイル名を設定
            intent.putExtra(Intent.EXTRA_TITLE, CREATE_FILE_NAME);
            // pikkerを起動
            resultLauncher.launch(intent);
        });

        // assetの「rizero2.jpg」を表示
        AssetManager assetManager = getResources().getAssets();
        try (InputStream in = assetManager.open(OPEN_ASSET_FILE)) {
            imageView.setImageDrawable(
                    new BitmapDrawable(getResources(), BitmapFactory.decodeStream(in)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /* 選択したURIにファイルを出力します。*/
    private void outputFile(Intent resultData) {
        Uri uri = resultData.getData();
        // uriを表示
        this.textView.setText(String.format(Locale.US, "Uri:[%s]", uri.toString()));

        // ASSETの画像を指定URI先に出力
        AssetManager assetManager = getResources().getAssets();
        try (BufferedInputStream bin = new BufferedInputStream(assetManager.open(OPEN_ASSET_FILE));
             BufferedOutputStream bout = new BufferedOutputStream(
                     getContentResolver().openOutputStream(uri))) {
            byte[] buff = new byte[10240 * 4];
            int readLength;
            while((readLength = bin.read(buff)) != -1) {
                bout.write(buff, 0, readLength);
            }
            // 出力を忘れないように(closeではない)
            bout.flush();
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

