package com.mosin.topweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private int currentPosition = 0;
    private int indexArr = 0;
    TextView showCity, showTempView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOrientation();
        findView();
        String[] data = getResources().getStringArray(R.array.city_names);

        if (currentPosition == 1) {
            SocSource sourceData = new SocSource(getResources());
            initDaysView(sourceData.build());
            initRecyclerView(data);
            initDaysView(sourceData);
        } else initRecyclerView(data);

    }

    public void initOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            currentPosition = 1;
        } else currentPosition = 0;
    }

    public void findView() {
        showCity = findViewById(R.id.cityNameViewFragmentShowCityInfo);
        showTempView = findViewById(R.id.showTempViewFragmentShowCityInfo);
    }

    public void initRecyclerView(final String[] data) {
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerAdapter adapter = new RecyclerAdapter(data);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            String[] tempArr = getResources().getStringArray(R.array.temps);
            String[] cityNamesArr = getResources().getStringArray(R.array.city_names);

            @Override
            public void onItemClick(View view, int position) {
                indexArr = recyclerView.getChildLayoutPosition(view);
                Snackbar.make(view, "Подтвердите действие", Snackbar.LENGTH_LONG)
                        .setAction("сменить город", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (currentPosition == 0) {
                                    Intent intent = new Intent(MainActivity.this, ShowCitySingleModeActivity.class);
                                    intent.putExtra("cityIndex", indexArr);
                                    startActivity(intent);
                                } else {
                                    showCity.setText(cityNamesArr[indexArr]);
                                    showTempView.setText(tempArr[indexArr]);
                                }
                            }
                        }).show();
            }
        });
    }

    public void initDaysView(SocSource SourceData) {
        RecyclerView recyclerViewDays = findViewById(R.id.recycler_day_view);
        recyclerViewDays.setHasFixedSize(true);
        LinearLayoutManager layoutManagerDays = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDays.setLayoutManager(layoutManagerDays);
        DaysAdapterScrollView adapterDays = new DaysAdapterScrollView(SourceData);
        recyclerViewDays.setAdapter(adapterDays);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity", indexArr);
        super.onSaveInstanceState(outState);
    }
}