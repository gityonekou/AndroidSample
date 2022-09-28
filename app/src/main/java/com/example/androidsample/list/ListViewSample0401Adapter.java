package com.example.androidsample.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidsample.R;

import java.util.List;

/**
 * ListViewSample0401で使用するAdapterです。BaseAdapterを継承して作成します。
 * 基本構造はListViewSample0301Adapterと全く同じですがリスト項目を参照するための引数がListに変更されています。
 * (ListViewSample0401でのリスト表示内容の変数変更のためアダプタ側も変更)
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ListViewSample0401Adapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final int layoutId;
    // リスト表示内容
    private final List<String> nameList;
    private final List<Integer> imageIDList;

    private static class ViewHolder {
        ImageView image;
        TextView name;
        TextView comment;
    }

    public ListViewSample0401Adapter(
            Context context, int layoutId, List<String> nameList, List<Integer> imageIDList) {
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.nameList = nameList;
        this.imageIDList = imageIDList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StringBuilder mes = new StringBuilder("getView() called.[position=");
        mes.append(position);
        mes.append("][");
        mes.append(getCount());
        mes.append("]");

        ViewHolder holder;

        if(convertView == null) {
            mes.append("[convertView=null]");
            convertView =  this.inflater.inflate(this.layoutId, parent, false);
            holder = new ViewHolder();
            holder.image =  convertView.findViewById(R.id.list_image_item);
            holder.name =  convertView.findViewById(R.id.list_text_name);
            holder.comment =  convertView.findViewById(R.id.list_text_comment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Log.d("debug", mes.toString());
        holder.image.setImageResource(this.imageIDList.get(position));
        holder.name.setText(this.nameList.get(position));
        StringBuilder message = new StringBuilder(50);
        message.append("アニメ第？？話にて登場\n");
        message.append("お住まい：？？？？？？\n");
        message.append("みんなからのコメント：コメント募集中です。\n");
        holder.comment.setText(message);
        return convertView;
    }

    @Override
    public int getCount() {
        return this.nameList.size();
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
