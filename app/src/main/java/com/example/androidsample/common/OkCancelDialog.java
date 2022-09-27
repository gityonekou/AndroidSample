package com.example.androidsample.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;

/**
 * メッセージに対してOK、キャンセルの選択を行うダイアログです。
 *
 * 呼び出し元にて以下のキーに対応する値をBundleで設定してください。
 * TITLE_KEY:このダイアログで表示するタイトル
 * MESSAGE_KEY:このダイアログで表示するメッセージ
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class OkCancelDialog extends DialogFragment {

    public static final String TITLE_KEY = "title_key";
    public static final String MESSAGE_KEY = "message_key";

    public interface OkCancelListener {
        void onOkClicked();
        void onCancelClicked();
    }
    private OkCancelListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OkCancelListener) {
            listener = (OkCancelListener)context;
        } else {
            throw new ClassCastException(context.getClass().getName()
            + " must implement OkCancelDialog.OkCancelListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle args = getArguments();
        if(args != null) {
            builder.setTitle(args.getString(TITLE_KEY));
            builder.setMessage(args.getString(MESSAGE_KEY));
            builder.setPositiveButton(R.string.ok, (dialog, id) -> listener.onOkClicked());
            builder.setNegativeButton(R.string.cancel, (dialog, id) -> listener.onCancelClicked());
        }
        return builder.create();
    }
}
