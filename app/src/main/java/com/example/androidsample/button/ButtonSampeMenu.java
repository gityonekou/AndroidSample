package com.example.androidsample.button;
/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 各種ボタンサンプルの動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 1.Button
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

public class ButtonSampeMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListViewの設定
        ListView menuList = findViewById(R.id.list_view);
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.button_menu,
                android.R.layout.simple_list_item_1
        ));
        menuList.setOnItemClickListener(this);
    }

    /* メニュー選択時の動作：対象のサンプルを呼びだし */
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                // 簡単な Button アプリを作る
                intent = new Intent(getApplicationContext(), ButtonSampe0101.class);
                break;
            case 1:
                // レイアウトをJavaコードだけで設定する(LinearLayout ver1)
                intent = new Intent(getApplicationContext(), ButtonSampe0201.class);
                break;
            case 2:
                // レイアウトをJavaコードだけで設定する(LinearLayout ver2)
                intent = new Intent(getApplicationContext(), ButtonSampe0202.class);
                break;
            case 3:
                // レイアウトをJavaコードだけで設定する(RelativeLayout ver)
                intent = new Intent(getApplicationContext(), ButtonSampe0203.class);
                break;
            case 4:
                // shape(シェイプ)を使ってカスタムボタンを作る(デベロッパーサンプルを試す)
                intent = new Intent(getApplicationContext(), ButtonSampe0301.class);
                break;
            case 5:
                // shape(シェイプ)を使ってカスタムボタンを作る
                intent = new Intent(getApplicationContext(), ButtonSampe0302.class);
                break;
            case 6:
                // ImageButton に画像を設定する(レイアウトを使用)
                intent = new Intent(getApplicationContext(), ButtonSampe0401.class);
                break;
            case 7:
                // ImageButton に画像を設定する(Javaコードで設定(android:src))
                intent = new Intent(getApplicationContext(), ButtonSampe0402.class);
                intent.putExtra(ButtonSampe0402.TYPE, ButtonSampe0402.TYPE_SRC);
                break;
            case 8:
                // ImageButton に画像を設定する(Javaコードで設定(android:src))
                intent = new Intent(getApplicationContext(), ButtonSampe0402.class);
                intent.putExtra(ButtonSampe0402.TYPE, ButtonSampe0402.TYPE_BACKGROUND);
                break;
            case 9:
                // onClickListenerの色々な設定
                intent = new Intent(getApplicationContext(), ButtonSampe0501.class);
                break;
            case 10:
                // Button 配列を設定する(ViewにTagでマーキングする)
                intent = new Intent(getApplicationContext(), ButtonSampe0601.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);
    }
}
