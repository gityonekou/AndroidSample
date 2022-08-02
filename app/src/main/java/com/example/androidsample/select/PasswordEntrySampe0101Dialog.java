package com.example.androidsample.select;
/*
 * 　カスタムでユーザID、パスワードを入力するAlertDialogを作成します。
 *
 *
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;

public class PasswordEntrySampe0101Dialog extends DialogFragment {

    public interface NoticePasswordEntryDialogListener {
        void entryValue(DialogFragment fragment, String id, String password);
    }
    private NoticePasswordEntryDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof NoticePasswordEntryDialogListener) {
            this.listener = (NoticePasswordEntryDialogListener) context;
        } else {
            throw new ClassCastException(context.getClass().getName()
                    + " must implement PasswordEntrySampe0101Dialog.NoticePasswordEntryDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.passwordentry_sample0101_dialog_title);
        // requireActivity()についてはAlertDialogSampe0201CustomAlertDialogを参照
        View entryView = requireActivity().getLayoutInflater().inflate(
                R.layout.passwordentry_sample0101_dialog_layout, null);
        dialog.setView(entryView);
        // サインイン時、未入力の場合は再度入力をさせたいのでここではリスナーにnullを指定
        dialog.setPositiveButton(R.string.signin, null);
        // キャンセルの場合はこのまま画面を閉じる
        dialog.setNegativeButton(R.string.cancel, (dialogInterface, id) -> requireDialog().cancel());

        // create()ではダイアログのみしか生成されないのでgetButton時にnullが返されてしまう
        // show()を呼び出す必要あり。
        AlertDialog returnDialog = dialog.show();

        // ダイアログ生成後、ポジティブボタンのリスナを再設定することで入力内容のチェックがエラーならダイアログを閉じない
        // ようにする
        Button positiveBtn = returnDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveBtn.setOnClickListener(v -> {
            EditText username = entryView.findViewById(R.id.username);
            EditText password = entryView.findViewById(R.id.password);
            if(username.getText().toString().equals("")
                    || password.getText().toString().equals("")) {
                Toast.makeText(getActivity(),
                        R.string.passwordentry_sample0101_dialog_error,
                        Toast.LENGTH_LONG).show();
            } else {
                this.listener.entryValue(
                        this, username.getText().toString(), password.getText().toString());
                // ダイアログを消す
                requireDialog().dismiss();
            }
        });
        return returnDialog;
    }
}
