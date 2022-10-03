package com.example.androidsample.text;

import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * TextViewSampe0401にて呼び出されるActionModeです。
 * 指定エリアのテキストを反転(選択)したときに該当する操作を実装します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class TextViewSampe0401TextActionMode extends ActionMode.Callback2 {

    private final TextView textView, copiedView;

    /**
     * コンストラクタです。
     *
     * @param textView テキストエリア(指定する側のテキストエリア)
     * @param copiedView 指定したテキストを出力する側のテキストエリア
     *
     */
    public TextViewSampe0401TextActionMode(TextView textView, TextView copiedView) {
        Log.d("debug", "TextViewSampe0401TextActionMode start");
        this.textView = textView;
        this.copiedView = copiedView;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Log.d("debug", "onCreateActionMode start");
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Log.d("debug", "onPrepareActionMode start");
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        Log.d("debug", "onActionItemClicked start");
        switch(menuItem.getItemId()) {
            case android.R.id.copy:
                int min = 0;
                int max = this.textView.getText().length();
                if(this.textView.isFocused()) {
                    final int selStart = this.textView.getSelectionStart();
                    final int selEnd = this.textView.getSelectionEnd();
                    min = Math.max(0, Math.min(selStart, selEnd));
                    max = Math.max(0, Math.max(selStart, selEnd));
                }

                CharSequence selectedText = this.textView.getText().subSequence(min, max);
                this.copiedView.setText(selectedText.toString());

                //ActionModeの終了
                actionMode.finish();
                return true;
            case android.R.id.cut:
            case android.R.id.paste:
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        Log.d("debug", "onDestroyActionMode start");
    }
}
