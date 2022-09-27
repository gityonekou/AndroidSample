/*
 * アンドロイド標準アイコンを一覧表示(GridView)します。
 * 　対象URL：https://akira-watson.com/android/gridview-icon.html
 *
 * アンドロイドで標準で用意されているアイコンをGridViewで一覧表示します。
 * GridViewについては「リスト表示」メニューの「GridView」にて確認ください。
 *
 * 【アンドロイドの標準アイコン】
 * かなりの数があります。今回は最初の24個のみをリストで表示するサンプルです。
 * パターン１としてデータクラスとなるIconListクラスを作成しそのデータを表示するタイプ
 * ・Android標準アイコン(一覧表示)メニューに対応
 * パターン２としてstring_arrayに表示するリストの値を定義しgetIdentifierで対応するIDを取得するタイプを作成します。
 * リソースのgetIdentifierメソッドには以下のパラメータを渡します。
 * int imageID = context.getResources().getIdentifier("android:id/[キー名]",null, null);
 * キー名:alert_dark_frameなど
 *
 */
package com.example.androidsample.imageview;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * アンドロイド標準アイコンの一覧表示
 * 「15. Android標準アイコン(一覧表示)」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/gridview-icon.html
 *
 *【サンプルについて】
 * アンドロイドで標準で用意されているアイコンをGridViewで一覧表示します。
 * GridViewについては「リスト表示」メニューの「GridView」にて確認ください。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ImageViewSampe0602 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // サンプルの実行タイプとその値
    public static final String TYPE_KEY = "type_key";
    //データクラスとなるIconListクラスを作成しそのデータを表示するタイプ
    public static final int TYPE1 = 1;
    // string_arrayに表示するリストの値を定義しgetIdentifierで対応するIDを取得するタイプ
    public static final int TYPE2 = 2;

    private TextView textView;
    private String[] nameArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_sample0602);

        this.textView = findViewById(R.id.imageview_sample0602_textview);
        GridView gridView = findViewById(R.id.imageview_sample0602_gridview);

        // タイプにより表示するデータを分けるて表示データを作成
        List<Integer> imageIdList;
        int type = getIntent().getIntExtra(TYPE_KEY, TYPE1);
        if(type == TYPE2) {
            Resources res = getResources();
            this.nameArray = res.getStringArray(R.array.imageview_sampe0602_icon_name_array);
            imageIdList = new ArrayList<>(this.nameArray.length);
            for(String iconName : this.nameArray) {
                imageIdList.add(res.getIdentifier("android:drawable/" + iconName,
                        null, null));
            }
        } else {
            ImageViewSampe0602IconData listData = new ImageViewSampe0602IconData();
            this.nameArray = listData.getListNameArray();
            imageIdList = Arrays.asList(listData.getiListImageIdArray());
        }
        // アダプタ、リスナーを設定
        gridView.setAdapter(new ImageViewSampe0602Adapter(
                getApplicationContext(),
                R.layout.imageview_sampe0602_grid_items,
                imageIdList
        ));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.textView.setText(this.nameArray[position]);
    }
}
