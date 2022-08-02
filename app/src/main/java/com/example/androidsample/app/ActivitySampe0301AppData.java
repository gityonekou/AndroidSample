package com.example.androidsample.app;

import android.app.Application;

/*
 * ActivitySampe0301共通で使うグローバル変数(アプリケーション内の共有データ)を定義したクラスです。
 *
 */
public class ActivitySampe0301AppData extends Application {
    private String testString = "default";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }
}
