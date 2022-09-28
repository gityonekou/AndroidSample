package com.example.androidsample.list;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.ListFragment;

import com.example.androidsample.R;
import com.example.androidsample.common.InfoMessageDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * ListFragmentSampe0101で呼び出されるListを内蔵したフラグメントです。
 * 対象URL:https://akira-watson.com/android/listactivity.html
 * 対象URL:https://android.keicode.com/basics/fragments-listfragment.php
 *
 * サンプルにあるListActivityがAPI30で非推奨となっため代替となるListFragmentでサンプルを作成しています。
 * 基本はフラグメント作成のサンプル＋今回のリストアクティビティの部分です。
 * アダプタ部分はにゃんのサンプルでそのまま利用できます
 * フラグメントの作成はFragmentSampe0101やFragmentSampe0301などを参照のこと
 * ListFragmentを使う場合、通常のFragmentと違いonCreateViewメソッドをオーバーライドする必要がありません。
 * そのため、フラグメントに対応するリストのみのレイアウトxmlも必要なくなる点に注意です。
 * アンドロイドデベロッパー：https://developer.android.com/guide/components/fragments?hl=ja
 *  「注:フラグメントが ListFragment のサブクラスの場合、デフォルトの実装で ListView がonCreateView() から返される
 *      ため、これを実装する必要はありません。」
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ListFragmentSampe0101Fragment extends ListFragment {
    private final Integer[] imageDrawables = {
            android.R.drawable.ic_menu_call,
            android.R.drawable.ic_menu_close_clear_cancel,
            android.R.drawable.ic_menu_compass,
            android.R.drawable.ic_menu_crop,
            android.R.drawable.ic_menu_delete,
            android.R.drawable.ic_menu_directions,
            android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_edit,
            android.R.drawable.ic_menu_help
    };
    private final String[] imageComments = {
            "call", "cancel", "compass", "crop", "delete",
            "directions", "gallery","edit","help"
    };

    /* ListFragmentを使用する場合アンドロイドデフォルトのリストのレイアウトxmlを使用するためonCreateViewメソッドの
     * オーバーライドと「fragment_listfragment_sample0101」は不要になります。レイアウトソースは参考として残しておきます。
     */
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//       return inflater.inflate(R.layout.fragment_listfragment_sample0101, container, false);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 表示するリストを元にアダプタを作成し設定
        List<ListFragmentSample0101CellData> list = new ArrayList<>(imageDrawables.length);
        for(int i = 0; i < imageDrawables.length; i++) {
            list.add(new ListFragmentSample0101CellData(imageComments[i], imageDrawables[i]));
        }
        setListAdapter(new ListFragmentSampe0101ListViewAdapter(
                requireContext(), R.layout.listfragment_sample0101_list, list));
    }

    @Override
    public void onListItemClick(@NonNull ListView list, @NonNull View view, int position, long id) {
        // リストの選択内容をダイアログで出力する
        DialogFragment dialog = new InfoMessageDialog();
        Bundle args = new Bundle();
        args.putString(InfoMessageDialog.MESSAGE_KEY, "選択内容：" + imageComments[position]);
        dialog.setArguments(args);
        dialog.show(requireActivity().getSupportFragmentManager(), InfoMessageDialog.class.getName());
    }
}
