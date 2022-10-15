package com.example.androidsample;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidsample.app.AppSampeMenuFragment;
import com.example.androidsample.bar.BarGuiSampeMenuFragment;
import com.example.androidsample.button.ButtonSampeMenuFragment;
import com.example.androidsample.common.CallUnderConstructionActivity;
import com.example.androidsample.datastorage.DataStorageSampeMenuFragment;
import com.example.androidsample.imageview.ImageViewSampeMenuFragment;
import com.example.androidsample.list.ListViewSampeMenuFragment;
import com.example.androidsample.notify.NotifySampeMenuFragment;
import com.example.androidsample.other.OtherSampeMenuFragment;
import com.example.androidsample.select.SelectedGuiSampeMenuFragment;
import com.example.androidsample.text.TextSampeMenuFragment;

/**
 * トップメニューに対応するフラグメントです
 * トップメニューの表示と項目選択時のリスナーを実装します。
 * メニューのフラグメントはAbstractMenuListを継承し実装します。
 *
 **************************************
 * 変更履歴:
 * ver2.00 新規作成
 * ver2.01 更新
 * ・タイトルとメニューリストのリソースID設定メソッドの名前変更
 *
 */
public class MainMenuFragment extends AbstractMenuListFragment {

    // タイトル
    private String title;
    // メニューリスト
    private String[] menuItems;

    /**
     * このインスタンスを生成して返します。
     * @param title 表示するタイトル
     * @return このフラグメントのインスタンス
     */
    public static MainMenuFragment newInstance(String title) {
        MainMenuFragment fragment = new MainMenuFragment();
        // パラメータを設定
        fragment.title = title;
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // アタッチ時、トップメニューのリスト表示データを取得
        this.menuItems = context.getResources().getStringArray(createTextArrayResId());
    }

    @Override
    protected String createTitleMessage() {
        return title;
    }

    @Override
    protected int createTextArrayResId() {
        return R.array.top_menu;
    }

    /* メニュー選択時の動作：対象のサンプルを呼びだし */
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                // 1. Button
                fragment = ButtonSampeMenuFragment.newInstance(this.menuItems[position]);
                break;
            case 1:
                // 2. TextViewとEditText
                fragment = TextSampeMenuFragment.newInstance(this.menuItems[position]);
                break;
            case 2:
                // 3. ImageView
                fragment = ImageViewSampeMenuFragment.newInstance(this.menuItems[position]);
                break;
            case 3:
                // 4. 各種進捗バー(ProgressBar, SeekBar)
                fragment = BarGuiSampeMenuFragment.newInstance(this.menuItems[position]);
                break;
            case 4:
                // 5. 通知(トースト, スナックバー)
                fragment = NotifySampeMenuFragment.newInstance(this.menuItems[position]);
                break;
            case 5:
                // 6.：選択GUI(CheckBox, Spinner:プルダウン, 各種Picker, Alertダイアログ)
                fragment = SelectedGuiSampeMenuFragment.newInstance(this.menuItems[position]);
                break;
            case 6:
                // 7. アプリ(Activity, Fragment, Service)
                fragment = AppSampeMenuFragment.newInstance(this.menuItems[position]);
                break;
            case 7:
                // 8. リスト表示(ListView⇒RecyclerView, GridView)
                fragment = ListViewSampeMenuFragment.newInstance(this.menuItems[position]);
                break;
            case 8:
                // 9. データストレージ(I/O)
                fragment = DataStorageSampeMenuFragment.newInstance(this.menuItems[position]);
                break;
            case 9:
                // 10. その他メニュー
                fragment = OtherSampeMenuFragment.newInstance(this.menuItems[position]);
                break;
        }
        if(fragment != null) {
            FragmentManager manager = getParentFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.menu_container, fragment);
            transaction.commit();
        } else {
            Intent intent = new Intent(this.getContext(), CallUnderConstructionActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }
}
