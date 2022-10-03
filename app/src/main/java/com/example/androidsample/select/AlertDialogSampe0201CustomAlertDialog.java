package com.example.androidsample.select;

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

/**
 * AlertDialogSampe0201で呼び出されるカスタムされたAlertDialogです。
 *
 * 呼び出し元にて表示画像を選択時のリスナーを実装してください。
 * AlertDialogSampe0201CustomAlertDialog.NoticeCustomDialogListenerインターフェース
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class AlertDialogSampe0201CustomAlertDialog extends DialogFragment {

    /**
     * AlertDialogSampe0201CustomAlertDialog.NoticeCustomDialogListenerインターフェース
     *
     */
    public interface NoticeCustomDialogListener {
        /**
         * 画像選択時に呼び出されます
         *
         * @param fragment このダイアログのフラグメント
         * @param imageId 選択した画像に対応するイメージリソースID
         * @param message 選択した画像に対応するメッセージ
         */
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
