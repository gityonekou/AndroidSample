package com.example.androidsample.list;
/*
 * ListViewSample0401で呼び出されるリスト選択時のアクション選択ダイアログです。
 * 上に移動、下に移動、削除、キャンセルをリスト形式で表示し選択結果を呼び出し元に返します。
 *
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class ListViewSample0401ActionDialog extends DialogFragment {

    public static final String TITLE_KEY = "title";
    public static final String UP_ACTION_DELETE = "up_action_delete";
    public static final String DOWN_ACTION_DELETE = "down_action_delete";
    public static final int DELETE_ON = 1;
    public static final int DELETE_OFF = -1;

    public interface OnItemClickActionListener {
        void moveAbove();
        void moveBelow();
        void delete();
        void cancel();
    }
    private OnItemClickActionListener listener;
    private boolean upDeleteOn = false;
    private boolean downDelteOn = false;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnItemClickActionListener) {
            listener = (OnItemClickActionListener)context;
        } else {
            throw new ClassCastException(context.getClass().getName()
             + " must implement ListViewSample0401ActionDialog.OnItemClickActionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        List<String> menuList = new ArrayList<>();
        Bundle args = getArguments();
        if(args != null) {
            dialog.setTitle(args.getString(TITLE_KEY));
            // 上に移動を表示するかどうか判定し、表示しないならフラグをOn<後でリスナーで使用する>
            if(args.getInt(UP_ACTION_DELETE, DELETE_OFF) == DELETE_OFF) {
                menuList.add("上に移動");
            } else {
                upDeleteOn = true;
            }
            // 下に移動を表示するかどうか判定し、表示しないならフラグをOn<後でリスナーで使用する>
            if(args.getInt(DOWN_ACTION_DELETE, DELETE_OFF) == DELETE_OFF) {
                menuList.add("下に移動");
            } else {
                downDelteOn = true;
            }
        }
        menuList.add("削除");
        menuList.add("cancel");
        String[] menus = new String[menuList.size()];
        for(int i = 0; i < menuList.size(); i++) {
            menus[i] = menuList.get(i);
        }
        dialog.setItems(menus, (dialogInterface, idx) -> {
            if(menuList.size() == 2) {
                /* 削除：キャンセルのみの表示の場合 */
                if(idx == 0) {
                    listener.delete();
                } else {
                    listener.cancel();
                }
            } else if(menuList.size() == 3) {
                /* [上に移動 or 下に移動]、削除：キャンセルを表示の場合 */
                if(idx == 0) {
                    // 上に移動削除時は下に移動が選択内容
                    if(upDeleteOn) listener.moveBelow();
                    // 下に移動削除時は上に移動が選択内容
                    if(downDelteOn) listener.moveAbove();
                } else if(idx == 1) {
                    listener.delete();
                } else {
                    listener.cancel();
                }
            } else {
                /* 上に移動、下に移動、削除：キャンセルを表示の場合 */
                switch (idx) {
                    case 0:
                        listener.moveAbove();
                        break;
                    case 1:
                        listener.moveBelow();
                        break;
                    case 2:
                        listener.delete();
                        break;
                    case 3:
                        listener.cancel();
                }
            }

        });
        return dialog.create();
    }
}
