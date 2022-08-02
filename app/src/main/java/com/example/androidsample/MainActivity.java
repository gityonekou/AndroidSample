package com.example.androidsample;
/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * トップページに各種サンプルの動作を確認できるリンクのページを作成しています。
 *
 */
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidsample.app.AppSampeMenu;
import com.example.androidsample.bar.BarGuiSampeMenu;
import com.example.androidsample.button.ButtonSampeMenu;
import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.imageview.ImageViewSampeMenu;
import com.example.androidsample.list.ListViewSampeMenu;
import com.example.androidsample.notify.NotifySampeMenu;
import com.example.androidsample.other.OtherSampeMenu;
import com.example.androidsample.select.SelectedGuiSampeMenu;
import com.example.androidsample.text.TextSampeMenu;
import com.example.androidsample.datastorage.DataStorageSampeMenu;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /*
    // トップメニューリストをStringArrayから読み込むように変更のためコメントアウト
    // ソース自体は後で参照できるようにするためコメントアウトとする。トップメニュー以外は変更前のソースは削除とする
    // トップメニューリスト
    private static final String[] menus;
    static {
        menus = new String[]{
                "1. Button★変更前★",
                "2. TextViewとEditText",
                "3. ImageView",
                "4. GUI：進捗バー(ProgressBar, SeekBar)",
                "5. 通知(トースト, スナックバー)",
                "6. GUI：選択ボタン(CheckBox, プルダウン)",
                "7. アプリ(Activity, Fragment, Service)",
                "99. その他",
        };
    }
    */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListViewの設定
        ListView menuList = findViewById(R.id.list_view);

        /*
        //Topメニューのみ後で参照できるように変更前のソースを残す
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                menus);
        menuList.setAdapter(adapter);
        */
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.top_menu,
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
                // 1. Button
                intent = new Intent(this.getApplicationContext(), ButtonSampeMenu.class);
                break;
            case 1:
                // 2. TextViewとEditText
                intent = new Intent(this.getApplicationContext(), TextSampeMenu.class);
                break;
            case 2:
                // 3. ImageView
                intent = new Intent(this.getApplicationContext(), ImageViewSampeMenu.class);
                break;
            case 3:
                // 4. 各種進捗バー(ProgressBar, SeekBar)
                intent = new Intent(this.getApplicationContext(), BarGuiSampeMenu.class);
                break;
            case 4:
                // 5. 通知(トースト, スナックバー)
                intent = new Intent(this.getApplicationContext(), NotifySampeMenu.class);
                break;
            case 5:
                // 6.：選択GUI(CheckBox, Spinner:プルダウン, 各種Picker, Alertダイアログ)
                intent = new Intent(this.getApplicationContext(), SelectedGuiSampeMenu.class);
                break;
            case 6:
                // 7. アプリ(Activity, Fragment, Service)
                intent = new Intent(this.getApplicationContext(), AppSampeMenu.class);
                break;
            case 7:
                // 8. リスト表示(ListView⇒RecyclerView, GridView)
                intent = new Intent(this.getApplicationContext(), ListViewSampeMenu.class);
                break;
            case 8:
                // 9. データストレージ(I/O)
                intent = new Intent(this.getApplicationContext(), DataStorageSampeMenu.class);
                break;
            case 9:
                // 10. その他メニュー
                intent = new Intent(this.getApplicationContext(), OtherSampeMenu.class);
                break;
            default:
                intent = new Intent(this.getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }

        startActivity(intent);
    }
}