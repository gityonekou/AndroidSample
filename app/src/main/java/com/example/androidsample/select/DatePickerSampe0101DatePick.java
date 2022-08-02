package com.example.androidsample.select;
/*
 * DatePickerSampe0101で呼び出されるDatePickerです。
 *
 * サンプルにはDialogFragmentにもDatePickerDialog.OnDateSetListenerを空で実装する処理がありますが、
 * onCreateDialogでDatePickerDialogをnewする際に(DatePickerSampe0101)getActivity()を渡すように
 * 修正してある場合はこのクラスでonDateSetを実装する必要はありません。
 * そもそもActivityの方に実装してあるonDateSetが呼ばれ、このクラスで実装したonDateSetが呼ばれることはないので注意
 * ⇒デバック文を入れて確かめたので確定です。
 *
 * 自分のカスタマイズとして日付Aと日付Bの入力をできるように変更しました。onCreateDialog内で引数として渡された値をもとに
 * ActivityのonDateSet内で日付Aなのか日付Bなのかの振り分けができるようにタグに値を設定しています。
 *
 */
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

/*
 * DatePickerDialogを生成する際に(DatePickerSampe0101)getActivity(),を渡す場合はOnDateSetListenerの実装は不要です。
public class DatePickerSampe0101DatePick extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{
*/
public class DatePickerSampe0101DatePick extends DialogFragment {

    public static final String DATE_PICKER_KEY = "DatePickerType";
    public static final String DATE_PICKER_A = "DatePickerA";
    public static final String DATE_PICKER_B = "DatePickerB";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                (DatePickerSampe0101)getActivity(),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        Bundle arguments = getArguments();
        if(arguments != null) {
            dialog.getDatePicker().setTag(arguments.getString(
                    DATE_PICKER_KEY, "arguments key not value"));
        } else {
            dialog.getDatePicker().setTag("arguments null");
        }
        return dialog;
    }
/*
 *DatePickerDialogを生成する際に(DatePickerSampe0101)getActivity(),を渡す場合はOnDateSetListenerの実装は不要です。
    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Log.d("debug", "DialogFragment onDateSet called");
    }
 */
}
