package com.example.androidsample.app;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * Activityの画面遷移
 * ActivitySampe0101から遷移するサブActivityです。
 * returnボタン押下時にこのサブアクテビティを終了(finish()呼び出し)させます。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ActivitySampe0101SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sample0101_sub);

        Button btn = findViewById(R.id.return_button);
        // サブアクティビティを終了する
        btn.setOnClickListener(v -> finish());
    }
}
