package com.example.androidsample.list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsample.R;

import java.util.List;

/**
 * RecyclerViewSample0102で使用するアダプタです
 *
 **************************************
 * 変更履歴:
 * ver1.00 新規作成
 * ver2.00 Javadoc追加対応
 *
 */
public class RecyclerViewSample0102Adapter
        extends RecyclerView.Adapter<RecyclerViewSample0102Adapter.ViewHolder> {

    // リスト表示内容
    private final List<String> nameList;
    private final List<Integer> imageIDList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView comment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.list_image_item);
            name = itemView.findViewById(R.id.list_text_name);
            comment = itemView.findViewById(R.id.list_text_comment);
        }
    }
    public RecyclerViewSample0102Adapter(List<String> nameList, List<Integer> imageIDList) {
        this.nameList = nameList;
        this.imageIDList = imageIDList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("debug", "onCreateViewHolder() called. ");
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_sampe0102_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("debug", "onBindViewHolder() called. [position=" + position + "]");
        holder.image.setImageResource(this.imageIDList.get(position));
        holder.name.setText(this.nameList.get(position));
        StringBuilder message = new StringBuilder(50);
        message.append("アニメ第？？話にて登場\n");
        message.append("お住まい：？？？？？？\n");
        message.append("コメント募集中です。\n");
        holder.comment.setText(message);
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}
