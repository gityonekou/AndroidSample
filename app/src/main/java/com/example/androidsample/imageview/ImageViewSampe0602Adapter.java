package com.example.androidsample.imageview;
/*
 * ImageViewSampe0602で使用するAdapterです。BaseAdapterを継承して作成します。
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.androidsample.R;

import java.util.List;

public class ImageViewSampe0602Adapter extends BaseAdapter {

    private static class ViewHolder {
        ImageView imageView;
    }

    private final LayoutInflater inflater;
    private final int layoutId;
    private final List<Integer> imageIdList;

    public ImageViewSampe0602Adapter(Context context, int layoutId, List<Integer> imageIdList) {
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.imageIdList = imageIdList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = this.inflater.inflate(layoutId, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.grid_image_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.imageView.setImageResource(this.imageIdList.get(position));
        return convertView;
    }

    @Override
    public int getCount() {
        return this.imageIdList.size();
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
