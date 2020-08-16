package com.mosin.topweather;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ShowCitySingleModeActivity extends AppCompatActivity {
    private TextView cityText, showTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_show_city_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        if (savedInstanceState == null) {
            String [] cityNames = getResources().getStringArray(R.array.city_names);
            String [] showTempbyCity = getResources().getStringArray(R.array.temps);
            int index = (int) getIntent().getSerializableExtra("cityIndex");
            cityText.setText(cityNames[index]);
            showTemp.setText(showTempbyCity[index]);;
        }
    }
    public void findViews(){
        cityText = findViewById(R.id.cityNameViewFragmentShowCityInfo);
        showTemp = findViewById(R.id.showTempViewFragmentShowCityInfo);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}

