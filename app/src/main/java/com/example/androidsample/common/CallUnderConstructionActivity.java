package com.example.androidsample.common;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * まだ工事中の画面に対応するアクティビティです
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class CallUnderConstructionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_under_construction);
        Intent intent = getIntent();
        StringBuilder positionStr = new StringBuilder("position:");
        positionStr.append(intent.getIntExtra("position", -1));
        TextView positionView = findViewById(R.id.selected_position);
        positionView.setText(positionStr);

        StringBuilder idStr = new StringBuilder("id:");
        idStr.append(intent.getLongExtra("id", -1L));
        TextView idView = findViewById(R.id.selected_id);
        idView.setText(idStr);
    }
}
