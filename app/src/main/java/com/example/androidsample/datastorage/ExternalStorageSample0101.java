package com.example.androidsample.datastorage;
/*
 * アプリ固有の外部ストレージ(External Storage)にファイルを保存する
 * 　対象URL：https://akira-watson.com/android/external-storage-image.html
 *
 * 画像ファイルなどの容量の大きなファイルの場合、内部ストレージでは容量が足りないので外部ストレージに
 * 保存することになります。
 *
 * 【アプリ固有の外部ストレージ】
 * ・アプリで使う画像や動画等、大きなファイルを保存できる
 * ・アプリがアンインストールされると削除される
 * ・外部ストレーが使用可能状態か確認が必要
 * ・アプリ自身からのアクセスならPermissionは不要
 * ・他のアプリも適切な権限を持っていればにアクセスでき、自分のコントロールの及ばない所で読み取られる可能性がある
 *
 * [getExternalFilesDirメソッド]
 * 以前は「Environment.getExternalStorageDirectory().getPath()」を使ってダイレクトのパスを取得できましたが
 * API29からは非推奨となりました。
 * ※getExternalStorageDirectory()で取得したパスは、アプリから直接アクセスできなくなったので実質的には使用不可
 * ⇒Androidはアプリごとに固有のフォルダが作成されるため、そこを使いなさいとのこと。
 * API29以降は
 * 「Context＃getExternalFilesDir(String)」メソッドを使って外部ストレージのディレクトリパスを取得します。
 * File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ファイル名.jpg");
 *
 * Environment.DIRECTORY_PICTURES
 * Environment.DIRECTORY_DCIM
 * ・・・・
 * ↑外部ストレージに同様のディレクトリがあると思います。対応するディレクトリ名を選びます
 *
 * ------------------------
 * 【サンプルについて】
 * ボタン押下でアセットにある画像を外部ストレージに保存します。
 * 外部ストレージに保存した画像を表示するボタンも追加します。
 *
 */
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExternalStorageSample0101 extends AppCompatActivity {
    // 保存するファイル名
    private static final String FILE_NAME="externalstorage_sample0101.jpg";
    private static final String ASSETS_FILE_NAME="rizero_image/rizero2.jpg";
    private File file;
    private TextView textView;
    private ImageView imageView;
    private Button readBtn;
    private Button deleteBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_externalstorage_sample0101);

        // 保存ファイルのインスタンスを取得
        this.file = new File(getApplicationContext().getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), FILE_NAME);
        Log.d("log", "path:" + this.file.getPath());

        // GUI設定
        this.textView = findViewById(R.id.externalstorage_sample0101_text_view);
        this.imageView = findViewById(R.id.externalstorage_sample0101_image_view);
        Button saveBtn = findViewById(R.id.externalstorage_sample0101_button_save);
        saveBtn.setOnClickListener( v -> saveExternalStorage());
        this.readBtn = findViewById(R.id.externalstorage_sample0101_button_read);
        this.readBtn.setOnClickListener( v -> readExternalStorage());
        this.deleteBtn = findViewById(R.id.externalstorage_sample0101_button_delete);
        this.deleteBtn.setOnClickListener( v -> {
            if(this.file.exists()) {
                if(this.file.delete()) {
                    String message = "ファイルを削除しました。[path=" + this.file.getPath() + "]";
                    this.textView.setText(message);
                } else {
                    String message = "ファイルの削除に失敗しました。[path=" + this.file.getPath() + "]";
                    this.textView.setText(message);
                }
            } else {
                String message = "削除対象のファイルがありません。[path=" + this.file.getPath() + "]";
                this.textView.setText(message);
            }
            // 読込・削除ボタンを設定
            setButtonEnabled();
        });
        // 読込・削除ボタンを設定
        setButtonEnabled();
    }

    /** アセットから読み込んだ画像ファイルを外部ストレージに保存します。 */
    private void saveExternalStorage() {
        if(isExternalStorageWritable()) {
            // アセットからファイルを読み込み
            try (BufferedInputStream bin = new BufferedInputStream(
                    getResources().getAssets().open(ASSETS_FILE_NAME));
                 BufferedOutputStream bout = new BufferedOutputStream(
                            new FileOutputStream(this.file))) {
                byte[] buff = new byte[10240 * 4];
                int readLen;
                while((readLen = bin.read(buff)) != -1) {
                    bout.write(buff, 0, readLen);
                }
                // 出力を忘れないように(closeではない)
                bout.flush();

                // 完了メッセージを出力
                this.textView.setText(R.string.save_commit);
            } catch (IOException ex) {
                ex.printStackTrace();
                this.textView.setText(R.string.save_failed);
            }
        } else {
            this.textView.setText(R.string.externalstorage_sample0101_is_writable_fail_text);
        }
        // ファイル出力成功なら読込・削除ボタンを可にする
        setButtonEnabled();
    }
    /** 外部ストレージの画像ファイルを読み込みImageViewに配置します。 */
    private void readExternalStorage() {
        if(isExternalStorageReadable()) {
            try (InputStream in = new FileInputStream(this.file)) {
                this.imageView.setImageDrawable(new BitmapDrawable(
                        getResources(), BitmapFactory.decodeStream(in)));
            } catch(IOException ex) {
                ex.printStackTrace();
                this.textView.setText(R.string.read_failed);
            }
        } else {
            this.textView.setText(R.string.externalstorage_sample0101_is_readable_fail_text);
        }
    }

    /** 外部ストレージが出力可能かどうか */
    private boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
    /** 外部ストレージが読込可能かどうか */
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state)
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }
    /** ファイルの有り無しで読込ボタン・削除ボタンのアクティブ・非アクティブを設定します。 */
    private void setButtonEnabled() {
        if(this.file.exists()) {
            this.readBtn.setEnabled(true);
            this.deleteBtn.setEnabled(true);
        } else {
            this.readBtn.setEnabled(false);
            this.deleteBtn.setEnabled(false);
        }
    }
}
