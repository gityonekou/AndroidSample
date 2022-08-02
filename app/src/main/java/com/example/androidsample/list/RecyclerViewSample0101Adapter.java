package com.example.androidsample.list;
/*
 * RecyclerViewSample0101で使用するアダプタです
 *
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsample.R;

public class RecyclerViewSample0101Adapter
        extends RecyclerView.Adapter<RecyclerViewSample0101Adapter.ViewHolder> {
    private final String[] dataSet;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.listitem_textview);
        }
        public TextView getTextView() {
            return textView;
        }
    }
    public RecyclerViewSample0101Adapter(String[] dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recyclerview_sampe0101_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(dataSet[position]);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
