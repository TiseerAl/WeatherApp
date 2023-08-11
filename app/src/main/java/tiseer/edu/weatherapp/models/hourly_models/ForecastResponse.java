package tiseer.edu.weatherapp.models.hourly_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastResponse {
    @SerializedName("list")
    List<ForecastItem> hourlyList;
    City city;
    String name;

    public List<ForecastItem> getHourlyList() {
        return hourlyList;
    }

    public City getCity() {
        return city;
    }
}

class Rain{
    double threeHours;
}

class CoordinatesInForecast {
    double lat;
    double lon;
}