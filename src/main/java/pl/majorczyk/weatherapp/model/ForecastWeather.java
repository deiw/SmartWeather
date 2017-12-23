package pl.majorczyk.weatherapp.model;

public class ForecastWeather extends Weather {

    private String day;
    private String time;

    public ForecastWeather() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
