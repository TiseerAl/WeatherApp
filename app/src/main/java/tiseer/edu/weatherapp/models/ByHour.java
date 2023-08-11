package tiseer.edu.weatherapp.models;

import org.json.JSONArray;

public class ByHour {

    private String timestamp_Local;
    private String description;
    private String app_temp;

   //constructor:
    public ByHour(String timestamp_Local, String description, String app_temp) {
        this.timestamp_Local = timestamp_Local;
        this.description = description;
        this.app_temp = app_temp;
    }

    public ByHour(JSONArray timestamp_Local, String description, String app_temp){
    }

    //getters & setters:
    public String getTime() {
        return timestamp_Local;
    }
    public void setTime(String time) {
        this.timestamp_Local = timestamp_Local;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getApp_temp() {
        return app_temp;
    }
    public void setApp_temp(String app_temp) {
        this.app_temp = app_temp;
    }

    //to string:

    @Override
    public String toString() {
        return "ByHour{" +
                "timestamp_Local='" + timestamp_Local + '\'' +
                ", description='" + description + '\'' +
                ", app_temp='" + app_temp + '\'' +
                '}';
    }
}
