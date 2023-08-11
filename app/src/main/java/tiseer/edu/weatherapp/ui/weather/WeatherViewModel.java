package tiseer.edu.weatherapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import tiseer.edu.weatherapp.models.hourly_models.ForecastResponse;
import tiseer.edu.weatherapp.models.WeatherDataSource;
import tiseer.edu.weatherapp.models.weather_models.Coord;
import tiseer.edu.weatherapp.models.weather_models.WeatherResponse;

public class WeatherViewModel extends ViewModel {

    public LiveData<WeatherResponse> weatherLiveData;
    public MutableLiveData<String> cityInput = new MutableLiveData<>();
    public MutableLiveData<String> langInput = new MutableLiveData<>();

    public WeatherViewModel() {
        weatherLiveData = WeatherDataSource.getWeather("");
    }

    public LiveData<ForecastResponse> getHourlyForecast(Coord cityCoords){
        LiveData<ForecastResponse> hourlyForecast = WeatherDataSource.getHourlyForecast(cityCoords.getLat(), cityCoords.getLon());

        return hourlyForecast;
    }

    public MediatorLiveData<ForecastResponse> forecastResponseMediatorLiveData(){

        MediatorLiveData<ForecastResponse> forecastResponseMediatorLiveData = new MediatorLiveData<>();
        forecastResponseMediatorLiveData.addSource(weatherLiveData, s -> {
            if (!s.getName().isEmpty()) {
                getHourlyForecast(s.getCoord()).observeForever(new Observer<ForecastResponse>() {
                    @Override
                    public void onChanged(ForecastResponse forecastResponse) {
                        forecastResponseMediatorLiveData.postValue(forecastResponse);
                    }
                });

                //updateWeather(s.getName());
            }
        });
        forecastResponseMediatorLiveData.addSource(cityInput, city -> {
            if (!city.isEmpty()){
                weatherLiveData = WeatherDataSource.getWeather(city);
            }
        });

        return forecastResponseMediatorLiveData;
    }

    private void updateWeather(String city) {
        weatherLiveData = WeatherDataSource.getWeather(city);
    }
}