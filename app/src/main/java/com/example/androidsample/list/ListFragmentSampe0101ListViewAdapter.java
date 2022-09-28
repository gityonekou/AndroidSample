package com.example.androidsample.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidsample.R;

import java.util.List;

/**
 * ListFragmentSampe0101Fragmentで使用するアダプタです
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class ListFragmentSampe0101ListViewAdapter
            extends ArrayAdapter<ListFragmentSample0101CellData> {
    private static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
    private final LayoutInflater inflater;
    private final int itemLayout;

    public ListFragmentSampe0101ListViewAdapter(Context context, int itemLayout,
                                                List<ListFragmentSample0101CellData> list) {
        super(context, 0, list);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = this.inflater.inflate(this.itemLayout, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.list_textview);
            holder.imageView = convertView.findViewById(R.id.list_imageview);
            convertView.setTag(holder);
        } else {
            holder =  (ViewHolder)convertView.getTag();
        }
        ListFragmentSample0101CellData cellData = getItem(position);
        if(cellData != null) {
            holder.textView.setText(cellData.imageComment);
            holder.imageView.setImageResource(cellData.imageDrawableId);
        }
        return convertView;
    }
}
