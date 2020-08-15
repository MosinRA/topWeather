package com.mosin.topweather;

import android.os.Bundle;
import android.os.Parcel;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.zip.Inflater;

public class CityFragment extends Fragment {

    private ListView cityNameListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choice_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initViews(view);
//        initList();
    }

//    public void initViews(View view){
//        cityNameListView = view.findViewById(R.id.showCityTextView);
//    }
//
//    public void initList(){
//        LinearLayout linearLayout = (LinearLayout)v;
//        String[] cities = getResources().getStringArray(R.array.city_names);
//        LayoutInflater inflater getLayoutInflater();
//
//        for (int i = 0; i < cities.length; i++) {
//            String city = cities[i];
//            View item = Inflater.inflate(R.layout.item_layout, linearLayout, false);
//            TextView tv = item.findViewById(R.id.itemCityTextView);
//            tv.setText(city);
//            layoutView.addView(item);
//            final int fi = i;
//            tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // опираемся на Parcel, а не на текущую позицию
//                    currentParcel = new Parcel(fi, getResources().getStringArray(R.array.city_names)[fi]);
//                    showCoatOfArms(currentParcel);
//                }
//            });
//        }
    }
