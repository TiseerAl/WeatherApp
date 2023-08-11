package tiseer.edu.weatherapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Extension {

    public static String getFormattedDate(Date date){
        String formattedDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        try {
            formattedDate = sdf.format(date);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }

        return formattedDate;
    }
}
