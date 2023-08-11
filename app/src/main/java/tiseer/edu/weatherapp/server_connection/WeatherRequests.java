package tiseer.edu.weatherapp.server_connection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import tiseer.edu.weatherapp.models.hourly_models.ForecastResponse;
import tiseer.edu.weatherapp.models.weather_models.WeatherResponse;

public interface WeatherRequests {

    @GET("city/{cityName}")
    Call<WeatherResponse> getWeatherByQuery(@Path("cityName") String cityName);

    @GET("city/fivedaysforcast/{lat}/{lon}")
    Call<ForecastResponse> getHourlyForecast(@Path("lat") double latitude,
                                             @Path("lon") double longitude);
}
