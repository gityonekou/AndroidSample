package com.example.androidsample.app;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * Activityの画面遷移
 * ActivitySampe0201から遷移するサブActivityです。
 * returnボタン押下時にこのサブアクテビティを終了(finish()呼び出し)させます。
 * 詳細はActivitySampe0201を参照のこと
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ActivitySampe0201SubActivity extends AppCompatActivity {

    private String message;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sample0201_sub);

        // Mainアクティビティからのデータを取得しテキストに設定
        Intent intentMain = getIntent();
        this.message = intentMain.getStringExtra(ActivitySampe0201.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.text_view);
        textView.setText(this.message);

        this.editText = findViewById(R.id.edit_text);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener( v -> {

            Intent intentSub = new Intent();
            // 未入力の場合はEXTRA_MESSAGEのキーに対応する値が更新されないので前の値が反映されたままとなる点に注意
            // つまり、ここで明示的にMainActivityで設定した値を追加しなくてもEXTRA_MESSAGEキーに
            // 対応する値はResultに反映されたままとなる。(setResultで設定したIntentのインスタンスで
            // 値が塗り替えられるのではなく、値をaddする形になる点に注意
            if(this.editText.getText() != null) {
                intentSub.putExtra(ActivitySampe0201.EXTRA_MESSAGE,
                        this.message + this.editText.getText().toString());
            }
            //edit textをクリアし終了
            this.editText.setText("");
            setResult(RESULT_OK, intentSub); // ここで追加するIntentで値を追加する形となる
            finish();
        });

    }
}
