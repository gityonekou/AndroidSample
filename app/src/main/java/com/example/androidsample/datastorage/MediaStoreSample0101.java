/*
 * パブリックな共有ストレージ(メディアコレクション：MediaStore)に画像を保存する
 * 　対象URL：https://akira-watson.com/android/mediastore-save.html
 *
 * メディアコレクションは共有可能なストレージで写真、動画やミュージックなどのメディアを保存できる領域です。
 * ・MediaStore APIを使ってアクセスし、他のアプリが作成したメディアには読み出し(Read Only)でアクセスできる
 * ・読み出しには「READ_EXTERNAL_STORAGE」のPermissionが必要
 * ・保存に「WRITE_EXTERNAL_STORAGE」は不要（Android 10 以降）
 * ⇒今回のサンプルではAPI28も対象としているためパーミッション設定とコードの分岐が必要
 * ・アプリが削除されてもファイルは残っている
 *
 * [ファイル種別ごとの格納先]
 * ・画像：（写真とスクリーンショットを含む）：DICM/、Picture/ ディレクトリに格納
 * ・動画：DCIM/、Movies/、Pictures/ ディレクトリに格納
 * ・オーディオ：Alarms/、Audiobooks/、Music/、Notifications/、Podcasts/、Ringtones/ ディレクトリに格納
 * ・オーディオ プレイリスト：Music/ ディレクトリ、または Movies/ ディレクトリに格納
 *
 * 【プロバイダにデータを挿入し挿入したコンテンツへのURIを受け取る】
 * Mediaファイルを保存するには、「ContentResolver.insert()」を使ってプロバイダにデータを挿入します。
 * プロバイダに新しい行を挿入し、その行のコンテンツ URI を返します。
 *
 * ContentValues newValues = new ContentValues();
 * newValues.put(パラメータ,　値);
 * (newValues.put(MediaStore.Images.Media.IS_PENDING, 1);など)
 * Uri newUri = newUri = getContentResolver().insert(
 *      [UserDictionary.Words.CONTENT_URI],   // the user dictionary content URI
 *      newValues                           // the values to insert
 * );
 * ※UserDictionary.Words.CONTENT_URIには実際には対象となるパスへのURIを取得し設定してください。詳しくはサンプルの
 * コードを参照
 * ・ContentValues：
 *  ContentResolverにデータを保存するためのクラスで、ファイル情報を入力し、insert() の引数に使います
 *
 * 注意点としてファイルの排他処理をする必要があるのでContentValuesに値を設定してファイルのロック⇒処理完了後に
 * 解除をする必要があります。
 * newValues.put(MediaStore.Images.Media.IS_PENDING, 1); // ロック
 * ⇒insertに渡すパラメータに追加
 *
 * [ファイル出力完了後、ファイルのロックを解除]
 * newValues.clear();
 * newValues.put(MediaStore.Images.Media.IS_PENDING, 0); // ロック解除
 *　getContentResolver().update(newUri, values, null, null);
 *
 * -------------------------
 * API28以下での追加事項
 * マニフェストに以下を追加
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
 *          android:maxSdkVersion="28" />
 * APIレベル28以下の場合のコードを追加；詳しくは以下コードを参照
 * ------------------------
 *
 */
package com.example.androidsample.datastorage;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.androidsample.R;

import java.io.IOException;
import java.io.OutputStream;

/**
 * パブリックな共有ストレージ(メディアコレクション：MediaStore)に画像を保存する
 * 「4.MediaStore：ファイル出力」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/mediastore-save.html
 *
 *【サンプルについて】
 * ボタン押下でdrawableに入れた画像をMediaStoreに保存します。
 * drawableの画像データからInputStreamを取得する方法もコーディングします。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class MediaStoreSample0101 extends AppCompatActivity {

    private static final String FILE_NAME = "MediaStoreSample0101.jpg";
    private static final String MIME_TYPE = "image/jpeg";
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediastore_sample0101);

        // 各種GUI設定
        this.textView = findViewById(R.id.mediastore_sample0101_textview);
        this.imageView = findViewById(R.id.mediastore_sample0101_imageview);
        Button btn = findViewById(R.id.mediastore_sample0101_button);
        btn.setOnClickListener( v -> {
            /* API29未満(つまり、API28までは)の場合 */
            // パーミッション：android.permission.WRITE_EXTERNAL_STORAGEが許可されているかを確認
            // 許可されていないなら対象のパーミッション設定ダイアログを表示
            // [補足]
            // WRITE_EXTERNAL_STORAGEパーミッションはRuntime Permissionに該当するため、
            // マニフェストへの登録＋アプリ起動中に許可を得るように設定が必要です。(API29以降は不要。。。)
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q) {
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(this, permissions, 1);
                    return;
                }
            }
            // 外部ストレージへの出力が許可されているならDrawableの対象ファイルを外部ストレージに出力する
            if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                ContentResolver resolver = getApplicationContext().getContentResolver();
                ContentValues values = new ContentValues();
                // コンテンツクエリ：列名：ファイル名
                values.put(MediaStore.Images.Media.DISPLAY_NAME, FILE_NAME);
                // コンテンツクエリ：列名：マイム
                values.put(MediaStore.Images.Media.MIME_TYPE, MIME_TYPE);

                Uri collection;
                // API29以上の場合はファイルロックを行う、コンテンツのURI取得をgetContentUriメソッドで行う
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    // コンテンツクエリ：書込み時にメディアファイルに排他的にアクセスする(API29以降)
                    values.put(MediaStore.Images.Media.IS_PENDING, 1);
                    // MediaStoreへのパス(Uri)を取得(API29以降)
                    collection
                            = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
                } else {
                    collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                }
                Uri item = resolver.insert(collection, values);
                try (OutputStream out = resolver.openOutputStream(item)) {
                    Bitmap btmp = BitmapFactory.decodeResource(getResources(), R.drawable.rizero1);
                    btmp.compress(Bitmap.CompressFormat.JPEG, 70, out);
                    this.imageView.setImageDrawable(new BitmapDrawable(getResources(), btmp));
                    // 完了メッセージを出力
                    this.textView.setText(R.string.save_commit);
                } catch(IOException ex) {
                    ex.printStackTrace();
                    this.textView.setText(R.string.save_failed);
                }

                // API29以降の場合、ファイルのロック処理を追加しているのでファイルのアンロックを行う
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    // ファイルロックを解除
                    values.clear();
                    values.put(MediaStore.Images.Media.IS_PENDING, 0);
                    resolver.update(item, values, null, null);
                }

            } else {
                this.textView.setText(R.string.externalstorage_sample0101_is_writable_fail_text);
            }
        });

    }
}
