package com.example.androidsample.imageview;
/*
 * ImageViewSampe0602で表示する一覧データを定義したデータクラスです。
 *
 */
public class ImageViewSampe0602IconData {
    private final String[] iListName = {
            "alert_dark_frame", "alert_light_frame", "arrow_down_float",
            "arrow_up_float", "bottom_bar", "btn_default", "btn_default_small",
            "btn_dialog", "btn_dropdown", "btn_minus",
            "btn_plus", "btn_radio", "btn_star",
            "btn_star_big_off", "btn_star_big_on", "button_onoff_indicator_off",
            "button_onoff_indicator_on", "checkbox_off_background", "checkbox_on_background",
            "dark_header", "dialog_frame", "dialog_holo_dark_frame",
            "dialog_holo_light_frame", "divider_horizontal_bright",
    };
    private final Integer[] iListImageId = {
            android.R.drawable.alert_dark_frame
            ,android.R.drawable.alert_light_frame
            ,android.R.drawable.arrow_down_float
            ,android.R.drawable.arrow_up_float
            ,android.R.drawable.bottom_bar
            ,android.R.drawable.btn_default
            ,android.R.drawable.btn_default_small
            ,android.R.drawable.btn_dialog
            ,android.R.drawable.btn_dropdown
            ,android.R.drawable.btn_minus
            ,android.R.drawable.btn_plus
            ,android.R.drawable.btn_radio
            ,android.R.drawable.btn_star
            ,android.R.drawable.btn_star_big_off
            ,android.R.drawable.btn_star_big_on
            ,android.R.drawable.button_onoff_indicator_off
            ,android.R.drawable.button_onoff_indicator_on
            ,android.R.drawable.checkbox_off_background
            ,android.R.drawable.checkbox_on_background
            ,android.R.drawable.dark_header
            ,android.R.drawable.dialog_frame
            ,android.R.drawable.dialog_holo_dark_frame
            ,android.R.drawable.dialog_holo_light_frame
            ,android.R.drawable.divider_horizontal_bright
    };

    public String[] getListNameArray() {
        return  this.iListName;
    }

    public Integer[] getiListImageIdArray() {
        return this.iListImageId;
    }
}
