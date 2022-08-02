package com.example.androidsample.select;
/*
 * TimePickerSampe0101で呼び出されるDatePickerです。
 *
 * サンプルにはDialogFragmentにもTimePickerDialog.OnTimeSetListenerを空で実装する処理がありますが、
 * onCreateDialogでTimePickerDialogをnewする際に(TimePickerSampe0101)getActivity()を渡すように
 * 修正してある場合はこのクラスでonTimeSetを実装する必要はありません。
 * ⇒DatePickerSampe0101と同様です。
 *
 */
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerSampe0101TimePick extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        return new TimePickerDialog(
                getActivity(),
                (TimePickerSampe0101)getActivity(),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);
    }
}
