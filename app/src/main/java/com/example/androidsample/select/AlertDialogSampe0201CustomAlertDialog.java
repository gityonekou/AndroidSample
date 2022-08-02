package com.example.androidsample.select;
/*
 * AlertDialogSampe0201で呼び出されるカスタムされたAlertDialogです。
 *
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;

public class AlertDialogSampe0201CustomAlertDialog extends DialogFragment {

    public interface NoticeCustomDialogListener {
        void choiceImage(DialogFragment fragment, int imageId, String message);
    }
    private NoticeCustomDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof NoticeCustomDialogListener) {
            this.listener = (NoticeCustomDialogListener)context;
        } else {
            throw new ClassCastException(context.getClass().getName()
                    + " must implement AlertDialogSampe0201CustomAlertDialog.NoticeCustomDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(R.string.alertdialog_sample0201_title);
        // getActivity()は戻り値がnullの可能性があるのでnull判定が必要だが、
        // requireActivity()だと戻り値がnullになることはないのでこちらを使う(デベロッパーガイドより)
        // todo:requireActivity()についてもう少し調べる必要あり
        View alertView = requireActivity().getLayoutInflater().inflate(
                R.layout.alertdialog_sample0201_alertdialog_layout, null);
        // 作成したレイアウトでViewを設定
        alert.setView(alertView);
        // 各種リスナの設定
        ImageView image1 = alertView.findViewById(R.id.alertdialog_sample0201_alertdialog_image1);
        image1.setOnClickListener(v -> {
            listener.choiceImage(this, R.drawable.moon1,
                    getString(R.string.alertdialog_sample0201_moon));
            // ダイアログを消す
            requireDialog().dismiss();
        });
        ImageView image2 = alertView.findViewById(R.id.alertdialog_sample0201_alertdialog_image2);
        image2.setOnClickListener( v -> {
            listener.choiceImage(this, R.drawable.moon6,
                    getString(R.string.alertdialog_sample0201_tibimoon));
            // ダイアログを消す
            requireDialog().dismiss();
        });
        return alert.create();
    }
}
