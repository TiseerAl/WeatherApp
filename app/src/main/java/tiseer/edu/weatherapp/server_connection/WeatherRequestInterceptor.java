package tiseer.edu.weatherapp.server_connection;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherRequestInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String API_KEY_NAME = "x-rapidapi-key";
        String API_KEY_VALUE = "370f715c6cmsh2aedaf88c0ad5bcp1aafa4jsncf895a26cb42";
        String API_HOST_NAME = "x-rapidapi-host";
        String API_HOST_VALUE = "open-weather13.p.rapidapi.com";

        Request request = chain.request().newBuilder()
                .addHeader(API_KEY_NAME, API_KEY_VALUE)
                .addHeader(API_HOST_NAME, API_HOST_VALUE)
                .build();
        return chain.proceed(request);
    }
}
