package com.example.androidsample.list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

/**
 * ListViewSample0301でリストタップ時に呼ばれるアクティビティです。タップされたリスト項目の詳細情報を表示します。
 *
 * 対象URL:https://akira-watson.com/android/listview_2.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ListViewSample0301SubActivity extends AppCompatActivity {

    public static final String NAME_KEY = "name";
    public static final String IMAGE_KEY = "image";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_sample0301_sub);
        Intent intent = getIntent();

        TextView textView = findViewById(R.id.listview_sample0301sub_selected_text);
        textView.setText(intent.getStringExtra(NAME_KEY));

        ImageView imageView = findViewById(R.id.listview_sample0301sub_selected_photo);
        int imageId = intent.getIntExtra(IMAGE_KEY, -1);
        if(imageId != -1) {
            imageView.setImageResource(imageId);
        }
    }
}
