/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 8. リスト表示(ListView⇒RecyclerView, GridView)のサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 16.ListView
 * 17.RecyclerView
 * 18.GridView
 * ※GridView：標準アイコンをGridViewで表示は「3. ImageView～」メニューに移動しました。
 *
 */
package com.example.androidsample.list;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.androidsample.AbstractMenuListFragment;
import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.R;

/**
 * トップメニューにて以下選択時に対応するフラグメントです
 * 「8. リスト表示(ListView⇒RecyclerView, GridView)」
 *
 * メニューリストの表示項目設定と項目選択時のリスナーを実装します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.トップメニュー、サブメニューをフラグメントに変更
 *    AbstractMenuList継承にてListViewSampeMenuをフラグメントに変更
 * 2.javadoc追加対応
 *
 */
public class ListViewSampeMenuFragment extends AbstractMenuListFragment {

    private String title;

    /**
     * このフラグメントのインスタンスを生成して返します
     *
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static ListViewSampeMenuFragment newInstance(String title) {
        ListViewSampeMenuFragment fragment = new ListViewSampeMenuFragment();
        // パラメータを設定
        fragment.title = title;
        return fragment;
    }

    @Override
    protected String getTitleMessage() {
        return title;
    }

    @Override
    protected int getTextArrayResId() {
        return R.array.list_menu;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent;
        switch(position) {
            case 0:
                // 1.簡単なテキストリストの表示(simple_list_item_1)
                intent = new Intent(getContext(), ListViewSample0101.class);
                break;
            case 1:
                // 2.簡単なテキストリストの表示(リストのレイアウトをカスタマイズ)
                intent = new Intent(getContext(), ListViewSample0102.class);
                break;
            case 2:
                // 3.簡単なテキストリストの表示(ヘッダー、フッター)
                intent = new Intent(getContext(), ListViewSample0103.class);
                break;
            case 3:
                // 4.ListViewタグを使う(＋レイアウトでヘッダー、フッター)
                intent = new Intent(getContext(), ListViewSample0201.class);
                break;
            case 4:
                // 5.ListFragmentの使い方(ListActivity:非推奨の代替)
                intent = new Intent(getContext(), ListFragmentSample0101.class);
                break;
            case 5:
                // 6.BaseAdapterを継承しカスタムでadapterを作る
                intent = new Intent(getContext(), ListViewSample0301.class);
                break;
            case 6:
                // 7.ListViewアイテムの移動と削除
                intent = new Intent(getContext(), ListViewSample0401.class);
                break;
            case 7:
                // 8.個々のアイテムでレイアウトを変える
                intent = new Intent(getContext(), ListViewSample0501.class);
                break;
            case 8:
                // 9.RecyclerViewでテキストリストの表示
                intent = new Intent(getContext(), RecyclerViewSample0101.class);
                break;
            case 9:
                // 10.RecyclerViewで画像リストの表示
                intent = new Intent(getContext(), RecyclerViewSample0102.class);
                break;
            case 10:
                // 11.RecyclerViewとItemTouchHelperでドラッグ&ドロップ
                intent = new Intent(getContext(), RecyclerViewSample0102.class);
                intent.putExtra(RecyclerViewSample0102.ITEM_TOUCH_HELPER,
                        RecyclerViewSample0102.ITEM_TOUCH_HELPER_ON);
                break;
            case 11:
                // 12.GridViewで画像を格子状に並べる
                intent = new Intent(getContext(), GridViewSample0101.class);
                break;
            case 12:
                // 13.Picassoでネット上の画像をGridViewで表示
                intent = new Intent(getContext(), GridViewSample0201.class);
                break;
            default:
                intent = new Intent(getContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }
        startActivity(intent);
    }
}
