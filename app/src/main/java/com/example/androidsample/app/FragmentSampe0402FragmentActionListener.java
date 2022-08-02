package com.example.androidsample.app;

public interface FragmentSampe0402FragmentActionListener {
    public static final int FRAGMENT_SAMPLE0402_FRAGMENT01 = 1;
    public static final int FRAGMENT_SAMPLE0402_FRAGMENT02 = 2;

    /**
     * フラグメントタイプに対応したフラグメントをカウント値をもとに生成し、画面に張り付けを行います。
     * @param fragmentType 生成するフラグメントに対応する値
     * @param count カウント値
     */
    public void replaceFragment(int fragmentType, int count);

    /**
     * 画面に対応するフラグメント値をもとにスタックをpopします。
     * pop後の画面がない場合はこのアクティビティを終了します。
     * @param fragmentType　画面に対応するフラグメント値
     */
    public void removeFragment(int fragmentType);
}
