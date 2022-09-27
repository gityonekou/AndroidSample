/*
 * アプリ固有の内部ストレージ(Internal Storage)にファイルを保存する
 * 　対象URL：https://akira-watson.com/android/fileoutputstream.html
 *
 * ストレージには内部ストレージ(Internal Storage)と外部ストレージ(External Storage)があります。
 * アプリには固有のストレージ領域があり、ここに起動時に必要な設定データなどを保存させることができます。
 * 【内部ストレージ：Internal Storage】
 * ・常に使用できる
 * ・ここに保存されたファイルは、自分のアプリからのみアクセスできる
 * ・アプリがアンインストールされると削除される
 * ・ユーザーからも他のアプリからも、自分のファイルにアクセスできないようにしたい場合に適する
 * ・アクセスPermissionは不要
 * ・ストレージ領域は限られているので大きなファイルは保存できない
 *
 * [getFilesDir()]
 *　Context#getFilesDir()を使ってアプリ固有の内部ストレージへのパスを取得できます。
 * /data/data/[package_name]/files/
 *
 * adb shell コマンドではセキュリティーでブロックされて見えませんが、run-as を使えばデバック状態であれば見ることはできます。
 *
 * [ファイルの読み込みについて]
 * FileReader単体で使うのではなくバッファリングされたBufferedReaderを使うこと
 * FileReaderは1文字ずつの読み込みのため読み込みについては非効率です。
 *
 */
package com.example.androidsample.datastorage;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * アプリ固有の内部ストレージ(Internal Storage)にファイルを保存する
 * 「1.ファイルの入出力(アプリ固有の内部ストレージとgetFilesDir)」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/fileoutputstream.html
 *
 *【サンプルについて】
 * このサンプルではEditTextで入力した文字列をTestFile_0101.txt というファイルに入れて保存し、そのファイルを
 * ボタン押下で読み込みます。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class InternalStorageSample0101 extends AppCompatActivity {

    private static final String FILE_NAME="internal_storage_sample0101.txt";
    private File file;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internalstorage_sample0101);

        this.file = new File(getApplicationContext().getFilesDir(), FILE_NAME);
        this.editText = findViewById(R.id.internalstorage_sample0101_edit_text);
        this.textView = findViewById(R.id.internalstorage_sample0101_textview);
        this.textView.setText(R.string.internalstorage_sample0101_init_text);

        // ファイルを保存
        Button saveBtn = findViewById(R.id.internalstorage_sample0101_save_button);
        saveBtn.setOnClickListener( v -> {
            String text = this.editText.getText().toString();
            if(text.length() != 0) {
                if(saveFile(text)) this.textView.setText(R.string.save_commit);
                else this.textView.setText(R.string.save_failed);
            } else {
                this.textView.setText(R.string.internalstorage_sample0101_no_text);
            }
        });
        // ファイルを読み込み
        Button readBtn = findViewById(R.id.internalstorage_sample0101_read_button);
        readBtn.setOnClickListener( v -> {
            if(this.file.exists()) {
                String readStr = readFile();
                if (readStr != null) {
                    this.editText.setText(readStr);
                    this.textView.setText(readStr);
                    Toast.makeText(
                            getApplicationContext(), R.string.read_commit, Toast.LENGTH_SHORT).show();
                } else {
                    this.textView.setText(R.string.read_failed);
                }
            } else {
                String message = "読込対象のファイルがありません。[path=" + this.file.getPath() + "]";
                this.textView.setText(message);
            }
        });
        // ファイルを削除
        Button deleteBtn = findViewById(R.id.internalstorage_sample0101_delete_button);
        deleteBtn.setOnClickListener( v -> {
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
        });
    }

    /** 入力文字列を上書きモードで保存する */
    private boolean saveFile(String saveText) {
        // 上書きモードでWriterをオープン。追記モードの場合はFileWriterのコンストラクタでパラメータを指定すること
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            writer.write(saveText);
            writer.flush();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /** ファイルを読み込みして返します。読込対象のファイルは存在していることが前提です。*/
    private String readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            StringBuilder readTextBuff = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                readTextBuff.append(line).append('\n');
            }
            return readTextBuff.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
