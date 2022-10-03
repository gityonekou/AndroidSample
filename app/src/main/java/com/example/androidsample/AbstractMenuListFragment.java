package com.example.androidsample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/**
 * 各種メニューを表示するフラグメントの基底となるクラスです。
 * 各種メニューに対応するフラグメントはこのクラスを継承して実装してください。
 *
 **************************************
 * 変更履歴:
 * ver2.00 新規作成
 *
 */
public abstract class AbstractMenuListFragment extends Fragment
        implements AdapterView.OnItemClickListener {

    private static final String KEY = "title";

    /**
     *  タイトル部に表示するメッセージを取得します。
     *  このメソッドを継承先で実装してください。
     * @return タイトル部の表示メッセージ
     */
    protected abstract String getTitleMessage();

    /**
     * 表示するメニューリストに対応するリソースIDを返します。
     * このメソッドを継承先で実装してください。
     * @return メニューリストに対応するリソースID
     */
    protected abstract int getTextArrayResId();

    /* アクティビティへのアタッチ時に呼び出される */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // パラメータを設定
        Bundle args = new Bundle();
        args.putString(KEY, getTitleMessage());
        setArguments(args);
    }

    /* フラグメントで表示するViewを生成し返します。 */
    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menulist, container, false);
    }

    /* Viewに各種パーツを配置します */
    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // タイトルを設定
        Bundle args = getArguments();
        if(args != null) {
            TextView textView = view.findViewById(R.id.menu_text);
            textView.setText(args.getString(KEY));
        }

        // ListViewの設定
        ListView menuList = view.findViewById(R.id.menu_list);
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getContext(),
                getTextArrayResId(),
                android.R.layout.simple_list_item_1
        ));
        menuList.setOnItemClickListener(this);
    }
}
