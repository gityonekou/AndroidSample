package com.example.androidsample.list;
/*
 * ListViewSample0501で使用するAdapterです。BaseAdapterを継承して作成します。
 * 基本構造はListViewSample0301Adapterと全く同じです
 */
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidsample.R;

public class ListViewSample0501Adapter extends BaseAdapter {

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

    public ListViewSample0501Adapter(
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

            // pojisionにより背景色を変更
            int sw = position % 5;
            switch (sw) {
                case 0:
                    convertView.setBackgroundColor(Color.rgb(255, 127, 255));
                    break;
                case 1:
                    convertView.setBackgroundColor(Color.rgb(255, 220, 127));
                    break;
                case 2:
                    convertView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            300
                    ));
                    convertView.setBackgroundColor(Color.rgb(127, 127, 255));
                    holder.name.setTextColor(Color.rgb(0, 0, 255));
                    holder.name.setTextSize(30.0f);
                    holder.name.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);

                    LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                            300, 300
                    );
                    imageParams.setMargins(25, 0, 0, 0);
                    holder.image.setLayoutParams(imageParams);
                    break;
                case 3:
                    convertView.setBackgroundColor(Color.rgb(127, 255, 127));
                    break;
                default:
                    convertView.setBackgroundColor(Color.rgb(255, 255, 127));
            }

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
