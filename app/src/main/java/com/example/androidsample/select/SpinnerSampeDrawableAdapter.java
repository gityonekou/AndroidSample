package com.example.androidsample.select;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidsample.R;

/**
 * SpinnerSampe0201で使用するスピナーのアダプターです。
 * イメージリソース(Drawable)から表示画像を読み込みアダプターに設定します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class SpinnerSampeDrawableAdapter extends BaseAdapter {

    // レイアウトからViewを生成するためのクラス
    private final LayoutInflater inflater;
    // レイアウトID
    private final int layoutId;
    // リスト表示内容
    private final String[] names;
    private final int[] imageIDs;

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    /**
     * コンストラクタです。
     *
     * @param context 呼び出し元のコンテキスト
     * @param itemLayoutId 表示するスピナーのレイアウト
     * @param spinnerItems スピナーに表示する一覧の文字列配列
     * @param spinnerImages スピナーに表示する一覧画像の文字列配列
     */
    SpinnerSampeDrawableAdapter(Context context,
                                int itemLayoutId,
                                String[] spinnerItems,
                                String[] spinnerImages) {
        this.inflater = LayoutInflater.from(context);
        this.layoutId = itemLayoutId;
        this.names = spinnerItems;
        // イメージリソースIDの配列を生成
        this.imageIDs = new int[spinnerImages.length];
        Resources res = context.getResources();
        for(int i = 0; i < spinnerImages.length; i++) {
            this.imageIDs[i] = res.getIdentifier(
                    spinnerImages[i], "drawable", context.getPackageName());
        }

    }

    /* 要素数を返す。つまり、下で呼び出すgetVieweの繰り返し数を返す */
    @Override
    public int getCount() {
        return this.names.length;
    }
    /* プルダウンの一行ごとのViewをもとに表示の設定を行う。 */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = this.inflater.inflate(this.layoutId, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.image_view);
            holder.textView = convertView.findViewById(R.id.text_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.imageView.setImageResource(this.imageIDs[position]);
        holder.textView.setText(this.names[position]);

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
