package com.example.androidsample.select;
/*
 * WEBのアンドロイドプログラミングサンプルをまとめて一つのプロジェクトとしました。
 * 参考ページ:nyanのアプリ開発：Androidアプリ版
 * URL:https://akira-watson.com/
 *
 * 6.GUI：選択GUI(CheckBox, プルダウン, 各種Picker, Alertダイアログ)のサンプル動作を確認できるリンクのページを作成しています。
 * [にゃんのサンプルとのリンク]
 * 9.チェックボックス
 * 10.Spinner：プルダウン
 * 14.Picker
 * 19.AlertDialog
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

public class SelectedGuiSampeMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // リストを設定
        ListView menuList = findViewById(R.id.list_view);
        menuList.setAdapter(ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.selected_gui_menu,
                android.R.layout.simple_list_item_1
        ));
        menuList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent;
        
        switch(position) {
            case 0:
                // [CheckBox]CheckBoxの配置
                intent = new Intent(getApplicationContext(), CheckBoxSampe0101.class);
                break;
            case 1:
                // [Spinner]Spinner(スピナー)でプルダウンを表示する
                intent = new Intent(getApplicationContext(), SpinnerSampe0101.class);
                break;
            case 2:
                // [Spinner]Spinnerでプルダウンを表示する(文字列リソースxml版)
                intent = new Intent(getApplicationContext(), SpinnerSampe0102.class);
                break;
            case 3:
                // [Spinner]Spinnerをカスタマイズする(drawable版)
                intent = new Intent(getApplicationContext(), SpinnerSampe0201.class);
                intent.putExtra(SpinnerSampe0201.TYPE, SpinnerSampe0201.TYPE_DRAWABLE);
                break;
            case 4:
                // [Spinner]Spinnerをカスタマイズする(assets版)
                intent = new Intent(getApplicationContext(), SpinnerSampe0201.class);
                intent.putExtra(SpinnerSampe0201.TYPE, SpinnerSampe0201.TYPE_ASSETS);
                break;
            case 5:
                // 6.DatePicker
                intent = new Intent(getApplicationContext(), DatePickerSampe0101.class);
                break;
            case 6:
                // 7.TimePicker
                intent = new Intent(getApplicationContext(), TimePickerSampe0101.class);
                break;
            case 7:
                // 8.NumberPicker
                intent = new Intent(getApplicationContext(), NumberPickerSampe0101.class);
                break;
            case 8:
                // 9.AlertDialog
                intent = new Intent(getApplicationContext(), AlertDialogSampe0101.class);
                intent.putExtra(AlertDialogSampe0101.TYPE, AlertDialogSampe0101.TYPE_DEFAULT);
                break;
            case 9:
                // 10.AlertDialog(OK,Cancel)
                intent = new Intent(getApplicationContext(), AlertDialogSampe0101.class);
                intent.putExtra(AlertDialogSampe0101.TYPE, AlertDialogSampe0101.TYPE_OK_CANCEL);
                break;
            case 10:
                // 11.カスタムAlertDialog
                intent = new Intent(getApplicationContext(), AlertDialogSampe0201.class);
                break;
            case 11:
                // 12.カスタムAlertDialog(パスワード入力)
                intent = new Intent(getApplicationContext(), PasswordEntrySampe0101.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), CallUnderConstructionActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
        }
        startActivity(intent);
    }
}
