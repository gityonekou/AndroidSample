/*
 * (SAF：Storage Access Framework)でフォトアプリから画像を取り出す
 * 　対象URL：https://akira-watson.com/android/gallery.html
 *
 * デフォルトでAndroidにインストールされているギャラリーあるいはフォトから画像を取得するには
 * SAF(Storage Access Framework)を使うと可能です。
 * 画像ファイルを読み出したい場合などは、ACTION_OPEN_DOCUMENTアクションを使い、また保存したい場合は
 * ACTION_CREATE_DOCUMENTアクションを使います。
 *
 * 【SAF, Storage Access Framework】
 * SAFを使うことによりコンテンツを容易に参照できます。
 * ・ドキュメント、ストレージ、プロバイダ全体から簡単にドキュメント、画像、その他のファイルを参照して開くことができる
 * ・標準のPicker UIにより、アプリやプロバイダを通じて一貫性のある方法でファイルにアクセスできる
 *
 * Application　→ System　UI[Content Object Picker UI] → Provider[google drive/android usb/mycloud/...]
 *
 * [Intent#ACTION_OPEN]
 * ・Intent.ACTION_OPEN_DOCUMENT：ピッカーを使用してファイルを選択するためのIntent
 * ・Intent.CATEGORY_OPENABLE：openFileDescriptor()によるファイル ストリームとして利用可能な
 * 　「開くことができる」ファイルのカテゴリーを選択できる画面を表示するIntent。上(ACTION_OPEN_DOCUMENT)と合わせて指定する
 * ・setType：MIME タイプを指定、取得するファイルの形式をフィルターする
 *
 * コードは以下となります
 *　// ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file browser.
 *　Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
 * // Filter to only show results that can be "opened", such as a　file (as opposed to a list of contacts or timezones)
 * intent.addCategory(Intent.CATEGORY_OPENABLE);
 * // Filter to show only images, using the image MIME data type.
 * intent.setType("image/*");
 *
 * resultLauncher.launch(intent);
 *
 * ランチャーを利用するので「registerForActivityResult」メソッドで結果を受け取ります。
 * ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
 *      new ActivityResultContracts.StartActivityForResult(),
 *      new ActivityResultCallback<ActivityResult>() {
 *          @Override
 *          public void onActivityResult(ActivityResult result) {
 *              if (result.getResultCode() == Activity.RESULT_OK) {
 *                  Intent resultData  = result.getData();
 *                  if (resultData  != null) {
 *                      。。。
 *                  }
 *              }
 *          }
 *      });
 *
 * [ParcelFileDescriptor]
 * ParcelFileDescriptorはContentPriviter間のデータの受け渡しに使います。
 * ContentPriviterについては「5.MediaStore：ファイル参照」(MediaStoreSample0201)を参照。
 * コンテントプロバイダーを経由してデータを受け取りたい場面は多々あると思うので
 * よく覚えておくように。。
 *
 * [FileDescriptor](JDK.1.0からあるクラスです。。)
 * FileDescriptor:ファイル記述子クラスのインスタンスは、開いたファイル、開いたソケット、またはバイトの別の
 * ソース(シンク)を表す、ベースとなるマシン固有の構造への不透明なハンドルとして機能します。
 *
 *
 */
package com.example.androidsample.datastorage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Locale;

/**
 * (SAF：Storage Access Framework)でフォトアプリから画像を取り出す
 * 「6.SAF(Storage Access Framework)でフォトアプリから画像を取り出す」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/gallery.html
 *
 *【サンプルについて】
 * フォトにある画像を読みだして表示させるプログラムです。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class StorageAccessFrameworkSample0101 extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK) {
                    if(result.getData() != null) {
                        openImage(result.getData());
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
        setContentView(R.layout.activity_saf_sample0101);

        this.textView = findViewById(R.id.saf_sample0101_textview);
        this.imageView = findViewById(R.id.saf_sample0101_imageview);
        Button btn = findViewById(R.id.saf_sample0101_button);
        btn.setOnClickListener( v -> {
            // ピッカーを使用してファイルを選択するためのIntent
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            // 選択可能なファイルのみを参照するように追加指定
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            // 参照するファイルタイプを指定
            intent.setType("image/*");
            // pikkerを表示
            resultLauncher.launch(intent);
        });
    }
    /* ピッカーにて選択されたイメージをImageViewに表示します。 */
    private void openImage(Intent resultData) {

        //　選択した画像を参照するためのURIを取得
        Uri uri = resultData.getData();
        this.textView.setText(String.format(Locale.US, "Uri:[%s]", uri.toString()));

        // ParcelFileDescriptorはContentPriviter間のデータの受け渡しに使います。
        // try-with-resources使用にてサンプルのclose処理は記述していません
        // ParcelFileDescriptorのクローズタイミングはfinallyタイミングでOKです。
        try (ParcelFileDescriptor pdDescripter
                     = getContentResolver().openFileDescriptor(uri, "r")){

            if(pdDescripter != null) {
                // Fileを操作するためのハンドル:詳細は上の解説を参照(JDK.1.0からあるクラスです。。)
                FileDescriptor fileDescriptor = pdDescripter.getFileDescriptor();
                Bitmap btmp = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                // ImageViewに配置
                this.imageView.setImageDrawable(new BitmapDrawable(getResources(), btmp));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
