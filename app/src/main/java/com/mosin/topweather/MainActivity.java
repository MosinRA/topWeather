package com.mosin.topweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.mosin.topweather.model.WeatherRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    private int currentPosition = 0;
    private int indexArr = 0;
    TextView showCity, showTempView, showWindSpeed, showPressure, showHumidity;
    ImageView icoWeather;
    String temperatureValue, pressureText, humidityStr, windSpeedStr, icoWeatherText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOrientation();
        findView();
        String[] data = getResources().getStringArray(R.array.city_names);
        initRecyclerView(data);
    }

    public void initOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            currentPosition = 1;
        } else currentPosition = 0;
    }

    public void findView() {
        showCity = findViewById(R.id.cityNameViewFragmentShowCityInfo);
        showTempView = findViewById(R.id.showTempViewFragmentShowCityInfo);
        showWindSpeed = findViewById(R.id.windSpeedView);
        showPressure = findViewById(R.id.pressureView);
        showHumidity = findViewById(R.id.humidityView);
        icoWeather = findViewById(R.id.weatherIcoViewFragmentShowCityInfo);
    }

    public void initRecyclerView(final String[] data) {
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerAdapter adapter = new RecyclerAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            String[] cityNamesArr = getResources().getStringArray(R.array.city_names);

            @Override
            public void onItemClick(View view, int position) {
                indexArr = recyclerView.getChildLayoutPosition(view);
                intitWeatherJsonParam();
                if (currentPosition == 1) {
                    showCity.setText(cityNamesArr[indexArr]);
                }
            }

            private void intitWeatherJsonParam() {
                try {
                    final URL uri = new URL(WEATHER_URL + cityNamesArr[indexArr] + "&units=metric&appid="+ "762ee61f52313fbd10a4eb54ae4d4de2");
                    final Handler handler = new Handler(Looper.myLooper()); // Запоминаем основной поток
                    new Thread(new Runnable() {
                        public void run() {
                            HttpsURLConnection urlConnection = null;
                            try {
                                urlConnection = (HttpsURLConnection) uri.openConnection();
                                urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                                urlConnection.setReadTimeout(10000); // установка таймаута - 10 000 миллисекунд
                                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                                String result = getLines(in);
                                // преобразование данных запроса в модель
                                Gson gson = new Gson();
                                final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                                // Возвращаемся к основному потоку
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        displayWeather(weatherRequest);
                                    }
                                });
                            } catch (Exception e) {
                                Log.e("D", "Fail connection", e);
                                e.printStackTrace();
                            } finally {
                                if (null != urlConnection) {
                                    urlConnection.disconnect();
                                }
                            }
                        }
                    }).start();
                } catch (MalformedURLException e) {
                    Log.e("D", "Fail URI", e);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity", indexArr);
        super.onSaveInstanceState(outState);
    }

    private String getLines(BufferedReader reader) {
        StringBuilder rawData = new StringBuilder(1024);
        String tempVariable;

        while (true) {
            try {
                tempVariable = reader.readLine();
                if (tempVariable == null) break;
                rawData.append(tempVariable).append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawData.toString();
    }

    @SuppressLint("DefaultLocale")
    private void displayWeather(WeatherRequest weatherRequest) {
        temperatureValue = String.format(Locale.getDefault(), "%.0f", weatherRequest.getMain().getTemp());
        pressureText = String.format(Locale.getDefault(),"%d", weatherRequest.getMain().getPressure());
        humidityStr = String.format(Locale.getDefault(), "%d", weatherRequest.getMain().getHumidity());
        windSpeedStr = String.format(Locale.getDefault(), "%.0f", weatherRequest.getWind().getSpeed());
        String icoView = weatherRequest.getWeather()[0].getIcon();

        if (currentPosition == 0){
            Intent intent = new Intent(MainActivity.this, ShowCitySingleModeActivity.class);
            intent.putExtra("tempNow", temperatureValue);
            intent.putExtra("pressNow", pressureText);
            intent.putExtra("humidityNow", humidityStr);
            intent.putExtra("windSpeedNow", windSpeedStr);
            intent.putExtra("cityIndex", indexArr);
            intent.putExtra("icoView", icoView);
            startActivity(intent);
        }
        else {
        showTempView.setText(temperatureValue);
        showWindSpeed.setText(windSpeedStr);
        showPressure.setText(pressureText);
        showHumidity.setText(humidityStr);
        setIcoViewImage(weatherRequest);
        }
    }

    private void setIcoViewImage(WeatherRequest weatherRequest){
        String icoView = weatherRequest.getWeather()[0].getIcon();
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