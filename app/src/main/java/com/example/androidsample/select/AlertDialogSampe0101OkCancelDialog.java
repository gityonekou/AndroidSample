package com.example.androidsample.select;
/*
 * AlertDialogSampe0101で呼び出される[OK][Cancel]ボタンを実装したAlertDialogです。
 *
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;

public class AlertDialogSampe0101OkCancelDialog extends DialogFragment {

    public interface NoticeOkCancelDialogListener {
        void choiceButton(DialogFragment fragment, String choiceText);
    }
    private NoticeOkCancelDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof NoticeOkCancelDialogListener) {
            listener = (NoticeOkCancelDialogListener)context;
        } else {
            throw new ClassCastException(context.getClass().getName()
                    + " must implement AlertDialogSampe0101OkCancelDialog.NoticeOkCancelDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(R.string.alertdialog_sample0101_ok_cancel_title);
        alert.setMessage(R.string.alertdialog_sample0101_ok_cancel_message);
        alert.setPositiveButton(R.string.ok, (dialog, id) ->
            listener.choiceButton(this, "choice OK button [id:" +id + "]"));
        alert.setNegativeButton(R.string.cancel, (dialog, id) ->
                listener.choiceButton(this, "choice Cancel button [id:" +id + "]"));
        return alert.create();
    }
}
