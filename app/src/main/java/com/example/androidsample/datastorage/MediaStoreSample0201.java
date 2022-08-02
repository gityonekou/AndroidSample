package com.example.androidsample.datastorage;
/*
 * パブリックな共有ストレージ(メディアコレクション：MediaStore)にあるスマホの画像や音楽ファイルを検索する
 * 　対象URL：https://akira-watson.com/android/mediastore.html
 *
 * スマホ内で共有されるMediaStorageにアクセスして画像や音楽ファイルをContentProviderを使って参照するサンプルです。
 *
 * 【ContentProvider：コンテンツプロバイダ】
 * コンテンツプロバイダは外部アプリに対しテーブルに似たものとしてデータを提供する仕組みです。
 * コンテンツプロバイダにアクセスする一般的なパターンは、以下のようになります。
 * １．CursorLoaderを使用してバックグラウンドで非同期クエリを実行
 * ２．UI の Activity/Fragment は、クエリに対して CursorLoader を呼び出し
 * ３．ContentResolver を使用して ContentProvider からデータを取得
 *
 * [ContentResolver.query()]
 * 上記１～３．はContentResolver.query()を呼び出してデータ情報を取得することになりますが、
 * 中では１～３の処理を行っています。（UI側では基本その流れを意識する必要はありません。
 * ContentResolver contentResolver = getContentResolver();
 * Cursor cursor = contentResolver.query(
 *          Uri,  　　　　　　　// UserDictionary.Words.CONTENT_URI, table
 *          projection,      // The columns to return for each row
 *          selection,       // Selection criteria
 *          selectionArgs,   // Selection criteria
 *          sortOrder);      // The sort order for the returned rows
 *
 * Uri： DataBaseでのtable_nameに相当
 * 　　　例えば、画像であれば「MediaStore.Images.Media.EXTERNAL_CONTENT_URI」を渡します。
 * 　　　これは[content://media/external/images/media]でありここを検索していくことになります。
 * projection： DataBaseでのcolumnであり、データ項目です。　
 * 　　　　　　　　例えば画像では以下のようなcolumnがあります
 * 　　　　　　　　　・MediaStore.Images.Media._ID
 * 　　　　　　　　　・MediaStore.Images.Media.DATA
 * 　　　　　　　　　・MediaStore.Images.Media.DISPLAY_NAME
 * 　　　　　　　　　・MediaStore.Images.Media.TITLE
 * 　　　　　　　　　・MediaStore.Images.Media.DATE_TAKEN
 * 　　　　　　　　　・MediaStore.Images.Media.DATE_ADDED
 * selection： DataBaseのWHEREキーワードと同じです。
 * selectionArgs： Selectionと一緒に使用される。selectionの疑問符(？)に入る内容がSelection argsです。
 * 　　　　　　　　　　argsの型は String[]になります。
 * sortOrder： クエリ結果の行が表示される順序を指定。タイムスタンプで昇順・降順に分けるなど
 *
 * 【android.permission.READ_EXTERNAL_STORAGE】
 * ContentProviderを使ってスマホ内のMediaStre内を検索しますのでPermissionが必要になります。
 * AndroidManifest.xmlに以下を追加
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 * READ_EXTERNAL_STORAGE は Runtime Permission に該当するため、アプリ起動中に許可を得るように
 * コーディングが必要です。
 *
 *---------------------------------------
 * 【サンプル】
 * MediaStore内の画像ファイルを参照します。最初に読み出し許可の処理を行います。
 *
 * ※実際に検索結果のデータを表示するにはParcelFileDescriptorなどでストリームを開く必要があります。
 * 詳しくは「StorageAccessFrameworkSample0101」が参考になるかと。
 * ※上記サンプルではSAFでピッカーを表示して選択した画像を表示するサンプルとなるので１００％参考になるわけではない
 * が、ParcelFileDescriptorの使い方の参考にはなると思います。
 *
 *
 */
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.util.Locale;

public class MediaStoreSample0201 extends AppCompatActivity {

    private final ActivityResultLauncher<String> permissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(), result -> {
                if(result) {
                    readContent();
                } else {
                    this.textView.setText(R.string.mediastore_sample0201_invalid_permission);
                }
            }
    );

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediastore_sample0201);

        this.textView = findViewById(R.id.mediastore_sample0201_textview);

        // パーミッション：android.permission.READ_EXTERNAL_STORAGEが許可されていないなら設定ダイアログを表示
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            this.permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            readContent();
        }
    }

    @SuppressLint("Range")
    private void readContent() {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = null;

        try {
            cursor = resolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, null, null, null
            );
            if(cursor != null && cursor.moveToFirst()) {
                StringBuilder message = new StringBuilder(String.format(Locale.US,
                        "MediaStore.Images size = %d\n\n", cursor.getCount()));
                do {
                    /*
                     * cursor.getColumnIndex()メソッドでエラーが出る(0以上の数値を指定してください)が出るので
                     * @SuppressLint("Range") で無視するように設定しています
                     * ここでは対象のカラム名を指定するので問題ないです
                     */
                    message.append("ID:");
                    message.append(cursor.getString(cursor.getColumnIndex(
                            MediaStore.Images.Media._ID)));
                    message.append("\n");
                    message.append("Title:");
                    message.append(cursor.getString(cursor.getColumnIndex(
                            MediaStore.Images.Media.TITLE)));
                    message.append("\n");
                    message.append("Path:");
                    message.append(cursor.getString(cursor.getColumnIndex(
                            MediaStore.Images.Media.DATA)));
                    message.append("\n\n");
                } while(cursor.moveToNext());
                // カーソルのクローズを忘れないように(ここは昔なつかしのDBのResultSetと同じ)
                cursor.close();
                cursor = null;
                this.textView.setText(message.toString());
            } else {
                this.textView.setText(R.string.no_search_data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(cursor != null) cursor.close();
        }
    }
}
