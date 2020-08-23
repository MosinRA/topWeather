package com.mosin.topweather;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ShowCitySingleModeActivity extends AppCompatActivity {
    private TextView cityText, showTemp, showWindSpeed, showPressure, showHumidity;
    ImageView icoWeather;

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
            int index = (int) getIntent().getSerializableExtra("cityIndex");
            cityText.setText(cityNames[index]);
            Intent intent = getIntent();
            String temp = intent.getStringExtra("tempNow");
            String windSpeed = intent.getStringExtra("windSpeedNow");
            String pressure = intent.getStringExtra("pressNow");
            String humidity = intent.getStringExtra("humidityNow");
            String icoView = intent.getStringExtra("icoView");
            showTemp.setText(temp);
            showWindSpeed.setText(windSpeed);
            showPressure.setText(pressure);
            showHumidity.setText(humidity);
            if (icoView.equals("01d")){
                icoWeather.setImageResource(R.drawable.clear_sky_d);
            }
            else if (icoView.equals("01n")){
                icoWeather.setImageResource(R.drawable.clear_sky_n);
            }
            else if (icoView.equals("02d") || icoView.equals("03d") || icoView.equals("04d")){
                icoWeather.setImageResource(R.drawable.few_clouds_d);
            }
            else if (icoView.equals("02n") || icoView.equals("03n") || icoView.equals("04n")){
                icoWeather.setImageResource(R.drawable.few_clouds_n);
            }
            else if (icoView.equals("09d") || icoView.equals("10d")){
                icoWeather.setImageResource(R.drawable.rain_d);
            }
            else if (icoView.equals("09n") || icoView.equals("10n")){
                icoWeather.setImageResource(R.drawable.rain_n);
            }
            else if (icoView.equals("13n") || icoView.equals("13d")){
                icoWeather.setImageResource(R.drawable.snow);
            }
            else if (icoView.equals("50n") || icoView.equals("50d")){
                icoWeather.setImageResource(R.drawable.mist);
            }
        }
    }

    public void findViews(){
        cityText = findViewById(R.id.cityNameViewFragmentShowCityInfo);
        showTemp = findViewById(R.id.showTempViewFragmentShowCityInfo);
        showWindSpeed = findViewById(R.id.windSpeedView);
        showPressure = findViewById(R.id.pressureView);
        showHumidity = findViewById(R.id.humidityView);
        icoWeather = findViewById(R.id.weatherIcoViewFragmentShowCityInfo);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}

