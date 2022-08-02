package com.example.androidsample.select;
/*
 * DatePickerをDialogFragmentで実装する
 *
 * 　対象URL：https://akira-watson.com/android/datepicker-timepicker.html
 *
 * 日付や時間をユーザーに入力してもらうために DatePicker や TimePicker を使うと便利です。
 *
 * 【DialogFragment】
 * Pickerの実装についてはAndroid 3.0 以上で DialogFragment を使うことが推奨されています。
 * また、Time Picker はDate Picker と同様に作成できます。
 *
 * 【DatePicker】
 * このサンプルではDatePickerについてサンプルを作成しています。
 * 自分のカスタマイズとして日付Aと日付Bの入力をできるように変更しました。onDateSet内で設定したタグにて
 * 振り分けをしています。
 *
 * Googleの解説では、Layoutの android:onClick で Activity のメソッドが呼ばれ、Pickerの
 * DialogFragment が表示されます。
 * <Button
 * 　　...
 * 　　android:onClick="Activityで呼び出すメソッド" />
 *
 * 設定したデータを取り出すには
 * Picker の DatePickerDialog.OnDateSetListener を使って、 MainActivityにデータを戻してやります。
 *
 *
 */
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.example.androidsample.R;

import java.util.Locale;

public class DatePickerSampe0101 extends FragmentActivity
        implements DatePickerDialog.OnDateSetListener{

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker_sample0101);
        this.textView = findViewById(R.id.datepicker_sample0101_textview);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        if (datePicker.getTag() != null) {
            Log.d("debug", "Activity onDateSet called. [tag=" + datePicker.getTag().toString() + "]");
        } else {
            Log.d("debug", "Activity onDateSet called. [tag=null]");
        }
        String str;
        if (datePicker.getTag().toString().equals(DatePickerSampe0101DatePick.DATE_PICKER_A)) {
            str = String.format(Locale.US,
                    "DATE A value[%d/%02d/%02d]", year, monthOfYear + 1, dayOfMonth);
        } else if (datePicker.getTag().toString().equals(DatePickerSampe0101DatePick.DATE_PICKER_B)) {
            str = String.format(Locale.US,
                    "DATE B value[%d/%02d/%02d]", year, monthOfYear + 1, dayOfMonth);
        } else {
            str = "エラー:tag値が不正。要ログ確認。";
        }
        this.textView.setText(str);
    }

    public void showDatePickerDialogA(View view) {
        DialogFragment fragment = new DatePickerSampe0101DatePick();
        Bundle args = new Bundle();
        args.putString(DatePickerSampe0101DatePick.DATE_PICKER_KEY, DatePickerSampe0101DatePick.DATE_PICKER_A);
        fragment.setArguments(args);
        fragment.show(getSupportFragmentManager(), DatePickerSampe0101DatePick.DATE_PICKER_KEY);
    }
    public void showDatePickerDialogB(View view) {
        DialogFragment fragment = new DatePickerSampe0101DatePick();
        Bundle args = new Bundle();
        args.putString(DatePickerSampe0101DatePick.DATE_PICKER_KEY, DatePickerSampe0101DatePick.DATE_PICKER_B);
        fragment.setArguments(args);
        fragment.show(getSupportFragmentManager(), DatePickerSampe0101DatePick.DATE_PICKER_KEY);
    }
}
