package com.example.androidsample.list;
/*
 * ListFragmentの使い方(ListActivity:非推奨の代替)
 * 対象URL:https://akira-watson.com/android/listactivity.html
 * 対象URL:https://android.keicode.com/basics/fragments-listfragment.php
 *
 * サンプルにあるListActivityがAPI30で非推奨となっため代替となるListFragmentでサンプルを作成しています。
 * 基本はフラグメント作成のサンプル＋今回のリストアクティビティの部分です。
 * アダプタ部分はにゃんのサンプルでそのまま利用できます
 *
 */
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsample.R;

public class ListFragmentSample0101 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listfragment_sample0101);
    }
}
