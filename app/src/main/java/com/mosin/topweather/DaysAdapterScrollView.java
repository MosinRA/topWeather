package com.mosin.topweather;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DaysAdapterScrollView extends RecyclerView.Adapter<DaysAdapterScrollView.ViewHolder> {

    //    private String [] dataDays;
    private SocSource dataSource;

    public DaysAdapterScrollView(SocSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public DaysAdapterScrollView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_scrol_menu_to_show_city_info, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        Log.d("SocnetAdapter", "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DaysAdapterScrollView.ViewHolder viewHolder, int i) {
//        viewHolder.getTextView().setText(dataDays[i]);
        Soc soc = dataSource.getSoc(i);
        viewHolder.setData(soc.getDescription(), soc.getPicture());
        Log.d("SocnetAdapter", "onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.imageView);

        }

        public void setData(String description, int picture) {
            getImage().setImageResource(picture);
            getDescription().setText(description);
        }

        public TextView getDescription() {
            return description;
        }

        public ImageView getImage() {
            return image;
        }

    }
}

