package com.example.androidsample.select;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidsample.R;

import java.io.InputStream;

/**
 * SpinnerSampe0201で使用するスピナーのアダプターです。
 * assetsから表示画像を読み込みアダプターに設定します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class SpinnerSampeAssetsAdapter extends BaseAdapter {

    // assets path
    private static final String PATH = "rizero_image/";

    // レイアウトからViewを生成するためのクラス
    private final LayoutInflater inflater;
    // Resources
    private final Resources res;
    // レイアウトID
    private final int layoutId;
    // リスト表示内容
    private final String[] names;
    private final String[] filePaths;

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    /**
     * コンストラクタです。
     * @param context 呼び出し元のコンテキスト
     * @param itemLayoutId 表示するスピナーのレイアウト
     * @param spinnerItems スピナーに表示する一覧アイテムの文字列配列
     * @param spinnerImageFilePaths スピナーに表示する一覧画像パスの文字列配列
     */
    SpinnerSampeAssetsAdapter(Context context,
                              int itemLayoutId,
                              String[] spinnerItems,
                              String[] spinnerImageFilePaths) {
        this.res = context.getResources();
        this.inflater = LayoutInflater.from(context);
        this.layoutId = itemLayoutId;
        this.names = spinnerItems;
        // イメージリソースへのファイルパスの配列を生成
        this.filePaths = new String[spinnerImageFilePaths.length];
        for(int i = 0; i < spinnerImageFilePaths.length; i++) {
            this.filePaths[i] = PATH + spinnerImageFilePaths[i];
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

        holder.textView.setText(this.names[position]);
        AssetManager assetManager = this.res.getAssets();
        try (InputStream in = assetManager.open(this.filePaths[position])) {
            Bitmap btmp = BitmapFactory.decodeStream(in);
            holder.imageView.setImageDrawable(new BitmapDrawable(this.res, btmp));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
