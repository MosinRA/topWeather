package com.mosin.topweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DaysAdapterScrollView extends RecyclerView.Adapter<DaysAdapterScrollView.ViewHolder>{

    private String [] dataDays;
    public DaysAdapterScrollView(String[] dataDays){
        this.dataDays = dataDays;
    }

    @NonNull
    @Override
    public DaysAdapterScrollView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_scrol_menu_to_show_city_info, viewGroup, false);
        return new DaysAdapterScrollView.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysAdapterScrollView.ViewHolder viewHolder, int i) {
        viewHolder.getTextView().setText(dataDays[i]);
    }

    @Override
    public int getItemCount() {
        return dataDays.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cityTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityTextView = (TextView) itemView;
        }

        public TextView getTextView() {
            return cityTextView;
        }
    }
}

