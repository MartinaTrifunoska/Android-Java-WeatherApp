package com.example.weather;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.example.weather.api.ApiClient;
import com.example.weather.api.WeatherApi;
import com.example.weather.models.City;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView country, description, latitude, longitude, humidity, sunrise, sunset, pressure, wind, temp;
    private EditText editName;
    private Button search;
    private ImageView image;
    private ConstraintLayout firstConstraint, secondConstraint;
    private WeatherApi weatherApi;
    private String url = "http://openweathermap.org/img/wn/";
    public static String app_id = "9c9541255ef36f3a16f72440ab12bd0d";
    private String metric = "metric";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                if(!name.isEmpty()){
                    searchWeather(name);
                }
                else{
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        temp = findViewById(R.id.temp);
        country = findViewById(R.id.country);
        description = findViewById(R.id.description);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        humidity = findViewById(R.id.humidity);
        sunrise = findViewById(R.id.sunrise);
        sunset = findViewById(R.id.sunset);
        pressure = findViewById(R.id.pressure);
        wind = findViewById(R.id.wind);
        editName = findViewById(R.id.editName);
        search = findViewById(R.id.search);
        image = findViewById(R.id.imageView);
        firstConstraint = findViewById(R.id.firstConstraint);
        secondConstraint = findViewById(R.id.secondConstraint);
    }


    private void searchWeather(String name){
        weatherApi = ApiClient.getWeatherApiInstance();
        if(weatherApi != null){
            weatherApi.getWeatherData(name,app_id,metric).enqueue(new Callback<City>() {
                @Override
                public void onResponse(Call<City> call, Response<City> response) {
                    if(response.isSuccessful()){
                        displayData(response.body());
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        editName.setText("");
                    }
                }

                @Override
                public void onFailure(Call<City> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void displayData(City city) {
        firstConstraint.setVisibility(View.VISIBLE);
        secondConstraint.setVisibility(View.VISIBLE);
        editName.setText("");
        country.setText("Country: " + city.getSys().getCountry());
        description.setText(city.getWeather().get(0).getDescription());
        latitude.setText(String.valueOf(city.getCoord().getLat()));
        longitude.setText(String.valueOf(city.getCoord().getLon()));
        humidity.setText(String.valueOf(city.getMain().getHumidity()) + " %");
        pressure.setText(String.valueOf(city.getMain().getPressure()) + " hPa");
        temp.setText(String.valueOf(city.getMain().getTemp()) + " °C");
        sunset.setText(city.getSys().getSunset() + " UTC");
        sunrise.setText(city.getSys().getSunrise()+ " UTC");
        wind.setText(String.valueOf(city.getWind().getSpeed()) + " m/s");
        Glide.with(this).load(url + city.getWeather().get(0).getIcon() + "@2x.png" ).into(image);
    }
}