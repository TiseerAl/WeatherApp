package tiseer.edu.weatherapp.models;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tiseer.edu.weatherapp.models.hourly_models.ForecastResponse;
import tiseer.edu.weatherapp.models.weather_models.WeatherResponse;
import tiseer.edu.weatherapp.server_connection.RetrofitImpl;
import tiseer.edu.weatherapp.server_connection.WeatherRequests;


public class WeatherDataSource {


    private static WeatherRequests weatherRequests;

    public static LiveData<WeatherResponse> getWeather(String city) {
        weatherRequests = RetrofitImpl.getInstance().create(WeatherRequests.class);
        MutableLiveData<WeatherResponse> weathersMutableLiveData = new MutableLiveData<>();

        weatherRequests.getWeatherByQuery(city.isEmpty() ? "london" : city).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NotNull Call<WeatherResponse> call, @NotNull Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    weathersMutableLiveData.postValue(weatherResponse);
                }
            }

            @Override
            public void onFailure(@NotNull Call<WeatherResponse> call, @NotNull Throwable t) {
                Log.d("getWeatherByQuery error", t.getMessage());
            }
        });


        return weathersMutableLiveData;
    }

    public static LiveData<ForecastResponse> getHourlyForecast(double lat, double lng) {
        weatherRequests = RetrofitImpl.getInstance().create(WeatherRequests.class);
        MutableLiveData<ForecastResponse> forecastMutableLiveData = new MutableLiveData<>();

        weatherRequests.getHourlyForecast(lat, lng).enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(@NotNull Call<ForecastResponse> call, @NotNull Response<ForecastResponse> response) {
                if (response.isSuccessful()) {
                    ForecastResponse forecastResponse = response.body();
                    forecastMutableLiveData.postValue(forecastResponse);
                }else{
                    Log.d("getHourlyForecast error", response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<ForecastResponse> call, @NotNull Throwable t) {
                Log.d("getHourlyForecast error", t.getMessage());
            }
        });


        return forecastMutableLiveData;
    }

}


