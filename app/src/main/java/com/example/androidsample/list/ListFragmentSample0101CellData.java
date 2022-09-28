package com.example.androidsample.list;

/**
 * ListFragmentSample0101で使用するデータクラスです
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ListFragmentSample0101CellData {
    public String imageComment;
    public int imageDrawableId;

    ListFragmentSample0101CellData(String imageComment, int imageDrawableId) {
        this.imageComment = imageComment;
        this.imageDrawableId = imageDrawableId;
    }
}
