package com.example.androidsample.list;
/*
 * GridViewで画像を格子状に並べる。画像クリック時の画面遷移もここで実装します。
 * 対象URL:https://akira-watson.com/android/gridview.html
 *
 * ギャラリーのように画像を格子状に並べるには、GridViewを使います。基本的な使い方はListView同様でアダプタを使用します。
 * アダプタの設計も(ConvertViewの再利用が必要など)ListView作成時と同じ条件となります。
 *
 * 表示する画像リストのレイアウトは以下のようにしています。
 * レイアウトは２列で縦長は150dpあたりとし、名前はシャドウを付けて背景の明暗に対応する
 * GridView内でのセルの配置
 * android:numColumns="2":縦２列の配置
 * android:horizontalSpacing="1dp":横のスペーサーを1dp (縦ラインです!)
 * android:verticalSpacing="1dp":縦のスペーサーを1dp(横ラインです!)
 * android:stretchMode="columnWidth"： 各列が同じ幅にストレッチされる
 *
 * 各種属性値の詳細はにゃんサンプルを参照
 *
 *　また、今回画像の上にテキストを表示しますがそのテキストに影を入れています。
 * android:shadowDx=”2″ : 影の水平オフセット
 * android:shadowDy=”2″ : 影の垂直オフセット
 * android:shadowRadius=”2″ : ぼかしレベル
 * こちらの詳細もにゃんサンプルを参照のこと
 *
 */
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.util.ArrayList;
import java.util.List;

public class GridViewSample0101 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String[] names;
    private List<Integer> imageIdList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_sample0101);

        Resources res = getResources();
        this.names = res.getStringArray(R.array.listview_sampe0301_name_array);
        String[] imageIdStr = res.getStringArray(R.array.listview_sampe0301_drawable_array);
        this.imageIdList = new ArrayList<>(imageIdStr.length);
        for(String imgstr : imageIdStr) {
            this.imageIdList.add(res.getIdentifier(imgstr, "drawable", getPackageName()));
        }

        GridView gridView = findViewById(R.id.gridview_sample0101_gridview);
        gridView.setAdapter(new GridViewSample0101Adapter(getApplicationContext(),
                R.layout.gridview_sampe0101_grid_items,
                this.names,
                this.imageIdList));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), ListViewSample0301SubActivity.class);
        intent.putExtra(ListViewSample0301SubActivity.NAME_KEY, this.names[position]);
        intent.putExtra(ListViewSample0301SubActivity.IMAGE_KEY, this.imageIdList.get(position));
        startActivity(intent);
    }
}
