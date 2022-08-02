package com.example.androidsample.list;
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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.R;

public class ListViewSampeMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // メニューリストを設定
        ListView list = findViewById(R.id.list_view);
        list.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.list_menu,
                android.R.layout.simple_list_item_1
        ));
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent;
        switch(position) {
            case 0:
                // 1.簡単なテキストリストの表示(simple_list_item_1)
                intent = new Intent(getApplicationContext(), ListViewSample0101.class);
                break;
            case 1:
                // 2.簡単なテキストリストの表示(リストのレイアウトをカスタマイズ)
                intent = new Intent(getApplicationContext(), ListViewSample0102.class);
                break;
            case 2:
                // 3.簡単なテキストリストの表示(ヘッダー、フッター)
                intent = new Intent(getApplicationContext(), ListViewSample0103.class);
                break;
            case 3:
                // 4.ListViewタグを使う(＋レイアウトでヘッダー、フッター)
                intent = new Intent(getApplicationContext(), ListViewSample0201.class);
                break;
            case 4:
                // 5.ListFragmentの使い方(ListActivity:非推奨の代替)
                intent = new Intent(getApplicationContext(), ListFragmentSample0101.class);
                break;
            case 5:
                // 6.BaseAdapterを継承しカスタムでadapterを作る
                intent = new Intent(getApplicationContext(), ListViewSample0301.class);
                break;
            case 6:
                // 7.ListViewアイテムの移動と削除
                intent = new Intent(getApplicationContext(), ListViewSample0401.class);
                break;
            case 7:
                // 8.個々のアイテムでレイアウトを変える
                intent = new Intent(getApplicationContext(), ListViewSample0501.class);
                break;
            case 8:
                // 9.RecyclerViewでテキストリストの表示
                intent = new Intent(getApplicationContext(), RecyclerViewSample0101.class);
                break;
            case 9:
                // 10.RecyclerViewで画像リストの表示
                intent = new Intent(getApplicationContext(), RecyclerViewSample0102.class);
                break;
            case 10:
                // 11.RecyclerViewとItemTouchHelperでドラッグ&ドロップ
                intent = new Intent(getApplicationContext(), RecyclerViewSample0102.class);
                intent.putExtra(RecyclerViewSample0102.ITEM_TOUCH_HELPER,
                        RecyclerViewSample0102.ITEM_TOUCH_HELPER_ON);
                break;
            case 11:
                // 12.GridViewで画像を格子状に並べる
                intent = new Intent(getApplicationContext(), GridViewSample0101.class);
                break;
            case 12:
                // 13.Picassoでネット上の画像をGridViewで表示
                intent = new Intent(getApplicationContext(), GridViewSample0201.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }
        startActivity(intent);
    }
}
