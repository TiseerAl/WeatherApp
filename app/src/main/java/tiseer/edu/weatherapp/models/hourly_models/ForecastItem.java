package tiseer.edu.weatherapp.models.hourly_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastItem {
    MainInForecast main;
    @SerializedName("weather")
    List<WeatherInForecast> weatherInForecast;
    Rain rain;
    @SerializedName("dt_txt")
    String exactTime;

    public MainInForecast getMain() {
        return main;
    }

    public void setMain(MainInForecast main) {
        this.main = main;
    }

    public List<WeatherInForecast> getWeatherInForecast() {
        return weatherInForecast;
    }

    public void setWeatherInForecast(List<WeatherInForecast> weatherInForecast) {
        this.weatherInForecast = weatherInForecast;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public String getExactTime() {
        return exactTime;
    }

    public void setExactTime(String exactTime) {
        this.exactTime = exactTime;
    }
}
