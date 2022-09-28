package com.example.androidsample.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidsample.R;
import com.squareup.picasso.Picasso;

/**
 * GridViewSample0201で使用するAdapterです。BaseAdapterを継承して作成します。
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class GridViewSample0201Adapter extends BaseAdapter {
    private final Context context;
    private final LayoutInflater inflater;
    private final int layoutId;
    private final int width;
    private final int height;
    private final String[] nameArrays;
    private final String[] urlArrays;

    private static class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
    public GridViewSample0201Adapter(Context context, int layoutId, int width, int height,
            String[] nameArrays, String[] urlArrays) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.width = width;
        this.height = height;
        this.nameArrays = nameArrays;
        this.urlArrays = urlArrays;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = this.inflater.inflate(this.layoutId, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.grid_text_view);
            holder.imageView = convertView.findViewById(R.id.grid_image_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView.setText(this.nameArrays[position]);
        Picasso.with(this.context)
                .load(this.urlArrays[position])
                .resize(this.width, this.height)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_delete)
                .into(holder.imageView);
        return convertView;
    }

    @Override
    public int getCount() {
        return this.nameArrays.length;
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
