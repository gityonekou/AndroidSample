/*
 * アプリ固有の内部ストレージ(Internal Storage)にファイルを保存する
 * 　対象URL：https://akira-watson.com/android/fileoutputstream.html
 *
 * 内部ストレージの解説についてはサンプル0101を参照ください。
 *
 * ファイルの入出力について
 * Context#openFileInputメソッド、openFileOutputメソッドを使い直接StreamをContextから受け取るタイプです。
 * また、サンプル0101同様にバッファリングされたI/Oを使うことが重要です。
 * ※1文字ずつの入出力は非効率です。
 * [Context#openFileInputメソッド、openFileOutputメソッド]
 *  File API の代わりに、openFileOutput()を呼び出して、filesDirディレクトリ内のファイルへの書き込みを行う
 * FileOutputStreamを取得することもできます。
 *
 * FileOutputStream fileOutputstream  = openFileOutput("test.txt", Context.MODE_PRIVATE);
 * ・第一引数はファイル名のみの指定です
 * ・第二引数のモード：MODE_APPEND：追加で書き込み
 * 　　　　　　　　　：MODE_PRIVATE：このアプリのみアクセス許可
 *
 * FileInputStream fileInputStream = openFileInput("test.txt");
 * FileInputStreamはBufferedI/Oにラップしてから出力する必要があります。
 *
 */
package com.example.androidsample.datastorage;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * アプリ固有の内部ストレージ(Internal Storage)にファイルを保存する
 * 「2.ファイルの入出力(アプリ固有の内部ストレージとopenFileInput・Output)」に対応するアクティビティです。
 *
 * 対象URL：https://akira-watson.com/android/fileoutputstream.html
 *
 *【サンプルについて】
 * サンプルの仕様についてはInternalStorageSample0101と同じです。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class InternalStorageSample0102 extends AppCompatActivity {

    private static final String FILE_NAME="internal_storage_sample0102.txt";
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
        this.textView.setText(R.string.internalstorage_sample0102_init_text);

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
        // 追記モードの場合は「Context.MODE_APPEND」を指定する。今回は毎回上書きなのでMODE_PRIVATEでファイルを開く
        try (BufferedOutputStream out = new BufferedOutputStream(
                openFileOutput(FILE_NAME, Context.MODE_PRIVATE))) {
            out.write(saveText.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /** ファイルを読み込みして返します。読込対象のファイルは存在していることが前提です。*/
    private String readFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(openFileInput(FILE_NAME), StandardCharsets.UTF_8))) {
            StringBuilder readBuff = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                readBuff.append(line).append('\n');
            }
            return readBuff.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
