package com.example.androidsample.list;
/*
 * GridViewSample0101で使用するAdapterです。BaseAdapterを継承して作成します。
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidsample.R;

import java.util.List;

public class GridViewSample0101Adapter extends BaseAdapter {

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    private final LayoutInflater inflater;
    private final int gridItemLayoutId;
    private final String[] names;
    private final List<Integer> imageIdList;

    public GridViewSample0101Adapter(
            Context context, int gridItemLayoutId, String[] names, List<Integer> imageIdList) {
        this.inflater = LayoutInflater.from(context);
        this.gridItemLayoutId = gridItemLayoutId;
        this.names = names;
        this.imageIdList = imageIdList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = this.inflater.inflate(this.gridItemLayoutId, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.grid_image_item);
            holder.textView = convertView.findViewById(R.id.grid_text_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.imageView.setImageResource(this.imageIdList.get(position));
        holder.textView.setText(this.names[position]);

        return convertView;
    }

    @Override
    public int getCount() {
        return names.length;
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
