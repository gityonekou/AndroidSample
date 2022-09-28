/*
 * ListViewSample0301で使用するAdapterです。BaseAdapterを継承して作成します。
 *
 * [BaseAdapter]
 * カスタムadapterを作成する手順としては：
 * 1.BaseAdapterを継承したクラスを作成
 * 2.getView()、getCount()、getItem()、getItemId()メソッドの４つをオーバーライド
 * 3.リスト項目のレイアウトをカスタマイズ
 * になります。
 *
 * 基本的なBaseAdapterの構造は
 * private class TestAdapter extends BaseAdapter {
 *  private LayoutInflater inflater;
 *  private static class ViewHolder {
 *      ImageView image;
 *  }
 *  public TestAdapter(Context context) {
 *      inflater = LayoutInflater.from(context);
 *  }
 * @Override
 * public int getCount() { return リストの項目数 }
 * @Override
 * public Object getItem(int position) {return position;}
 * @Override
 * public long getItemId(int position) {return position;}
 * @Override
 * public View getView(int position, View convertView, ViewGroup parent) {
 *      // レイアウトを作成
 *      ViewHolder holder;
 *      if (convertView == null) {
 *          convertView = inflaterを使い新規にレイアウトを作成
 *          holder = new ViewHolder();
 *          holder.image = convertView.findViewById(R.id.イメージViewId);
 *          convertView.setTag(holder);
 *　     }else{
 *          holder = (ViewHolder)convertView.getTag();
 *      }
 *      return convertView;
 *  }
 *　}
 *
 * 各種メソッドの説明
 * ・getCount()：配列やListの要素数を返す
 * ・getItem()：indexやオブジェクトを返す
 * ・getItemId()：特別なIDをindexの他に返す
 * ・getView()：setImageBitmap() など、ここで描画させている
 *
 * 【convertViewの再利用】
 * リスト数が10個程度だとあまり気にならないのですが、画像を貼り付けてリスト数が数百になると、あるいはリストを増やせる
 * 仕様のケースでは、スクロールが重くなり最終的には Out Of Memory で終了…となる可能性があります。
 * これを防ぐためには上記getViewメソッドでconvertViewを再利用するように実装する必要があります。
 * テキストのリストであれば問題ないかもしれませんが、画像を扱うListViewではこれは必須です。
 *
 *
 */
package com.example.androidsample.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidsample.R;

/**
 * ListViewSample0301で使用するAdapterです。BaseAdapterを継承して作成します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ListViewSample0301Adapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final int layoutId;
    // リスト表示内容
    private final String[] names;
    private final int[] imageIDs;

    private static class ViewHolder {
        ImageView image;
        TextView name;
        TextView comment;
    }

    public ListViewSample0301Adapter(
            Context context, int layoutId, String[] names, int[] imageIDs) {
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.names = names;
        this.imageIDs = imageIDs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView =  this.inflater.inflate(this.layoutId, parent, false);
            holder = new ViewHolder();
            holder.image =  convertView.findViewById(R.id.list_image_item);
            holder.name =  convertView.findViewById(R.id.list_text_name);
            holder.comment =  convertView.findViewById(R.id.list_text_comment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.image.setImageResource(this.imageIDs[position]);
        holder.name.setText(this.names[position]);
        StringBuilder message = new StringBuilder(50);
        message.append("アニメ第？？話にて登場\n");
        message.append("お住まい：？？？？？？\n");
        message.append("みんなからのコメント：コメント募集中です。\n");
        holder.comment.setText(message);
        return convertView;
    }

    @Override
    public int getCount() {
        return imageIDs.length;
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
