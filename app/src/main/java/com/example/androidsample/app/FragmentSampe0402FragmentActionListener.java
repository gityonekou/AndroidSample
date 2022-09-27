package com.example.androidsample.app;

/**
 * 複数のFragment間の画面遷移(貼り付け部分の通知版)：FragmentSampe0402でフラグメントを切り替えるためのインターフェースです。
 * 通知受け取り側(Activity)でこのインターフェースを実装してフラグメントの切り替えを実行します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public interface FragmentSampe0402FragmentActionListener {
    int FRAGMENT_SAMPLE0402_FRAGMENT01 = 1;
    int FRAGMENT_SAMPLE0402_FRAGMENT02 = 2;

    /**
     * フラグメントタイプに対応したフラグメントをカウント値をもとに生成し、画面に張り付けを行います。
     * @param fragmentType 生成するフラグメントに対応する値
     * @param count カウント値
     */
    void replaceFragment(int fragmentType, int count);

    /**
     * 画面に対応するフラグメント値をもとにスタックをpopします。
     * pop後の画面がない場合はこのアクティビティを終了します。
     * @param fragmentType　画面に対応するフラグメント値
     */
    void removeFragment(int fragmentType);
}
