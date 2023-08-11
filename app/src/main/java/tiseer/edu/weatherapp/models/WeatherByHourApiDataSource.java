package tiseer.edu.weatherapp.models;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherByHourApiDataSource {

    private static final String url = "https://api.weatherbit.io/v2.0/forecast/hourly?city=dimona,il&key=4c48c026c76c4b5cbc7f1f5380fb12e2&hours=48";

    public static void getWeatherByHour (MutableLiveData<List<ByHour>>callback){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(); //TODO: show message !!
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.err.println("Reached Reponse");
                String jsonString = response.body().string();
                List<ByHour> timestamp_localList = new ArrayList<>();
                try {
                    JSONObject rootObject = new JSONObject(jsonString);
                    JSONArray timestamp_local = rootObject.getJSONArray("timestamp_local");

                    for (int i = 0; i <timestamp_local.length() ; i++) {
                        JSONObject timestamp_localObject = timestamp_local.getJSONObject(i);

                        String description = timestamp_localObject.getString("description");
                        String app_temp = timestamp_localObject.getString("app_temp");

                        timestamp_localList.add(new ByHour(timestamp_local, description, app_temp));
                    }
                    System.err.println("Success!");
                    //
                    callback.postValue(timestamp_localList);
                }catch (JSONException e){
                    System.err.println("Failed to Retrieve Reponse");
                    e.printStackTrace();// TODO: show message !!
                }
            }
        });
    }
}
