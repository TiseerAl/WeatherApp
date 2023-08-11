package tiseer.edu.weatherapp.ui.day;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import tiseer.edu.weatherapp.models.ByHour;
import tiseer.edu.weatherapp.models.WeatherByHourApiDataSource;

public class AllDayViewModel extends ViewModel {


    private MutableLiveData<List<ByHour>> weatherLiveData = new MutableLiveData<> ();

    public AllDayViewModel() {
        WeatherByHourApiDataSource.getWeatherByHour(weatherLiveData);
    }

    public LiveData<List<ByHour>> getWeatherByHour() { return weatherLiveData; }
}