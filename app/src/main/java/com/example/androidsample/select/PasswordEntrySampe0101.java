package com.example.androidsample.select;
/*
 * 　カスタムでユーザID、パスワードの入力ダイアログを作成します。詳細はアンドロイドデベロッパーガイドを参照
 * 　対象URL：https://developer.android.com/guide/topics/ui/dialogs?hl=ja
 *
 *
 */
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;

public class PasswordEntrySampe0101 extends AppCompatActivity
        implements PasswordEntrySampe0101Dialog.NoticePasswordEntryDialogListener{

    private TextView idTextView;
    private TextView passwordTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordentry_sample0101);
        this.idTextView = findViewById(R.id.passwordentry_sample0101_id);
        this.passwordTextView = findViewById(R.id.passwordentry_sample0101_password);
        Button btn = findViewById(R.id.passwordentry_sample0101_button);
        btn.setOnClickListener( v -> {
            DialogFragment fragment = new PasswordEntrySampe0101Dialog();
            fragment.show(getSupportFragmentManager(), PasswordEntrySampe0101Dialog.class.getName());
        });
    }

    @Override
    public void entryValue(DialogFragment fragment, String id, String password) {
        this.idTextView.setText(id);
        this.passwordTextView.setText(password);
    }
}
