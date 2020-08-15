package com.mosin.topweather;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ShowCitySinglModeActivity extends AppCompatActivity {
    private TextView cityText;

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
            Intent details = getIntent();
            String cityName = details.getStringExtra("cityName");
            cityText.setText(cityName);
        }
    }
    public void findViews(){
        cityText = findViewById(R.id.cityNameViewFragmentShowCityInfo);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
