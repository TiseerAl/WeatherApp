package tiseer.edu.weatherapp.ui.adapters;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tiseer.edu.weatherapp.R;
import tiseer.edu.weatherapp.models.hourly_models.ForecastItem;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<ForecastItem> forecastItems = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.all_day_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ForecastItem forecastItem = forecastItems.get(position);
        String date = forecastItem.getExactTime().split(" ", 2)[0];
        String time = forecastItem.getExactTime().split(" ", 2)[1];
        holder.tvHour.setText(date + "\n" + time);
        String description = forecastItem.getWeatherInForecast().get(0).getDescription();
        int drawableRes;
        switch (description) {
            case "few clouds":
                drawableRes = R.drawable.few_clouds;
                break;
            case "broken clouds":
                drawableRes = R.drawable.broken_clouds;
                break;
            case "scattered clouds":
                drawableRes = R.drawable.scattered_clouds;
                break;
            case "overcast clouds":
                drawableRes = R.drawable.overcast_clouds;
                break;
            case "light rain":
                drawableRes = R.drawable.light_rain;
                break;
            default:
                drawableRes = R.drawable.clear_sky;
                break;
        }
        holder.cardIV.setBackgroundResource(drawableRes);
        //holder.cardIV.setColorFilter(Color.argb(255, 0 ,0, 0));
        double temp = forecastItem.getMain().getTemp();
        // Converting Kelvin to celsius
        temp -= 273.15;
        holder.tvMinTemp.setText(holder.itemView.getContext().getString(R.string.weather_temp, temp));
        holder.tvHourDescription.setText(description);
    }

    @Override
    public int getItemCount() {
        return forecastItems.size();
    }

    public void updateItems(List<ForecastItem> newItems) {
        forecastItems.clear();
        forecastItems.addAll(newItems);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHour;
        TextView tvHourDescription;
        TextView tvMinTemp;
        ConstraintLayout cardIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHour = itemView.findViewById(R.id.tvHour);
            tvHourDescription = itemView.findViewById(R.id.tvHourDescription);
            tvMinTemp = itemView.findViewById(R.id.tvHourTemp);
            cardIV = itemView.findViewById(R.id.cardImage);
        }

    }
}
