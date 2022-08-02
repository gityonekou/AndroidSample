package com.example.androidsample.select;
/*
 * AlertDialogSampe0101で呼び出されるAlertDialogです。
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

public class AlertDialogSampe0101AlertDialog extends DialogFragment {
    private String[] menuList;
    public interface NoticeDefaultDialogListener {
        void onDialogItemClick(DialogFragment fragment, String selectText);
    }
    private NoticeDefaultDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        // onAttachはフラグメントのライフサイクルでonCreateよりも前に呼ばれ、ContextにFragmentがアタッチされた
        // 際(つまり、transaction.commit();が呼び出されたタイミング)に呼び出されるライフサイクルイベントです。
        // このタイミング以降でActivityなどのContextを参照することが可能になります。
        // ※このサンプルではAlertDialogSampe0101のfragment.showが呼び出されたタイミングに該当します。
        //
        super.onAttach(context);
        // 渡されたcontext(呼び出し元のActivity)がNoticeDialogListenerを実装しているかどうかを確認。
        if(context instanceof  NoticeDefaultDialogListener) {
            this.listener = (NoticeDefaultDialogListener)context;
        } else {
            throw new ClassCastException(context.getClass().getName()
                    + " must implement AlertDialogSampe0101AlertDialog.NoticeDefaultDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        // タイトル
        alert.setTitle(R.string.alertdialog_sample0101_title);
        // 選択項目
        this.menuList = getResources().getStringArray(R.array.alertdialog_sampe0101_array);
        // アイテム選択時、値を渡すリスナー経由で選択値を呼び出し元に返す
        alert.setItems(this.menuList, (dialog, idx) -> {
            this.listener.onDialogItemClick(this, menuList[idx]);
            // ダイアログを閉じる処理はAlertDialog側で行われるのでここで実装する必要はない。
            // ただし、InputEventReceiverのログで警告が出てしまうが対処方法はないみたい？？
            // そもそもAlertDialog自体がOK、キャンセルなどのボタン押下で処理を終わらせる前提なので
            // 仕様外の実装としてあきらめるしかないような。。
        });
        return alert.create();
    }
}
