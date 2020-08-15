package com.mosin.topweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private int currentPosition = 0;
    private boolean isLand;
    TextView showCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOrientation();
        findView();
        String[] data = getResources().getStringArray(R.array.city_names);
        String[] dataDays = getResources().getStringArray(R.array.days_name);
        if(currentPosition == 1){
            initRecyclerView(data);
            initDaysView(dataDays);
        }
        else initRecyclerView(data);
    }

    public void initOrientation(){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            currentPosition = 1;
        } else currentPosition = 0;
    }

    public void findView(){
        showCity = findViewById(R.id.cityNameViewFragmentShowCityInfo);
    }

    public void initRecyclerView (final String[] data){
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerAdapter adapter = new RecyclerAdapter(data);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (currentPosition == 0) {
                    Intent intent = new Intent(MainActivity.this, ShowCitySinglModeActivity.class);
                    intent.putExtra("cityName", ((TextView) view).getText());
                    startActivity(intent);
                }else {
                    showCity.setText(((TextView) view).getText());
                   }
            }
        });
    }

    public void initDaysView (String[] dataDays){
        RecyclerView recyclerViewDays = findViewById(R.id.recycler_day_view);
        recyclerViewDays.setHasFixedSize(true);
        LinearLayoutManager layoutManagerDays = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDays.setLayoutManager(layoutManagerDays);
        DaysAdapterScrollView adapterDays = new DaysAdapterScrollView(dataDays);
        recyclerViewDays.setAdapter(adapterDays);
    }

    private Container getCoatContainer() {
        String[] cities = getResources().getStringArray(R.array.city_names);
        Container container = new Container();
        container.position = currentPosition;
        container.cityName = cities[currentPosition];
        return container;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity", currentPosition);
        super.onSaveInstanceState(outState);
    }
}