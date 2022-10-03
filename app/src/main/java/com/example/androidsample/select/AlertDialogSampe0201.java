/*
 * カスタムAlertDialog
 *
 * 　対象URL：https://akira-watson.com/android/alertdialog2.html
 *
 * 説明用の画像やテキストの設定をカスタマイズして表示します。
 * 具体的にはAlertDialogを生成するFragmentの中で、
 * alert.setView(int layoutRsId)；
 * のようにレイアウトをViewとして指定して画像を追加するとできます。
 *
 */
package com.example.androidsample.select;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;

/**
 * AlertDialog サンプル02
 * 「11.カスタムAlertDialog」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/alertdialog2.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class AlertDialogSampe0201 extends AppCompatActivity
        implements AlertDialogSampe0201CustomAlertDialog.NoticeCustomDialogListener {

    private TextView textView;
    private ImageView imageView;
    private float dp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertdialog_sample0201);
        // db単位を取得
        this.dp = getResources().getDisplayMetrics().density;

        this.textView = findViewById(R.id.alertdialog_sample0201_textview);
        this.imageView = findViewById(R.id.alertdialog_sample0201_imageview);
        Button btn = findViewById(R.id.alertdialog_sample0201_button);
        btn.setOnClickListener( v -> {
            DialogFragment fragment = new AlertDialogSampe0201CustomAlertDialog();
            fragment.show(getSupportFragmentManager(),
                    AlertDialogSampe0201CustomAlertDialog.class.getName());
        });
    }

    @Override
    public void choiceImage(DialogFragment fragment, int imageId, String message) {
        this.textView.setText(message);
        this.imageView.setImageResource(imageId);
        // ただ、今回サンプルの画像が大きいのでサイズを以下で固定する
        this.imageView.setLayoutParams(new LinearLayout.LayoutParams(
                (int)(250 * this.dp),
                (int)(300 * this.dp)));
    }
}
