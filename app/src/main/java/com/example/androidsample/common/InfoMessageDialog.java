package com.example.androidsample.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;

/**
 * メッセージを出力するのみのダイアログです。OKボタンを表示します。
 *
 * 呼び出し元にて以下のキーに対応する値をBundleで設定してください。
 * MESSAGE_KEY:このダイアログで表示するメッセージ
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class InfoMessageDialog extends DialogFragment {

    public static final String MESSAGE_KEY = "message_key";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("メッセージ：");
        Bundle arguments = getArguments();
        if(arguments != null) {
            builder.setMessage(arguments.getString(MESSAGE_KEY));
        }
        builder.setPositiveButton(R.string.ok, null);
        return builder.create();
    }
}
