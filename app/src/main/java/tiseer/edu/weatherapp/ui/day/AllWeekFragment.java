package tiseer.edu.weatherapp.ui.day;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tiseer.edu.weatherapp.R;
import tiseer.edu.weatherapp.models.hourly_models.ForecastItem;
import tiseer.edu.weatherapp.ui.adapters.WeatherAdapter;
import tiseer.edu.weatherapp.ui.weather.WeatherViewModel;

public class AllWeekFragment extends Fragment {


    private WeatherViewModel weatherViewModel;
    private WeatherAdapter weatherAdapter;
    private TextView emptyStateTV, titleTV;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_allweek, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        initViews(view);
        setObservers();
    }

    private void initViews(View view) {
        emptyStateTV = view.findViewById(R.id.empty_state_text_view);
        titleTV = view.findViewById(R.id.allDayTitle);
        RecyclerView rvAllDay = view.findViewById(R.id.rvAllDay);
        weatherAdapter = new WeatherAdapter();
        rvAllDay.setAdapter(weatherAdapter);
        rvAllDay.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    private void setObservers() {
        weatherViewModel.forecastResponseMediatorLiveData().observe(requireActivity(), forecastResponse ->{
            if (forecastResponse != null) {
                List<ForecastItem> dataItemList = forecastResponse.getHourlyList();
                if (dataItemList != null) {
                    emptyStateTV.setVisibility(dataItemList.isEmpty() ? View.VISIBLE : View.GONE);
                    if (!dataItemList.isEmpty()) {
                        titleTV.setText(forecastResponse.getCity().getName());
                        weatherAdapter.updateItems(dataItemList);
                    }
                }
            }
        });
    }
}