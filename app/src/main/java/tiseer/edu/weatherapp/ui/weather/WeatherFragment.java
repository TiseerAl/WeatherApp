package tiseer.edu.weatherapp.ui.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;

import okhttp3.internal.Util;
import tiseer.edu.weatherapp.R;
import tiseer.edu.weatherapp.models.WeatherDataSource;
import tiseer.edu.weatherapp.models.weather_models.WeatherItem;
import tiseer.edu.weatherapp.models.weather_models.WeatherResponse;
import tiseer.edu.weatherapp.utils.Constants;
import tiseer.edu.weatherapp.utils.Extension;

import static tiseer.edu.weatherapp.utils.Constants.CURRENT_CITY;
import static tiseer.edu.weatherapp.utils.Constants.MY_PREF;

public class WeatherFragment extends Fragment {

    private TextView tvTemp, tvDescription, tvCity, tvDate;
    private WeatherViewModel weatherViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setObservers();
    }

    private void initViews(@NonNull View view) {

        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        tvTemp = view.findViewById(R.id.tvTemp);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvCity = view.findViewById(R.id.tvCity);
        tvDate = view.findViewById(R.id.tvDate);
    }

    private void setObservers() {
        weatherViewModel.weatherLiveData.observe(getViewLifecycleOwner(), new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                WeatherItem weather = weatherResponse.getWeather().get(0);
                double temp = (5 * (weatherResponse.getMain().getTemp() - 32)) / 9;
                tvTemp.setText(getString(R.string.weather_temp, temp));
                tvDescription.setText(weather.getDescription());
                tvCity.setText(weatherResponse.getName());
            }
        });

        tvDate.setText(Extension.getFormattedDate(new Date()));
    }
}