/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 3.ImageViewとScrollView、GridView：標準アイコンをGridViewで表示のサンプル動作を確認できる
 * リンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 4.ImageView
 * 15.ScrollView
 * 18.GridView:「標準アイコンをGridViewで表示」
 *
 */
package com.example.androidsample.imageview;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.androidsample.AbstractMenuListFragment;
import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.R;

/**
 * トップメニューにて以下選択時に対応するフラグメントです
 * 「3. ImageViewとScrollView、Android標準アイコン」
 *
 * メニューリストの表示項目設定と項目選択時のリスナーを実装します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 更新
 * 1.トップメニュー、サブメニューをフラグメントに変更
 *    AbstractMenuList継承にてImageViewSampeMenuをフラグメントに変更
 * 2.javadoc追加対応
 * ver2.01 更新
 * ・タイトルとメニューリストのリソースID設定メソッドの名前変更
 *
 */
public class ImageViewSampeMenuFragment extends AbstractMenuListFragment {

    private String title;

    /**
     * このフラグメントのインスタンスを生成して返します。
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static ImageViewSampeMenuFragment newInstance(String title) {
        ImageViewSampeMenuFragment fragment = new ImageViewSampeMenuFragment();
        // パラメータを設定
        fragment.title = title;
        return fragment;
    }

    @Override
    protected String onCreateTitleMessage() {
        return title;
    }

    @Override
    protected int onCreateTextArrayResId() {
        return R.array.image_view_menu;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // ImageViewを使って画像を表示する：(android:srcを使う)
                intent = new Intent(getContext(), ImageViewSampe0101.class);
                break;
            case 1:
                // ImageViewを使って画像を表示する：(ImageView.setImageResourceメソッドを使う)
                intent = new Intent(getContext(), ImageViewSampe0102.class);
                break;
            case 2:
                // ImageViewを使って画像を表示する：(assetsに画像を置きそれを取り込む)
                intent = new Intent(getContext(), ImageViewSampe0103.class);
                break;
            case 3:
                // 上サンプルをLayoutを使わずコードで(ic_launcherを画像として表示)
                intent = new Intent(getContext(), ImageViewSampe0201.class);
                break;
            case 4:
                // 画像をドラッグする：(View.layout()メソッド)
                intent = new Intent(getContext(), ImageViewSampe0301.class);
                break;
            case 5:
                // 画像をドラッグする：(onTouchムーブアクション)
                intent = new Intent(getContext(), ImageViewSampe0302.class);
                break;
            case 6:
                // Matrixで画像を回転、フリップ、縮小させる
                intent = new Intent(getContext(), ImageViewSampe0401.class);
                break;
            case 7:
                // Picassoで大きい画像を扱ってみる
                intent = new Intent(getContext(), ImageViewSampe0501.class);
                break;
            case 8:
                // ScrollView(縦スクロール)
                intent = new Intent(getContext(), ScrollViewSampe0101.class);
                intent.putExtra(ScrollViewSampe0101.KEY, ScrollViewSampe0101.TYPE1);
                break;
            case 9:
                // ScrollView(複数UI：縦スクロール)
                intent = new Intent(getContext(), ScrollViewSampe0101.class);
                intent.putExtra(ScrollViewSampe0101.KEY, ScrollViewSampe0101.TYPE2);
                break;
            case 10:
                // ScrollView(縦スクロール)コード版
                intent = new Intent(getContext(), ScrollViewSampe0102.class);
                break;
            case 11:
                // HorizontalScrollView(横スクロール)
                intent = new Intent(getContext(), HorizontalScrollViewSampe0101.class);
                break;
            case 12:
                // HorizontalScrollView(横スクロール)コード版
                intent = new Intent(getContext(), HorizontalScrollViewSampe0102.class);
                break;
            case 13:
                // Android標準アイコン
                intent = new Intent(getContext(), ImageViewSampe0601.class);
                break;
            case 14:
                // Android標準アイコン(一覧表示)
                intent = new Intent(getContext(), ImageViewSampe0602.class);
                intent.putExtra(ImageViewSampe0602.TYPE_KEY, ImageViewSampe0602.TYPE1);
                break;
            case 15:
                // Android標準アイコン(一覧表示:getIdentifierを使う)
                intent = new Intent(getContext(), ImageViewSampe0602.class);
                intent.putExtra(ImageViewSampe0602.TYPE_KEY, ImageViewSampe0602.TYPE2);
                break;
            default:
                intent = new Intent(getContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);

    }
}
