package tiseer.edu.weatherapp.server_connection;

import static tiseer.edu.weatherapp.utils.Constants.BASE_URL;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitImpl {

    private static Retrofit retrofit;

    private RetrofitImpl() {
    }

    public static synchronized Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                            new OkHttpClient.Builder()
                                    .addInterceptor(new WeatherRequestInterceptor())
                                    .build())
                    .build();
        }
        return retrofit;
    }
}
