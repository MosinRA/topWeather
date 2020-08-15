package com.mosin.topweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        private String [] data;
        private OnItemClickListener itemClickListener;
        public RecyclerAdapter(String[] data){
            this.data = data;
        }

        @NonNull
        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_layout, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder viewHolder, int i) {
            viewHolder.getTextView().setText(data[i]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView cityTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                cityTextView = (TextView) itemView;
                cityTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemClickListener != null) {
                            itemClickListener.onItemClick(v, getAdapterPosition());
                        }
                    }
                });
            }

            public TextView getTextView() {
                return cityTextView;
            }
        }
    }

