/*
 * TimePickerをDialogFragmentで実装する
 *
 * 　対象URL：https://akira-watson.com/android/datepicker-timepicker.html
 *
 * 日付や時間をユーザーに入力してもらうために DatePicker や TimePicker を使うと便利です。
 *
 * 【DialogFragment】
 * Pickerの実装についてはAndroid 3.0 以上で DialogFragment を使うことが推奨されています。
 * また、TimePicker はDatePicker と同様に作成できます。
 *
 * 【TimePicker】
 * このサンプルではTimePickerについてサンプルを作成しています。
 * 作成方法についてはDatePickerと同様となるのでDatePickerSampe0101にて確認のこと
 * なお、こちらのサンプルではAppCompatActivityを継承して作成しています。
 * AppCompatActivity自体がFragmentActivityを継承して作成しているので動作に問題はありませんが、
 * AppCompatActivityの方がクラスの処理内容は多くなるので重くはなるかもです。。
 *
 *
 */
package com.example.androidsample.select;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.androidsample.R;

import java.util.Locale;

/**
 * TimePicker サンプル01
 *
 * 「7.TimePicker」に対応するアクティビティです。
 *
 * 　対象URL：https://akira-watson.com/android/datepicker-timepicker.html
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class TimePickerSampe0101 extends AppCompatActivity
        implements TimePickerDialog.OnTimeSetListener {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker_sample0101);
        this.textView = findViewById(R.id.timepicker_sample0101_textview);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        this.textView.setText(String.format(Locale.US, "%02d:%02d", hourOfDay, minute));
    }

    /**
     * 「時間を選択」ボタンを押下時のイベントリスナーです。レイアウトより直接呼び出します。
     *
     * @param view レイアウト
     */
    public void showTimePickerDialog(View view) {
        DialogFragment fragment = new TimePickerSampe0101TimePick();
        fragment.show(getSupportFragmentManager(), "timePicker");
    }
}
